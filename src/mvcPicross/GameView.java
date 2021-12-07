package mvcPicross;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import mvcPicross.GameController.Controller;

public class GameView extends JFrame{


	public JPanel panalOne;
	public JPanel panalOneOne;
	public JPanel panalThree;
	public JPanel panalThreeThree;
	public  int i=0;
	public  Timer timer;
	public JButton[][] squares;
	public JButton reset;
	public JLabel[] row; 
	public JLabel[] col;
	public JLabel time;
	public JLabel points;
	public JCheckBox myBox;
	public JTextField timeField;
	public JTextField pointsField;
	public JTextArea jTextArea;
	public int myTotalPoints =0;
	public static final int DEFAULTGAME	=1;
	public  Color errorColor=Color.RED;
	public  Color correctColor=Color.GREEN;
	public Color  markColor=Color.YELLOW;
	public JScrollPane jScrollPane;
	JMenuBar menuBar;
	JMenu game; 
	JMenu help ;
	Icon newGameImage; 
	JMenuItem newGame; 
	Icon solutionImage; 
	JMenuItem solution; 
	Icon exitImage;
	JMenuItem exit;
	Icon colourImage;
	JMenuItem colors;
	Icon aboutImage; 
	JMenuItem about; 
	Font myFont;
	
	
	public void setRowLabel(int DIMENSION, GameModel model) {
		
		try {
			for(int i=0; i <DIMENSION ;i++) {
				int count=0;
				String str="";
				for(int j =0; j<DIMENSION; j++) {
					if(model.getMyBox(i,j)==1) {
						count++;
					}
					if(j+1 <DIMENSION) {
						if(model.getMyBox(i,j)==1 && model.getMyBox(i,j+1)==0 ) {
							
							str=str+count+",";
							
							count=0;
						}
					}				
					
				}
				if(str=="") {
					row[i].setText(count+"");
				}else {
					if(count > 0) {
						row[i].setText(str+count);
					}else {
						row[i].setText(str);
					}
					
				}
				
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void setColumLabel(int DIMENSION,GameModel model) {
		
		try {
			for(int i=0; i <DIMENSION ;i++) {
				int count=0;
				String str="";
				for(int j =0; j<DIMENSION; j++) {
					if(model.getMyBox(j,i)==1) {
						count++;
					}
					if(j+1 <DIMENSION) {
						if(model.getMyBox(j,i)==1 && model.getMyBox(j+1,i)==0 ) {
							str=str+count+",";
							count=0;
						}
					}		
					
				}
				if(str=="") {
					col[i].setText(count+"");
				}else {
					if(count > 0) {
						col[i].setText(str+count);
					}else {
						col[i].setText(str);
					}
					
				}
				
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	GameView() {
		panalOne = new JPanel();
		panalOneOne = new JPanel();
		panalThree = new JPanel();
		panalThreeThree = new JPanel();
		squares = new JButton[5][5];
		 reset = new JButton("Reset");
		 row = new JLabel[5];
		 col = new JLabel[5];
		 time = new JLabel("Time : ");
		 points = new JLabel("Points : ");
		 myBox = new JCheckBox(" Mark ");
		 timeField = new JTextField();
		pointsField = new JTextField();
		 jTextArea = new JTextArea();
		myTotalPoints =0;
		jScrollPane = new JScrollPane(jTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		menuBar = new JMenuBar();
		 game = new JMenu("Game");
		 help = new JMenu("Help");
		newGameImage = new ImageIcon("piciconnew.gif");
		 newGame = new JMenuItem("New",newGameImage);
		solutionImage = new ImageIcon("piciconsol.gif");
		 solution = new JMenuItem("Solution",solutionImage);
		 exitImage = new ImageIcon("piciconext.gif");
		exit = new JMenuItem("Exit",exitImage);
		colourImage = new ImageIcon("piciconcol.gif");
		colors = new JMenuItem("Colors",colourImage);
		aboutImage = new ImageIcon("piciconabt.gif");
		about = new JMenuItem("About",aboutImage);
		myFont = new Font("Serif", Font.TRUETYPE_FONT, 12);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(2, 2, 2, 2);

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 3.0;
		c.weighty = 3.0;

		JPanel markPanel = new JPanel();
		markPanel.setPreferredSize(new Dimension(100, 100));
		markPanel.setLayout(new BorderLayout());
		myBox.setPreferredSize(new Dimension(50, 50));

		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 1;
		c.ipady = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(markPanel, c);
		markPanel.add(myBox, BorderLayout.SOUTH);

		JPanel topPanel = new JPanel();
		topPanel.setSize(new Dimension(500, 100));
		topPanel.setLayout(new GridLayout(0, 5, 2, 2));
		c.gridx = 1;
		c.gridy = 0;
		c.ipadx = 1;
		c.ipady = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(topPanel, c);
		for (int i = 0; i < 5; i++) {
			int j = i + 1;
			int num = j;
			col[i] = new JLabel();
			col[i].setSize(new Dimension(5, 5));
			col[i].setHorizontalAlignment(JLabel.CENTER);
			col[i].setOpaque(true);
			topPanel.add(col[i]);
		}


		JPanel controlPanel = new JPanel();
		controlPanel.setPreferredSize(new Dimension(200, 600));
		controlPanel.setLayout(new GridBagLayout());
		GridBagConstraints c1 = new GridBagConstraints();
		c1.insets = new Insets(2, 2, 2, 2);

		c.fill = GridBagConstraints.BOTH;
		c1.weightx = 1.0;
		c1.weighty = 1.0;
		c1.insets = new Insets(2, 2, 2, 2);

		c.gridx = 2;
		c.gridy = 0;
		c.ipadx = 1;
		c.ipady = 1;
		c.gridwidth = 2;
		c.gridheight = 2;
		add(controlPanel, c);

		Icon image = new ImageIcon("piccrossNameMin.jpg");
		JLabel imageLabel = new JLabel(image);
		imageLabel.setOpaque(false);
		c1.fill = GridBagConstraints.BOTH;
		panalOne.setPreferredSize(new Dimension(200, 25));
		panalOne.add((imageLabel));

		c1.gridx = 0;
		c1.gridy = 0;

		controlPanel.add(panalOne, c1);

		c1.fill = GridBagConstraints.BOTH;
		panalOneOne.setSize(new Dimension(100, 50));
		panalOneOne.setLayout(new GridLayout(0, 2, 2, 2));
		timeField.setFont(new Font(" ", Font.CENTER_BASELINE, 10));
		panalOneOne.add(points);
		panalOneOne.add(pointsField);
	//	pointsField.setText(" " +getMyPoints());
		c1.gridx = 0;
		c1.gridy = 1;
		c1.ipadx = 1;
		c1.ipady = 1;
		controlPanel.add(panalOneOne, c1);

		c1.fill = GridBagConstraints.BOTH;
		jScrollPane.setPreferredSize(new Dimension(200, 300));
		c1.gridx = 0;
		c1.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 2;
		controlPanel.add(jScrollPane, c1);

		c1.fill = GridBagConstraints.BOTH;
		panalThreeThree.setSize(new Dimension(200, 75));
		panalThreeThree.setLayout(new GridLayout(0, 2, 2, 2));
		panalThreeThree.add(time);
		panalThreeThree.add(timeField);
		c1.gridx = 0;
		c1.gridy = 3;
		controlPanel.add(panalThreeThree, c1);

		c1.fill = GridBagConstraints.BOTH;
		panalThree.setSize(new Dimension(200, 150));
		panalThree.setBackground(Color.ORANGE);
		panalThree.setLayout(new GridLayout(1, 0, 2, 2));
		panalThree.add(reset);
		c1.gridx = 0;
		c1.gridy = 4;
		c.gridwidth = 1;
		c.gridheight = 1;
		controlPanel.add(panalThree, c1);

		JPanel leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(100, 500));
		leftPanel.setLayout(new GridLayout(5, 0, 2, 2));
		c.gridx = 0;
		c.gridy = 1;
		c.ipadx = 1;
		c.ipady = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(leftPanel, c);
		for (int i = 0; i < 5; i++) {
			int j = i + 1;
			int num = j++;
			row[i] = new JLabel();
			row[i].setHorizontalAlignment(JLabel.CENTER);
			row[i].setSize(new Dimension(5, 5));
			row[i].setOpaque(true);
			leftPanel.add(row[i]);
		}


		JPanel boardPanal = new JPanel();
		boardPanal.setPreferredSize(new Dimension(500, 500));
		boardPanal.setLayout(new GridLayout(5, 5, 2, 2));
		c.gridx = 1;
		c.gridy = 1;
		c.ipadx = 1;
		c.ipady = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(boardPanal, c);

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				squares[i][j] = new JButton();
				squares[i][j].setPreferredSize(new Dimension(100, 100));
				squares[i][j].setOpaque(true);
				squares[i][j].setBackground(Color.WHITE);
				boardPanal.add(squares[i][j]);
			}
		}
		menuBar.add(game);
		game.getFontMetrics(myFont);
		game.add(newGame);
		game.add(solution);
		game.add(exit);


		game.setMnemonic('G');
		menuBar.add(help);
		help.setMnemonic('H');
		help.add(colors);
		help.add(about);




		this.setSize(850, 700);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setJMenuBar(menuBar);
		this.setTitle("Ajithyugan Jeyakumar's A2 with MVC");
		
		timer=new Timer();
		
		setTimer();
	}
	
	public void updateBoard(GameModel model) {
		try {
			
			i=0;
			timer=new Timer();
			
			setTimer();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void setBoardLabelConfiguration(GameModel model) {
		
	}
	
	public void newGameReset(GameModel model) {
			for(int i=0; i<model.DIMENSION; i++) {
				
				for(int j=0; j <model.DIMENSION; j++) {
					
						this.squares[i][j].setBackground(Color.WHITE);
					
					this.squares[i][j].setEnabled(true);
				}
			}
			this.reset.setEnabled(true);
			this.myBox.setEnabled(true);
	}
	
	public void showSolution(GameModel model) {
		
		for(int i=0; i<model.DIMENSION; i++) {
			
			for(int j=0; j <model.DIMENSION; j++) {
				if(model.getMyBox(i, j)==1) {
					this.squares[i][j].setBackground(correctColor);
				}else {
					this.squares[i][j].setBackground(markColor);
				}
				this.squares[i][j].setEnabled(false);
			}
		}
		this.reset.setEnabled(false);
		this.myBox.setEnabled(false);
	}
	
	public void cancelTimer() {
		timer.cancel();
		stopTimer();
	}
	
	public void stopTimer() {
		
		i=0;
		timeField.setText("");
		
	}
	

	
	public  void setTimer() {
		try {
			timer.scheduleAtFixedRate(new TimerTask() {	
				@Override
				public void run() {	
					timeField.setText(i+" seconds");
					i++;
					
				}
			}, 0, 1000);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	
	void addMyListener( ActionListener actionListener) {
		
		// I have to put all listener here 
		reset.addActionListener(actionListener);
		myBox.addActionListener(actionListener);
		for (int i = 0; i < 5; i++) {for (int j = 0; j < 5; j++) {
		squares[i][j].addActionListener(actionListener);}}
		solution.addActionListener(actionListener);
		newGame.addActionListener(actionListener);
		colors.addActionListener(actionListener);
		exit.addActionListener(actionListener);
		
	}
	void gameDefault() {
		
//		 board=new int[][]{ {0,0,1,0,0}, {0,0,1,0,0}, {1,1,1,1,1}, {0,1,1,1,0},
//			{0,1,0,1,0},};
	}
	
	void serverAPI() {
		
	}
	
	void addText(ActionListener actionListener ) {
		GameModel gameModel = new GameModel();
		GameView  gameView = new GameView();
		GameController controller = new GameController();
		pointsField.setText("" + gameModel.getMyPoints());

	}
}
