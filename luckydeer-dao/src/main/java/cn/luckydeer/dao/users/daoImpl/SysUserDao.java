package cn.luckydeer.dao.users.daoImpl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import cn.luckydeer.dao.users.daoInterface.ISysUserDao;

public class SysUserDao extends SqlSessionDaoSupport implements ISysUserDao {

    private String namespace;

    @Override
    public List<String> queryAllPerms(String userId) {
        if (StringUtils.isBlank(userId)) {
            return null;
        }
        return getSqlSession().selectList(namespace + "queryAllPerms", userId);
    }

    @Override
    public List<String> queryAllMenuId(String userId) {
        if (StringUtils.isBlank(userId)) {
            return null;
        }
        return getSqlSession().selectList(namespace + "queryAllMenuId", userId);
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

}
