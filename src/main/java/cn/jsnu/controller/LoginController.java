package cn.jsnu.controller;

import cn.jsnu.domain.Admin;
import cn.jsnu.service.AdminService;
import cn.jsnu.util.ImageUtil;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录模块实现
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    /*1.1 查找到login.jsp页面*/
    @RequestMapping("/toLogin.do")
    public String toLogin() {
        System.out.println("进入登录页面");
        return "main/login";
    }

    /*1.2 获取验证码图片
     * HttpServletResponse: 将验证码图片写出到页面中
     * HttpSession: 存储验证码的值
     * */
    @RequestMapping("/createImage.do")
    public void createImage(HttpServletResponse response,
                            HttpSession session) throws IOException {
        // 2.1 获取map对象
        Map<String, BufferedImage> imageMap =
                ImageUtil.createImage();
        // 2.2 获取验证码内容  9527
        String code = imageMap.keySet().iterator().next();
        // 2.3 将code保存到session中用于登录对比
        session.setAttribute("imageCode", code);
        // 2.4 根据code获取图片
        BufferedImage image = imageMap.get(code);
        // 2.5 设置写出的响应头 text/html  text/plain
        response.setContentType("image/jpeg");
        // 2.6 设置输出流
        OutputStream ops = response.getOutputStream();
        // 2.7 写出
        ImageIO.write(image, "jpeg", ops);
        // 2.8  关闭
        ops.close();

    }

    /**
     * 1.3 登录功能实现
     */
    private final static int SUCCESS = 0; // 登录成功
    private final static int ADMIN_CODE_ERROR = 1;// 用户名失败
    private final static int PASSWORD_ERROR = 2;// 密码错误
    private final static int IMAGE_CODE_ERROR = 3;// 验证码失败

    @Autowired
    private AdminService adminService;

    @RequestMapping("/login.do")
    @ResponseBody
    public Map<String, Object> login(String adminCode,
                                     String password,
                                     String code,
                                     HttpSession session) {
        // 3.1 创建map对象
        Map<String, Object> result = new HashMap<>();
        // 3.2 获取session中的验证码
        String imageCode =
                (String) session.getAttribute("imageCode");
        // 3.3 判断验证码是否正确  equalsIgnoreCase不区分大小写
        if (code == null || !code.equalsIgnoreCase(imageCode)) {
            result.put("flag", IMAGE_CODE_ERROR);
            return result;
        }
        // 3.4 根据用户的code查询信息
        Admin admin = adminService.findByCode(adminCode);
        // 3.5 判断用户是否为空
        if (admin == null) {
            result.put("flag", ADMIN_CODE_ERROR);
            return result;
        }
        // 3.6 判断密码是否一致
        if (!admin.getPassword().equals(password)) {
            result.put("flag", PASSWORD_ERROR);
            return result;
        } else {
            // 3.7 登录成功
            session.setAttribute("admin", admin);

            result.put("flag", SUCCESS);
        }

        return result;
    }

    // 1.4 进入主页
    @RequestMapping("/toIndex.do")
    public String toIndex() {
        System.out.println("进入主页了");
        return "main/index";
    }
}
