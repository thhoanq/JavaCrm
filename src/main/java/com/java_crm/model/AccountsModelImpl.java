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
import com.java_crm.pojo.Roles;

public class AccountsModelImpl implements AccountsModel{

	RolesModel rolesModel = new RolesModelImpl();
	
	@Override
	public boolean insertAccounts(Accounts data) {
		Connection con = MySQLConnection.getConnection();
		String url = "insert into accounts(full_name, gender, email, password, image, phone_num, role_id)"
				+ "value(?, ?, ?, ?, ?, ?, ?)";
		boolean isSuccess = false;
		
		try {
			PreparedStatement statement = con.prepareStatement(url);
			statement.setString(1, data.getFullName());
			statement.setString(2, data.getGender());
			statement.setString(3, data.getEmail());
			statement.setString(4, data.getPassword());
			statement.setString(5, data.getImage());
			statement.setString(6, data.getPhoneNum());
			statement.setInt(7, data.getRole().getId());
			
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

	@Override
	public Accounts getAccount(String email) {
		Accounts data =  null;
		Connection con = MySQLConnection.getConnection();
		String querry = "call get_account(?)";
		
		try {
			PreparedStatement statement = con.prepareStatement(querry);
			statement.setString(1, email);
			
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				data = new Accounts();
				data.setId(result.getInt("id"));
				data.setFullName(result.getString("full_name"));
				data.setGender(result.getString("gender"));
				data.setEmail(result.getString("email"));
				data.setPassword(result.getString("password"));
				data.setImage(result.getString("image"));
				data.setPhoneNum(result.getString("phone_num"));
				Roles role = rolesModel.getRoles(result.getInt("role_id"));
				data.setRole(role);
				Projects project = new Projects();
				project.setId(result.getInt("project_id"));
				data.setProject(project);
				data.setTaskNum(result.getInt("task_num"));
			}
			con.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
		return data;
	}

	@Override
	public Accounts getAccountByName(String userName) {
		Accounts data =  null;
		Connection con = MySQLConnection.getConnection();
		String querry = "select * from accounts where full_name = ?";
		
		try {
			PreparedStatement statement = con.prepareStatement(querry);
			statement.setString(1, userName);
			
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				data = new Accounts();
				data.setId(result.getInt("id"));
				data.setFullName(result.getString("full_name"));
				data.setGender(result.getString("gender"));
				data.setEmail(result.getString("email"));
				data.setPassword(result.getString("password"));
				data.setImage(result.getString("image"));
				data.setPhoneNum(result.getString("phone_num"));
				Roles role = rolesModel.getRoles(result.getInt("role_id"));
				data.setRole(role);
				Projects project = new Projects();
				project.setId(result.getInt("project_id"));
				data.setProject(project);
				data.setTaskNum(result.getInt("task_num"));
			}
			con.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
		return data;
	}
	
	@Override
	public Accounts getAccount(int id) {
		// TODO Auto-generated method stub
		Accounts data =  null;
		Connection con = MySQLConnection.getConnection();
		String querry = "select * from accounts where id = ? ";
		
		try {
			PreparedStatement statement = con.prepareStatement(querry);
			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				data = new Accounts();
				data.setId(result.getInt("id"));
				data.setFullName(result.getString("full_name"));
				data.setGender(result.getString("gender"));
				data.setEmail(result.getString("email"));
				data.setPassword(result.getString("password"));
				data.setImage(result.getString("image"));
				data.setPhoneNum(result.getString("phone_num"));
				Roles role = rolesModel.getRoles(result.getInt("role_id"));
				data.setRole(role);
				Projects project = new Projects();
				project.setId(result.getInt("project_id"));
				data.setProject(project);
				data.setTaskNum(result.getInt("task_num"));
			}
			con.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
		return data;
	}

	@Override
	public List<Accounts> getListAccount() {
		// TODO Auto-generated method stub
		List<Accounts> datas = new ArrayList<Accounts>();
		Connection con = MySQLConnection.getConnection();
		String query = "select * from accounts";
		
		try {
			PreparedStatement statement = con.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				Accounts data = new Accounts();
				data.setId(result.getInt("id"));
				data.setFullName(result.getString("full_name"));
				data.setGender(result.getString("gender"));
				data.setEmail(result.getString("email"));
				data.setPassword(result.getString("password"));
				data.setImage(result.getString("image"));
				data.setPhoneNum(result.getString("phone_num"));			
				Roles role = rolesModel.getRoles(result.getInt("role_id"));
				data.setRole(role);
				Projects project = new Projects();
				project.setId(result.getInt("project_id"));
				data.setProject(project);
				data.setTaskNum(result.getInt("task_num"));
				
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
	public boolean deleteAccounts(int id) {
		// TODO Auto-generated method stub
		Boolean isSuccess = false;
		Connection con = MySQLConnection.getConnection();
		String query = "delete from accounts where id = ?";
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, id);
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
	public boolean updateAccounts(Accounts user) {
		// TODO Auto-generated method stub
		Boolean isSuccess = false;
		Connection con = MySQLConnection.getConnection();
		String query = "update accounts "
					 + "set"
					 + "	full_name = ?, "
					 + "	gender = ?, "
					 + "	email = ?, "
					 + "	password = ?, "
					 + "	phone_num = ?, "
					 + "	role_id = ? "
					 + "where"
					 + "	id = ?;";
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, user.getFullName());
			statement.setString(2, user.getGender());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getPassword());
			statement.setString(5, user.getPhoneNum());
			statement.setInt(6, user.getRole().getId());
			statement.setInt(7, user.getId());
			
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
	public List<Accounts> getListByRole(int idRole) {
		// TODO Auto-generated method stub
		List<Accounts> datas = new ArrayList<Accounts>();
		Connection con = MySQLConnection.getConnection();
		String query = "select * from accounts where role_id = ?";
		
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, idRole);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				Accounts data = new Accounts();
				data.setId(result.getInt("id"));
				data.setFullName(result.getString("full_name"));
				data.setGender(result.getString("gender"));
				data.setEmail(result.getString("email"));
				data.setPassword(result.getString("password"));
				data.setImage(result.getString("image"));
				data.setPhoneNum(result.getString("phone_num"));			
				Roles role = rolesModel.getRoles(result.getInt("role_id"));
				data.setRole(role);
				Projects project = new Projects();
				project.setId(result.getInt("project_id"));
				data.setProject(project);
				data.setTaskNum(result.getInt("task_num"));
				
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
	public List<Accounts> getListByProject(int idProject) {
		// TODO Auto-generated method stub
		List<Accounts> datas = new ArrayList<Accounts>();
		Connection con = MySQLConnection.getConnection();
		String query = "select * from accounts where project_id = ?";
		
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, idProject);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				Accounts data = new Accounts();
				data.setId(result.getInt("id"));
				data.setFullName(result.getString("full_name"));
				data.setGender(result.getString("gender"));
				data.setEmail(result.getString("email"));
				data.setPassword(result.getString("password"));
				data.setImage(result.getString("image"));
				data.setPhoneNum(result.getString("phone_num"));			
				Roles role = rolesModel.getRoles(result.getInt("role_id"));
				data.setRole(role);
				Projects project = new Projects();
				project.setId(result.getInt("project_id"));
				data.setProject(project);
				data.setTaskNum(result.getInt("task_num"));
				
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
	public boolean deleteAccountProject(Accounts user) {
		// TODO Auto-generated method stub
		Boolean isSuccess = false;
		Connection con = MySQLConnection.getConnection();
		String query = "update accounts "
					 + "set"
					 + "	project_id = null "
					 + "where"
					 + "	id = ?;";
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, user.getId());
			
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
	public boolean insertAccountProject(Accounts user, int idPro) {
		// TODO Auto-generated method stub
		Boolean isSuccess = false;
		Connection con = MySQLConnection.getConnection();
		String query = "update accounts "
					 + "set"
					 + "	project_id = ? "
					 + "where"
					 + "	id = ?;";
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, idPro);
			statement.setInt(2, user.getId());
			
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
