package cn.jsnu.service;

import cn.jsnu.domain.Role;
import cn.jsnu.domain.RolePage;

import java.util.List;

public interface RoleService {
    List<Role> findRoleList(RolePage page);

    // 统计角色数量
    int findRoleRows(RolePage page);

    int findRoleName(String name);

    int addRole(String name);
    int updateRole(Role role);
    int delRole(int id);

    Role findRoleById(Integer id);
}
