package com.clic.service;

import com.clic.domain.CloudMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <h3>CloudMessagePush</h3>
 * <p>信息推送接口</p>
 *
 * @author : John Fallen
 * @date : 2020-09-17 11:45
 **/
public interface CloudMessageService {
    public int addOneRecord(CloudMessage cloudMessage);
    public int addBatchRecord(List<CloudMessage> cloudMessagesBatch);
}
