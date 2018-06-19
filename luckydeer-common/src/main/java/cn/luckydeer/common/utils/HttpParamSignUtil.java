package cn.luckydeer.common.utils;

import org.apache.commons.lang.StringUtils;

import cn.luckydeer.common.enums.HttpChannelType;

/**
 * 请求参数签名工具
 * 
 * @author yuanxx
 * @version $Id: HttpParamSignUtil.java, v 0.1 2018年6月19日 下午4:15:47 yuanxx Exp $
 */
public class HttpParamSignUtil {

    /**
     * 
     * 注解：根据请求获取当前登陆渠道
     * @param contextPath
     * @return
     * @author yuanxx @date 2018年6月19日
     */
    public static HttpChannelType getByUrl(String contextPath) {
        if (StringUtils.isBlank(contextPath)) {
            return null;
        }

        String tails[] = StringUtils.split(contextPath, ".");

        if (null == tails || tails.length < 2) {
            return null;
        }
        String tail = tails[(tails.length - 1)];

        if (StringUtils.equalsIgnoreCase("web", tail)) {
            return HttpChannelType.PC;
        }

        if (StringUtils.equalsIgnoreCase("wx", tail)) {
            return HttpChannelType.WEIXIN;
        }

        return null;
    }

}
