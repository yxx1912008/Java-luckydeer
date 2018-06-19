package cn.luckydeer.manager.token;

import java.util.Date;

import cn.luckydeer.common.constants.HeaderContants;
import cn.luckydeer.common.utils.DateUtilSelf;
import cn.luckydeer.common.utils.SelfStringUtils;
import cn.luckydeer.memcached.api.DistributedCached;
import cn.luckydeer.memcached.enums.CachedType;
import cn.luckydeer.model.token.TokenModel;

/**
 * Token管理
 * 
 * @author yuanxx
 * @version $Id: TokenManager.java, v 0.1 2018年6月19日 下午5:05:07 yuanxx Exp $
 */
public class TokenManager {

    private DistributedCached distributedCached;

    /**
     * 
     * 注解：设置Token信息
     * @param userId
     * @return
     * @author yuanxx @date 2018年6月19日
     */
    public boolean setToken(String token, String userId) {

        TokenModel tokenModel = (TokenModel) distributedCached.get(CachedType.USER_SESSION, token);
        Date date = new Date();
        if (null == tokenModel) {
            tokenModel = new TokenModel();
            tokenModel.setToken(SelfStringUtils.getRandomString(32));
            tokenModel.setUpdateTime(date);
            tokenModel.setExpireTime(DateUtilSelf.increaseHour(date,
                HeaderContants.TOKEN_FAILURE_TIME));
            tokenModel.setUserId(userId);
            return distributedCached.put(CachedType.USER_SESSION, tokenModel.getToken(),
                HeaderContants.TOKEN_FAILURE_TIME);
        } else {

            tokenModel.setUpdateTime(new Date());
            tokenModel.setExpireTime(DateUtilSelf.increaseHour(date,
                HeaderContants.TOKEN_FAILURE_TIME));
            return distributedCached.update(CachedType.USER_SESSION, token,
                HeaderContants.TOKEN_FAILURE_TIME, tokenModel);
        }
    }

}
