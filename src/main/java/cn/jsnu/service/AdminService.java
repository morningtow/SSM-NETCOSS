package cn.jsnu.service;

import cn.jsnu.domain.Admin;
import org.springframework.stereotype.Service;


public interface AdminService {
    // 根据code查找用户数据信息
    Admin findByCode(String code);
}
