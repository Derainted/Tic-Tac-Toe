import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

	static String[] board;
	static String turn;

	static String winnerCheck() {
		for (int a = 0; a < 8; a++) {
			String line = null;

			switch (a) {
			case 0:
				line = board[0] + board[1] + board[2];
				break;
			case 1:
				line = board[3] + board[4] + board[5];
				break;
			case 2:
				line = board[6] + board[7] + board[8];
				break;
			case 3:
				line = board[0] + board[3] + board[6];
				break;
			case 4:
				line = board[1] + board[4] + board[7];
				break;
			case 5:
				line = board[2] + board[5] + board[8];
				break;
			case 6:
				line = board[0] + board[4] + board[8];
				break;
			case 7:
				line = board[2] + board[4] + board[6];
				break;
			}

			// Winner X
			if (line.equals("XXX")) {
				return "X";
			}

			// Winner O
			else if (line.equals("OOO")) {
				return "O";
			}
		}

		for (int a = 0; a < 9; a++) {
			if (Arrays.asList(board).contains(String.valueOf(a + 1))) {
				break;
			} else if (a == 8) {
				return "draw";
			}
		}

		// To enter the X Or O at the exact place on board.
		System.out.println(turn + "'s turn; enter a slot number to place " + turn + " in:");
		return null;
	}

	static void printBoard() {
		System.out.println("|---|---|---|");
		System.out.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |");
		System.out.println("|-----------|");
		System.out.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |");
		System.out.println("|-----------|");
		System.out.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |");
		System.out.println("|---|---|---|");
	}
	
	
	// Main method
	public static void main(String[] args) {

//		TicTacToe tictactoe = new TicTacToe();

		boolean isValid;

		do {
			isValid = true;
			runGame();

			Scanner userReply = new Scanner(System.in);
			System.out.println("Play again? (Y/N)");

			String answer = userReply.nextLine().toLowerCase();

			if ("y".equals(answer)) {
				isValid = true;
			} else {
				isValid = false;
				userReply.close();
				System.out.println("Game Exit");

			}

		} while (isValid);

	}

	public static void runGame() {

		Random rand = new Random();
		int StartingPlayer = rand.nextInt(2);

	
		Scanner s = new Scanner(System.in);
		board = new String[9];

		if (StartingPlayer % 2 == 1) {
			turn = "X";
		} else {
			turn = "O";
		}

		String winner = null;

		for (int a = 0; a < 9; a++) {
			board[a] = String.valueOf(a + 1);
		}
		
		System.out.println("|*********************|");
        System.out.println("|**   TIC TAC TOE   **|");
        System.out.println("|*********************|");
		printBoard();
		System.out.println(turn + " starts: Enter box number 1 to 9 " + turn + " in:");

		while (winner == null) {
			int numInput;

			// Error! in case of not valid number 1 to 9"
			try {
				numInput = s.nextInt();
				if (!(numInput > 0 && numInput <= 9)) {
					System.out.println("Invalid input; re-enter slot number:");
					continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input; re-enter slot number:");
				continue;
			}

			// Logic to decide the turn.
			if (board[numInput - 1].equals(String.valueOf(numInput))) {
				board[numInput - 1] = turn;

				if (turn.equals("X")) {
					turn = "O";
				} else {
					turn = "X";
				}

				printBoard();
				winner = winnerCheck();
			} else {
				System.out.println("Slot occupied; re-enter slot number:");
			}
		}

		// To Draw
		
		if (winner.equalsIgnoreCase("draw")) {
			System.out.println("It's a draw! Thank you for playing.");
		}

		// For winner -to display Congratulations! message.
		else {
			System.out.println("Congrats! " + winner + " won!");
		}

	}

}
