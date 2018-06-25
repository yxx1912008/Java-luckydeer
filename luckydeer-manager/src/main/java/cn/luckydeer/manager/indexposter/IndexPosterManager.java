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

    /**
     * 
     * 注解：通过爬虫 抓取首页海报信息
     * 并存入数据库
     * @return
     * @author yuanxx @date 2018年6月25日
     * @param <T>
     */
    //TODO 暂时没有加入多线程的情况
    public <T> boolean getIndexPosterForUrl(String url, Class<T> modelClass) {
        logger.info("开始爬取内容:class=" + modelClass + ";url:" + url);
        /** 1.创建首页抓取爬虫  */
        Spider indexPosterSpider = OOSpider.create(Site.me(), new CatPipeLine(),
            IndexPosterModel.class).addUrl(WebmagicConstant.CAT_HOST);
        /** 2.注册爬虫监听器  */
        CustomSpiderMonitor spiderMonitor = new CustomSpiderMonitor();
        /** 3.注册爬虫  */
        try {
            spiderMonitor.register(indexPosterSpider);
            /** 启动爬虫 使用默认线程数目  */
            indexPosterSpider.thread(WebmagicConstant.DEFAULT_THREAD).start();
            /** 获取当前爬虫的监听器  */
            List<SpiderListener> list = indexPosterSpider.getSpiderListeners();
            if (CollectionUtils.isEmpty(list)) {
                logger.error("获取爬虫监听器失败");
                return false;
            }
            /** 获取监听器实例  */
            MonitorSpiderListener listener = (MonitorSpiderListener) list.get(0);
            /** 获取爬虫状态  */
            SpiderStatusMXBean spiderStatusMBean = spiderMonitor.getSpiderStatusMBean(
                indexPosterSpider, listener);
            while (true) {
                if (StringUtils.equals(spiderStatusMBean.getStatus(), Status.Stopped.name())) {
                    logger.info("爬虫抓取结束");
                    return true;
                }
            }
        } catch (JMException e) {
            logger.error("首页爬虫注册失败:" + WebmagicConstant.CAT_HOST, e);
        }
        return false;
    }

    public static void main(String[] args) throws Exception {

        IndexPosterManager manager = new IndexPosterManager();
        String url = WebmagicConstant.CAT_HOST;
        manager.getIndexPosterForUrl(url, IndexPosterModel.class);

    }
}
