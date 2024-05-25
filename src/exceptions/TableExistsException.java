package exceptions;

public class TableExistsException extends Exception{

    public TableExistsException(int tableNumber) {
        super("Table " + tableNumber + " already exists.");
    }

}
