package cn.luckydeer.dao.user.dataobject;

import java.util.Date;

/**
 * 系统用户POJO
 * 
 * @author yuanxx
 * @version $Id: SysUserDo.java, v 0.1 2018年6月20日 上午11:25:31 yuanxx Exp $
 */
public class SysUserDo {
    private String  userId;

    private String  nickName;

    private String  phone;

    private String  headPicUrl;

    private String  openId;

    private String  unionId;

    private Date    regTime;

    private Date    updateTime;

    private Integer userStatus;

    private String  regChanel;

    private Integer groupId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHeadPicUrl() {
        return headPicUrl;
    }

    public void setHeadPicUrl(String headPicUrl) {
        this.headPicUrl = headPicUrl;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getRegChanel() {
        return regChanel;
    }

    public void setRegChanel(String regChanel) {
        this.regChanel = regChanel;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}