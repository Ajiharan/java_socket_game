package mvcPicross;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

public class GamePicross extends JWindow {
	 private static final long serialVersionUID = 6248477390124803341L;
	  private final int duration;
	  private GameController controller;
	  public GamePicross(int duration) {
		    this.duration = duration;
	 }
	  
	  public void showSplashWindow() {

		     JPanel content = new JPanel(new BorderLayout());
		     content.setBackground(Color.GRAY);
		    
		    int width =  630;
		    int height = 300;
		    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		    int x = (screen.width-width)/2;
		    int y = (screen.height-height)/2;
		    setBounds(x,y,width,height);

		    /** label is a Object of the JLabel class We will ad our jpg file on this label*/
		    JLabel label = new JLabel(new ImageIcon("piccrossLogo.jpg")); 
		  
		    JLabel demo = new JLabel("Name: Ajithyugan Jeyakumar SID: 040997743", JLabel.CENTER);
		    demo.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 18));
		    content.add(label, BorderLayout.CENTER);
		    content.add(demo, BorderLayout.SOUTH);
		    Color customColor = Color.BLACK;
		    content.setBorder(BorderFactory.createLineBorder(customColor, 20));
		    
		    setContentPane(content);
		    setVisible(true);

		    try {
		    	 Thread.sleep(duration); }
		    catch (InterruptedException e) {
		    	e.printStackTrace();
		    	}

		    dispose(); 
		  }
	  
	  
	  public  void startGame() {
		  int duration = 4000;
		   
		    	try{
		    	 duration = 2000;
		    	}catch (NumberFormatException nfe){
		    	  System.out.println("Wrong command line argument: must be an integer number");
		    	  System.out.println("The default duration 10000 milliseconds will be used");
		    	  nfe.printStackTrace();	
		    	} 
		    
		    
		    GamePicross splashWindow = new GamePicross(duration);
		 splashWindow.showSplashWindow();
			// TODO Auto-generated method stub
			exc();
	  }
	public static void main(String[] args) {
//		startGame();
	}
	  
	  public GameController getGameController() {
		  return this.controller;
	  }
	public  void exc() {
		GameModel gameModel = new GameModel();
		GameView gameView = new GameView();
		controller = new GameController(gameView, gameModel);
		((GameController) controller).start();
	}

}
