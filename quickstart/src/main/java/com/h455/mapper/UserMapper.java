package com.h455.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.h455.entity.Users;
import org.springframework.stereotype.Repository;

/**
 * <h3>quickstart</h3>
 * <p>mapper接口</p>
 *
 * @author : John Fallen
 * @date : 2020-11-11 15:43
 **/
@Repository
public interface UserMapper extends BaseMapper<Users> {
}
