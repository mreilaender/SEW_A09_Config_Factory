package start;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			//this is just needed on my computer
			if(System.getProperty("user.name").equals("manuel")) {
				System.err.println("username is manuel");
				parser.PASSWORD = "manuel";
			}
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
}