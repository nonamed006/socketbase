package testproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class CSThread extends Thread{
	private Socket socket;
	
	public CSThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"));
			
			while(true) {
				String data = br.readLine();
				if(data == null) {
					break;
				}
				pw.println(data);
				pw.flush();
				System.out.println(data);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
