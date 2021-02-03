package com.tyataacademy.java.project.emanagement.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tyataacademy.java.project.emanagement.model.Compliance;
import com.tyataacademy.java.project.emanagement.model.Department;
import com.tyataacademy.java.project.emanagement.model.EmployeeBean;
import com.tyataacademy.java.project.emanagement.model.StatusReport;
import com.tyataacademy.java.project.emanagement.repository.ComplianceRepository;
import com.tyataacademy.java.project.emanagement.repository.DepartmentRepository;
import com.tyataacademy.java.project.emanagement.repository.EmployeeRepository;
import com.tyataacademy.java.project.emanagement.repository.StatusReportRepository;

public class EmployeeManagementController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EmployeeRepository employeeRepo = null;
	DepartmentRepository deptRepo = null;
	ComplianceRepository compRepo = null;
	StatusReportRepository stsrtpRepo = null;
    private static String INSERT_OR_EDIT = "/employee.jsp";
    private static String DASHBOARD = "/dashboard.jsp";
    private static String INSERT_OR_EDIT_DEPT = "/department.jsp";
    private static String INSERT_OR_EDIT_COMP = "/compliance.jsp";
    private static String INSERT_OR_EDIT_COMMENT = "/comment.jsp";


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("I am in do Get");
		try {
			requestHelper(request, response);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("I am in do Post");
		try {
			requestHelper(request, response);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	
	public void requestHelper(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		System.out.println(request.getServletPath());
		System.out.println("method: "+request.getMethod());
		String action = request.getServletPath();
		String param = request.getParameter("action");
		String cancelParm = request.getParameter("cancel");
		System.out.println("action parameter: "+ action);
		Enumeration<String> parameterNames = request.getParameterNames();
		
		String forward="";
		
		if(param == null){
			authenticateEmployee(request,response);
		}else if(request.getSession().getAttribute("id")!=null){
			//-------------------------Employee-Start-------------------			
			if(param!=null && param.equals("add")){
				System.out.println("Redirect to Add user Page!");
				forward = INSERT_OR_EDIT;
	            RequestDispatcher dispatcherAddUser = request.getRequestDispatcher(forward);
	            dispatcherAddUser.forward(request, response);
			}
			
			if(param!=null && param.equals("edit")){
				forward = INSERT_OR_EDIT;
				System.out.println("Redirect to Edit user Page!");
				Long userId = Long.parseLong(request.getParameter("id"));
				employeeRepo = new EmployeeRepository();
				EmployeeBean user = employeeRepo.getUserById(userId);
				request.setAttribute("user", user);
	            RequestDispatcher dispatcherUser = request.getRequestDispatcher(forward);
	            dispatcherUser.forward(request, response);
			}
			
			if(param!=null && param.equals("delete")){
				System.out.println("deleting user!");
				forward = DASHBOARD;
				String userIdToDelete = request.getParameter("id");
				if(userIdToDelete != null){
					employeeRepo = new EmployeeRepository();
					employeeRepo.deleteUser(Long.parseLong(userIdToDelete));
				}
				doBeforeReturnToDashBoard(request,response);
	            RequestDispatcher dispatcherAddUser = request.getRequestDispatcher(forward);
	            dispatcherAddUser.forward(request, response);			
			}
			
			if(param!=null && param.equals("save") && cancelParm==null){
				System.out.println("save user!");
				forward = DASHBOARD;
				EmployeeBean user = new EmployeeBean();
				user.setFname(request.getParameter("fname"));
				user.setLname(request.getParameter("lname"));
				user.setPhone(request.getParameter("phone"));
				user.setEmail(request.getParameter("email"));
				user.setRole(request.getParameter("role"));
				user.setUserid(request.getParameter("userid"));
				user.setPassword(request.getParameter("password"));	
				String id= request.getParameter("id");
				if(id == null || id.isEmpty()){
					//save
					saveUser(user);
				}else{
					//update
					user.setId(Long.parseLong(id));
					udpateUser(user);
				}
				doBeforeReturnToDashBoard(request,response);
	            RequestDispatcher dispatcherAddUser = request.getRequestDispatcher(forward);
	            dispatcherAddUser.forward(request, response);
			}
			//-------------------------Employee-End-------------------

			//-------------------------Department-Start-------------------
			if(param!=null && param.equals("add_dept")){
				System.out.println("Redirect to Add Department Page!");
				forward = INSERT_OR_EDIT_DEPT;
	            RequestDispatcher dispatcherAddUser = request.getRequestDispatcher(forward);
	            dispatcherAddUser.forward(request, response);
			}
			
			if(param!=null && param.equals("edit_dept")){
				forward = INSERT_OR_EDIT_DEPT;
				System.out.println("Redirect to Edit Department Page!");
				Integer deptId = Integer.parseInt(request.getParameter("id"));
				deptRepo = new DepartmentRepository();
				Department dept = deptRepo.getDepartmentById(deptId);
				request.setAttribute("dept", dept);
	            RequestDispatcher dispatcherUser = request.getRequestDispatcher(forward);
	            dispatcherUser.forward(request, response);
			}
			
			if(param!=null && param.equals("delete_dept")){
				System.out.println("deleting Department!");
				forward = DASHBOARD;
				String deptIdToDelete = request.getParameter("id");
				if(deptIdToDelete != null){
					deptRepo = new DepartmentRepository();
					deptRepo.deleteDepartment(Integer.parseInt(deptIdToDelete));
				}
				doBeforeReturnToDashBoard(request,response);		            
				RequestDispatcher dispatcherAddUser = request.getRequestDispatcher(forward);
	            dispatcherAddUser.forward(request, response);			
			}
			
			if(param!=null && param.equals("save_dept") && cancelParm==null){
				System.out.println("save Department!");
				forward = DASHBOARD;
				Department dept = new Department();
				dept.setDeptName(request.getParameter("dept_name"));
				String id= request.getParameter("id");
				if(id == null || id.isEmpty()){
					//save
					saveDepartment(dept);
				}else{
					//update
					dept.setId(Integer.parseInt(id));
					udpateDepartment(dept);
				}
				doBeforeReturnToDashBoard(request,response);
	            RequestDispatcher dispatcherAddUser = request.getRequestDispatcher(forward);
	            dispatcherAddUser.forward(request, response);
			}
			//-------------------------Department-End--------------------
			
			//-------------------------Comp-Start-------------------			
			if(param!=null && param.equals("add_comp")){
				System.out.println("Redirect to Add Compliance Page!");
				forward = INSERT_OR_EDIT_COMP;
	            RequestDispatcher dispatcherAddUser = request.getRequestDispatcher(forward);
	            dispatcherAddUser.forward(request, response);
			}
			
			if(param!=null && param.equals("edit_comp")){
				forward = INSERT_OR_EDIT_COMP;
				System.out.println("Redirect to Edit Compliance Page!");
				Integer compId = Integer.parseInt(request.getParameter("id"));
				compRepo = new ComplianceRepository();
				Compliance comp = compRepo.getComplianceById(compId);
				request.setAttribute("comp", comp);
	            RequestDispatcher dispatcherUser = request.getRequestDispatcher(forward);
	            dispatcherUser.forward(request, response);
			}
			
			if(param!=null && param.equals("delete_comp")){
				System.out.println("deleting Compliance!");
				forward = DASHBOARD;
				String compIdToDelete = request.getParameter("id");
				if(compIdToDelete != null){
					compRepo = new ComplianceRepository();
					compRepo.deleteCompliance(Integer.parseInt(compIdToDelete));
				}
				doBeforeReturnToDashBoard(request,response);	            
				RequestDispatcher dispatcherAddUser = request.getRequestDispatcher(forward);
	            dispatcherAddUser.forward(request, response);			
			}
			
			if(param!=null && param.equals("save_comp") && cancelParm==null){
				
				System.out.println("save Compliance!");
				forward = DASHBOARD;
				Compliance comp = new Compliance();
				comp.setRltype(request.getParameter("rltype"));
				comp.setDetails(request.getParameter("details"));
				comp.setCreateDate(new Date());
				comp.setDepartmentId(Integer.parseInt(request.getParameter("deptid")));
				String id= request.getParameter("id");
				if(id == null || id.isEmpty()){
					//save
					saveCompliance(comp);
				}else{
					//update
					comp.setId(Integer.parseInt(id));
					udpateCompliance(comp);
				}
				doBeforeReturnToDashBoard(request,response);
	            RequestDispatcher dispatcherAddUser = request.getRequestDispatcher(forward);
	            dispatcherAddUser.forward(request, response);
			}
			//-------------------------Comp-End-------------------

			//-------------------------Comment-Start-------------------			
			if(param!=null && param.equals("add_comment")){
				System.out.println("Redirect to Add Comment Page!");
				forward = INSERT_OR_EDIT_COMMENT;
	            RequestDispatcher dispatcherAddUser = request.getRequestDispatcher(forward);
	            dispatcherAddUser.forward(request, response);
			}
			
			if(param!=null && param.equals("edit_comment")){
				forward = INSERT_OR_EDIT_COMMENT;
				System.out.println("Redirect to Edit Comment Page!");
				Integer srtrptId = Integer.parseInt(request.getParameter("id"));
				stsrtpRepo = new StatusReportRepository();
				StatusReport stsrpt = stsrtpRepo.getStatusReportById(srtrptId);
				request.setAttribute("stsrpt", stsrpt);
	            RequestDispatcher dispatcherUser = request.getRequestDispatcher(forward);
	            dispatcherUser.forward(request, response);
			}
						
			if(param!=null && param.equals("save_comment") && cancelParm==null){
				
				System.out.println("save Comment!");
				forward = DASHBOARD;
				StatusReport stsrpt = new StatusReport();
				stsrpt.setCompId(Integer.parseInt(request.getParameter("compId")));

				//need to get id for current logged in user from session and call getUserById method
				employeeRepo = new EmployeeRepository();
				String currentUserId = ""+request.getSession().getAttribute("id");
				EmployeeBean loggedInUser = employeeRepo.getUserById(Long.valueOf(currentUserId));
				
				stsrpt.setEmpId(loggedInUser.getId().intValue());
				stsrpt.setDeptId(loggedInUser.getDepartmentId());
				
				stsrpt.setComments(request.getParameter("comments"));
				stsrpt.setCreateDate(new Date());
				String id= request.getParameter("id");
				if(id == null || id.isEmpty()){
					//save
					saveComment(stsrpt);
				}else{
					//update
					stsrpt.setId(Integer.parseInt(id));
					udpateComment(stsrpt);
				}
				doBeforeReturnToDashBoard(request,response);
	            RequestDispatcher dispatcherAddUser = request.getRequestDispatcher(forward);
	            dispatcherAddUser.forward(request, response);
			}
			//-------------------------Comment-End-------------------

			
			if(param!=null && param.equals("logout")){
				HttpSession session=request.getSession();
	            session.invalidate();
				response.sendRedirect("index.jsp");
			}
			
			if(cancelParm!=null && cancelParm.equals("Cancel")){
				forward = DASHBOARD;
				doBeforeReturnToDashBoard(request,response);
//				getEmployeeList(request,response);
//				getDeptList(request, response);
//				getComplianceList(request, response);
//				getCommentList(request, response);
	            RequestDispatcher dispatcherAddUser = request.getRequestDispatcher(forward);
	            dispatcherAddUser.forward(request, response);
			}
		}else{
			response.sendRedirect("index.jsp");	
		}		
	}
	
	public void authenticateEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		EmployeeBean user = new EmployeeBean();
		user.setUserid(request.getParameter("uid"));
		user.setPassword(request.getParameter("pwd"));
		String id = null;
		
		employeeRepo = new EmployeeRepository();
		deptRepo = new DepartmentRepository();
		compRepo = new ComplianceRepository();
		stsrtpRepo = new StatusReportRepository();
		
		String loginFlag = employeeRepo.logInUser(user);
		
		if(loginFlag.equals("SUCCESS")){
			System.out.println("Log In Successful: "+ loginFlag);
			request.setAttribute("user",user);
			
			HttpSession session=request.getSession();  
	        session.setAttribute("id",user.getId());
	        
			getEmployeeList(request,response);
			getDeptList(request, response);
			getComplianceList(request, response);
			getCommentList(request, response);
	        RequestDispatcher dispatcherUsers = request.getRequestDispatcher(DASHBOARD);
            dispatcherUsers.forward(request, response);
		}else{
			response.sendRedirect("index.jsp");
		}
	}
	
	public void getEmployeeList(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		List UserBeanList = employeeRepo.getAllUsers();
		request.setAttribute("userList", UserBeanList);
	}

	public void getDeptList(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		List deptList = deptRepo.getAllDepartment();
		if(deptList!=null)
		request.setAttribute("deptList", deptList);
	}

	public void getComplianceList(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		List compList = compRepo.getAllCompliance();
		request.setAttribute("compList", compList);
	}

	public void getCommentList(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		List stsrptList = stsrtpRepo.getAllStatusReport();
		request.setAttribute("stsrptList", stsrptList);
	}
	
	public void saveUser(EmployeeBean user) throws SQLException, IOException{
		employeeRepo = new EmployeeRepository();
		employeeRepo.createUser(user);
	}

	public void saveDepartment(Department dept) throws SQLException, IOException{
		deptRepo = new DepartmentRepository();
		deptRepo.createDepartment(dept);
	}

	public void saveCompliance(Compliance comp) throws SQLException, IOException{
		compRepo = new ComplianceRepository();
		compRepo.createCompliance(comp);
	}

	public void saveComment(StatusReport stsrpt) throws SQLException, IOException{
		stsrtpRepo = new StatusReportRepository();
		stsrtpRepo.createStatusReport(stsrpt);
	}
	
	public void udpateUser(EmployeeBean user) throws SQLException, IOException{
		employeeRepo = new EmployeeRepository();
		employeeRepo.updateUser(user);
	}
	
	public void udpateDepartment(Department dept) throws SQLException, IOException{
		deptRepo = new DepartmentRepository();
		deptRepo.updateDepartment(dept);
	}

	public void udpateCompliance(Compliance comp) throws SQLException, IOException{
		compRepo = new ComplianceRepository();
		compRepo.updateCompliance(comp);
	}

	public void udpateComment(StatusReport stsrpt) throws SQLException, IOException{
		stsrtpRepo = new StatusReportRepository();
		stsrtpRepo.updateStatusReport(stsrpt);
	}
	
	public void setLoggedInUser(HttpServletRequest request, HttpServletResponse response) throws IOException, NumberFormatException, SQLException{
		employeeRepo = new EmployeeRepository();
		String idInString = ""+request.getSession().getAttribute("id");
		EmployeeBean loggedInUser = employeeRepo.getUserById(Long.valueOf(idInString));
		request.setAttribute("user",loggedInUser);
	}
	
	public void doBeforeReturnToDashBoard(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, IOException, SQLException, ServletException{
		setLoggedInUser(request,response);
		getEmployeeList(request,response);
		getDeptList(request, response);
		getComplianceList(request, response);
		getCommentList(request, response);
	}


}
