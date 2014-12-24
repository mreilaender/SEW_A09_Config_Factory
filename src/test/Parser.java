package test;

import java.io.Console;
import java.io.PrintWriter;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.HelpFormatter;

public class Parser {
	private Options options;
	private Console console;
	private CommandLine cmd;
	private CommandLineParser parser;
	public String USERNAME = System.getProperty("user.name"),
			PASSWORD = "",
			IP = "localhost",
			DATABASE = null,
			SORT_FIELD = "",
			SORT_DIRECTION = "ASC",
			FILTER = "",
			DELIMITER = ";",
			OUTPUT = null,
			OUTPUT_FILENAME = null,
			TABLENAME = null,
			FILE_EXTENSION = null
			;
	private final String[][] opt = new String[][]{
		{"h", "true", "Hostname des DBMS. Standard: localhost"},
		{"u", "true", "Benutzername. Standard: Benutzername des im Betriebssystem angemeldeten Benutzers"},
		{"p", "false", "Passwort. Alternativ kann ein Passwortprompt angezeigt werden. Standard: keins"},
		{"d", "true", "Name der Datenbank"},
//		{"s", "true", "Feld, nach dem sortiert werden soll (nur eines möglich, Standard: keines)"},
//		{"r", "true", "Sortierrichtung. Standard: ASC"},
//		{"w", "true", "eine Bedingung in SQL-Syntax, die zum Filtern der Tabelle verwendet wird. Standard: keine"},
//		{"t", "true", "Trennzeichen, dass für die Ausgabe verwendet werden soll. Standard: ; "},
//		{"f", "true", "Kommagetrennte Liste (ohne Leerzeichen) der Felder, die im Ergebnis enthalten sein sollen. * soll akzeptiert werden (Pflicht)"},
		{"o", "true", "Name der Ausgabedatei. Standard: keine -> Ausgabe auf der Konsole"},
//		{"T", "true", "Tabellenname (Pflicht)"},
		{"F", "true", "Datei-Typ der Ausgabedatei (XML, PHP, YAML, ...)"}
	};
	private HelpFormatter help;
	
	public Parser() {
		help = new HelpFormatter();
	}
	public void fillOptions() {
		options = new Options();
		for(int i = 0;i < opt.length;++i) {
			options.addOption(opt[i][0], Boolean.parseBoolean(opt[i][1]), opt[i][2]);
		}
	}
	public void createParser(String[]args) {
		try {
			parser = new BasicParser();
			cmd = parser.parse(options, args);
		} catch (Exception e) {
			System.out.println("Couldn't parse options, because " + e.getMessage());
			System.exit(0);
		}
	}
	public void retrieveArgs() {
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
		if(cmd.hasOption('F')) {
			FILE_EXTENSION = cmd.getOptionValue('F');
		}
	}
	public Console getConsole() {
		return console;
	}
	public void printHelp(PrintWriter pw) {
		help.printOptions(pw, 50, options, 5, 5);
		pw.flush();
	}
}