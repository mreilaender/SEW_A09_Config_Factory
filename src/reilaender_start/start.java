package reilaender_start;

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

			//get the result of the SQL statment
			ResultSet rs = con.executeQuery("SELECT id, type, value FROM element");

			ConfigFactory factory = null;

			if(parser.FILE_EXTENSION.equals("xml"))
				factory = new XMLFactory();

			if(parser.FILE_EXTENSION.equals("yaml"))
				factory = new YAMLFactory();

			if(parser.FILE_EXTENSION.equals("php"))
				factory = new PHPFactory();

			if(parser.FILE_EXTENSION.equals("ini"))
				factory = new INIFactory();
			
			for(;rs.next() != false;) {
				Element tmp = factory.getConfigElement(rs.getString(2), rs.getString(3));
				if(tmp != null)
					elements.add(tmp);
			}
			//output will be written in a file named by the given args (file extension[xml, php, ...] and filename[-o])
			if(parser.OUTPUT_FILENAME != null) {
				File f = new File(parser.OUTPUT_FILENAME + "." + parser.FILE_EXTENSION);
				try {
					//create file
					f.createNewFile();
					PrintWriter pw = new PrintWriter(f);
					
					//Writing to the file
					for(int i = 0;i < elements.size();++i) {
						pw.println(elements.get(i).getFormat());
						pw.flush();
					}
					//close writer
					pw.close();
				} catch(IOException e) {
					System.out.println("Couldn't create file (maybe no permissions?). Output will be on the console.");
					//Setting the attribute to null so the other output method will be done (Output on the stdout).
					parser.OUTPUT_FILENAME = null;
				}
			} 
			//Ausgabe auf stdout
			if(parser.OUTPUT_FILENAME == null) {
				//Trying to clear the console for better formated output
				try {
					//On windows its cls
					if(System.getProperty("os.name").contains("Windows")) {
						Runtime.getRuntime().exec("cls");
					} else { //on linux its clear
						Runtime.getRuntime().exec("clear");
					}
				} catch (IOException e) {
					System.out.println("Couldn't clear the console.");
					System.out.println("-------------- Config -------------");
				}
				//Finally outputed on the stdout
				for(int i = 0;i < elements.size();++i) {
					System.out.println(elements.get(i).getFormat());
				}
			}
			con.closeStreams();
		}
	}
}