package mvcPicross;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GameServer extends JFrame implements Runnable  {

	Socket sock;
	static int nclient = 0, nclients = 0;
	static Thread thread;
	static ServerSocket servsock;
	
	private String valStr="";
	JPanel panel1, panel2, panel3;
	JLabel serverLabel;
	JTextArea serverTextArea = new JTextArea();
	// Icon serverIcon = new ImageIcon("piccorssLogoServer.png");

	JScrollPane serverScrollPane = new JScrollPane(serverTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

	static final String END = "end";
	
	JLabel port;
	JTextField portField;
		
	JButton execute;
	JButton results;
	JCheckBox finalize;
	JButton end;
	private boolean isFinalize=false;

	GameServer() {
		port = new JLabel("Port:");
		portField = new JTextField();
			
		 execute = new JButton("Execute");
		 results = new JButton("Results");
		finalize = new JCheckBox("Finalize");
		 end = new JButton("End");

		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(2, 2, 2, 2);

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.0;
		c.weighty = 2.0;

		serverLabel = new JLabel(new ImageIcon("piccorssLogoServer.png"));
		serverLabel.setPreferredSize(new Dimension(550, 140));
		c.gridx = 0;
		c.gridy = 0;

		add(serverLabel, c);

		panel2 = new JPanel();
		panel2.setSize(new Dimension(550, 60));
		panel2.setLayout(new GridLayout(0, 6, 2, 2));
		panel2.add(port);
		panel2.add(portField);
		panel2.add(execute);
		panel2.add(results);
		panel2.add(finalize);
		panel2.add(end);

		c.gridx = 0;
		c.gridy = 1;

		add(panel2, c);

		//panel3 = new JPanel();
		serverScrollPane.setPreferredSize(new Dimension(550, 160));
		c.gridx = 0;
		c.gridy = 2;
		add(serverScrollPane, c);
		//panel3.setBackground(Color.black);

		this.setSize(600, 400);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Ajithyugan Jeyakumar's A3 GameServer");
		this.execute.addActionListener(new serverController());
		this.finalize.addActionListener(new serverController());
					
	}
	
	



	public static void main(String arg[]) /* throws IOException */ {

		GameServer server=new GameServer();
		thread=new Thread(server);
		
		/*
		 * int port = (Integer.valueOf(arg[0])).intValue(); servsock = new
		 * ServerSocket(port); Thread servDaemon = new Thread(new GameServer());
		 * servDaemon.start(); System.out.println("Server alive on " +
		 * InetAddress.getLocalHost() + " in port " + port + "!");
		 */
	}


	public void run() {
			
			this.serverTextArea.setText("Exec button..."+"\n"+"port = "+Integer.parseInt(portField.getText())+"\n"+"waiting for clients to connect");
			System.out.println("server started"+Integer.parseInt(portField.getText()));
			try {
				servsock=new ServerSocket(Integer.parseInt(portField.getText()));
			}catch(Exception e) {
				System.out.println(e);
			}
			while(true){
//				if(isFinalize) {
//					try {
//						servsock.close();
//						break;
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
					System.out.println(isFinalize);
					try {
						sock = servsock.accept();
						nclient += 1;
						nclients += 1;
						System.out.println("Connecting " + sock.getInetAddress() + " in port " + sock.getPort() + ".");
						System.out.println("servsock"+servsock.getLocalPort());
						this.serverTextArea.setText(this.serverTextArea.getText()+"\n"+"Current number of clients: " + nclients);
					} catch (IOException ioe) {
						System.out.println(ioe);
					}
					Worked w = new Worked(sock, nclient);
					w.start();
				
				
			}
		
	}
	
	
	class Worked extends Thread {
		Socket sockNew;
		int clientid, markPosition,lastMarkPosition;
		String strcliid;

		public Worked(Socket s, int nclient) {
			sockNew = s;
			clientid = nclient;
		}

		public void run() {
			String clientData;
			String originalData;
			PrintWriter out = null;
			try {
				out = new PrintWriter(sockNew.getOutputStream(),true);
				BufferedReader br1 = new BufferedReader(new InputStreamReader(sockNew.getInputStream()));
				out.println(clientid);
				clientData = br1.readLine();
				originalData=clientData;

				System.out.println("clientData : "+clientData);
				while (clientData ==null || !clientData.equals(END)) {

					clientData = br1.readLine();

					originalData=clientData;
					if(clientData !=null) {
						
						markPosition = clientData.indexOf("#");
						lastMarkPosition=clientData.lastIndexOf("#");
						
						if(markPosition==lastMarkPosition) {
							strcliid = clientData.substring(0, markPosition);
							clientData = clientData.substring(markPosition + 1, clientData.length());
						}else {
							strcliid = clientData.substring(0, markPosition);
							clientData = clientData.substring(markPosition + 1, lastMarkPosition);
						}
//						System.out.println("markPosition : "+markPosition+" : "+lastMarkPosition);
//						System.out.println("clientData : "+clientData);
						if(clientData.equals("PO")) {
							System.out.println("clientData P0: "+clientData);
							sockNew.close();
							break;
						}
						if(clientData.equals("P1")) {
							System.out.println("clientData P1: "+clientData);
							
							valStr=originalData.substring(lastMarkPosition+1,originalData.length());
							serverTextArea.setText(serverTextArea.getText()+"\n"+"cli"+"["+strcliid+"] "+valStr);
						}
						else if(clientData.equals("P2")) {
							System.out.println("clientData P1: "+clientData);
							serverTextArea.setText(serverTextArea.getText()+"\n"+"cli"+"["+strcliid+"] "+clientData);
							out.println(clientid+"#"+valStr);
							out.flush();
							
						}

					}

				}
				System.out.println("Disconnecting " + sockNew.getInetAddress() + "!");
				nclients -= 1;
				System.out.println("Current number of clients: " + nclients);
				serverTextArea.setText(serverTextArea.getText()+"\n"+"Current number of clients: " + nclients);
				if (nclients == 0) {
					System.out.println("Ending server...");
					sockNew.close();
					servsock.close();
					System.exit(0);
					
				}
			} catch (IOException ioe) {
				System.out.println(ioe);
			}catch(Exception e) {
				System.out.println(e);
			}
		}
	}
	class serverController implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object eventObject = e.getSource();
			
			if(eventObject==execute) {
				thread.start();
			}else if(eventObject==finalize) {
				isFinalize=!isFinalize;
				System.out.println("finalized : "+isFinalize);
				
			}
		}

	}
}
