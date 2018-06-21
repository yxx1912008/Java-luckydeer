package cn.luckydeer.test.user;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.luckydeer.dao.user.daoInterface.ISysUserDao;
import cn.luckydeer.dao.user.dataobject.SysUserDo;
import cn.luckydeer.test.base.BaseTest;

/**
 * 
 * 系统用户信息测试
 * @author yuanxx
 * @version $Id: SysUserDaoTest.java, v 0.1 2018年6月21日 下午3:59:30 yuanxx Exp $
 */
public class SysUserDaoTest extends BaseTest {

    @Autowired
    private ISysUserDao sysUserDao;

    /**
     * 
     * 测试：插入一条系统用户信息
     * @throws Exception
     * @author yuanxx @date 2018年6月21日
     */
    @Test
    public void testName() throws Exception {

        SysUserDo record = new SysUserDo();
        String userId = "xxxx";
        record.setUserId(userId);
        int result = sysUserDao.insert(record);
        System.out.println(result);
    }

}
