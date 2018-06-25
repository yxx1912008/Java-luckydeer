package cn.luckydeer.manager.webmagic.pipeline;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;
import cn.luckydeer.dao.webmagic.dataobject.CatIndexPosterDo;
import cn.luckydeer.manager.indexposter.IndexPosterManager;
import cn.luckydeer.webmagic.model.IndexPosterModel;

public class CatPipeLine implements PageModelPipeline<Object> {

    private IndexPosterManager indexPosterManager;

    @Override
    public void process(Object obj, Task task) {
        System.out.println("------正在处理-------");
        if (obj instanceof IndexPosterModel) {
            IndexPosterModel model = (IndexPosterModel) obj;
            CatIndexPosterDo record = new CatIndexPosterDo();
            record.setGmtCreate(new Date());
            record.setImgUrl(model.getImgUrl());
            record.setTargetUrl(model.getTargetUrl());
            indexPosterManager.insert(record);
        }
        System.out.println(ToStringBuilder.reflectionToString(obj));
    }

    public IndexPosterManager getIndexPosterManager() {
        return indexPosterManager;
    }

    public void setIndexPosterManager(IndexPosterManager indexPosterManager) {
        this.indexPosterManager = indexPosterManager;
    }

}
