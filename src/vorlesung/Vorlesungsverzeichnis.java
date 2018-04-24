package vorlesung;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class Vorlesungsverzeichnis {

	private List<List<String>> vv;
	
	private Set<Vorlesung> sv;

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
		sv = new HashSet<>();
	
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
		
		for(List<String> l : vv) {
			sv.add(new Vorlesung(l.get(0), l.get(1), l.get(2), Integer.parseInt(l.get(3))));
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
	
	public List<String> titlesSet(){
		List<String> result = new ArrayList<String>();
		for(Vorlesung l : sv) {
			result.add(l.getTitle());
		}
		Collections.sort(result);
		return result;
	}
	
	

	/**
	 * Liefert die Menge derjenigen Dozenten, die zwei oder mehr Vorlesungen halten.
	 * @return
	 */
	public Set<String> workaholics() {
		int counter = 0;
		Set<String> result = new TreeSet<>();
		for(List<String> l : vv) {
			counter = 0;
			for(List<String> k : vv) {
				if(l.get(2).contains(k.get(2))) {
					counter++;
				}
			}
			if(counter>=2) {
				result.add(l.get(2));
			}
		}
		return result;
	}
	
	public Set<String> workaholicsSet() {
		int counter = 0;
		Set<String> result = new TreeSet<>();
		for(Vorlesung l : sv) {
			counter = 0;
			for(Vorlesung k : sv) {
				if(l.getLecturer().equals(k.getLecturer())) {
					counter++;
				}
			}
			if(counter>=2) {
				result.add(l.getLecturer());
			}
		}
		return result;
	}
	
	

	/**
	 * Liefert eine Map, die Studiengruppen auf Listen von Vorlesungstiteln abbildet.
	 * Unter dem Schlüssel MT2 wäre für die oben angegebene Datenbasis zum Beispiel als Wert
	 * die Liste [Mathematik 2, Audio-/Videotechnik] zu finden.
	 * @return
	 */
	public Map<String, List<String>> groupToTitles() {
		Map<String, List<String>> result = new HashMap<>();
		Set<String> temp;

		for(List<String> key : vv) {
			temp = new TreeSet<>();						//sortiert sonst HashSet<>();
			for(List<String> value :vv) {
				if(key.get(0).equals(value.get(0))) {
					temp.add(value.get(1));
				}
			}
			result.put(key.get(0), new ArrayList<>(temp));
		}
		return result;
	}

	/**
	 * 
	 * Liefert eine Map, die Vorlesungen auf Listen von Dozenten, die diese Vorlesungen halten,
	 * abbildet. Als Schlüssel werden in der Map nur Vorlesungen verwendet,
	 * die von unterschiedlichen Dozenten gehalten werden. 
	 * Entsprechend der obigen Datenbasis würde in diesem Fall nur ein Eintrag
	 * in der Map stehen mit dem Schlüssel Mathematik 2 und dem Wert [von Coelln, Rabe]
	 * als Liste.
	 * 
	 */
	
	
	
	@Override
	public String toString() {
		return sv.toString();
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
