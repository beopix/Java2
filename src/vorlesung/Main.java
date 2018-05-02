package vorlesung;

import java.io.IOException;
import generics.*;


public class Main {
	public static void main(String[] args) throws IOException, TextFileFormatException {
		System.out.println("Main");
		
		Vorlesungsverzeichnis a = new Vorlesungsverzeichnis("file.txt");
		System.out.println(a);
		System.out.println(a.titles());
//		System.out.println(a.titlesSet());
//		
//	
//		System.out.println(a.workaholics());
//		System.out.println(a.workaholicsSet());
//		System.out.println(a.groupToTitles());
//		System.out.println(a.groupToTitlesSet());
		
		
//		System.out.println(a.multipleTitles());
//		System.out.println(a.descendingTitles());

//		System.out.println(a.groupToTitles().get("MT2"));
//		Node<Vorlesung> nv = new Node<>(new Vorlesung("I2", "Mathe", "Bob", 25));
//		Node<Vorlesung> cnv = new Node<>(new Vorlesung("I2", "Mathe", "Bob", 25));
//		System.out.println(nv.equals(cnv));
//		System.out.println(nv);
//		System.out.println(cnv);
		
	}
}
