package com.clic.Exception;

/**
 * <h3>CloudMessagePush</h3>
 * <p>文件传输错误异常</p>
 *
 * @author : John Fallen
 * @date : 2020-09-12 11:41
 **/
public class FileException extends Exception{
    public static final int WrongTemplate = 1;
    public static final int NoData = 2;
    public static final int WrongSuffix = 3;
    public static final int WrongData = 4;


    public String Message;
    public int messageType;

    @Override
    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public FileException(int messageType) {
        this.messageType = messageType;
        switch(messageType) {
            case WrongTemplate: setMessage("文件模板错误，请重新上传"); break;
            case NoData:setMessage("文件没有任何数据，请重新上传");break;
            case WrongSuffix:setMessage("不是excel文件，请重新选择");break;
            case WrongData:setMessage("文件内容错误，请重新上传");break;
            default: break;
        }
    }

}
