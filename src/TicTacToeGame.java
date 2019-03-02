import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class TicTacToeGame extends JFrame{


	private static final int GRIDSIZE = 3;
	private int filledSquares = 0;
	private int inLine = 0;
	private boolean win = false;
	private int player1 = 0;
	private int player2 = 0;
	private Space[][] terrain = new Space[GRIDSIZE][GRIDSIZE];
	
	
	public TicTacToeGame () {
		initGUI();
		setTitle("Game Window");
		setSize(400, 200);
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void initGUI() {
		JLabel titleLabel = new JLabel("Tic Tac Toe");
		Font titleFont = new Font (Font.SERIF, Font.BOLD, 27);
		titleLabel.setFont(titleFont);
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBackground(new Color(39, 75, 132));
		//Old Background Color: titleLabel.setBackground(new Color(29,62,114));
		titleLabel.setOpaque(true);
		add(titleLabel, BorderLayout.PAGE_START);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(GRIDSIZE,GRIDSIZE));
		add(centerPanel, BorderLayout.CENTER);
		for(int r = 0; r < GRIDSIZE; r++) {
			for(int c = 0; c < GRIDSIZE; c++) {
				terrain[r][c] = new Space(r, c);
				terrain[r][c].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Space button = (Space) e.getSource();
						int row = button.getRow();
						int col = button.getCol();
						clickedSpace(row, col);
					}
				});
				centerPanel.add(terrain[r][c]);
			}
		}
	}
	
	private String playerTurn () {
		if (filledSquares % 2 == 0) {
			return "X";
		}else{
			return "O";
		}
	}
	
	private void clickedSpace(int row, int col) {
		if (terrain[row][col].isFilled() == false ) {
			terrain[row][col].filled(true, playerTurn());
			checkNeighbors (row, col);
			filledSquares++;
			
		}
		if (win == true) {
			String winner = null;
			if (playerTurn().equals("X")) {
				player1++;
				winner = "Player 1";
			}else {
				player2++;
				winner = "Player 2";
			}
			String message = winner + ", you won this round! Would you like to play again?";
			promptForNewGame(message);
		}else {
			if (filledSquares == GRIDSIZE*GRIDSIZE) {
				String message = "It is a tie. Would you like to play again?";
				promptForNewGame(message);
			}
		}
		
	}
	
	private void checkNeighbors (int row, int col) {
		check (row , col-2);
		check (row , col-1);
		check (row , col+1);
		check (row , col +2);
		if (inLine == 2) {
			win = true;
		}
		inLine = 0;
		check (row-2 , col);
		check (row-1 , col);
		check (row+1 , col);
		check (row+2 , col);
		if (inLine == 2) {
			win = true;
		}
		inLine = 0;
		check (row-2 , col-2);
		check (row-1 , col-1);
		check (row+1 , col+1);
		check (row+2 , col+2);
		if (inLine == 2) {
			win = true;
		}
		inLine = 0;
		check (row-2 , col+2);
		check (row-1 , col+1);
		check (row+1 , col-1);
		check (row+2 , col-2);
		if (inLine == 2) {
			win = true;
		}
		inLine = 0;
	}
	
	private void check(int row, int col) {
		if ( row > -1 && row < GRIDSIZE && col > -1 && col < GRIDSIZE &&
				terrain[row][col].isFilled() == true && playerTurn() == terrain[row][col].tileClicker()) {
			inLine++;
		}
	}

	private void promptForNewGame(String message) {
		int option = JOptionPane.showConfirmDialog(this, "Player 1 has:  " + player1 + "\nPlayer 2 has:  " 
				+ player2 + "\n" + message, "Play Again?", JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION) {
			newGame();
		}else {
			System.exit(10);
		}
	}
	
	private void newGame() {
		for (int y = 0; y < GRIDSIZE; y++) {
			for (int x = 0; x < GRIDSIZE; x++) {
				terrain [x][y].reset();
				win = false;
				
			}
		}
		filledSquares = 0;
	}
	
	public static void main(String[] args) {
		try {
            //UI = user interface
			String className = UIManager.getCrossPlatformLookAndFeelClassName();
            UIManager.setLookAndFeel(className);
        } catch ( Exception e) {}
        
        EventQueue.invokeLater(new Runnable (){
            @Override
            public void run() {
                new TicTacToeGame();
            }   
        });

	}

}
