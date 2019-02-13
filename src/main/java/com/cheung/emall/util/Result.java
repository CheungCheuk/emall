package com.cheung.emall.util;
/**
 * 该类包含错误代码、错误信息和数据等，用以前端调试
 */
public class Result {
    public static int SUCCESS_CODE = 0;
    public static int FAIL_CODE = 1;

    int code;
    String message;
    Object data;

    /**
     * 
     */
    private Result (int code, String message, Object data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result success(){
        return new Result(SUCCESS_CODE,null,null);
    }

    public static Result success(Object result){
        return  new Result(SUCCESS_CODE, "", result);
    }

    
    public static Result fail(String message){
        return  new Result(FAIL_CODE, message, null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
