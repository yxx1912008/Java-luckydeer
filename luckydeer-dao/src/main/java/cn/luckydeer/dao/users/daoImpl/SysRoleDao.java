package cn.luckydeer.dao.users.daoImpl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import cn.luckydeer.dao.users.daoInterface.ISysRoleDao;

public class SysRoleDao extends SqlSessionDaoSupport implements ISysRoleDao {

    private String namespace;

    @Override
    public List<String> queryRoleIdList(String createUserId) {
        if (StringUtils.isBlank(createUserId)) {
            return null;
        }
        return getSqlSession().selectList(namespace + "queryRoleIdList", createUserId);
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

}
