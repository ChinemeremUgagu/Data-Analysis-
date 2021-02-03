package com.tyataacademy.java.project.emanagement.repository;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tyataacademy.java.project.emanagement.model.Compliance;

public class ComplianceRepository extends AbstractRepository{

	public ComplianceRepository() throws IOException {
		super();
	}

	//Create Compliance
	public void createCompliance(Compliance comp) throws SQLException{
		PreparedStatement pstmt = con.prepareStatement("INSERT INTO compliance(rltype,details,create_date,deptid) VALUES (?,?,?,?)");
		pstmt.setString(1,comp.getRltype());
		pstmt.setString(2,comp.getDetails());
		pstmt.setDate(3,new java.sql.Date(comp.getCreateDate().getTime()));
		pstmt.setInt(4,comp.getDepartmentId());
		pstmt.executeUpdate();
	}

	//Get Compliance
	public Compliance getComplianceById(Integer id) throws SQLException{
		Compliance comp = new Compliance();
		PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM compliance where id=?");
		preparedStatement.setInt(1, id);
		ResultSet rs = preparedStatement.executeQuery();
		
		if(rs.next()){
			comp.setId(rs.getInt("id"));
			comp.setRltype(rs.getString("rltype"));
			comp.setDetails(rs.getString("details"));
			comp.setCreateDate(rs.getDate("create_date"));
			comp.setDepartmentId(rs.getInt("deptid"));
		}
		return comp;
	}
	
	//Update Compliance
	public void updateCompliance(Compliance comp) throws SQLException{
		PreparedStatement upstmt = con.prepareStatement("UPDATE compliance SET rltype = ?, details = ?, create_date = ?, deptid = ? WHERE id = ?");
		upstmt.setString(1, comp.getRltype());
		upstmt.setString(2, comp.getDetails());
		upstmt.setDate(3, new java.sql.Date(comp.getCreateDate().getTime()));
		upstmt.setInt(4, comp.getDepartmentId());
		upstmt.setInt(5, comp.getId());
		upstmt.executeUpdate();
	}
	
	//delete Compliance
	public void deleteCompliance(Integer compId) throws SQLException{
		PreparedStatement dpstmt = con.prepareStatement("DELETE FROM compliance WHERE id = ?");
		dpstmt.setInt(1, compId);
		dpstmt.executeUpdate();
	}
	
	//get List of all Compliance
	public List<Compliance> getAllCompliance() throws SQLException{
		Statement stm = con.createStatement();
		List<Compliance> compList = new ArrayList<Compliance>();
		String sql = "Select * from compliance;";
		ResultSet rs= stm.executeQuery(sql);
		while(rs.next()){
			Compliance compBean = new Compliance();
			compBean.setId(rs.getInt("id"));
			compBean.setRltype(rs.getString("rltype"));
			compBean.setDetails(rs.getString("details"));
			compBean.setCreateDate(new Date(rs.getDate("create_date").getTime()));
			compBean.setDepartmentId(rs.getInt("deptid"));
			compList.add(compBean);
		}
		return compList;
	}
	
}
