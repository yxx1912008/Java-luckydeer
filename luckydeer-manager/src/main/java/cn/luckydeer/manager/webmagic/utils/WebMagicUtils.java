package cn.luckydeer.manager.webmagic.utils;

import java.util.List;

import javax.management.JMException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.SpiderListener;
import us.codecraft.webmagic.Spider.Status;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.monitor.SpiderStatusMXBean;
import us.codecraft.webmagic.monitor.SpiderMonitor.MonitorSpiderListener;
import cn.luckydeer.manager.webmagic.monitor.CustomSpiderMonitor;
import cn.luckydeer.manager.webmagic.pipeline.CatPipeLine;
import cn.luckydeer.webmagic.constants.WebmagicConstant;
import cn.luckydeer.webmagic.model.IndexPosterModel;

/**
 * 自写爬虫框架工具类
 * 
 * @author yuanxx
 * @version $Id: WebMagicUtils.java, v 0.1 2018年6月25日 上午11:21:49 yuanxx Exp $
 */
public class WebMagicUtils {

    Logger logger = LoggerFactory.getLogger("LUCKYDEER-MANAGER-LOG");

    /**
     * 
     * 注解：通过爬虫爬取信息
     * 并进行后续处理
     * @param url 请求地址
     * @param modelClass model class
     * @return
     * @author yuanxx @date 2018年6月25日
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
                /**  根据状态判断爬虫是否抓取完毕 */
                if (StringUtils.equals(spiderStatusMBean.getStatus(), Status.Stopped.name())) {
                    logger.info("爬虫抓取结束");
                    return true;
                }
            }
        } catch (JMException e) {
            logger.error("首页爬虫注册失败:" + WebmagicConstant.CAT_HOST, e);
        } catch (Exception e) {
            logger.error("爬虫抓取失败", e);
        }
        return false;
    }

}
