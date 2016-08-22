/**
 * Created by ocekmez on 7/11/2016.
 */
public class DuplicateChildException extends Exception {
    public String detail;

    DuplicateChildException(String detail) {
        super(detail);
    }
}
