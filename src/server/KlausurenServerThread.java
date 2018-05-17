package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class KlausurenServerThread implements Runnable{
	Socket s;
	String operation;
	String key;
	String value;

	String feedback;

	String success = "1";
	String failure = "0";

	String full;

	String[] array;

	BufferedReader br;
	PrintWriter pw;

	Set<Integer> values = new TreeSet<Integer>();

	public KlausurenServerThread(Socket s) {
		this.s = s;
	}

	@Override
	public void run() {
		try {
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			pw = new PrintWriter(s.getOutputStream());
			full = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("String: "+ full);

		array = full.trim().split("\\s+|,\\s+|,");
		operation = array[0];

		if(operation.equals("stop") || operation.equals("STOP")) {
			pw.print(success);
			KlausurenServer.writeData();
			KlausurenServer.check = false;
			try {
				KlausurenServer.ss.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(array.length<2 && !(operation.equals("getall") || operation.equals("GETALL"))){
			System.out.println("no right order");
			pw.println("no right order");
		} else if(operation.equals("getall") || operation.equals("GETALL")){
			//will be processed bellow
		} else {
			key = array[1];
		}
		
		
		if(operation.equals("put") || operation.equals("PUT")) {
			if(KlausurenServer.sm.containsKey(key)) {
				feedback = KlausurenServer.sm.get(key).toString();
			}else {
				feedback = "";
			}
			for(int i=2;i<array.length; i++) {
				values.add(Integer.parseInt(array[i]));
			}
			KlausurenServer.sm.put(key, values);
			KlausurenServer.writeData();
			pw.println(success + " " + feedback);
		}
		
		if(operation.equals("get") || operation.equals("GET")) {
			if(KlausurenServer.sm.containsKey(key)) {
				feedback = KlausurenServer.sm.get(key).toString();
				pw.println(success + " " + feedback);
			}else {
				pw.println(failure);
			}
		}
		
		if(operation.equals("del") || operation.equals("DEL")) {
			if(KlausurenServer.sm.containsKey(key)) {
				feedback = KlausurenServer.sm.get(key).toString();
				KlausurenServer.sm.remove(key);
				KlausurenServer.writeData();
				pw.println(success + " " + feedback);
			}else {
				pw.println(failure);
			}
		}
		
		if(operation.equals("getall") || operation.equals("GETALL")) {
			if(!KlausurenServer.sm.values().isEmpty()) {
				
				List<Set<Integer>> tempMem = new ArrayList<>();
				List<Set<Integer>> feedbackList = new ArrayList<>();
				
				for(Set<Integer> s: KlausurenServer.sm.values()) {
					tempMem.add(s);
					feedbackList.add(s);
				}
				
				for(Set<Integer> s : tempMem) {
					for(Set<Integer> p : tempMem) {
						if(!s.equals(p) && s.containsAll(p)) {
							feedbackList.remove(p);
						}
					}
				}
								
				System.out.println(tempMem);
				System.out.println(feedbackList);
				
				pw.println(success + " " + feedbackList);
			}else {
				pw.println(failure);
			}
		}
	

		try {
			pw.close();
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
