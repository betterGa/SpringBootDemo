package com.jia.SpringBootDemo.Exception;

public class ResponseResult {
    public ResponseResult() {
    }



    // 响应码
    private String code;

    // 响应信息，包括正常的和异常的信息
    private String message;

    // 响应结果
    private Object result;

    public ResponseResult(BaseErrorInfo errorInfo) {
        this.code = errorInfo.getResultCode();
        this.message = errorInfo.getResultMsg();
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }


    public ResponseResult success(){
        return success(null);
    }

    // 成功信息
    public static ResponseResult success(Object data){
        ResponseResult responseResult=new ResponseResult();
        responseResult.setCode(CommonEnum.SUCCESS.getResultCode());
        responseResult.setMessage(CommonEnum.SUCCESS.getResultMsg());
        responseResult.setResult(data);
        return responseResult;
    }

    // 错误信息
    public static ResponseResult error(String code,String message){
    ResponseResult responseResult=new ResponseResult();
    responseResult.setCode(code);
    responseResult.setMessage(message);
    responseResult.setResult(null);
    return responseResult;
    }

    // 重载，不传错误码
    public static ResponseResult error(String message){
        ResponseResult responseResult=new ResponseResult();
        responseResult.setCode("-1");
        responseResult.setMessage(message);
        responseResult.setResult(null);
        return responseResult;
    }
    // 重载，传 BaseErrorInfo 实现类
    public static ResponseResult error(BaseErrorInfo baseErrorInfo){
        ResponseResult responseResult=new ResponseResult();
        responseResult.setCode(baseErrorInfo.getResultCode());
        responseResult.setMessage(baseErrorInfo.getResultMsg());
        responseResult.setResult(null);
        return responseResult;
    }

}
