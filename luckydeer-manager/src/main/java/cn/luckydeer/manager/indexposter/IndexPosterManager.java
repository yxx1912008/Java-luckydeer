package cn.luckydeer.manager.indexposter;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.JMException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
            //TODO 此处增加日志
            //            logger.info("读取海报缓存过期时间失败");
            Date expiryTime = DateUtilSelf.increaseHour(date, 2);
            posterCache.put("expiryTime", expiryTime);

        }

        return null;
    }

    public List<IndexPosterModel> getIndexPosterModels() throws Exception, Exception {
        Spider indexPosterSpider = OOSpider.create(Site.me(), new CatPipeLine(),
            IndexPosterModel.class).addUrl(WebmagicConstant.CAT_HOST);

        try {
            /** 注册爬虫监听器  */
            CustomSpiderMonitor spiderMonitor = new CustomSpiderMonitor();

            /** 注册爬虫  */
            spiderMonitor.register(indexPosterSpider);
            /** 启动爬虫  */
            indexPosterSpider.thread(5).start();

            List<SpiderListener> list = indexPosterSpider.getSpiderListeners();

            MonitorSpiderListener listener = (MonitorSpiderListener) list.get(0);

            SpiderStatusMXBean spiderStatusMBean = spiderMonitor.getSpiderStatusMBean(
                indexPosterSpider, listener);
            while (true) {
                if (StringUtils.equals(spiderStatusMBean.getStatus(), Status.Stopped.name())) {
                    System.out.println("爬虫停止了");
                    break;
                }
            }

        } catch (JMException e) {
            logger.error("爬虫注册失败：", e);
            return null;
        }
        return null;
    }

    public static void main(String[] args) throws Exception {

        IndexPosterManager manager = new IndexPosterManager();

        manager.getIndexPosterModels();

    }
}
