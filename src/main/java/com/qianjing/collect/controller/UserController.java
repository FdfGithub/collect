package com.qianjing.collect.controller;

import com.qianjing.collect.comm.Const;
import com.qianjing.collect.comm.Response;
import com.qianjing.collect.domain.User;
import com.qianjing.collect.dto.RegisterDto;
import com.qianjing.collect.exception.OutException;
import com.qianjing.collect.form.UserForm;
import com.qianjing.collect.service.TaskService;
import com.qianjing.collect.service.UserService;
import com.qianjing.collect.util.ObjectUtil;
import com.qianjing.collect.util.SessionUtil;
import com.qianjing.collect.vo.TaskVo;
import com.qianjing.collect.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectUtil objectUtil;

    @PostMapping("/register")
    @ResponseBody
    public Response<Void> register(UserForm form, String code) {
        RegisterDto dto = new RegisterDto();
        dto.setCode(code);
        dto.setUser(objectUtil.assembleObject(form, User.class));
        dto.setLocalCode(SessionUtil.get("localCode", String.class));
        return userService.register(dto);
    }

    @PostMapping("/admin/login")
    public String loginAdmin(String email, String userPwd, HttpServletResponse response) throws ServletException, IOException {
        UserVo user = userService.login(email, userPwd).getData();
        if(user.getUserRole() == Const.UserRole.ADMIN){
            response.sendRedirect("/task/collects/page");
        }
        throw new OutException("不是管理员身份");
    }

    @ResponseBody
    @GetMapping("/getCode")
    public Response<Void> getCode(String email) {
        userService.getCode(email);
        return Response.returnSuccess(null, "验证码已发送");
    }
}
