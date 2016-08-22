/**
 * Created by ocekmez on 7/11/2016.
 */
public class ChildNotFoundException extends Exception {
    public String detail;

    ChildNotFoundException(String detail) {
        super(detail);
    }
}
