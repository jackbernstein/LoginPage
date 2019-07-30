package com.Jack.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	
	private final String url = "jdbc:postgresql://localhost:5432/sample_db";
	

	public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
 
        return conn;
    }
	
	public void createUser(Connection conn, String email, 
			String first, String last, String password) {
		try {
			if(email.isBlank() | first.isBlank() | last.isBlank() | password.isBlank()) {
				System.out.println("Must include all information");
				return;
			} else {
				Statement st = conn.createStatement();
				st.executeUpdate("INSERT INTO users VALUES "
						+ "('" + email + "', '" + first + "', '" + last + "', '" + password + "')");
				System.out.println("User Created:");
				System.out.println("Email: " + email);
				System.out.println("Name: " + first + " " + last);
				st.close();
			}
		} catch (SQLException e) {
			System.out.println("Email already exists");
		}
	}
	
	
	
	
	public static void main(String[] args) throws SQLException {
		DB app = new DB();
		Connection conn = app.connect();
		app.createUser(conn, "jackbernstein@mail.com", "Jack", "Bernstein", "Password");
		conn.close();
		
	}

}
