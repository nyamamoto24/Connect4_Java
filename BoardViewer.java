/*
 * The BoardViewer is written as a separate class in case someone wants to
 * implement a graphical user interface instead of terminal output. It is 
 * good programming practice to have separate classes for modeling
 * the data and for viewing it.
 */

public class BoardViewer
{
    public BoardViewer()
    {
        
    }

    public void showBoard(Board board)
    {
        // 29 dashes: 4 per column, plus one because there's a bar on both sides
        String line = "-----------------------------";

        System.out.println();
        System.out.println(line);
        
        for(int row = 6; row >= 1; row--)
        {
            System.out.print("|");
            for(int col = 1; col <= 7; col++)
            {
                System.out.print(" " + board.getMark(row,col) + " |");
            }
            System.out.println();
            System.out.println(line);
        }
    }
}
