public class Board
{
    private String[][] board = new String[6][7];
    public Board()
    { 
        clearBoard();
        for(int j = 0; j < 7; j++)
        {
            board[5][j] = Integer.toString(j + 1);
        }
    }
    public void clearBoard()
    {
        for(int r = 0; r < board.length; r++)
        {
            for(int c = 0; c < board[0].length; c++)
            {
                board[r][c] = " ";
            } 
        }
    }

    public String getMark(int row, int col) //fix
    {
        return board[6 - row][col - 1];
    }
    
    public int getEmptySquares() 
    {
        int count = 0;  
        for(int r = 0; r < board.length; r++)
        {
            for(int c = 0; c < board[0].length; c++)
            {
                if(board[r][c].equals(" "))
                {
                   count++;
                }
            
            }
        }
        return count;        
    }  
    public boolean play(int col, String mark) //make simpler
    {
        if(col < 0 || col > 7)
        {
            return false;
        }
        for(int i = 5; i >= 0; i--) //going from "lowest" row
        {
            if(board[i][col - 1].equals(" "))
            {
                board[i][col - 1] = mark;
                return true;
            }
        }
        return false;
    }
    public String checkForWin()
    {
        //Horizontal Victory!
        for(int r = 0; r < board.length; r++)
        {
            for(int c = 0; c < board[0].length - 3; c++)
            //boundary set so we don't go out of bounds when the mark is closer to the edges with "- 3"
            {
                if(!board[r][c].equals(" ") //don't want a space win
                && board[r][c].equals(board[r][c+1])
                && board[r][c].equals(board[r][c+2])
                && board[r][c].equals(board[r][c+3]))
                {
                    return board[r][c];               
                }
            }
        }

        //Vertical Victory!
        for(int r = 0; r < board.length - 3; r++)
        {
            for(int c = 0; c < board[0].length; c++)
            {
                if(!board[r][c].equals(" ")
                && board[r][c].equals(board[r+1][c])
                && board[r][c].equals(board[r+2][c])
                && board[r][c].equals(board[r+3][c]))
                {
                    return board[r][c];
                }
            }
        }

        //Up-right diagnal win!
        for(int r = 0; r < board.length - 3; r++)
        {
            for(int c = 0; c < board[0].length - 3; c++)
            {
                if(!board[r][c].equals(" ")
                && board[r][c].equals(board[r+1][c + 1])
                && board[r][c].equals(board[r+2][c + 2])
                && board[r][c].equals(board[r+3][c + 3]))
                {
                    return board[r][c];
                }
            }
        }

        //Up-Left diagnal win!
        for(int r = 0; r < board.length - 3; r++) 
        {
            for(int c = 0; c < board[0].length; c++)
            {
                if(!board[r][c].equals(" ")
                && board[r][c].equals(board[r+1][c - 1])
                && board[r][c].equals(board[r+2][c - 2])
                && board[r][c].equals(board[r+3][c - 3]))
                {
                    return board[r][c];
                }
            }
        }


        return null;

    

    }
       
    
    
    public Board copy()
    {
        Board fakeClone = new Board();
        for(int r = 0; r < board.length; r++)
        {
            for(int c = 0; c < board[0].length; c++)
            {
                fakeClone.board[r][c] = board[r][c];
            }
        }
        return fakeClone;
    }
    public String[][] getBoard() //personal helper method
    {
        return board;
    }
    public static void main(String[] args) 
    {
        Board board = new Board();
        board.play(4,"X");
        System.out.println(board.getMark(2,4));
        board.play(3,"X");
        board.play(2,"X");
        board.play(1,"X");
        board.copy(); 
        board.getEmptySquares();
        
    

    }
    
}
