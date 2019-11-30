package cn.fengyichao.bbs.exception;

/**
 * @author fengyichao
 * @date 2019/11/30 - 18:54
 */
public class NoThisPostException extends RuntimeException {
    private String message;

    public NoThisPostException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
