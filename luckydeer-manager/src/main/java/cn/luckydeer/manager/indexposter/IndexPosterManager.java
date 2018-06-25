package cn.luckydeer.manager.indexposter;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.JMException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Spider.Status;
import us.codecraft.webmagic.SpiderListener;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.monitor.SpiderMonitor.MonitorSpiderListener;
import us.codecraft.webmagic.monitor.SpiderStatusMXBean;
import cn.luckydeer.common.utils.DateUtilSelf;
import cn.luckydeer.manager.webmagic.monitor.CustomSpiderMonitor;
import cn.luckydeer.manager.webmagic.pipeline.CatPipeLine;
import cn.luckydeer.webmagic.constants.WebmagicConstant;
import cn.luckydeer.webmagic.model.IndexPosterModel;

/**
 * 
 * 购物猫首页海报管理
 * @author yuanxx
 * @version $Id: IndexPosterManager.java, v 0.1 2018年6月22日 下午5:07:50 yuanxx Exp $
 */
public class IndexPosterManager {

    Logger                             logger      = LoggerFactory
                                                       .getLogger("LUCKYDEER-MANAGER-LOG");

    /** 缓存 用来缓存首页海报  */
    private static Map<String, Object> posterCache = new HashMap<String, Object>();

    /**
     * 
     * 注解：获取首页海报
     * @return
     * @author yuanxx @date 2018年6月22日
     */
    private List<IndexPosterModel> getIndexPoster() {

        Date date = new Date();

        /**  是否超过 最后更新时间 默认缓存更新时间为每两个小时  */
        if (null == posterCache.get("expiryTime")
            || (DateUtilSelf.calculateDecreaseMinute((Date) posterCache.get("expiryTime"), date) > 120)) {
            logger.info("读取海报缓存过期时间失败");
            Date expiryTime = DateUtilSelf.increaseHour(date, 2);
            posterCache.put("expiryTime", expiryTime);
            //            List<IndexPosterModel> list = getIndexPosterForUrl();

        }

        return null;
    }
}
