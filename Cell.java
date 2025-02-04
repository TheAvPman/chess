/**
   Objects of type Cell represent cells on a chessboard.

   @author Pierre Cagne
   @date last updated on 2025/1/15
*/

public class Cell {

  /**
     @field status
       The status (free or occupied by a piece) of the current cell.
     @field color
       The color (black or white)
     @field value
       The value (king, queen, rook, bishop, knight, pawn) of the
       piece occupying the current cell.

     For a cell with status FREE, the values of color and value don't
     matter.
  */
  private Status status;
  private PieceColor color;
  private PieceValue value;


  /**
     Constructor. Make a new cell with specified status, color, and value.

     @arg status
       The status to assign to the cell.
     
     @arg color
       The color to assign to the cell.

     @arg value
       The value to assign to the cell.
  */
  public Cell (Status status, PieceColor color, PieceValue value) {
    this.status = status;
    this.color = color;
    this.value = value;
  }
  
  /**
     Getter.
     
     @return the value of the status field.
  */
  public Status getStatus () {
    return status;
  }

  /**
     Getter.
     
     @return the value of the color field.
  */
  public PieceColor getColor () {
    return color;
  }

  /**
     Getter.
     
     @return the value of the value field.
  */  
  public PieceValue getValue () {
    return value;
  }


  /**
     String representation of a cell. If the cell is free, its string
     representation is "X". If the cell is occupied, its string
     representation is the first letter of its value in lowercase for
     black pieces and in uppercase for white piece. Since knight and
     king both start with a k, we use the letter n for knights instead.
     
     @return the string representation of the cell as described above.
  */
  public String toString () {
    if (status == Status.FREE)
      return "*";

    String res;
    switch (value) {
    case KNIGHT :
      res = "N";
      break;
    default:
      res = "" + value.toString().charAt(0);
    }

    if (color == PieceColor.BLACK)
      res = res.toLowerCase();

    return res;
  }
  
}
