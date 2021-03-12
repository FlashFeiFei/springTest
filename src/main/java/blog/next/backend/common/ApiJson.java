package blog.next.backend.common;

public class ApiJson {
    //状态码
    private int code;
    //消息
    private String msg;
    //数据
    private Object data;

    /**
     * 构建一个successJson
     *
     * @param msg  提示消息
     * @param data 数据
     * @return ApiJson
     */
    public static ApiJson successJson(String msg, Object data) {
        return new ApiJson(0, msg, data);
    }

    /**
     * 错误的异常信息
     *
     * @param msg 错误信息
     * @return ApiJson
     */
    public static ApiJson errorJson(String msg) {
        return new ApiJson(-1, msg, null);
    }

    /**
     * 构建一个apiJons
     *
     * @param code 状态码
     * @param msg  提示消息
     * @param data 数据,可以是null
     */
    public ApiJson(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
