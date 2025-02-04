/**
 * The class that handles validation and movement for the chess project!
 * The instance variables that were requested
 * ended up being unused in my implementation
 * and have therefore been left out.
 * <p>
 * Furthermore, the getters and setters were
 * un-required so I left them out for brevity.
 * <p>
 * If that is an issue let me know and I can
 * refactor my code to include them.
 * @author Benjamin Isserman
 * @version 1.1 (Now with updated checkstyle adherence and java documentation!)
 */
public class Move
{
    private final int dRow;
    private final int dCol;
    /**
     * Creates a new instance of the movement object.
     *
     * @param board the chess board
     * @param from  the movement origin
     * @param to    the final movement location
     */
    public Move(Cell[][] board, int[] from, int[] to)
    {
        dRow = to[0] - from[0];
        dCol = to[1] - from[1];
        if (isLegal(board, from, to) && to[0] >= 0
                && to[1] >= 0 && to[0] < 8 && to[1] < 8
                && from[0] >= 0 && from[1] >= 0 && from[0] < 8 && from[1] < 8)
        {
            make(board, from, to);
        }
        else
        {
            System.out.println("Move invalid.");
        }
    }

    /**
     * Makes the move on the board,
     * currently it is heavily oversimplified.
     *
     * @param board the chess board
     * @param from  the origin
     * @param to    the new location
     */
    public void make(Cell[][] board, int[] from, int[] to)
    {
        board[to[0]][to[1]] = board[from[0]][from[1]];
        board[from[0]][from[1]] =
                new Cell(Status.FREE, PieceColor.BLACK, PieceValue.PAWN);
    }

    /**
     * Is legal boolean.
     *
     * @param board the chess board
     * @param from  the origin
     * @param to    the new location
     * @return the boolean
     */
    public boolean isLegal(Cell[][] board, int[] from, int[] to)
    {
        if (board[to[0]][to[1]].getColor()
                == board[from[0]][from[1]].getColor()
                && board[to[0]][to[1]].getStatus() == Status.OCCUPIED)
        {
            return false;
        }
        PieceValue pType = board[from[0]][from[1]].getValue();
        PieceColor pColor = board[from[0]][from[1]].getColor();
        if (pType == PieceValue.ROOK)
        {
            if (to[0] - from[0] != 0 && to[1] - from[1] != 0)
            {
                return false;
            }
            return hitScanRook(board, from);
        }
        if (pType == PieceValue.KING)
        {
            return (Math.abs(to[0] - from[0]) <= 1
                    && Math.abs(to[1] - from[1]) <= 1);
        }
        if (pType == PieceValue.BISHOP)
        {
            if (Math.abs(to[0] - from[0]) != Math.abs(to[1] - from[1]))
            {
                return false;
            }
            return hitScanBishop(board, from);
        }
        if (pType == PieceValue.QUEEN)
        {
            if (Math.abs(to[0] - from[0]) == Math.abs(to[1] - from[1]))
            {
                return hitScanBishop(board, from);
            }
            if (to[0] == from[0] || to[1] == from[1])
            {
                return hitScanRook(board, from);
            }
            return false;
        }
        if (pType == PieceValue.KNIGHT)
        {
            return knightLoc();
        }
        if (pType == PieceValue.PAWN)
        {
            if (pColor == PieceColor.BLACK)
            {
                return pawnMov(board, 0, from, to);
            }
            else
            {
                return pawnMov(board, 1, from, to);
            }
        }
        return true;
    }

    /**
     * Performs a hit scan operation for rook validation.
     *
     * @param board the chess board
     * @param from  the origin
     * @return the boolean
     */
    private boolean hitScanRook(Cell[][] board, int[] from)
    {
        if (dRow != 0)
        {
            if (dRow > 0)
            {
                for (int i = 1; i < dRow; i++)
                {
                    if (board[from[0] + i][from[1]].getStatus()
                            == Status.OCCUPIED)
                    {
                        return false;
                    }
                }
            }
            else
            {
                for (int i = 1; i < Math.abs(dRow); i++)
                {
                    if (board[from[0] - i][from[1]].getStatus()
                            == Status.OCCUPIED)
                    {
                        return false;
                    }
                }
            }
        }
        else
        {
            if (dCol > 0)
            {
                for (int i = 1; i < dCol; i++)
                {
                    if (board[from[0]][from[1] + i].getStatus()
                            == Status.OCCUPIED)
                    {
                        return false;
                    }
                }
            }
            else
            {
                for (int i = 1; i < Math.abs(dCol); i++)
                {
                    if (board[from[0]][from[1] - i].getStatus()
                            == Status.OCCUPIED)
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Performs a hit scan operation for bishop validation.
     *
     * @param board the chess board
     * @param from  the origin
     * @return the boolean
     */
    private boolean hitScanBishop(Cell[][] board, int[] from)
    {
        if (dRow > 0 && dCol > 0)
        {
            for (int i = 1; i < Math.abs(dRow); i++)
            {
                if (board[from[0] + i][from[1] + i].getStatus()
                        == Status.OCCUPIED)
                {
                    return false;
                }
            }
        }
        else if (dRow > 0 && dCol < 0)
        {
            for (int i = 1; i < Math.abs(dRow); i++)
            {
                if (board[from[0] + i][from[1] - i].getStatus()
                        == Status.OCCUPIED)
                {
                    return false;
                }
            }
        }
        else if (dRow < 0 && dCol > 0)
        {
            for (int i = 1; i < Math.abs(dRow); i++)
            {
                if (board[from[0] - i][from[1] + i].getStatus()
                        == Status.OCCUPIED)
                {
                    return false;
                }
            }
        }
        else if (dRow < 0 && dCol < 0)
        {
            for (int i = 1; i < Math.abs(dRow); i++)
            {
                if (board[from[0] - i][from[1] - i].getStatus()
                        == Status.OCCUPIED)
                {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Accounts for every possible knight location.
     *
     * @return the boolean
     */
    private boolean knightLoc()
    {
        return (Math.abs(dRow) == 2 && Math.abs(dCol)
                == 1 || Math.abs(dRow) == 1 && Math.abs(dCol) == 2);
    }

    /**
     * Handles complex pawn movement on the chess board.
     * Accounts for diagonal capture and double step.
     * 
     * @param board the chess board
     * @param color the color
     * @param from  the origin
     * @param to    the new location
     * @return the boolean
     */
    private boolean pawnMov(Cell[][] board, int color, int[] from, int[] to)
    {

        if (color == 1)
        {
            if (dCol == 1 && dRow == 0
                    && board[to[0]][to[1]].getStatus() == Status.FREE)
            {
                return true;
            }
            if ((dCol == 2) && (dRow == 0) && (from[1] == 1)
                    && (board[from[0]][from[1] + 1].getStatus() == Status.FREE)
                    && (board[to[0]][to[1]].getStatus() == Status.FREE))
            {
                return true;
            }
            return (Math.abs(dRow) == 1 && dCol == 1
                    && board[to[0]][to[1]].getStatus()
                    == Status.OCCUPIED);
        }
        else
        {
            if (dCol == -1 && dRow == 0
                    && board[to[0]][to[1]].getStatus() == Status.FREE)
            {
                return true;
            }
            if (dCol == -2 && dRow == 0 && from[1] == 6
                    && board[from[0]][from[1] - 1].getStatus() == Status.FREE
                    && board[to[0]][to[1]].getStatus() == Status.FREE)
            {
                return true;
            }
            return (Math.abs(dRow) == 1 && dCol
                    == -1 && board[to[0]][to[1]].getStatus()
                    == Status.OCCUPIED);
        }
    }
}
