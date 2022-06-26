package p1;

/**
 * Represents the position that consists of row and column
 * @author Daria Melnyk
*/
public class Posicion {
    protected int column;
    protected int row;

    /**
     * Creates the Position with the specified coordinates
     * @param r The position's row
     * @param c The position's column
     */
    public Posicion(int r, int c){
        this.column = c;
        this.row = r;
        //
    }

    /**
     * Gets the position's row.
     * @return An int representing the row.
     */
    public int getRow() {
        return row;
    }

    /**
     * Sets the position's row.
     * @param row An int representing the row.
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Gets the position's column.
     * @return An int representing the column.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Sets the position's column.
     * @param column An int representing the column.
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * Forms the String of object of class
     * @return a String representing the position e.g. (1:7)
     */
    public String toString(){
        String pos = "(" + row + ":" + column + ")";
        return pos;
    }
}
