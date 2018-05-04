package com.example.demo.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhongxiaoyu
 * @create 2018/4/19
 * @since 1.0.0
 */
public class FreeMakerGen {

   private final static String DIR_PATH = "D:\\lazy\\gitTry\\demo\\src\\main\\resources\\templates" ;

    public static void genGeneratorConfig(String templateName,String packageName,String outPath,String[] tablesNames) throws IOException, TemplateException {

        //第一步：实例化Freemarker的配置类
        Configuration conf = new Configuration();
        //第二步：给配置类设置路径
        String dir = DIR_PATH;
        conf.setDirectoryForTemplateLoading(new File(dir));

        Template template = conf.getTemplate(templateName);

        //第三步：处理模板及数据之间将数据与模板合成一个HTML
        //第四步：输出html
        Writer out = new FileWriter(new File(outPath));
        //定义数据
        //  Map root = new HashMap();
        //执行生成
        //定义数据
        Map<String,Object> root = new HashMap<String,Object>();
        root.put("mySqlDrivePath","D:\\lazy\\tooltest\\lib\\mysql-connector-java-5.0.8-bin.jar");
        root.put("DBPath", "localhost:3306/dst");
        root.put("DBUser", "root");
        root.put("DBPwd", "");
        root.put("poPackageName", packageName+".domain.po");
        root.put("mapPackageName", packageName+".dao");
        root.put("daoPackageName", packageName+".mapper");
        root.put("poPath", "D:\\lazy\\tooltest\\MVCFile\\genFile");
        root.put("mapPath", "D:\\lazy\\tooltest\\MVCFile\\genFile");
        root.put("daoPath", "D:\\lazy\\tooltest\\MVCFile\\genFile");
        //遍历List<Map>
        List<TableSetting> tableSettings = new ArrayList<>();
        TableSetting setting = null;
        for(String name:tablesNames){
            setting = new TableSetting();
            String[] nameArr = name.split("_");
            String objName = StringUtils.EMPTY;
            for(String nameItem:nameArr){
                objName += nameItem.substring(0,1).toUpperCase()+nameItem.substring(1);
            }
            setting.setTableName(name);
            setting.setObjectName(objName);
            tableSettings.add(setting);
        }
        root.put("tableSettings", tableSettings);
        template.process(root, out);
        out.flush();
        out.close();
    }

    @Getter
    @Setter
    public static class TableSetting {
        private String tableName;
        private String objectName;
    }



}