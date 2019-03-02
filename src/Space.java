import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.Color;

public class Space extends JButton{
	private final static int SIZE = 50;
	private int row = 0;
	private int col = 0;
	private String player = null;
	private boolean filled = false;
	
	
	public Space (int r, int c) {
		row = r;
		col = c;
		filled = false;
		Dimension size = new Dimension (SIZE, SIZE);
		setPreferredSize(size);		
	}
	
	public void filled(boolean f, String p) {
		filled = f;
		player = p;
		setText(p);
		if (p == "X") {
			setBackground(Color.RED);
		}else if (p == "O") {
			setBackground(Color.BLUE);
		}
	}
	
	public String tileClicker () {
		return player;
	}
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public boolean isFilled() {
		return filled;
	}
	
	public void reset() {
		filled = false;
		setText(null);
		setBackground(null);
	}
}
