package mvcPicross;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.*;

public class Game {

	static Game game = new Game();
	public static void main(String arg[]) {

		  if (arg == null) {
			  showMessage();
		  } else if (arg.length == 0) {
			  showMessage();
		  } else if (arg[0].equals("A")) {
			  GamePicross.main(null);
		  }else if (arg[0].equals("S")) {
			  GameServer.main(null);
		  }else if (arg[0].equals("C")) {
			  GameClient.main(null);
		  } else {
			  showMessage();
		  }
		 

	}
	static void showMessage() {
		JOptionPane.showMessageDialog(null, "Syntax:"
				+ "\nGame A: standalone execution (GamePicross)"
				+ "\nGame S: starts server (GameServer)"
				+ "\nGame C: starts client (GameClient)");

	}


}
