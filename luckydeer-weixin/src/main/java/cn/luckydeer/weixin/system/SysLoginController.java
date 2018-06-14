package cn.luckydeer.weixin.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.luckydeer.baseaction.shiro.utils.ShiroUtils;
import cn.luckydeer.common.model.ResponseObj;

/**
 * 系统登陆
 * 
 * @author yuanxx
 * @version $Id: SysLoginController.java, v 0.1 2018年6月14日 上午11:47:40 yuanxx Exp $
 */
@Controller
@RequestMapping(value = "/sys")
public class SysLoginController {

    
    
    /**
     * 
     * 注解：注销登陆
     * @return
     * @author yuanxx @date 2018年6月14日
     */
    @RequestMapping(value = "/logout.do", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.GET)
    @ResponseBody
    public String logout() {
        ShiroUtils.logout();
        return new ResponseObj().toJson().toString();
    }

}
