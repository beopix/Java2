package vorlesung;

import java.io.IOException;


public class Main {
	public static void main(String[] args) throws IOException, TextFileFormatException {
		System.out.println("Main");
		
		Vorlesungsverzeichnis a = new Vorlesungsverzeichnis("file.txt");
		System.out.println(a.toString());
		System.out.println(a.titles());
		System.out.println(a.titlesSet());
		
	
		System.out.println(a.workaholics());
		System.out.println(a.workaholicsSet());
//		System.out.println(a.groupToTitles());
//		System.out.println(a.groupToTitles().get("MT2"));
		
		
		
	}
}
