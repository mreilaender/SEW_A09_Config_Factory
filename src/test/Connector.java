package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.cli.ParseException;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class Connector {
	private Connection con;
	private Statement st;
	private MysqlDataSource ds;
	private Parser parser;
	
	public Connector(Parser parser) {
		this.parser = parser;
	}
	public void connect() throws SQLException {
		ds = new MysqlDataSource();
		
		if(parser.getConsole() == null) {
			System.out.println("Connector: Console is null" );
			parser.PASSWORD = "manuel";
		}
		
		ds.setServerName(parser.IP);
		ds.setUser(parser.USERNAME);
		ds.setPassword(parser.PASSWORD);
		ds.setDatabaseName(parser.DATABASE);
		
		con = ds.getConnection();
		st = con.createStatement();
	}
	public ResultSet executeQuery(String query) throws SQLException {
		return st.executeQuery(query);
	}
	public static void main(String[] args) throws SQLException, ParseException {
//		st.executeQuery("use notizv");
//		System.out.println("SQL Statement: ");
//		String tmp = s.nextLine();
//		rs = st.executeQuery("SELECT * FROM person");
//		
//		rs.next();
//		rs.next();
//		System.out.println(rs.getString(3));
		
		/*
		System.out.println("Defaultwerte? Ja[0] Nein[1]");
		System.out.println("DEFAULT_IP: " + DEFAULT_IP);
		System.out.println("DEFAULT_USERNAME: "+DEFAULT_USERNAME);
		System.out.println("DEFAULT_PASSWORD: "+DEFAULT_PASSWORD);

		if(Integer.parseInt(s.nextLine())==0) {
			ds.setServerName(testClass.DEFAULT_IP);
			ds.setUser(DEFAULT_USERNAME);
			ds.setPassword(DEFAULT_PASSWORD);
		} else {
			System.out.println("DB_IP: ");
			ds.setServerName(s.nextLine());

			System.out.println("User: ");
			ds.setUser(s.nextLine());

			System.out.println("PW: ");
			ds.setPassword(s.nextLine());

			System.out.println("DB: ");
			ds.setDatabaseName(s.nextLine());
		}
		try {
			//Connect to the DB
			System.out.println("here");
			con = ds.getConnection();

			//
			st = con.createStatement();

			System.out.println("SQL Statement: ");
			String tmp = s.nextLine();
			rs = st.executeQuery(tmp);

			System.out.println(rs.getString(0));
		} catch (Exception e) {
			// TODO: handle exception
			rs.close();
			st.close();
			con.close();
			s.close();
		}
		s.close();
		 */
	}

}