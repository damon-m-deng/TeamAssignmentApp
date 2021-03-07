package team.service;
/**
 *
 * @Description 自定义异常类 Customize Exception class
 * @version
 *
 */
public class TeamException extends Exception {
    static final long serialVersionUID = -3387515555529948L;

    public TeamException() {
    }

    public TeamException(String message) {
        super(message);
    }
}
