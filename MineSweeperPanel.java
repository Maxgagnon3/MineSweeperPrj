package project2;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MineSweeperPanel extends JPanel {

	private JButton[][] board;
	private JButton butQuit;
	private Cell iCell;

	private MineSweeperGame game;  // model

	public MineSweeperPanel() {

		JPanel bottom = new JPanel();
		JPanel center = new JPanel();

		// create game, listeners
		ButtonListener listener = new ButtonListener();

		game = new MineSweeperGame();

		// create the board
		center.setLayout(new GridLayout(5, 5));
		board = new JButton[10][10];

		for (int row = 0; row < 10; row++)
			for (int col = 0; col < 10; col++) {
				board[row][col] = new JButton("");
				board[row][col].addActionListener(listener);
				center.add(board[row][col]);
			}

		displayBoard();

		bottom.setLayout(new GridLayout(10, 10));

		// add all to contentPane
		add(new JLabel("!!!!!!  Mine Sweeper  !!!!"), BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		add(bottom, BorderLayout.SOUTH);

	}


	private void displayBoard() {

		for (int r = 0; r < 10; r++)
			for (int c = 0; c < 10; c++) {
				iCell = game.getCell(r, c);

				board[r][c].setText("");

				// readable, ifs are verbose
					
				if (iCell.isMine())
					board[r][c].setText("!");

				if (iCell.isExposed())
					board[r][c].setEnabled(false);
				else
					board[r][c].setEnabled(true);
			}
	}


	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			for (int r = 0; r < 10; r++)
				for (int c = 0; c < 10; c++)
					if (board[r][c] == e.getSource())
						game.select(r, c);

			displayBoard();
								
			if (game.getGameStatus() == GameStatus.Lost) {
				displayBoard();
				JOptionPane.showMessageDialog(null, "You Lose \n The game will reset");
				//exposeMines = false;
				game.reset();
				displayBoard();

			}

			if (game.getGameStatus() == GameStatus.Won) {
				JOptionPane.showMessageDialog(null, "You Win: all mines have been found!\n The game will reset");
				game.reset();
				displayBoard();
			}

		}

	}

}



