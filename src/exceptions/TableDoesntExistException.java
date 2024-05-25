package exceptions;

public class TableDoesntExistException extends Exception{
    
        public TableDoesntExistException(int tableNumber) {
            super("Table " + tableNumber + " doesn't exist.");
        }

}
