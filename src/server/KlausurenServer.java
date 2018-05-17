package server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class KlausurenServer{
	int port;
	public static Map<String, Set<Integer>> sm = new ConcurrentHashMap<>();
	static ServerSocket ss;
	Socket s;
	public static boolean check = true;


	public KlausurenServer() {
	}

	@SuppressWarnings("unchecked")
	public KlausurenServer(int port) {
		this.port = port;
		try {
			ss = new ServerSocket(port);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			ObjectInputStream obj = new ObjectInputStream(new FileInputStream("save_data.txt"));
			try {
				sm = (Map<String, Set<Integer>>) obj.readObject();
				for(Set<Integer> s: sm.values()) {
					System.out.println(s);
				}
				
				for(String s: sm.keySet()) {
					System.out.println(s);
				}
				obj.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected static void writeData() {
		try {
			ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream("save_data.txt"));
			obj.writeObject(sm);
			obj.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void runKlausurenServer() throws IOException{		
		while(check) {
			try {
				s = ss.accept();
			} catch (IOException e) {
				System.out.println("ServerSocked Closed");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!ss.isClosed())
				new Thread(new KlausurenServerThread(s)).start();
		}
	}
}
