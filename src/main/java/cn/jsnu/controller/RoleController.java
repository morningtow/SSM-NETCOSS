package cn.jsnu.controller;

import cn.jsnu.domain.Role;
import cn.jsnu.domain.RolePage;
import cn.jsnu.service.RoleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.deploy.net.HttpResponse;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 查找所以角色的信息
     */
    @RequestMapping("/findRoleList.do")
    public ModelAndView findRoleList(RolePage page) {
        ModelAndView mv = new ModelAndView();

        page.setRows(roleService.findRoleRows(page));
        mv.addObject("rolePage", page);

        // 查询所有的角色信息(计算起始行以及结束行)
        List<Role> roleList = roleService.findRoleList(page);

        mv.addObject("roles", roleList);
        mv.setViewName("role/role_list");
        return mv;
    }

    /**
     * 添加角色
     *
     * @return
     */
    @RequestMapping("/toAddRole.do")
    public String toAddRole() {
        return "role/add_role";
    }

    /**
     * 检查角色名称是否有效
     * 返回<"data",true/false>
     */
    @RequestMapping("/checkRoleName.do")
    public void checkRoleName(String name, HttpServletResponse response) throws IOException {
        // 根据角色名称查询信息
        int success = roleService.findRoleName(name);

        // 创建json对象
        ObjectMapper mapper = new ObjectMapper();

        // 将数据写入
        String json = null;
        if (success > 0) {
            json = mapper.writeValueAsString(false);
        } else {
            json = mapper.writeValueAsString(true);
        }

        PrintWriter pw = response.getWriter();
        pw.print(json);
        pw.flush();
        pw.close();
    }

    /**
     * 添加角色
     */
    @RequestMapping("/addRole.do")
    public String addRole(String name) {
        // 添加角色
        int success = roleService.addRole(name);

        //成功情况下 重定向
        return "redirect:findRoleList.do";
    }

    /**
     * 到编辑角色页面
     */
    @RequestMapping("/toUpdateRole.do")
    public ModelAndView toUpdateRole(Integer id) {

        Role role = roleService.findRoleById(id);
        ModelAndView mv = new ModelAndView();

        mv.addObject("role", role);

        mv.setViewName("role/update_role");

        return mv;
    }

    /**
     * 修改角色名称
     */
    @RequestMapping("/updateRole.do")
    public String updateRole(Integer role_id, String name) {
        Role role = new Role();
        role.setId(role_id);
        role.setName(name);
        // 修改角色名称
        int success = roleService.updateRole(role);
        // 返回
        return "redirect:findRoleList.do";
    }

    /**
     * 删除角色
     */
    @RequestMapping("/deleteRole.do")
    public String deleteRole(Integer id) {
        int success = roleService.delRole(id);
        return "redirect:findRoleList.do";
    }
}
