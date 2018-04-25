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

/**
 * @author xps_m
 */

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
		sv = new TreeSet<>();
	
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
	 * @return an alphabetical sorted list of titles
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
	 * @return a set of lecturers with two or more lectures
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
	 * @return a map with subject as key and a list of titles of the subject as value
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
	
	
	public Map<String, List<String>> groupToTitlesSet() {
		Map<String, List<String>> result = new HashMap<>();
		Set<String> subjectList;
		
		for(Vorlesung key : sv) {
			subjectList = new TreeSet<>();
			for(Vorlesung value : sv) {
				if(key.getSubject().equals(value.getSubject())) {
					subjectList.add(value.getTitle());
				}
			}
			result.put(key.getSubject(), new ArrayList<>(subjectList));
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
	 * @return a map with title as key and a list of lecturers as value
	 */
	
	public Map<String, List<String>> multipleTitles(){
		Map<String, List<String>> result = new HashMap<>();
		Set<String> titleList;
		
		for(Vorlesung cmpTo : sv) {
			titleList = new TreeSet<>();
			
			for(Vorlesung v : sv) {
				if(cmpTo.getTitle().equals(v.getTitle()) && (!cmpTo.getLecturer().equals(v.getLecturer()))) {
					titleList.add(cmpTo.getLecturer());
					titleList.add(v.getLecturer());
				}
			}
			if(titleList.size() >= 2) {
				result.put(cmpTo.getTitle(), new ArrayList<>(titleList));
			}
			
		}
		return result;
	}
	
	/**
	 * Liefert eine nach Teilnehmerzahl absteigend(!) sortierte Liste
	 * mit den Titeln aller Vorlesungen.
	 * @return a list of all lecture titles sorted by attendance descending
	 */
	
	public List<String> descendingTitles(){
		List<Vorlesung> temp = new ArrayList<>();
		List<String> result = new ArrayList<>();
		for(Vorlesung v : sv) {
			temp.add(v);
		}
		Collections.sort(temp, new Vorlesung().reversed());
		for(Vorlesung v : temp) {
			result.add(v.getTitle());
		}
		return result;
	}
	
	
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
