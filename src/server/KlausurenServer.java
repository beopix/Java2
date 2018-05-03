package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class KlausurenServer implements Runnable{
	int port;
	Map<String, Set<Integer>> sm = new HashMap<>();
	ServerSocket ss;
	Socket s;
	BufferedReader br;
	String p = null;

	public KlausurenServer(int port) {
		this.port = port;
		try {
			ss = new ServerSocket(port);
		} catch(IOException e){
			System.out.println("Server konnte nicht initialisiert werden");
		}
	}
	
	public void start() {
		this.run();
	}

	@Override
	public void run() {
		
		while(true) {
		try {
			s = ss.accept();
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			p = br.readLine();
			
		} catch (IOException e) {
			System.out.println("Thread konnte nicht starten");
			}
		
		System.out.println(p);
		try {
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		
	}



}
