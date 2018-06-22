package cn.luckydeer.manager.webmagic;

import org.apache.commons.lang3.builder.ToStringBuilder;

import cn.luckydeer.manager.model.IndexPosterModel;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

public class WebMagicManager implements PageModelPipeline<Object> {

    @Override
    public void process(Object obj, Task task) {
        System.out.println("------正在处理-------");
        if (obj instanceof IndexPosterModel) {
            System.out.println("当前操作是海报类型");
        }
        System.out.println(ToStringBuilder.reflectionToString(obj));

    }
}
