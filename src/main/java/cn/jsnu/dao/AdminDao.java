package cn.jsnu.dao;

import cn.jsnu.domain.Admin;

public interface AdminDao {
    Admin findByCode(String code);

}
