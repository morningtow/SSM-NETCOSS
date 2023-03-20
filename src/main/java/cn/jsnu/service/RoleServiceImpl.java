package cn.jsnu.service;

import cn.jsnu.dao.RoleDao;
import cn.jsnu.domain.Role;
import cn.jsnu.domain.RolePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleDao roleDao;
    @Override
    public List<Role> findRoleList(RolePage page) {
        return roleDao.findRoleList(page);
    }

    @Override
    public int findRoleRows(RolePage page) {
        return roleDao.findRoleRows(page);
    }

    @Override
    public int findRoleName(String name) {
        return roleDao.findRoleName(name);
    }

    @Override
    public int addRole(String name) {
        return roleDao.addRole(name);
    }

    @Override
    public int updateRole(Role role) {
        return roleDao.updateRole(role);
    }

    @Override
    public int delRole(int id) {
        return roleDao.delRole(id);
    }

    @Override
    public Role findRoleById(Integer id) {
        return roleDao.findRoleById(id);
    }
}
