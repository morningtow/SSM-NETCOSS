package cn.jsnu.dao;

import cn.jsnu.domain.Role;
import cn.jsnu.domain.RolePage;

import java.util.List;

public interface RoleDao {
    List<Role> findRoleList(RolePage page);

    // 统计角色信息
    int findRoleRows(RolePage page);

    // 查找角色信息
    int findRoleName(String name);

    int addRole(String name);

    int updateRole(Role role);
    int delRole(Integer id);
    Role findRoleById(Integer id);
}
