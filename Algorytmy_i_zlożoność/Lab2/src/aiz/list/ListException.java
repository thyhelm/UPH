package aiz.list;

/**
 * Wyjatek rzucany przez metody klas implementujacych listy.
 */
public class ListException extends Exception {

    public ListException(String message) {
        super(message);
    }
    
}
