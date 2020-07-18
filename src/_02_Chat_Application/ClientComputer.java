package _02_Chat_Application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ClientComputer {
	private String ip;
	private int port;
	

	Socket connection;

	ObjectOutputStream os;
	ObjectInputStream is;
	String message;
	ChatApp ca;
	public ClientComputer(String ip, int port, ChatApp ca) {
		this.ip = ip;
		this.port = port;
		this.ca = ca;
	}

	public void start(){
		try {
			
			connection = new Socket(ip, port);
		//	JOptionPane.showMessageDialog(null, "connection success! ~ client");
			os = new ObjectOutputStream(connection.getOutputStream());
		//	JOptionPane.showMessageDialog(null, "os success! ~ client");
			is = new ObjectInputStream(connection.getInputStream());

			os.flush();

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
		//JOptionPane.showMessageDialog(null, "os is not successful in connection");
			e.printStackTrace();
		}
		
		while (connection.isConnected()) {
			try {
				//JOptionPane.showMessageDialog(null, is.readObject());
				//System.out.println(is.readObject());
				 message = (String) is.readObject();
				 ca.ShowClientMessage(message);
				System.out.println("client: " + message);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void sendMessage(String input) {
		try {
			System.out.println("send message! - client");
			if (os != null) {
			 os.writeObject(input);
			 
			//	os.writeObject("CLIENT: " + message);
			os.flush(); 
			}
			} 
	 catch (IOException e) {
	e.printStackTrace();
	 }
	
	}

}
