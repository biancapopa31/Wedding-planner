package exceptions;

public class TableIsFullException extends Exception{
    public TableIsFullException(int tableNumber) {
        super("Table " + tableNumber + " is full.");
    }

}
