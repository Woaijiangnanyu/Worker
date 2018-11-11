package com.example.guojialin.worker.bean;

public class ResultMessage <T>{

    /**
     * code : 200
     * data : {"author":"大师兄","id":1001,"name":"数据结构","publisher":"fzt大师兄"}
     * message : 查询成功
     * success : true
     */

    private int code;
    private T data;
    private String message;
    private boolean success;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
