package vorlesung;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Vorlesungsverzeichnis {

	private List<List<String>> vv;

	/**
	 * Lädt die Datenbasis von einer Datei, deren Name als String übergeben wird.
	 * Für alle möglichen Formatfehler in der Datei soll eine TextFileFormatException
	 * mit einer aussagekräftigen Fehlermeldung geworfen werden.
	 * @param filename
	 * @throws IOException
	 * @throws TextFileFormatException
	 */
	public Vorlesungsverzeichnis(String filename) throws IOException, TextFileFormatException {
		vv = load(filename);

		for(List<String> l : vv) {
			if(l.size()<4) {
				throw new TextFileFormatException(0);
			}
			if(l.size()>4) {
				throw new TextFileFormatException(1);
			}
			for(String s : l) {
				if(s.isEmpty()) {
					throw new TextFileFormatException(2);
				}
				if(l.get(2).matches(".*\\d.*")) {
					throw new TextFileFormatException(3);
				}
				if(l.get(3).matches(".*\\D.*")) {
					throw new TextFileFormatException(3);
				}
			}
		}
	}

	/**
	 * Liefert eine alphabetisch sortierte Liste mit den Titeln aller Vorlesungen.
	 * @return
	 */
	public List<String> titles(){
		List<String> result = new ArrayList<String>();
		for(List<String> l : vv) {
			result.add(l.get(1));
		}
		Collections.sort(result);
		return result;
	}
	
	//Liefert die Menge derjenigen Dozenten, die zwei oder mehr Vorlesungen halten.
	
	public Set<String> workaholics() {
		
		
		
		return new HashSet<String>();
	}

	


	public static List<List<String>> load(String filename) throws IOException {
		List<List<String>> result = new ArrayList<List<String>>();
		BufferedReader br = new BufferedReader(new FileReader(filename));
		for (String line=br.readLine(); line!=null; line=br.readLine())
			result.add(Arrays.asList(line.split(":")));
		br.close();
		return result;
	}


}
