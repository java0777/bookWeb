package com.demo.model.entity;

import java.util.Map;

/**
 * Created by Administrator on 2017/7/10.
 */
public class AppResult {
    private boolean success;
    private String mess;
    private Object data;

    public boolean isSuccess() {
        return success;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
