package cn.luckydeer.dao.users.daoInterface;

import java.util.List;

public interface ISysUserDao {

    /**
     * 查询用户的所有权限
     *
     * @param userId 用户ID
     */
    List<String> queryAllPerms(String userId);

    /**
     * 查询用户的所有菜单ID
     */
    List<String> queryAllMenuId(String userId);

}