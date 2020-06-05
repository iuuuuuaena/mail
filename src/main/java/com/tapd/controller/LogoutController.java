package com.tapd.controller;

import com.tapd.entities.UserLoginStatus;
import com.tapd.enums.ResponseStatus;
import com.tapd.utils.CookieUtil;
import com.tapd.utils.ResultUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author jxd
 * @Date 2020-06-05  13:52
 * @Version 1.0
 */
@Controller
public class LogoutController  extends BaseController{



    //--------------------------------后台管理----------------------------------
    /**
     * 注销用户
     *
     * @param session
     * @return
     */
    @RequestMapping("/mail/logout")
    public String logoutManagementBackground(HttpSession session) {
        // 让session立即失效
        session.invalidate();
        return "redirect:login";
    }


    //--------------------------------前端商城------------------------------------


    /**
     * 商城注销用户
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/user/logout")
    public Object mallLogout(HttpServletRequest request, HttpServletResponse response){
        // 首先判断 用户登录状态
        UserLoginStatus userLoginStatus = getUserLoginStatus(request);
        if (userLoginStatus == null) {
            // 如果已经注销了，就返回已经注销
            return ResultUtils.fail(ResponseStatus.HAS_LOGOUT);
        }
        // 如果没有注销，就设置cookie的属性Authorization 为null，即登录状态注销
        CookieUtil.setCookie(response, "Authorization", null);
        // 返回注销成功
        return ResultUtils.ok(ResponseStatus.SUCCESS_LOGOUT);
    }
}
