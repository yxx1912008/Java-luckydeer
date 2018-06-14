package cn.luckydeer.dao.users.dataobject;

import java.io.Serializable;
import java.util.Date;

public class SysRoleDo implements Serializable {
    private Long              roleId;

    private String            roleName;

    private String            remark;

    private String            createUserId;

    private Date              createTime;

    private Long              deptId;

    private static final long serialVersionUID = 1L;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SysRoleDo other = (SysRoleDo) that;
        return (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(
            other.getRoleId()))
               && (this.getRoleName() == null ? other.getRoleName() == null : this.getRoleName()
                   .equals(other.getRoleName()))
               && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(
                   other.getRemark()))
               && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this
                   .getCreateUserId().equals(other.getCreateUserId()))
               && (this.getCreateTime() == null ? other.getCreateTime() == null : this
                   .getCreateTime().equals(other.getCreateTime()))
               && (this.getDeptId() == null ? other.getDeptId() == null : this.getDeptId().equals(
                   other.getDeptId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRoleId() == null) ? 0 : getRoleId().hashCode());
        result = prime * result + ((getRoleName() == null) ? 0 : getRoleName().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getDeptId() == null) ? 0 : getDeptId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", roleId=").append(roleId);
        sb.append(", roleName=").append(roleName);
        sb.append(", remark=").append(remark);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", deptId=").append(deptId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}