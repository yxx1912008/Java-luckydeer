package cn.luckydeer.common.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 域设置
 * 
 * @author yuanxx
 * @version $Id: DomainUtils.java, v 0.1 2018年6月15日 下午5:27:38 yuanxx Exp $
 */
public class DomainUtils {

    /** 日志. */
    private static final Log logger = LogFactory.getLog(DomainUtils.class);

    /**
     * 注解：
     * 参考：http://blog.csdn.net/mygrilzhuyulin/article/details/52690129
     * 前端关键加入
     * angular.module("app").config(function ($httpProvider) {
     * $httpProvider.defaults.withCredentials = true;
     * $httpProvider.defaults.headers.common['Authorization'] = "89757";
     * })
     * 
     * ajax:
     * jquery的post方法请求：
     *
     * $.ajax({
     *   type: "POST",
     *   url: "http://xxx.com/api/test",
     *  dataType: 'jsonp',
     *  xhrFields: {withCredentials: true},
     *  crossDomain: true,
     * })
     * 
     * 后台关键
     * response.setHeader("Access-Control-Allow-Credentials", "true");
     * @param request
     * @param response
     * @author yuanxx
     */
    public static void setAccessContrlAllowOrigin(HttpServletRequest request,
                                                  HttpServletResponse response) {

        String origin = request.getHeader("Origin");

    }

}
