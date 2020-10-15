package com.clic.mapper;

import com.clic.domain.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <h3>CloudMessagePush</h3>
 * <p>用户操作dao</p>
 *
 * @author : John Fallen
 * @date : 2020-09-16 16:33
 **/
@Repository
public interface AdminDao {

    public Admin findAdminByLogin(@Param("username") String username , @Param("password") String password);

}
