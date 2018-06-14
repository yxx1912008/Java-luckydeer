package cn.luckydeer.common.model;

import java.io.Serializable;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import cn.luckydeer.common.enums.ViewShowEnums;

import com.alibaba.fastjson.JSON;

/**
 * 
 * 返回实体类
 * @author yuanxx
 * @version $Id: ResponseObj.java, v 0.1 2018年6月14日 上午11:40:12 yuanxx Exp $
 */
public class ResponseObj implements Serializable {

    /**  serialVersionUID */
    private static final long serialVersionUID = -3424492323959030110L;

    /**  0失败 1成功  */
    private int               status;
    /** 返回信息 */
    private String            showMessage;

    private Object            data;

    /** 错误状态 */
    public ResponseObj(int status, String showMessage) {
        this.status = status;
        this.showMessage = showMessage;
    }

    /** 成功状态 */
    public ResponseObj() {
        this.status = ViewShowEnums.INFO_SUCCESS.getCode();
        this.showMessage = ViewShowEnums.INFO_SUCCESS.getDetail();
    }

    /** 带数据返回 */
    public ResponseObj(Object data) {
        this.data = data;
    }

    /** 返回完整信息 */
    public ResponseObj(int status, String showMessage, Object data) {
        this.status = status;
        this.showMessage = showMessage;
        this.data = data;
    }

    /**
     * 
     * 注解：返回Json
     * @return
     * @author yuanxx @date 2018年6月14日
     */
    public String toJson() {
        //TODO 暂时未考虑前端跨域问题 稍后解决
        String resultJson = null;
        if (null == this.data || (data instanceof String && StringUtils.isBlank(data.toString()))
            || (data instanceof Collection && CollectionUtils.isEmpty((Collection<?>) data))) {
            resultJson = getBlankDataJson();
            return resultJson;
        }
        return JSON.toJSONString(this);
    }

    private String getBlankDataJson() {
        StringBuilder builder = new StringBuilder("{\"status\":");
        builder.append(this.getStatus());
        builder.append(",\"showMessage\":\"");
        builder.append(this.getShowMessage());
        builder.append("\",\"data\":{}}");
        return builder.toString();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getShowMessage() {
        return showMessage;
    }

    public void setShowMessage(String showMessage) {
        this.showMessage = showMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
