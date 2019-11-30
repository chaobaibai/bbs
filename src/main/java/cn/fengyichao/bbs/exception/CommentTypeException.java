package cn.fengyichao.bbs.exception;

/**
 * @author fengyichao
 * @date 2019/11/30 - 18:21
 */
public class CommentTypeException extends RuntimeException {
    private String message;

    public CommentTypeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
