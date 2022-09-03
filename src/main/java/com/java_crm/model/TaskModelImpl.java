package com.java_crm.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java_crm.connection.MySQLConnection;
import com.java_crm.pojo.Accounts;
import com.java_crm.pojo.Projects;
import com.java_crm.pojo.Status;
import com.java_crm.pojo.Tasks;

public class TaskModelImpl implements TasksModel {

	StatusModel statusModel = new StatusModelImpl();
	ProjectsModel projectsModel = new ProjectsModelImpl();
	AccountsModel accountsModel = new AccountsModelImpl();
	
	@Override
	public List<Tasks> getListTasksByUser(int idUser) {
		// TODO Auto-generated method stub
		List<Tasks> tasks = new ArrayList<Tasks>();
		Connection con = MySQLConnection.getConnection();
		String query = "select * from task_detail where employee_task_id = ?";
		
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, idUser);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				Tasks data = new Tasks();
				data.setId(result.getInt("id"));
				data.setTaskName(result.getString("task_name"));
				data.setDescriptions(result.getString("descriptions"));
				data.setDayStart(result.getString("day_start"));
				data.setDayEnd(result.getString("day_end"));
				data.setSaveEdit(result.getString("save_edit"));
				data.setEmployeeGive(result.getString("employee_give"));
				Status status = statusModel.getStatus(result.getInt("status_id"));
				data.setStatus(status);
				Projects project = projectsModel.getProject(result.getInt("project_task_id"));
				data.setProject(project);
				Accounts account = accountsModel.getAccount(result.getInt("employee_task_id"));
				data.setEmployeeTask(account);
				
				tasks.add(data);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return tasks;
	}

	@Override
	public Tasks getTask(int idTask) {
		// TODO Auto-generated method stub
		Tasks data = null;
		Connection con = MySQLConnection.getConnection();
		String query = "select * from task_detail where id = ? ";
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, idTask);
			
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				data = new Tasks();
				data.setId(result.getInt("id"));
				data.setTaskName(result.getString("task_name"));
				data.setDescriptions(result.getString("descriptions"));
				data.setDayStart(result.getString("day_start"));
				data.setDayEnd(result.getString("day_end"));
				data.setSaveEdit(result.getString("save_edit"));
				data.setEmployeeGive(result.getString("employee_give"));
				Status status = statusModel.getStatus(result.getInt("status_id"));
				data.setStatus(status);
				Projects project = projectsModel.getProject(result.getInt("project_task_id"));
				data.setProject(project);
				Accounts account = accountsModel.getAccount(result.getInt("employee_task_id"));
				data.setEmployeeTask(account);			
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public boolean uppdateTask(Tasks data) {
		// TODO Auto-generated method stub
		Boolean isSuccess = false;
		Connection con = MySQLConnection.getConnection();
		String query = "update task_detail "
					 + "set "
					 + "	save_edit = ?, "
					 + "	status_id = ? "
					 + "where "
					 + "	id = ?;";
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, data.getSaveEdit());
			statement.setInt(2, data.getStatus().getId());
			statement.setInt(3, data.getId());
			
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
	public List<Tasks> getListTasksByProject(int idProject) {
		// TODO Auto-generated method stub
		List<Tasks> tasks = new ArrayList<Tasks>();
		Connection con = MySQLConnection.getConnection();
		String query = "select * from task_detail where project_task_id = ?";
		
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, idProject);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				Tasks data = new Tasks();
				data.setId(result.getInt("id"));
				data.setTaskName(result.getString("task_name"));
				data.setDescriptions(result.getString("descriptions"));
				data.setDayStart(result.getString("day_start"));
				data.setDayEnd(result.getString("day_end"));
				data.setSaveEdit(result.getString("save_edit"));
				data.setEmployeeGive(result.getString("employee_give"));
				Status status = statusModel.getStatus(result.getInt("status_id"));
				data.setStatus(status);
				Projects project = projectsModel.getProject(result.getInt("project_task_id"));
				data.setProject(project);
				Accounts account = accountsModel.getAccount(result.getInt("employee_task_id"));
				data.setEmployeeTask(account);
				
				tasks.add(data);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return tasks;
	}

	@Override
	public List<Tasks> getAllTasks() {
		// TODO Auto-generated method stub
		List<Tasks> tasks = new ArrayList<Tasks>();
		Connection con = MySQLConnection.getConnection();
		String query = "select * from task_detail";
		try {
			PreparedStatement statement = con.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				Tasks data = new Tasks();
				data.setId(result.getInt("id"));
				data.setTaskName(result.getString("task_name"));
				data.setDescriptions(result.getString("descriptions"));
				data.setDayStart(result.getString("day_start"));
				data.setDayEnd(result.getString("day_end"));
				data.setSaveEdit(result.getString("save_edit"));
				data.setEmployeeGive(result.getString("employee_give"));
				Status status = statusModel.getStatus(result.getInt("status_id"));
				data.setStatus(status);
				Projects project = projectsModel.getProject(result.getInt("project_task_id"));
				data.setProject(project);
				Accounts account = accountsModel.getAccount(result.getInt("employee_task_id"));
				data.setEmployeeTask(account);
				
				tasks.add(data);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tasks;
	}

	@Override
	public boolean insertTask(Tasks data) {
		// TODO Auto-generated method stub
		Connection con = MySQLConnection.getConnection();
		String url = "insert into task_detail(task_name, descriptions, day_start, day_end, save_edit, employee_give, project_task_id, employee_task_id)"
				+ "value(?, ?, ?, ?, ?, ?, ?, ?)";
		boolean isSuccess = false;
		
		try {
			PreparedStatement statement = con.prepareStatement(url);
			statement.setString(1, data.getTaskName());
			statement.setString(2, data.getDescriptions());
			statement.setString(3, data.getDayStart());
			statement.setString(4, data.getDayEnd());
			statement.setString(5, data.getSaveEdit());
			statement.setString(6, data.getEmployeeGive());
			statement.setInt(7, data.getProject().getId());
			statement.setInt(8, data.getEmployeeTask().getId());
			
			int result = statement.executeUpdate();
			con.close();
			
			if(result > 0) {
				isSuccess = true;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return isSuccess;
	}
}
