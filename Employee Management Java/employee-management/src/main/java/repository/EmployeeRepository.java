package com.tyataacademy.java.project.emanagement.repository;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tyataacademy.java.project.emanagement.model.EmployeeBean;


public class EmployeeRepository extends AbstractRepository{
	
	public EmployeeRepository() throws IOException {
		super();
	}

	//authenticate the user
	public String logInUser(EmployeeBean user){

		//read user name and pwd from bean
		String userId = user.getUserid();
		String pwd = user.getPassword();

		//Declare uid and pwd variables
		String userId_db = null;
		String pwd_db = null;

		//try connections to db
		try{
			Statement stm = con.createStatement();
			String sql = "SELECT * FROM employee;";
			ResultSet rs= stm.executeQuery(sql);
			//user while loop to traverse the rs
			while(rs.next()){
				userId_db = rs.getString("userid");
				pwd_db = rs.getString("password");
				if(userId.equals(userId_db) && pwd.equals(pwd_db)){
					user.setFname(rs.getString("fname"));
					user.setLname(rs.getString("lname"));
					user.setRole(rs.getString("role"));
					user.setId(rs.getLong("id"));
					return "SUCCESS";
				}
			}			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return "Invalid user credential!";
	}
	
	//LogOutUser
	public boolean logOutUser(){
		return true;
	}
	
	//Create User
	public void createUser(EmployeeBean user) throws SQLException{
		PreparedStatement pstmt = con.prepareStatement("INSERT INTO employee(fname,lname,email,phone,userid,password,role) VALUES (?,?,?,?,?,?,?)");
		pstmt.setString(1,user.getFname());
		pstmt.setString(2, user.getLname());
		pstmt.setString(3, user.getEmail());
		pstmt.setString(4, user.getPhone());
		pstmt.setString(5, user.getUserid());
		pstmt.setString(6, user.getPassword());
		pstmt.setString(7, user.getRole());
		pstmt.executeUpdate();
	}

	//Read User
	public EmployeeBean getUserById(Long id) throws SQLException{
		EmployeeBean user = new EmployeeBean();
		PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM employee where id=?");
		preparedStatement.setLong(1, id);
		ResultSet rs = preparedStatement.executeQuery();
		
		if(rs.next()){
			user.setId(rs.getLong("id"));
			user.setFname(rs.getString("fname"));
			user.setLname(rs.getString("lname"));
			user.setEmail(rs.getString("email"));
			user.setPhone(rs.getString("phone"));
			user.setUserid(rs.getString("userid"));
			user.setPassword(rs.getString("password"));
			user.setRole(rs.getString("role"));
			user.setDepartmentId(rs.getInt("deptid"));
		}
		return user;
	}
	
	//Update User
	public void updateUser(EmployeeBean user) throws SQLException{
		PreparedStatement upstmt = con.prepareStatement("UPDATE employee SET fname = ?, lname = ?, email = ?, phone = ?, userid = ?, password = ?, role=? WHERE id = ?");
		upstmt.setString(1, user.getFname());
		upstmt.setString(2, user.getLname());
		upstmt.setString(3, user.getEmail());
		upstmt.setString(4, user.getPhone());
		upstmt.setString(5, user.getUserid());
		upstmt.setString(6, user.getPassword());
		upstmt.setString(7, user.getRole());		
		upstmt.setLong(8, user.getId());
		upstmt.executeUpdate();
	}
	
	//delete User
	public void deleteUser(Long userId) throws SQLException{
		PreparedStatement dpstmt = con.prepareStatement("DELETE FROM employee WHERE id = ?");
		dpstmt.setLong(1, userId);
		dpstmt.executeUpdate();
	}
	
	//get List of all users
	public List<EmployeeBean> getAllUsers() throws SQLException{
		Statement stm = con.createStatement();
		List<EmployeeBean> userList = new ArrayList<EmployeeBean>();
		String sql = "Select * from employee;";
		ResultSet rs= stm.executeQuery(sql);
		while(rs.next()){
			EmployeeBean userBean = new EmployeeBean();
			userBean.setId(Long.parseLong(rs.getString("id")));
			userBean.setUserid(rs.getString("userid"));
			userBean.setFname(rs.getString("fname"));
			userBean.setLname(rs.getString("lname"));
			userBean.setEmail(rs.getString("email"));
			userBean.setPassword(rs.getString("password"));
			userBean.setPhone(rs.getString("phone"));
			userBean.setRole(rs.getString("role"));
			userList.add(userBean);
		}
		return userList;
	}
	
}
