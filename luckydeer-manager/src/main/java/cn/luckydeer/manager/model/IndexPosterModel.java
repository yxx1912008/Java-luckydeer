package cn.luckydeer.manager.model;

import java.io.Serializable;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.TargetUrl;
import cn.luckydeer.common.constants.webmagic.WebmagicConstant;
import cn.luckydeer.common.constants.webmagic.XpathConstant;
import cn.luckydeer.manager.webmagic.WebMagicManager;

/**
 * 首页海报Model
 * 
 * @author yuanxx
 * @version $Id: IndexPosterModel.java, v 0.1 2018年6月22日 上午10:49:01 yuanxx Exp $
 */
@TargetUrl(value = WebmagicConstant.CAT_HOST)
@ExtractBy(value = XpathConstant.INDEX_POSTER_TARGET_XPATH, multi = true)
public class IndexPosterModel implements Serializable {

    /**  */
    private static final long serialVersionUID = 5032183263812631794L;

    /**  图片Url地址 */
    @ExtractBy(value = XpathConstant.INDEX_POSTER_IMG_XPATH)
    private String            imgUrl;

    /** 图片链接的Url地址  */
    @ExtractBy(value = XpathConstant.INDEX_POSTER_TARGET_URL_XPATH)
    private String            targetUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public static void main(String[] args) {

        OOSpider.create(Site.me(), new WebMagicManager(), IndexPosterModel.class)
            .addUrl(WebmagicConstant.CAT_HOST).thread(5).run();

    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

}
