package cn.luckydeer.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.luckydeer.common.annotation.IgnoreAuth;

/**
 * 权限token验证
 * 
 * @author yuanxx
 * @version $Id: AuthorizationInterceptor.java, v 0.1 2018年6月14日 下午1:40:29 yuanxx Exp $
 */
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    /**
     * 预处理
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        /** 判断是否包含验证token的注解，如果包含，就验证，不包含就返回 */
        IgnoreAuth auth;
        if (handler instanceof HandlerMethod) {
            auth = ((HandlerMethod) handler).getMethodAnnotation(IgnoreAuth.class);
        } else {
            return false;
        }
        if (null != auth) {
            return true;
        }
        System.out.println("验证Token");
        return true;
    }

}
