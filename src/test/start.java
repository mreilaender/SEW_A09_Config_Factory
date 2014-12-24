package test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class start {

	public static void main(String[] args) throws SQLException {
		Parser parser = new Parser();
			parser.fillOptions();
			parser.createParser(args);
			parser.retrieveArgs();
			
		Connector con = new Connector(parser);
			con.connect();
			
//		con.executeQuery("USE notizv");
		ResultSet rs = con.executeQuery("SELECT * FROM person");
		
		rs.next();
		rs.next();
		
		System.out.println(rs.getString(3));
	}
}