package com.java_crm.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.java_crm.connection.MySQLConnection;
import com.java_crm.pojo.Status;

public class StatusModelImpl implements StatusModel {

	@Override
	public Status getStatus(int id) {
		// TODO Auto-generated method stub
		Status data = null;
		Connection con = MySQLConnection.getConnection();
		String query = "call get_status(?)";
		
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				data = new Status();
				data.setId(result.getInt("id"));
				data.setStatusName(result.getString("status_name"));
				data.setDescription(result.getString("descriptions"));
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

}
