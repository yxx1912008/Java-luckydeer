package cn.luckydeer.baseaction.shiro.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * 
 * Shiro工具类
 * @author yuanxx
 * @version $Id: ShiroUtils.java, v 0.1 2018年6月14日 上午11:24:23 yuanxx Exp $
 */
public class ShiroUtils {

    /**
     * 
     * 注解：获取用户session
     * @return
     * @author yuanxx @date 2018年6月14日
     */
    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * 
     * 注解：获取subject
     * @return
     * @author yuanxx @date 2018年6月14日
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 
     * 注解：获取用户对象
     * @return
     * @author yuanxx @date 2018年6月14日
     */
    //TODO 将要返回model对象，暂时先不去实现
    public static Object getUserEntity() {
        return (Object) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 
     * 注解：获取当前登录用户的Id
     * @return
     * @author yuanxx @date 2018年6月14日
     */
    //TODO 返回用户Id暂未实现
    public static String getUserId() {
        getUserEntity();
        return null;
    }

    /**
     * 
     * 注解：设置session指定键值
     * @param key
     * @param value
     * @author yuanxx @date 2018年6月14日
     */
    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    /**
     * 
     * 注解：通过Key获取value
     * @param key
     * @return
     * @author yuanxx @date 2018年6月14日
     */
    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    /**
     * 
     * 注解：判断用户是否登陆
     * @return
     * @author yuanxx @date 2018年6月14日
     */
    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

    /**
     * 
     * 注解：用户注销登陆
     * @author yuanxx @date 2018年6月14日
     */
    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    /**
     * 
     * 注解：根据Key清除Value
     * @param key
     * @return
     * @author yuanxx @date 2018年6月14日
     */
    public static boolean removeAttribute(String key) {
        if (StringUtils.isBlank(key)) {
            return false;
        }
        try {
            String kaptcha = getSessionAttribute(key).toString();
            if (StringUtils.isNotBlank(kaptcha)) {
                getSession().removeAttribute(key);
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

}
