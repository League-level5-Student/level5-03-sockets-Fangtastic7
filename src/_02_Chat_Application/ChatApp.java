package _02_Chat_Application;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import _02_Chat_Application.ChatApp;
import _02_Chat_Application.ClientComputer;
import _02_Chat_Application.ServerComputer;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp extends JFrame {
JButton button = new JButton("Send");
JButton button2 = new JButton("Send");
//JFrame frame = new JFrame();
JTextField input = new JTextField("Enter text here");
JTextField input2 = new JTextField("Enter text here");
JLabel label = new JLabel();
JLabel label2 = new JLabel();
JLabel chat = new JLabel("Chat");
JPanel panel = new JPanel();
	ServerComputer server;
	ClientComputer client;
	
	public static void main(String[] args) {
		new ChatApp();
	}
	public ChatApp() {
		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "Buttons!", JOptionPane.YES_NO_OPTION);
		if(response == JOptionPane.YES_OPTION){
			server = new ServerComputer(8080, this);
			setTitle("SERVER");
			JOptionPane.showMessageDialog(null, "Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
		
			add(panel);
			panel.add(chat);
			panel.add(label);
			panel.add(input);
			panel.add(button);
		
			button.addActionListener((e)->{
				System.out.println("button action! ~ server");
			server.sendMessage(input.getText());
			label.setText(label.getText() + "\n"+ input.getText());
			input.setText("   ");
	});
			
			setVisible(true);
			setSize(400, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			server.start();
			label.setText(label.getText() + "\n" + server.message);
			//while(true) {
			//	label.setText(label.getText() + "\n" + server.getMessage());
			//	}
		}else{
			setTitle("CLIENT");
			String ipStr = JOptionPane.showInputDialog("Enter the IP Address");
			String prtStr = JOptionPane.showInputDialog("Enter the port number");
			int port = Integer.parseInt(prtStr);
			client = new ClientComputer(ipStr, port, this);
			add(panel);
			panel.add(chat);
			panel.add(label2);
			panel.add(input);
			panel.add(button2);
			
			//label.setText(label.getText() + "\n" + server.input);
			button2.addActionListener((e)->{
				System.out.println("button action! ~ client");
			client.sendMessage(input.getText());
			label2.setText(label2.getText() + "\n"+ input.getText());
			input.setText("   ");
			});
		
			//input.addActionListener((e)->{
			//	String answer = input.getText() ;
			//});
			setVisible(true);
			setSize(400, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			client.start();
			
		//	while(true) {
		//	label.setText(label.getText() + "\n" + client.getMessage());
		//	}
		}
		
	}
	public void ShowClientMessage(String message) {
		label2.setText(label2.getText() + "\n" + message);
	}
	public void ShowServerMessage(String message) {
		label.setText(label.getText() + "\n" + message);
	}

}
