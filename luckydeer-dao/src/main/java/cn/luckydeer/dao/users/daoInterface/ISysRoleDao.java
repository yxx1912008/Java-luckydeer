package cn.luckydeer.dao.users.daoInterface;

import java.util.List;

/**
 * 角色管理 
 * 
 * @author yuanxx
 * @version $Id: ISysRoleDao.java, v 0.1 2018年6月14日 上午10:42:53 yuanxx Exp $
 */
public interface ISysRoleDao {

    /**
     * 查询用户创建的角色ID列表
     */
    List<String> queryRoleIdList(String createUserId);

}