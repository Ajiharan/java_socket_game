package mvcPicross;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GameController {

	private GameView gameView;
	private GameModel gameModel;
	private List<String> Correctmap=new ArrayList<>();
	private List<String> ErrorMap=new ArrayList<>();
	private List<String> MarkMap=new ArrayList<>();

	public GameController( ) {

	}

	public GameController(GameView gameView , GameModel gameModel ) {
		this.gameView = gameView;
		this.gameModel = gameModel;

	}
	public void start() {
		gameModel.defaultGame();
		gameView.setColumLabel(gameModel.DIMENSION, gameModel);
		gameView.setRowLabel(gameModel.DIMENSION, gameModel);
		
		gameView.setVisible(true);
		gameView.addMyListener(new Controller());

	}


	

	private Object eventObject;
	//public Object getObject () {return eventObject;}
	public void setObject(Object eventObject) {this.eventObject=eventObject;}
	
	public void resetGame() {
		Correctmap=new ArrayList<>();
		ErrorMap=new ArrayList<>();
		MarkMap=new ArrayList<>();
		
		for(int i =0; i <5 ;i++) {
			for(int  j =0; j < 5; j++) {
				gameView.squares[i][j].setBackground(Color.WHITE);
				gameView.squares[i][j].setEnabled(true);
			}
		}
		gameView.pointsField.setText("");
		gameView.myTotalPoints=0;
		gameView.stopTimer();
	}
	
	public void setColorInButton(int i,int j) {
		
		if(gameView.myBox.isSelected()) {
			if(gameModel.getMyBox(i, j)==0) {
				gameView.myTotalPoints = gameView.myTotalPoints + 1;
				gameView.squares[i][j].setBackground(gameView.markColor);
				MarkMap.add(i+","+j);
				
			}else {
				
				gameView.myTotalPoints = gameView.myTotalPoints - 1;
				gameView.squares[i][j].setBackground(gameView.errorColor);
				ErrorMap.add(i+","+j);
			}
		}else {
			if(gameModel.getMyBox(i, j)==1) {
				gameView.myTotalPoints = gameView.myTotalPoints + 1;
				gameView.squares[i][j].setBackground(gameView.correctColor);
				Correctmap.add(i+","+j);
			}else {
				
				gameView.myTotalPoints = gameView.myTotalPoints - 1;
				gameView.squares[i][j].setBackground(gameView.errorColor);
				ErrorMap.add(i+","+j);
			}
		}
		if (gameView.myTotalPoints <= 0) {
			gameView.myTotalPoints = gameView.myTotalPoints*0;
			
		}
	}

	public void gameGame(int i, int j) {
		
		setColorInButton(i,j);
				
		
//		System.out.println(i + " == " + j);
//		System.out.println(" myTotalPoints : " + gameView.myTotalPoints);
		gameView.pointsField.setText("" + gameView.myTotalPoints);
		gameView.squares[i][j].setEnabled(false);

	}
	
	public void resetColor() {
		Correctmap.forEach((e)->{
			StringTokenizer st = new StringTokenizer(e, ",");
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			gameView.squares[i][j].setBackground(gameView.correctColor);
			
		});
		ErrorMap.forEach((e)->{
			StringTokenizer st = new StringTokenizer(e, ",");
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			gameView.squares[i][j].setBackground(gameView.errorColor);
			
		});
		MarkMap.forEach((e)->{
			StringTokenizer st = new StringTokenizer(e, ",");
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			gameView.squares[i][j].setBackground(gameView.markColor);
			
		});
	}
	
	
	class Controller implements ActionListener {
		private ActionEvent e;
		public ActionEvent getActionEvent () {return e;}
		public void setActionEvent(ActionEvent e) {this.e=e;}


		@Override
		public void actionPerformed(ActionEvent e) {
			
			// TODO Auto-generated method stub
			int i = 0;
			int j = 0;
			Object eventObject = e.getSource();
			

			
			if (eventObject == gameView.reset) {

				gameView.jTextArea.setText("");
				gameView.jTextArea.append("Mark " + gameView.reset.getText() + "\n");
				resetGame();
			}
			else if (eventObject == gameView.myBox) {
				if (gameView.myBox.isSelected() == true)
					gameView.jTextArea.append(gameView.myBox.getText() + " Selected\n");
				else
					gameView.jTextArea.append(gameView.myBox.getText() + " Unselected\n");

			} else if(eventObject==gameView.solution) {
				gameView.cancelTimer();
				gameView.showSolution(gameModel);
				gameView.jTextArea.setText("The Solution Positions are: \n");
				       for (int x = 0; x < gameModel.board.length; x++) {
				        gameView.jTextArea.append(Arrays.toString(gameModel.board[x])+"\n");
				       }
				       
			}else if(eventObject==gameView.newGame) {
				gameModel.NewGame();
				gameModel.defaultGame();
				gameView.timer.cancel();
				gameView.updateBoard(gameModel);
				gameView.setColumLabel(gameModel.DIMENSION, gameModel);
				gameView.setRowLabel(gameModel.DIMENSION, gameModel);
				gameView.newGameReset(gameModel);
				
				Correctmap= new ArrayList<>();
				ErrorMap=new ArrayList<>();
				MarkMap=new ArrayList<>();
			} else if (eventObject==gameView.exit)
			{
				System.exit(0);
				
			} 
			else if(eventObject==gameView.colors) {
//				
				JFrame frame= new JFrame();
			    frame.setTitle("Colour model");
			    frame.setSize(400, 100);
			   
			    frame.setResizable(false);
			    JPanel errorPanel = new JPanel();
			    JPanel correctPanel = new JPanel();
			    JPanel markPanel = new JPanel();
			    JButton errorButton=new JButton("error");
			    JButton correctButton=new JButton("correct");
			    JButton markButton=new JButton("mark");
			    
			    errorPanel.setBackground(gameView.errorColor);
			    correctPanel.setBackground(gameView.correctColor);
			    markPanel.setBackground(gameView.markColor);

			    
			    
			    errorButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						gameView.errorColor=JColorChooser.showDialog(null,"Choose",Color.RED); 
						resetColor();
						
					}
				});

			    correctButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						gameView.correctColor=JColorChooser.showDialog(null,"Choose",Color.GREEN);  
						resetColor();
						
					}
				});
			    
			    markButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						gameView.markColor=JColorChooser.showDialog(null,"Choose",Color.YELLOW);  
						resetColor();
						
						
					}
				});


			    JPanel panel = new JPanel();  
		        panel.setLayout(new FlowLayout()); 
		        panel.setLayout(new GridLayout(2, 3, 2, 2));
		        panel.add(correctPanel);
		        panel.add(errorPanel);
		        panel.add(markPanel);

		        panel.add(correctButton);
		        panel.add(errorButton);
		        panel.add(markButton);
		        frame.add(panel);  

			    frame.setVisible(true);
			}
			else {
				for (i = 0; i < gameModel.DIMENSION; i++) {
					for (j = 0; j < gameModel.DIMENSION; j++) {
						if (eventObject == gameView.squares[i][j]) {
							//System.out.println(" eventObject -"+gameView.squares[i][j].getText());
							gameView.jTextArea.append("Pos "+gameView.squares[i][j].getText() + " " + i + " " + j + " clicked\n");
							
							gameGame(i, j);

						}

					}
				}
			}

		}

	}
}
