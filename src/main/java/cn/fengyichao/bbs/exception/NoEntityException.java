package cn.fengyichao.bbs.exception;

/**
 * @author fengyichao
 * @date 2019/11/30 - 8:47
 */
public class NoEntityException extends RuntimeException {

    private String message;

    public NoEntityException(String message) {
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
