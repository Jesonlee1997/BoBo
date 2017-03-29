package me.jesonlee.mymvc2.exception;

/**
 * Created by Administrator on 2017/3/26 0026.
 */
public class NoMethodFoundException extends RuntimeException {
    public NoMethodFoundException(String message) {
        super(message);
    }
}
