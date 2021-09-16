package testproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	//1. 소켓만들기
	private static final String SERVER_IP = "127.0.0.1";
	private static final int SERVER_PORT = 7000;
	public static void main(String[] args) {
		
		Socket socket = new Socket();
		Scanner scan = new Scanner(System.in);
		
		try {
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
			
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"));
			
			new ClientThread(socket).start();
			while(true) {
				System.out.println(">");
				String line = scan.nextLine();
				
				if("exit".equals(line)) {
					break;
				}
				pw.println(line);
				//플러시를 해야 버퍼로 들어온 pw가 다시 나감
				pw.flush();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
