package cn.springmvc.util;

/**
 * 类ReturnCode.java的实现描述：返回编码
 * 
 * @author wuzhanghong 2014-9-9 上午11:01:11
 */
public enum ReturnCode {	
    SUCCESS(1), /* 处理成功 */
    FAIL(2), /* 处理失败 */
    ERROR(3), /* 请求错误 */
    EXCEPTION(4), /* 处理异常 */
    SUCCESS_PART(5); /* 处理成功，部分 */

    private int value;

    private ReturnCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ReturnCode findByValue(int value) {
        switch (value) {
        case 1:
            return SUCCESS;
        case 2:
            return FAIL;
        case 3:
            return ERROR;
        case 4:
            return EXCEPTION;
        case 5:
            return SUCCESS_PART;
        default:
            return null;
        }
    }

}
