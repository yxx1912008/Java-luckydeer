package cn.luckydeer.weixin.controller.shirotest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.luckydeer.baseaction.annotation.IgnoreAuth;
import cn.luckydeer.baseaction.basecontroller.BaseController;
import cn.luckydeer.baseaction.utils.OperationContextHolder;
import cn.luckydeer.common.enums.ViewShowEnums;
import cn.luckydeer.common.model.ResponseObj;
import cn.luckydeer.model.user.UserSessionModel;

@Controller
public class ShiroController extends BaseController {

    @RequestMapping(value = "test.wx", produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public String test() throws Exception {
        return new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
            ViewShowEnums.INFO_SUCCESS.getDetail()).toJson(request, response);
    }

    /**
     * 
     * 注解：测试登陆（不进行拦截）
     * @return
     * @author yuanxx @date 2018年6月15日
     */
    @IgnoreAuth
    @RequestMapping(value = "testLogin.wx", produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public String testLogin(HttpServletRequest request, HttpServletResponse response, String userId) {

        if (StringUtils.isBlank(userId)) {
            return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(), "参数有误").toJson(request,
                response).toString();
        }

        HttpSession session = request.getSession();
        System.out.println("当前SessionId为:" + session.getId());
        session.setAttribute("userId", userId);
        UserSessionModel sessionUser = new UserSessionModel();
        sessionUser.setUserId(userId);
        OperationContextHolder.setIsLoggerUser(sessionUser);
        System.out.println("局部线程缓存的用户数据是:" + OperationContextHolder.getSessionUser().getUserId());
        Cookie cookie = new Cookie("userId", userId);
        cookie.setMaxAge(60 * 2);
        response.addCookie(cookie);
        return new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
            ViewShowEnums.INFO_SUCCESS.getDetail()).toJson(request, response);
    }

    /**
     * 
     * 注解：获取当前登陆信息
     * @return
     * @author yuanxx @date 2018年6月15日
     */
    @RequestMapping(value = "testGetCurrentUser.wx", produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    @IgnoreAuth
    public String testGetCurrentUser(HttpServletRequest request, HttpServletResponse response) {
        UserSessionModel userSession = OperationContextHolder.getSessionUser();
        System.out.println(request.getSession().getId());
        System.out.println(request.getSession().getAttribute("userId"));
        if (StringUtils.isNotBlank(userSession.getUserId())) {
            System.out.println("当前用户Id为空");
            return new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
                ViewShowEnums.INFO_SUCCESS.getDetail(), userSession.getUserId()).toJson(request,
                response);
        }
        return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(),
            ViewShowEnums.ERROR_FAILED.getDetail()).toJson(request, response);
    }

}
