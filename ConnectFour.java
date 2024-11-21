/*
 * This class allows two players to play one or more games of Tic Tac Toe. It controls
 * the flow of the games. Initially it creates two players and it makes a new board for
 * each new game.
 */

import java.util.Scanner;
public class ConnectFour
{
    // instance variables
    private Player p1;
    private Player p2;
    private Board board;
    private BoardViewer viewer;
    
    private int gameNumber;


    // Constructor
    public ConnectFour()
    {
        p1 = new Player("Player 1", true); // true means human player
        viewer = new BoardViewer();
        
        gameNumber = 0;
    }

    // Method
    public void playTicTacToe()
    {
        Scanner reader = new Scanner(System.in);

        // Select options before starting the game
        gameOptions();

        // Begin play
        boolean playAgain = true;
        while (playAgain)
        {
            oneGame();

            System.out.println("Player 1 has " + p1.getWins() + " wins.");
            System.out.println("Player 2 has " + p2.getWins() + " wins.\n");
            System.out.print("Would y'all like to play again? (Y/N) ");
            String response = reader.nextLine();
            if (response.charAt(0) == 'N' || response.charAt(0) == 'n')
            {
                playAgain = false;
            }
        }
    }

    private void gameOptions()
    {
        Scanner reader = new Scanner(System.in);
        
        // Select whether P2 will be an AI player.
        System.out.print("Do you want to play against an AI player? (Y/N) ");
        String aiPick = reader.nextLine();
        if(aiPick.charAt(0) == 'N' || aiPick.charAt(0) == 'n')
        {
            p2 = new Player("Player 2", true); // true means human player
        }
        else
        {
            p2 = new Player("Player 2", false); // false means AI player
        }

        
        p1.setMark("X");
        p2.setMark("O");
        // Players choose "X" and "O" for their marks
        System.out.print("Player 1 will be \"X\", player 2 will be \"O\".");
    }

    
    
    private void oneGame()
    {
        gameNumber++;   // hold the number of games that have been played
        boolean gameOver = false;
        board = new Board();        // Initially board shows locations of squares 1 - 9
        viewer.showBoard(board);    
        board.clearBoard();         // Resets all squares to be blank for the beginning of the game.
        
        Player[] playOrder = new Player[2];
        if(gameNumber%2 == 1)
        {
            playOrder[0] = p1;
            playOrder[1] = p2;
        }
        else
        {
            playOrder[0] = p2;
            playOrder[1] = p1;
        }
        
        while (!gameOver)
        {
            for(int i = 0 ; i<2; i++)
            {
                gameOver = oneTurn(playOrder[i]);
                if(gameOver)
                {
                    break;
                }
            }
            
        }
    }
    
    
    // Plays one turn, and returns true if the game has ended.
    // Printing of the game result, and updating player records, is done within this method.
    private boolean oneTurn(Player p)
    {
        // Process player #1's move
        System.out.print(p.getName() + ": ");
        int move = p.pickColumn(board.copy());
        boolean validMove = board.play(move, p.getMark());   // sets specific square with "X" or "O"
        if (!validMove)    // validMove returns false if the square is already filled
        {
            System.out.println("Invalid entry. Game over.\n");
            return true;
        }
        
        viewer.showBoard(board);
        
        
        String win = board.checkForWin();   // returns "X" or "O" if that player has won
        if (win != null)
        {
            if(win.equals(p1.getMark()))
            {
                p1.addWin();
                System.out.println(p1.getName() + " wins!\n");
                p2.addLoss();
            }
            else
            {
                p2.addWin();
                System.out.println(p2.getName() + " wins!\n");
                p1.addLoss();
            }
            return true;
        }
        if (board.getEmptySquares() == 0)
        {
            System.out.println("Tie Game!\n");
            p1.addTie();
            p2.addTie();
            return true;
        }
        return false;
    }
}
