package com.clic.mapper;

import com.clic.domain.CloudMessage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <h3>CloudMessagePush</h3>
 * <p>云信息推送</p>
 *
 * @author : John Fallen
 * @date : 2020-09-16 16:43
 **/
@Repository
public interface CloudMessageDao {
    public int pushOneRecord(@Param("cloudMessage") CloudMessage cloudMessage);
    public int addBatchRecord(@Param("cloudMessagesBatch")List<CloudMessage> cloudMessagesBatch);
}
