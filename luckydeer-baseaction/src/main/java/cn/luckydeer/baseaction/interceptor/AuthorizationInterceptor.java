package cn.luckydeer.baseaction.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Token校验
 * 
 * @author yuanxx
 * @version $Id: AuthorizationInterceptor.java, v 0.1 2018年6月15日 下午1:51:28 yuanxx Exp $
 */
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        
        System.out.println("方法预校验");
        
        return super.preHandle(request, response, handler);
    }

    
    
    
    
}
