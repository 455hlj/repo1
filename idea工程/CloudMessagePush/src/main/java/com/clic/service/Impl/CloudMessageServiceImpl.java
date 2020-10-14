package com.clic.service.Impl;

import com.clic.domain.CloudMessage;
import com.clic.mapper.CloudMessageDao;
import com.clic.service.CloudMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h3>CloudMessagePush</h3>
 * <p>推送云信息的service</p>
 *
 * @author : John Fallen
 * @date : 2020-09-17 11:43
 **/
@Service
public class CloudMessageServiceImpl implements CloudMessageService {

    @Autowired
    private CloudMessageDao cloudMessageDao;
    @Override
    public int addOneRecord(CloudMessage cloudMessage) {
        return cloudMessageDao.pushOneRecord(cloudMessage);
    }

    @Override
    public int addBatchRecord(List<CloudMessage> cloudMessagesBatch) {
        return cloudMessageDao.addBatchRecord(cloudMessagesBatch);
    }
}
