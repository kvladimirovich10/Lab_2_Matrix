/**
 * Created by Кирилл Киселев on 05.11.2016.
 */
public class MyException extends Exception {
    public MyException(Throwable e) {
        initCause(e);
    }
}
