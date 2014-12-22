package test;

import java.io.Console;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class testClass {
	private static String USERNAME = System.getProperty("user.name"),
			PASSWORD = "",
			IP = "localhost",
			DATABASE = null,
			SORT_FIELD = "",
			SORT_DIRECTION = "ASC",
			FILTER = "",
			DELIMITER = ";",
			OUTPUT = null,
			OUTPUT_FILENAME = null,
			TABLENAME = null
			;
	private static final String[][] opt = new String[][]{
		{"h", "true", "Hostname des DBMS. Standard: localhost"},
		{"u", "true", "Benutzername. Standard: Benutzername des im Betriebssystem angemeldeten Benutzers"},
		{"p", "false", "Passwort. Alternativ kann ein Passwortprompt angezeigt werden. Standard: keins"},
		{"d", "true", "Name der Datenbank"},
		{"s", "true", "Feld, nach dem sortiert werden soll (nur eines möglich, Standard: keines)"},
		{"r", "true", "Sortierrichtung. Standard: ASC"},
		{"w", "true", "eine Bedingung in SQL-Syntax, die zum Filtern der Tabelle verwendet wird. Standard: keine"},
		{"t", "true", "Trennzeichen, dass für die Ausgabe verwendet werden soll. Standard: ; "},
		{"f", "true", "Kommagetrennte Liste (ohne Leerzeichen) der Felder, die im Ergebnis enthalten sein sollen. * soll akzeptiert werden (Pflicht)"},
		{"o", "true", "Name der Ausgabedatei. Standard: keine -> Ausgabe auf der Konsole"},
		{"T", "true", "Tabellenname (Pflicht)"}
	};
	public static void main(String[] args) throws SQLException, ParseException {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		CommandLine cmd = null;
		CommandLineParser parser = null;

		MysqlDataSource ds = new MysqlDataSource();
		Scanner s = new Scanner(System.in);
		Console console = null;
		String pw = "";

		Options options = new Options();


		for(int i = 0;i < opt.length;++i) {
			options.addOption(opt[i][0], Boolean.parseBoolean(opt[i][1]), opt[i][2]);
		}

		try {
			parser = new BasicParser();
			cmd = parser.parse(options, args);
		} catch (Exception e) {
			System.out.println("Couldn't parse options, because " + e.getMessage());
			System.exit(0);
		}

		if(cmd.hasOption('h'))
			IP = cmd.getOptionValue('h');
		if(cmd.hasOption('u'))
			USERNAME = cmd.getOptionValue('u');
		if(cmd.hasOption("p")) {
			try {
				System.out.println("IP: "+cmd.getOptionValue('h'));
				console = System.console();
				PASSWORD = new String(console.readPassword("Password: "));
			} catch (NullPointerException e) {
				System.err.println("Info: Couldn't get Console Object, please run this programm from your OS CLI. Using default password for now.");
			}
		}
		if(cmd.hasOption('d'))
			DATABASE = cmd.getOptionValue('d');
		if(cmd.hasOption('s'))
			SORT_FIELD = cmd.getOptionValue('s');
		if(cmd.hasOption('r')) {
			SORT_DIRECTION = cmd.getOptionValue('r');
		}
		if(cmd.hasOption('w')) {
			FILTER = cmd.getOptionValue('w');
		}
		if(cmd.hasOption('t')) {
			DELIMITER = cmd.getOptionValue('t');
		}
		if(cmd.hasOption('f')) {
			OUTPUT = cmd.getOptionValue('f');
		}
		if(cmd.hasOption('o')) {
			OUTPUT_FILENAME = cmd.getOptionValue('o');
		}
		if(cmd.hasOption('T')) {
			TABLENAME = cmd.getOptionValue('T');
		}

		System.out.println("IP: "+IP);
		PASSWORD = "manuel";
		
		ds.setServerName(IP);
		ds.setUser(USERNAME);
		ds.setPassword(PASSWORD);
		ds.setDatabaseName(DATABASE);
		
		con = ds.getConnection();
		st = con.createStatement();
		
		st.executeQuery("use notizv");
//		System.out.println("SQL Statement: ");
//		String tmp = s.nextLine();
		rs = st.executeQuery("SELECT * FROM person");
		
		rs.next();
		rs.next();
		System.out.println(rs.getString(1));
		
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