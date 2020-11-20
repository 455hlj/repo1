package com.h455.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.h455.entity.Users;
import com.h455.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h3>quickstart</h3>
 * <p></p>
 *
 * @author : John Fallen
 * @date : 2020-11-11 14:31
 **/
@Service("userDetailsService")
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // 调用mapper方法，查询数据库中是否有传入的s的用户名
        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        //where username = :s;
        wrapper.eq("username",s);
        Users user = userMapper.selectOne(wrapper);

        //认证失败
        if(user==null)
        {
            throw new UsernameNotFoundException("用户不存在");
        }
        //给一个role角色和一个admin的权限
        List<GrantedAuthority>  auths = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_role,admin");
        //return 表示认证通过
        return new User(user.getUsername(),new BCryptPasswordEncoder().encode(user.getPassword()),auths);
    }

}
