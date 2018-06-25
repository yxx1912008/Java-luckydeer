package cn.luckydeer.manager.webmagic.pipeline;

import org.apache.commons.lang3.builder.ToStringBuilder;

import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;
import cn.luckydeer.dao.webmagic.daoInterface.ICatIndexPosterDao;
import cn.luckydeer.dao.webmagic.dataobject.CatIndexPosterDo;
import cn.luckydeer.webmagic.model.IndexPosterModel;

public class CatPipeLine implements PageModelPipeline<Object> {

    private ICatIndexPosterDao catIndexPosterDao;

    @Override
    public void process(Object obj, Task task) {
        System.out.println("------正在处理-------");
        if (obj instanceof IndexPosterModel) {
            System.out.println("当前操作是海报类型");
        }
        System.out.println(ToStringBuilder.reflectionToString(obj));
    }

    public ICatIndexPosterDao getCatIndexPosterDao() {
        return catIndexPosterDao;
    }

    public void setCatIndexPosterDao(ICatIndexPosterDao catIndexPosterDao) {
        this.catIndexPosterDao = catIndexPosterDao;
    }

}
