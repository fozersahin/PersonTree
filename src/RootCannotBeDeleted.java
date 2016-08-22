/**
 * Created by ocekmez on 7/11/2016.
 */
public class RootCannotBeDeleted extends Exception {
    public String detail;


    RootCannotBeDeleted(String detail) {
        super(detail);
    }

}