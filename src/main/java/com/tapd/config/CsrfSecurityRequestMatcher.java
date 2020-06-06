package com.tapd.config;

import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Author jxd
 * @Date 2020-06-06  07:53
 * @Version 1.0
 */
public class CsrfSecurityRequestMatcher implements RequestMatcher {

    private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");

    /**
     * 这个类用于重写RequestMatcher，自己配置请求的访问
     * @param request
     * @return
     */
    @Override
    public boolean matches(HttpServletRequest request) {
        List<String> unExecludeUrls = new ArrayList<>();
        //unExecludeUrls.add("/api/test");//（不允许post请求的url路径）此处根据自己的需求做相应的逻辑处理

        if (unExecludeUrls != null && unExecludeUrls.size() > 0) {
            String servletPath = request.getServletPath();
            request.getParameter("");
            for (String url : unExecludeUrls) {
                if (servletPath.contains(url)) {
                    return true;
                }
            }
        }
        return allowedMethods.matcher(request.getMethod()).matches();
    }
}
