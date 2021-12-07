package mvcPicross;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.net.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class GameClient extends JFrame {

	static final String END = "end";
	JLabel clientLabel;
	JPanel clientPanelOne;
	JPanel clientPanelTwo;
	JTextArea clientTextArea = new JTextArea();
	JLabel user = new JLabel("User:");
	JLabel server = new JLabel("Server:");
	JLabel cPort = new JLabel("Port:");
	JButton connect = new JButton("Connect");
	JButton cEnd = new JButton("End");
	JTextField userField = new JTextField();
	JTextField serverField = new JTextField();
	JTextField cPortField = new JTextField();



	JButton clientNewGame = new JButton("New Game");
	JButton sendGame = new JButton("Send Game");
	JButton receiveGame = new JButton("Receive Game");
	JButton sendDate = new JButton("Send Date");
	JButton play = new JButton("Play");
	clientController actionListener = new clientController();
	JScrollPane clientScrollPane = new JScrollPane(clientTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


	GameClient(){


		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(2, 2, 2, 2);

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.0;
		c.weighty = 3.0;

		clientLabel = new JLabel(new ImageIcon("piccorssLogoClient.png"));
		clientLabel.setPreferredSize(new Dimension(550, 120));
		c.gridx = 0;
		c.gridy = 0;

		add(clientLabel, c);

		clientPanelOne = new JPanel();
		clientPanelOne.setPreferredSize(new Dimension(550, 1));
		clientPanelOne.setLayout(new GridLayout(0, 8, 2, 2));
		clientPanelOne.add(user);
		clientPanelOne.add(userField);
		clientPanelOne.add(server);
		clientPanelOne.add(serverField);
		clientPanelOne.add(cPort);
		clientPanelOne.add(cPortField);
		clientPanelOne.add(connect);
		clientPanelOne.add(cEnd);



		c.gridx = 0;
		c.gridy = 1;

		add(clientPanelOne, c);

		clientPanelTwo = new JPanel();
		clientPanelTwo.setPreferredSize(new Dimension(550, 1));
		clientPanelTwo.setLayout(new GridLayout(0, 5, 2, 2));
		clientPanelTwo.add(clientNewGame);
		clientPanelTwo.add(sendGame);
		clientPanelTwo.add(receiveGame);
		clientPanelTwo.add(sendDate);
		clientPanelTwo.add(play);



		c.gridx = 0;
		c.gridy = 2;

		add(clientPanelTwo, c);

		//panel3 = new JPanel();
		clientScrollPane.setPreferredSize(new Dimension(550, 140));
		c.gridx = 0;
		c.gridy = 3;
		add(clientScrollPane, c);
		
		connect.addActionListener(actionListener);
		cEnd.addActionListener(actionListener);
		userField.addActionListener(actionListener);
		serverField.addActionListener(actionListener);
		cPortField.addActionListener(actionListener);
		clientNewGame.addActionListener(actionListener);
		sendGame.addActionListener(actionListener);
		receiveGame.addActionListener(actionListener);
		sendDate.addActionListener(actionListener);
		play.addActionListener(actionListener);


		this.setSize(600, 400);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setTitle("Ajithyugan Jeyakumar's A3 GameClient");

		connectClient();
	}
	
	public void connectClient() {
		try {
			 
            // Creating Socket class object and
            // initializing Socket
            Socket soc = new Socket("localhost", 6666);
 
            DataOutputStream d = new DataOutputStream(
                soc.getOutputStream());
 
            // Message to be displayed
            d.writeUTF("Hello client");
 
            // Flushing out internal buffers,
            // optimizing for better performance
            d.flush();
 
            // Closing the connections
 
            // Closing DataOutputStream
            d.close();
            // Closing socket
            soc.close();
        }
 
        // Catch block to handle exceptions
        catch (Exception e) {
 
            // Print the exception on the console
            System.out.println(e);
        }
	}
	public static void main(String arg[]) /* throws IOException */{

		new GameClient();


		/*
		 * Socket sock = new Socket(arg[0], (Integer.valueOf(arg[1])).intValue());
		 * BufferedReader br1 = new BufferedReader(new
		 * InputStreamReader(sock.getInputStream())); PrintStream dat = new
		 * PrintStream(sock.getOutputStream()); String strcliid = br1.readLine();
		 * System.out.println("Client n." + strcliid + " in server."); String
		 * consoleData; String serverData; BufferedReader br2 = new BufferedReader(new
		 * InputStreamReader(System.in)); try { System.out.print("Client[" + strcliid +
		 * "]: "); consoleData = br2.readLine(); while (!consoleData.equals(END)) {
		 * consoleData = strcliid + "#" + consoleData; dat.println(consoleData);
		 * dat.flush(); serverData = br1.readLine(); System.out.println("Server: " +
		 * serverData); System.out.print("Client[" + strcliid + "]: "); consoleData =
		 * br2.readLine(); } consoleData = strcliid + "#" + consoleData;
		 * dat.println(consoleData); dat.flush(); sock.close(); } catch (Exception e) {
		 * System.out.println(e); }
		 */
	}


		// I have to put all listener here 

		
	

	class clientController implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object eventObject = e.getSource();
			if (eventObject== cEnd)
			{
				System.out.print("\n AJITH");
				System.exit(0);

			} 
		}

	}
}
