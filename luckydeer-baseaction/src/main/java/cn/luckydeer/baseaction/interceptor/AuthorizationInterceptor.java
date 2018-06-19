package cn.luckydeer.baseaction.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.luckydeer.baseaction.annotation.IgnoreAuth;
import cn.luckydeer.baseaction.constant.UserDefaultConstant;
import cn.luckydeer.baseaction.exception.TokenException;
import cn.luckydeer.baseaction.utils.OperationContextHolder;
import cn.luckydeer.common.enums.ViewShowEnums;

/**
 * Token校验
 * 针对制定的请求进行校验
 * 如果请求想要不通过校验
 * 可以通过自己写的注解
 * 来进行忽略校验的请求
 * 
 * @author yuanxx
 * @version $Id: AuthorizationInterceptor.java, v 0.1 2018年6月15日 下午1:51:28 yuanxx Exp $
 */
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        IgnoreAuth auth;
        if (handler instanceof HandlerMethod) {
            /** 如果方法中包含注解 直接忽略Token验证 */
            auth = ((HandlerMethod) handler).getMethodAnnotation(IgnoreAuth.class);
        } else {
            return true;
        }
        if (null != auth) {
            return true;
        }

        /** 1.获取Token */
        String token = request.getHeader(UserDefaultConstant.LOGIN_TOKEN_KEY);

        /** 2.如果Token获取失败  */
        if (StringUtils.isBlank(token)) {
            throw new TokenException("您尚未登陆,请登录", ViewShowEnums.ERROR_FAILED.getStatus());
        }

        /** 3.查询token信息 */
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        System.out.println("清除系统会话");
        /** 清楚当前现线程的 缓存会话 */
        OperationContextHolder.clearUser();
        super.afterCompletion(request, response, handler, ex);
    }

}
