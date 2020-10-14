package com.clic.service;

import com.clic.domain.Admin;

/**
 * <h3>CloudMessagePush</h3>
 * <p>接口类</p>
 *
 * @author : John Fallen
 * @date : 2020-09-17 11:45
 **/
public interface AdminService {
    public Admin login(String usernaem,String password);
}
