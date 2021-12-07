package mvcPicross;
import java.awt.Dimension;
import java.util.Random;
import java.util.StringTokenizer;
public class GameModel {

	private int myPoints = 0;
	StringTokenizer st;
	String valStr  = "00100,00100,11111,01110,01010";
	int board [][];
	public static int DIMENSION = 5;


	/*
	 * public void gameGame(int i, int j) {
	 * 
	 * if (i == 0 && j == 2 || i == 1 && j == 2 || i == 2 && j == 0 || i == 2 && j
	 * == 1 || i == 2 && j == 2 || i == 2 && j == 3 || i == 2 && j == 4 || i == 3 &&
	 * j == 1 || i == 3 && j == 2 || i == 3 && j == 3 || i == 4 && j == 1 || i == 4
	 * && j == 3) myPoints = myPoints + 1; else myPoints = myPoints - 1;
	 * System.out.println(i + " == " + j); System.out.println(" myTotalPoints : " +
	 * myPoints); //pointsField.setText("" + myPoints);
	 * 
	 * }
	 */
	
	public void NewGame() {
		Random random=new Random();
		int randomNumber=random.nextInt(10);
		if(randomNumber >= 1) {

			String newStr="";
			for(int i = 0; i <DIMENSION; i++) {
				int rand=random.nextInt((int) Math.pow(2, DIMENSION)-1);
				if(i==DIMENSION-1) {
					newStr=newStr+Integer.toBinaryString( (1 << DIMENSION) | rand ).substring( 1 );
				}else {
					newStr=newStr+Integer.toBinaryString( (1 << DIMENSION) | rand ).substring( 1 )+",";
				}
							
			}
			System.out.println(newStr);
			this.valStr=newStr;
			
		}
	}
	
	
	public int getMyPoints() {
		return myPoints;
	}
	
	

	public void defaultGame() {

		try {
			int row =0;
			int col =0;
			this.board=new int[DIMENSION][DIMENSION];
			st = new StringTokenizer(valStr, ",");
			while (st.hasMoreTokens()) {
				valStr = st.nextToken();
				for (col = 0; col < DIMENSION; col++) {
					if (valStr.charAt(col) == '0') {
						board[row][col] = 0;
					}		

					else {
						board[row][col] = 1;
					}


				}
				row++;

			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}


	public int getMyBox(int row, int col)
	{	
		int value = board[row][col];
		return value;

	}


	public boolean  validMove( int board [][]) {

		for (int num = 0 ; num < DIMENSION ; num++) {
			for (int num2 = 0; num2 <DIMENSION; num2++) {
				if (board[num][num2] == '1') {
					return true;
				}
			}
		}
		return false;


	}
}
