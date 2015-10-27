package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBKudeatzaile {

	Connection conn;

	public void conOpen() {
		try {
			if (conn != null) {
				conn.close();
				conn = null;
			}

			//String userName = u;
			//String password = pass;
			String url = "jdbc:sqlite:";
			Class.forName("org.sqlite.JDBC").newInstance();
			conn = (Connection) DriverManager.getConnection(url);
			System.out.println("Database connection established");
		} catch (Exception e) {
			System.err.println("Cannot connect to database server");
			e.printStackTrace();
		}
	}

	private ResultSet query(Statement s, String query) throws SQLException {

		ResultSet rs = null;

		s.executeQuery(query);
		rs = s.getResultSet();

		return rs;
	}

	// singleton patroia
	private static DBKudeatzaile instantzia = new DBKudeatzaile();

	private DBKudeatzaile() {
	}

	public static DBKudeatzaile getInstantzia() {
		return instantzia;
	}

	public ResultSet execSQL(String query) throws SQLException {
		int count = 0;
		Statement s = null;
		ResultSet rs = null;

		s = (Statement) conn.createStatement();

		if (query.toLowerCase().indexOf("select") == 0) {

			// select agindu bat
			rs = this.query(s, query);
		} else {
			// update, delete, create agindu bat
			count = s.executeUpdate(query);
		}

		return rs;
	}
}
	

