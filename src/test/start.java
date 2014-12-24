package test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import reilaender_elements.*;
import reilaender_factories.*;

public class start {
	private static ArrayList<Element> elements;

	public static void main(String[] args) throws SQLException {
		elements = new ArrayList<Element>();

		Parser parser = new Parser();
		parser.fillOptions();
		parser.createParser(args);
		parser.retrieveArgs();

		if(args.length == 0 || args == null) {
			parser.printHelp(new PrintWriter(System.out));
		}
		else {
			Connector con = new Connector(parser);
			con.connect();


			ResultSet rs = con.executeQuery("SELECT id, type, value FROM element");

			if(parser.FILE_EXTENSION.equals("xml")) {
				ConfigFactory factory = new XMLFactory();
				for(;rs.next() != false;) {
					Element tmp = factory.getConfigElement(rs.getString(2), rs.getString(3));
					if(tmp != null)
						elements.add(tmp);
				}
			}
			if(parser.FILE_EXTENSION.equals("yaml")) {
				ConfigFactory factory = new YAMLFactory();
				for(;rs.next() != false;) {
					Element tmp = factory.getConfigElement(rs.getString(2), rs.getString(3));
					if(tmp != null)
						elements.add(tmp);
				}
			}
			if(parser.FILE_EXTENSION.equals("php")) {
				ConfigFactory factory = new PHPFactory();
				for(;rs.next() != false;) {
					Element tmp = factory.getConfigElement(rs.getString(2), rs.getString(3));
					if(tmp != null)
						elements.add(tmp);
				}
			}
			if(parser.FILE_EXTENSION.equals("ini")) {
				ConfigFactory factory = new INIFactory();
				for(;rs.next() != false;) {
					Element tmp = factory.getConfigElement(rs.getString(2), rs.getString(3));
					if(tmp != null)
						elements.add(tmp);
				}
			}

			//Ausgabe
			try {
				if(System.getProperty("os.name").contains("Windows")) {
					Runtime.getRuntime().exec("cls");
				} else {
					Runtime.getRuntime().exec("clear");
				}
			} catch (IOException e) {
				System.out.println("Couldn't clear the console.");
				System.out.println("-------------- Config -------------");
			}
			if(parser.OUTPUT_FILENAME != null) {
				File f = new File(parser.OUTPUT_FILENAME);
				try {
					f.createNewFile();
					PrintWriter pw = new PrintWriter(f);
				} catch(IOException e) {
					
				}
			} else {
				for(int i = 0;i < elements.size();++i) {
					//				System.out.println(elements.get(2).getFormat());
					System.out.println(elements.get(i).getFormat());
				}
			}
		}
		//		con.executeQuery("USE notizv");
		//		ResultSet rs = con.executeQuery("SELECT * FROM person");
		//		
		//		rs.next();
		//		rs.next();
		//		
		//		System.out.println(rs.getString(3));
	}
}