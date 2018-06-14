package cn.luckydeer.dao.test.userrole;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.luckydeer.dao.test.base.BaseTest;
import cn.luckydeer.dao.users.daoInterface.ISysRoleDao;

public class SysRoleTest extends BaseTest {

    @Autowired
    private ISysRoleDao sysRoleDao;

    @Test
    public void queryRoleIdList() throws Exception {
        String createUserId = "1";
        List<String> list = sysRoleDao.queryRoleIdList(createUserId);
        for (String string : list) {
            System.out.println(string);
        }
    }

}
