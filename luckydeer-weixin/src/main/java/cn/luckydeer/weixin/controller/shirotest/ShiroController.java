package cn.luckydeer.weixin.controller.shirotest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.luckydeer.baseaction.basecontroller.BaseController;
import cn.luckydeer.common.enums.ViewShowEnums;
import cn.luckydeer.common.model.ResponseObj;

@Controller
public class ShiroController extends BaseController {

    @RequestMapping(value = "test.wx", produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public String test() throws Exception {
        return new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
            ViewShowEnums.INFO_SUCCESS.getDetail()).toJson().toString();
    }

}
