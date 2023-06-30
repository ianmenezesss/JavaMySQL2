package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement(
			"INSERT INTO ACTOR " + "(first_name, last_name) " + "VALUES" + "(?,?)", Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, "Bob");
			st.setString(2, "Cleaver");
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				while (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done! Id: " + id);
				}
			}
			else {
				System.out.println("No rows affected!");
			}
			
		     }catch (SQLException e) {
				e.printStackTrace();
			} 
			finally {
				DB.closeStatement(st);
				DB.closeConnection();
	     }
	  }
	}