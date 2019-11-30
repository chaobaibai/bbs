package cn.fengyichao.bbs.exception;

/**
 * @author fengyichao
 * @date 2019/11/30 - 16:33
 */
public class NoParentIdException extends RuntimeException {
    private String message;

    public NoParentIdException(String message) {
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
