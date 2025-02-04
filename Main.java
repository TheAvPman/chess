/**
 * Main class for demo purposes.
 *
 * @author Pierre Cagne
 * @version last updated on 2025/1/15
 */
public class Main
{

    /**
     * Initialize the board given in argument to the initial position of
     * a game of chess.
     * @param board
     * The 2d-array of cells of size 8x8 to be initialized.
     */
    public static void initializeBoard(Cell[][] board)
    {

        for (int j = 0; j < board.length; j++)
        {
            board[j] = new Cell[8];
            for (int i = 0; i < 8; i++)
            {
                board[j][i] = new Cell(Status.FREE,
                        PieceColor.BLACK, PieceValue.PAWN);
            }
        }

        for (int j = 0; j < board.length; j++)
        {
            board[j][1] = new Cell(Status.OCCUPIED,
                    PieceColor.WHITE, PieceValue.PAWN);
            board[j][6] = new Cell(Status.OCCUPIED,
                    PieceColor.BLACK, PieceValue.PAWN);
        }
// Rook Init
        board[0][0] = new Cell(Status.OCCUPIED,
                PieceColor.WHITE, PieceValue.ROOK);
        board[7][7] = new Cell(Status.OCCUPIED,
                PieceColor.BLACK, PieceValue.ROOK);
        board[7][0] = new Cell(Status.OCCUPIED,
                PieceColor.WHITE, PieceValue.ROOK);
        board[0][7] = new Cell(Status.OCCUPIED,
                PieceColor.BLACK, PieceValue.ROOK);
// Knight Init
        board[1][0] = new Cell(Status.OCCUPIED,
                PieceColor.WHITE, PieceValue.KNIGHT);
        board[6][7] = new Cell(Status.OCCUPIED,
                PieceColor.BLACK, PieceValue.KNIGHT);
        board[6][0] = new Cell(Status.OCCUPIED,
                PieceColor.WHITE, PieceValue.KNIGHT);
        board[1][7] = new Cell(Status.OCCUPIED,
                PieceColor.BLACK, PieceValue.KNIGHT);
// Bishop Init
        board[2][0] = new Cell(Status.OCCUPIED,
                PieceColor.WHITE, PieceValue.BISHOP);
        board[5][7] = new Cell(Status.OCCUPIED,
                PieceColor.BLACK, PieceValue.BISHOP);
        board[5][0] = new Cell(Status.OCCUPIED,
                PieceColor.WHITE, PieceValue.BISHOP);
        board[2][7] = new Cell(Status.OCCUPIED,
                PieceColor.BLACK, PieceValue.BISHOP);
// King Init
        board[4][0] = new Cell(Status.OCCUPIED,
                PieceColor.WHITE, PieceValue.KING);
        board[4][7] = new Cell(Status.OCCUPIED,
                PieceColor.BLACK, PieceValue.KING);
// Queen Init
        board[3][0] = new Cell(Status.OCCUPIED,
                PieceColor.WHITE, PieceValue.QUEEN);
        board[3][7] = new Cell(Status.OCCUPIED,
                PieceColor.BLACK, PieceValue.QUEEN);
    }
    //TODO : initiliaze the rest!


    /**
     * Print a chessboard from the point of view of the white pieces.
     *
     * @param board
     * The 2d-array of cells that represents the chessboard to be printed.
     */

    public static void printBoard(Cell[][] board)
    {
        System.out.println("    Chess Board:");
        //start at row 7 and decrement each turn
        for (int i = 7; i >= 0; i--)
        {
            //colums are expored in ascending order
            System.out.print(i + 1 + "| ");
            for (int j = 0; j < 8; j++)
            {
                System.out.print(board[j][i] + " ");
            }
            System.out.println();
        }
        System.out.println("‾| Ā B̄ C̄ D̄ Ē F̄ Ḡ H̄");
    }

    /**
     * Compute the jagged array of all the pieces the board colums by colums.
     *
     * @param board
     * The 2d-array of cells that represents the chessboard.
     *
     * @return The jagged array 'pieces' where 'pieces[j]' contains all the
     * occupied cells (in row-ascending order) of the j-th colum on
     * 'board'.
     */

    public static Cell[][] getPiecesByColumns(Cell[][] board)
    {
        Cell[][] pieces = new Cell[8][];

        for (int j = 0; j < 8; j++)
        {

            int count = 0;
            for (int i = 0; i < 8; i++)
            {
                if (board[j][i].getStatus() == Status.OCCUPIED)
                {
                    count++;
                }
            }

            pieces[j] = new Cell[count];

            int k = 0;
            for (int i = 0; i < 8; i++)
            {
                if (board[j][i].getStatus() == Status.OCCUPIED)
                {
                    pieces[j][k] = board[j][i];
                    k++;
                }
            }
        }

        return pieces;
    }

    /**
     * Compute the jagged array of all the pieces the board diagonals by
     * diagonals.
     *
     * @param board
     * The 2d-array of cells that represents the chessboard.
     *
     * @return The jagged array 'pieces' where 'pieces[d]' contains all the
     * occupied cells (in (row,colum)-ascending order) of the d-th
     * diagonal on 'board'.
     * TODO* Complete the method!
     */

    public static Cell[][] getPiecesByDiags(Cell[][] board)
    {
        Cell[][] pieces = new Cell[15][];

        for (int d = 0; d < 15; d++)
        {

            //step 1: compute the length of the wannabe array pieces[d]
            int count = 0;
            for (int j = 0; j < 8; j++)
            {
                int i = d - j;
                if (i >= 0 && i < 8 && board[j][i].getStatus()
                        == Status.OCCUPIED)
                {
                    count++;
                }
            }

            //step 2: initialize pieces[d]
            pieces[d] = new Cell[count];

            int k = 0;
            //step 3: put the data in the newly created array pieces[d]
            for (int j = 0; j < 8; j++)
            {
                int i = d - j;
                if (i >= 0 && i < 8 && board[j][i].getStatus()
                        == Status.OCCUPIED)
                {
                    pieces[d][k] = board[j][i];
                    k++;
                }
            }
        }
        return pieces;
    }

    /**
     * main function for demo.
     * @param args command line arguments
     * @throws InterruptedException wait function
     */
    public static void main(String[] args) throws InterruptedException
    {

        Cell[][] board = new Cell[8][];

        initializeBoard(board);
        board[2][2] = new Cell(Status.FREE, PieceColor.BLACK, PieceValue.KING);
        //Movement tested for pawns. ( Works great :) )
        PGN pgn = new PGN("Test.txt");
        pgn.play(board);
        System.out.println();
    }

    /**
     * Print pbc.
     *
     * @param board the board
     */
    public void printPBC(Cell[][] board)
    {
        Cell[][] piecesByColumns = getPiecesByColumns(board);

        for (int j = 0; j < piecesByColumns.length; j++)
        {
            System.out.print("Column " + ((char) ('A' + j)) + ": ");
            for (int k = 0; k < piecesByColumns[j].length; k++)
            {
                System.out.print(piecesByColumns[j][k] + " ");
            }
            System.out.println();
        }
    }
}
