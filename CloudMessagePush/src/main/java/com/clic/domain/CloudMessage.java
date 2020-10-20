package com.clic.domain;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * <h3>CloudMessagePush</h3>
 * <p>云助理通知消息</p>
 *
 * @author : John Fallen
 * @date : 2020-09-12 11:16
 **/
public class CloudMessage implements Serializable {
    private String messageType;
    private String messageContent;
    private String toUser;
    private String messageTitle;
    private String copy;
    private String contentType;


    private static ArrayList<CloudMessage> MessageBatch ;

    public static ArrayList<CloudMessage> getMessageBatch() {
        return MessageBatch;
    }

    public static void setMessageBatch(ArrayList<CloudMessage> messageBatch) {
        MessageBatch = messageBatch;
    }

    public static String[] FIELDSARR = new String[]{"MessageType","MessageContent","ToUser",
            "MessageTitle","Copy"};
    public static String[]  FIELDSNAME = new String[]{"发送频道","信息内容","发送对象",
       "信息标题","是否可复制"};

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }


    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }


    public String getCopy() {
        return copy;
    }

    public void setCopy(String copy) {
        this.copy = copy;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public String toString() {
        return "CloudMessage{" +
                "messageType='" + messageType + '\'' +
                ", messageContent='" + messageContent + '\'' +
                ", toUser='" + toUser + '\'' +
                ", messageTitle='" + messageTitle + '\'' +
                ", copy='" + copy + '\'' +
                ", contentType='" + contentType + '\'' +
                '}';
    }

}
