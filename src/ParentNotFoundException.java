/**
 * Created by ocekmez on 7/11/2016.
 */
public class ParentNotFoundException extends Exception {
    public String detail;

    ParentNotFoundException(String detail) {
        super(detail);
    }
}
