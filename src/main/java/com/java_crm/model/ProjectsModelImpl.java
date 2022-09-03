package com.java_crm.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java_crm.connection.MySQLConnection;
import com.java_crm.pojo.Projects;

public class ProjectsModelImpl implements ProjectsModel {

	@Override
	public Projects getProject(int idProject) {
		// TODO Auto-generated method stub
		Projects data = null;
		Connection con = MySQLConnection.getConnection();
		String query = "select * from project_detail where id = ?";
		
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, idProject);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				data = new Projects();
				data.setId(result.getInt("id"));
				data.setProjectName(result.getString("project_name"));
				data.setEmployeeCreatId(result.getInt("id_employee_create"));
				data.setEmployeeLeaderId(result.getInt("id_employee_leader"));
				data.setDescriptions(result.getString("descriptions"));
				data.setDayStart(result.getString("day_start"));
				data.setDayEnd(result.getString("day_end"));
				data.setProgress(result.getInt("progress"));
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}

	@Override
	public List<Projects> getAllProjects() {
		// TODO Auto-generated method stub
		List<Projects> datas = new ArrayList<Projects>();
		Connection con = MySQLConnection.getConnection();
		String query = "select * from project_detail";
		try {
			PreparedStatement statement = con.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				Projects data = new Projects();
				data.setId(result.getInt("id"));
				data.setProjectName(result.getString("project_name"));
				data.setEmployeeCreatId(result.getInt("id_employee_create"));
				data.setEmployeeLeaderId(result.getInt("id_employee_leader"));
				data.setDescriptions(result.getString("descriptions"));
				data.setDayStart(result.getString("day_start"));
				data.setDayEnd(result.getString("day_end"));
				data.setProgress(result.getInt("progress"));
				datas.add(data);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datas;
	}

	@Override
	public List<Projects> getProjectByIdLeader(int idLeader) {
		// TODO Auto-generated method stub
		List<Projects> datas = new ArrayList<Projects>();
		Connection con = MySQLConnection.getConnection();
		String query = "select * from project_detail where id_employee_leader = ?";
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, idLeader);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				Projects data = new Projects();
				data.setId(result.getInt("id"));
				data.setProjectName(result.getString("project_name"));
				data.setEmployeeCreatId(result.getInt("id_employee_create"));
				data.setEmployeeLeaderId(result.getInt("id_employee_leader"));
				data.setDescriptions(result.getString("descriptions"));
				data.setDayStart(result.getString("day_start"));
				data.setDayEnd(result.getString("day_end"));
				data.setProgress(result.getInt("progress"));
				
				datas.add(data);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datas;
	}

	@Override
	public boolean insertProject(Projects data) {
		// TODO Auto-generated method stub
		Connection con = MySQLConnection.getConnection();
		String query = "insert into project_detail(project_name, id_employee_create, id_employee_leader, descriptions, day_start, day_end) "
				+ "value(?, ?, ?, ?, ?, ?)";
		boolean isSuccess = false;
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, data.getProjectName());
			statement.setInt(2, data.getEmployeeCreatId());
			statement.setInt(3,  data.getEmployeeLeaderId());
			statement.setString(4, data.getDescriptions());
			statement.setString(5, data.getDayStart());
			statement.setString(6, data.getDayEnd());
			
			int result = statement.executeUpdate();
			con.close();
			
			if(result > 0) {
				isSuccess = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isSuccess;
	}

	@Override
	public boolean deleteProject(int idProject) {
		// TODO Auto-generated method stub
		Boolean isSuccess = false;
		Connection con = MySQLConnection.getConnection();
		String query = "delete from project_detail where id = ?";
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, idProject);
			int result = statement.executeUpdate();
			con.close();
			if(result > 0) {
				isSuccess = true;
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isSuccess;
	}

	@Override
	public boolean updateProject(Projects data) {
		// TODO Auto-generated method stub
		Boolean isSuccess = false;
		Connection con = MySQLConnection.getConnection();
		String query = "update project_detail "
					 + "set "
					 + "	project_name = ?, "
					 + "	descriptions = ?, "
					 + "	day_start = ?, "
					 + "	day_end = ? "
					 + "where "
					 + "	id = ?;";
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, data.getProjectName());
			statement.setString(2, data.getDescriptions());
			statement.setString(3, data.getDayStart());
			statement.setString(4, data.getDayEnd());
			statement.setInt(5, data.getId());
			
			int result = statement.executeUpdate();
			con.close();
			
			if(result > 0) {
				isSuccess = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isSuccess;
	}

}
