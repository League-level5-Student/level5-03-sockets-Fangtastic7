package _02_Chat_Application;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class ServerComputer {
	private int port;
	
	private ServerSocket server;
	private Socket connection;

	ObjectOutputStream os;
	ObjectInputStream is;
	String message;
	ChatApp ca;
	public ServerComputer(int port, ChatApp ca) {
		this.port = port;
		this.ca = ca;
	}

	public void start(){
		try {
			server = new ServerSocket(port, 100);

			connection = server.accept();
			//JOptionPane.showMessageDialog(null, "connection success! ~ server");
			os = new ObjectOutputStream(connection.getOutputStream());
		//	JOptionPane.showMessageDialog(null, "os success! ~ server");
			is = new ObjectInputStream(connection.getInputStream());
			
			os.flush();

			while (connection.isConnected()) {
				try {
				//	JOptionPane.showMessageDialog(null, is.readObject());
				//	System.out.println(is.readObject());
					 message = (String) is.readObject();
					 ca.ShowServerMessage(message);
					System.out.println("server: " + message);
				}catch(EOFException e) {
					//JOptionPane.showMessageDialog(null, "Connection Lost");
					System.exit(0);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getIPAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "ERROR!!!!!";
		}
	}

	public int getPort() {
		return port;
	}

	public void sendMessage(String input) {
		System.out.println("send message! - server");
		try {
			if (os != null) {
			 os.writeObject(input);
			//	os.writeObject("CLIENT: " + message);
			os.flush(); 
			
			} }
	 catch (IOException e) {
	e.printStackTrace();
	 }
	}
}
