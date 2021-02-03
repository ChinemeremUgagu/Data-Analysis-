package com.tyataacademy.java.project.emanagement.repository;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tyataacademy.java.project.emanagement.model.Department;

public class DepartmentRepository extends AbstractRepository{
	
	public DepartmentRepository() throws IOException {
		super();
	}

	//Create Department
	public void createDepartment(Department dept) throws SQLException{
		PreparedStatement pstmt = con.prepareStatement("INSERT INTO department(name) VALUES (?)");
		pstmt.setString(1,dept.getDeptName());
		pstmt.executeUpdate();
	}

	//Get Department
	public Department getDepartmentById(Integer id) throws SQLException{
		Department dept = new Department();
		PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM department where id=?");
		preparedStatement.setInt(1, id);
		ResultSet rs = preparedStatement.executeQuery();
		
		if(rs.next()){
			dept.setId(rs.getInt("id"));
			dept.setDeptName(rs.getString("name"));
		}
		return dept;
	}
	
	//Update Department
	public void updateDepartment(Department dept) throws SQLException{
		PreparedStatement upstmt = con.prepareStatement("UPDATE department SET name = ? WHERE id = ?");
		upstmt.setString(1, dept.getDeptName());
		upstmt.setInt(2, dept.getId());
		upstmt.executeUpdate();
	}
	
	//delete Department
	public void deleteDepartment(Integer deptId) throws SQLException{
		PreparedStatement dpstmt = con.prepareStatement("DELETE FROM department WHERE id = ?");
		dpstmt.setInt(1, deptId);
		dpstmt.executeUpdate();
	}
	
	//get List of all Departments
	public List<Department> getAllDepartment() throws SQLException{
		Statement stm = con.createStatement();
		List<Department> deptList = new ArrayList<Department>();
		String sql = "Select * from department;";
		ResultSet rs= stm.executeQuery(sql);
		while(rs.next()){
			Department deptBean = new Department();
			deptBean.setId(Integer.parseInt(rs.getString("id")));
			deptBean.setDeptName(rs.getString("name"));
			deptList.add(deptBean);
		}
		return deptList;
	}
	
}
