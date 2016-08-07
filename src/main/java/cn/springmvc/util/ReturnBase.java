package cn.springmvc.util;

import java.io.Serializable;

/**
 * 类ReturnBase.java的实现描述：数据返回基类
 * 
 * @author wuzhanghong 2014-9-9 上午11:03:45
 */
public class ReturnBase implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 处理返回代码
     */
    private ReturnCode returnCode;
    /**
     * 处理返回消息
     */
    private String message;

    private StringBuilder params;

    public ReturnBase() {
        super();
    }

    public ReturnBase(ReturnCode returnCode) {
        super();
        this.returnCode = returnCode;
    }

    public ReturnBase(ReturnCode returnCode, String message) {
        super();
        this.returnCode = returnCode;
        this.message = message;
    }

    public ReturnCode getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(ReturnCode returnCode) {
        this.returnCode = returnCode;
    }

    public String getMessage() {
        if (params == null) {
            return message;
        }
        return message + " Params: [" + params.toString() + "]";
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ReturnBase appendParam(Object param) {
        if (params != null) {
            params.append("|");
        } else {
            params = new StringBuilder();
        }
        params.append(param);
        return this;
    }

    public ReturnBase appendParam(String name, Object param) {
        if (params != null) {
            params.append("|");
        } else {
            params = new StringBuilder();
        }
        params.append(name).append("=").append(param);
        return this;
    }

    @Override
    public String toString() {
        return null;
    }

}
