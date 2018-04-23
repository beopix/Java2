package vorlesung;

import java.io.IOException;


public class Main {
	public static void main(String[] args) throws IOException, TextFileFormatException {
		System.out.println("Main");
		
		Vorlesungsverzeichnis a = new Vorlesungsverzeichnis("file.txt");
		System.out.println(a.titles());
		

	}
}
