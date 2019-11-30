package cn.fengyichao.bbs.exception;

/**
 * @author fengyichao
 * @date 2019/11/30 - 18:36
 */
public class NoThisCommentException extends RuntimeException {

    private String message;

    public NoThisCommentException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
