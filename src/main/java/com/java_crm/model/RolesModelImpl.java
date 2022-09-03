package com.java_crm.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.java_crm.connection.MySQLConnection;
import com.java_crm.pojo.Roles;

public class RolesModelImpl implements RolesModel {

	@Override
	public Roles getRoles(int id) {
		// TODO Auto-generated method stub
		Roles role = null;
		Connection con = MySQLConnection.getConnection();
		String query = "call get_role(?)";
		
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				role = new Roles();
				role.setId(result.getInt("id"));
				role.setRoleName(result.getString("role_name"));
				role.setDescription(result.getString("descriptions"));
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return role;
	}
}
