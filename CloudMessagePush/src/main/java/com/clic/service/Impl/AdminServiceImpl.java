package com.clic.service.Impl;

import com.clic.domain.Admin;
import com.clic.mapper.AdminDao;
import com.clic.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <h3>CloudMessagePush</h3>
 * <p>用户操作的service</p>
 *
 * @author : John Fallen
 * @date : 2020-09-16 16:32
 **/
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;
    @Override
    public Admin login(String username, String password) {
        return adminDao.findAdminByLogin(username,password);
    }
}
