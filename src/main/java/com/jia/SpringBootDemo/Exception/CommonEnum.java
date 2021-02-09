package com.jia.SpringBootDemo.Exception;

public enum CommonEnum implements BaseErrorInfo{
    // 数据操作错误信息定义
    SUCCESS("200","成功"),
    BODY_NOT_MATCH("400","请求数据格式不符"),
    NOT_FOUND("404","未找到该资源"),
    INTERNAL_ERROR("500","服务器内部错误");

    private String resultCode;
    private String resultMsg;

    CommonEnum(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    @Override
    public String getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }
}
