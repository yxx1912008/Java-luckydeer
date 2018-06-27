package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.time.DateFormatUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

/**
 * 自动生成代码工具
 * 
 * @author yuanxx
 * @version $Id: App.java, v 0.1 2018年6月26日 下午7:56:48 yuanxx Exp $
 */
public class App {

    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        // 使用ClassLoader加载properties配置文件生成对应的输入流
        InputStream in = App.class.getClassLoader().getResourceAsStream("AutoCode.properties");
        // 使用properties对象加载输入流
        properties.load(in);

        /** FreeMarker 配置  */
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        /**  设置模板存放路径 */
        cfg.setDirectoryForTemplateLoading(new File(properties.getProperty("TEMPLATE_PATH")));
        /** 默认编码类型  */
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("classPath", properties.get("PACKAGE_NAME"));
        dataMap.put("className", properties.get("CLASS_NAME"));
        dataMap.put("time", DateFormatUtils.format(new Date(), "yyyy年MM月dd日 HH:mm:ss"));

        /** 获取模板  */
        Template template = cfg.getTemplate("Hello.ftl");

        /** 生成数据  */
        File docFile = new File(properties.getProperty("CLASS_PATH") + "\\"
                                + properties.getProperty("CLASS_NAME") + ".java");
        /** 写出流  */
        Writer out = null;
        out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
        template.process(dataMap, out);
        System.out.println("java 文件创建成功 !");
    }

}
