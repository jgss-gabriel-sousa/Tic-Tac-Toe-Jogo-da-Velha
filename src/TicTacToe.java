import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

public class TicTacToe extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton[] btn = new JButton[9];
	JLabel[] results = new JLabel[3];
	
	boolean xo = false;
	boolean[] clicked = new boolean[9];
	int xWins = 0;
	int oWins = 0;
	int draws = 0;
	
	public TicTacToe() {
		setVisible(true);
		setTitle("Tic-Tac-Toe");
		setDefaultCloseOperation(3);
		setLayout(null);
		setBounds(250,100,500,500);
		
		final int rows = 3;
		final int rowLength = 3;
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < rowLength; j++) {
				final int id = (i*3)+j;
				
				btn[id] = new JButton();
				add(btn[id]);
				
				btn[id].setBounds((100*i)+50, (100*j)+50, 95, 95);
				btn[id].setFont(new Font("Arial", Font.BOLD, 40));
				btn[id].setBackground(Color.GRAY);
				btn[id].setForeground(Color.WHITE);
				btn[id].setBorder(BorderFactory.createBevelBorder(0));
			}
		}
		
		for(int i = 0; i < results.length; i++) {
			results[i] = new JLabel();
			add(results[i]);
			
			results[i].setFont(new Font("Arial", 50, 20));
			results[i].setSize(300,100);
			results[i].setLocation(375,(50*i)+10);
		}
		updateResults();

		final int cells = rows*rowLength;
		for(int i = 0; i < cells; i++) {
			final int id = i;
			btn[id].addActionListener(new java.awt.event.ActionListener() {
				@Override				
				public void actionPerformed(ActionEvent ae) {
					if(!clicked[id]) {
						clicked[id] = true;
						changeCellState(btn[id]);
					}
				}
			});
		}
	}
	
	public void changeCellState(JButton btn) {
		if(xo) {
			btn.setText("O");
			xo = false;
		}else{
			btn.setText("X");
			xo = true;
		}
		
		winCheck();
	}
	
	public void winCheck() {
		final int winCond[][] = {
           {0,1,2},
           {3,4,5},
           {6,7,8},
           {0,3,6},
           {1,4,7},
           {2,5,8},
           {0,4,8},
           {6,4,2},
		};

		boolean win = false;
		
		for(int i = 0; i < winCond.length; i++) {			
			if(btn[winCond[i][0]].getText() ==  "X" && btn[winCond[i][1]].getText() == "X" && btn[winCond[i][2]].getText() == "X") {
				win = true;
				highlightBtn(winCond[i]);
				JOptionPane.showMessageDialog(null, "X wins!!!");
				xWins++;
				resetGame();
				break;
			}
		}
		//Check O Win
		if(!win) {
			for(int i = 0; i < winCond.length; i++) {			
				if(btn[winCond[i][0]].getText() == "O" && btn[winCond[i][1]].getText() == "O" && btn[winCond[i][2]].getText() == "O") {
					win = true;
					highlightBtn(winCond[i]);
					JOptionPane.showMessageDialog(null, "O wins!!!");
					oWins++;
					resetGame();
					break;
				}
			}
		}
		//Check Draw
		if(!win) {
			int c = 0;
			for(int i = 0; i < clicked.length; i++) {
				if(clicked[i]) {
					c++;
				}
			}
			
			if(c == clicked.length) {
				JOptionPane.showMessageDialog(null, "Draw");
				draws++;
				resetGame();
			}
		}
	}
	
	public void resetGame() {
		for(int i = 0; i < btn.length; i++) {
			btn[i].setText("");
			btn[i].setForeground(Color.WHITE);
			clicked[i] = false;
		}
		updateResults();
	}
	
	public void highlightBtn(int ids[]) {
		for(int i = 0; i < ids.length; i++) {
			btn[ids[i]].setForeground(Color.RED);
		}
	}
	
	public void updateResults() {
		results[0].setText("X Wins: "+xWins);
		results[1].setText("O Wins: "+oWins);
		results[2].setText(" Draws: "+draws);
	}
	
	public static void main(String[] args) {
		new TicTacToe();
	}
}