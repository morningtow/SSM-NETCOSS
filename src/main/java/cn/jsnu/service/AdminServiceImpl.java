package cn.jsnu.service;

import cn.jsnu.dao.AdminDao;
import cn.jsnu.domain.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adminService")
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin findByCode(String code) {
        return adminDao.findByCode(code);
    }
}
