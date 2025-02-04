import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * This class is responsible for handling PGNs.
 * I added the ability for the moves to occur over time to make it cinematic.
 *
 * @author Benjamin Isserman
 * @version 1.1 (Organized to fit with instructions)
 */
public class PGN
{
    char[] to = new char[2];
    char[] from = new char[2];
    private Scanner read;

    /**
     * Locates the pgn file.
     *
     * @param path the path
     */
    public PGN(String path)
    {
        try
        {
            File f = new File(path);
            read = new Scanner(f);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Initiates the next turn.
     * The majority of the code occurs here.
     *
     * @param board the board
     * @throws InterruptedException
     */
    public void makeNextTurn(Cell[][] board) throws InterruptedException
    {
        String info = read.nextLine().replaceFirst("^\\d+\\.\\s*", "");
        String[] infodex = new String[2];
        infodex = info.split(" ");
        for (int i = 0; i < 2; i++)
        {
            if (infodex[i].length() == 5)
            {
                from[0] = infodex[i].charAt(1);
                from[1] = infodex[i].charAt(2);
                to[0] = infodex[i].charAt(3);
                to[1] = infodex[i].charAt(4);
            }
            else if (infodex[i].length() == 3)
            {
                from[0] = 0;
                from[1] = 1;
                to[0] = 0;
                to[1] = 1;
            }
            else
            {
                from[0] = infodex[i].charAt(0);
                from[1] = infodex[i].charAt(1);
                to[0] = infodex[i].charAt(2);
                to[1] = infodex[i].charAt(3);
            }
            int[] iFrom = new int[2];
            int[] iTo = new int[2];
            iFrom[0] = (from[0] - 'a');
            iFrom[1] = (from[1] - '1');
            iTo[0] = (to[0] - 'a');
            iTo[1] = (to[1] - '1');
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
                    + "\n\n\n\n\n\n\n\n\n");
            Move move = new Move(board, iFrom, iTo);
            Main.printBoard(board);
            System.out.println("\n\n");
            Thread.sleep(2000);
        }
    }

    /**
     * Calls the makeNextTurn method for every line.
     *
     * @param board the board
     * @throws InterruptedException
     */
    public void play(Cell[][] board) throws InterruptedException
    {
        while (read.hasNextLine())
        {
            makeNextTurn(board);
        }
    }
}
