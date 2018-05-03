package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {

		InputStreamReader cin = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(cin);
		boolean check = true;
		int port = 0;
		String temp;
		while(check) {
			try {
				System.out.println("Port eingeben");
				temp = br.readLine();
				try {
					port=Integer.parseInt(temp);
					check = false;
				} catch (NumberFormatException e) {
					System.out.println("Nur Nummern eingeben");
				}
			} catch (IOException e) { 
				System.out.println("Eingabe konnte nicht gelesen werden.");
			}
		}
		
		KlausurenServer ks = new KlausurenServer(port);
		ks.start();
		
	}

}
