package com.example.demo.service;

import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.VerboseProgressCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhongxiaoyu
 * @create 2018/4/19
 * @since 1.0.0
 */
public class mybatisCreator {


    public static final String FREEMAKER_TEMPL_NAME="generatorConfig.ftl";
    public static final String TEMPL_OUT_NAME ="D:\\lazy\\tooltest\\src\\main\\resources\\generatorConfig.xml";
    public static void main(String[] args) {
        try {

            //java -jar mybatis-generator-core-1.3.1.jar -configfile ../src/generatorConfig.xml -overwrite
            String rootDirectory = getRootPath();
            // rootDirectory = "D:\\lazy\\Mybatis_Generator" ;
            String[] tablesNames = new String[]{"repay","loan_info"};
            String packageName = "com.tuniu.pay.dst";
            FreeMakerGen.genGeneratorConfig(FREEMAKER_TEMPL_NAME,packageName,TEMPL_OUT_NAME,tablesNames);

            String sourcePath = getPackagePath(rootDirectory,"src",packageName);
            String savePackageName = "com.tuniu.pay.dst";
            String savePath = getPackagePath(rootDirectory,"MVCFile",savePackageName);
/*		exeCmd("java -jar "+rootDirectory+File.separator+"lib"+File.separator+"mybatis-generator-core-1.3.1.jar -configfile "
				+rootDirectory+File.separator+"src"+File.separator+"generatorConfig.xml -overwrite");
		*/
            createBasicMybatisFiles(rootDirectory);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }


    private static void createBasicMybatisFiles(String rootDirectory) {
        List<String> warnings = new ArrayList<String>();
        try {
            //D:\Codes\zhongxiaoyu\dst\branchForTest\tooltest\src\main\resources\generatorConfig.xml
            String configFilePath = rootDirectory+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"generatorConfig.xml";
            boolean overwrite = true;
            File configFile = new File(configFilePath);
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(configFile);
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
                    callback, warnings);
            ProgressCallback progressCallback = new VerboseProgressCallback();
            // myBatisGenerator.generate(null);
            myBatisGenerator.generate(progressCallback);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        for (String warning : warnings) {
            System.out.println(warning);
        }
    }


    private static  String getRootPath(){
        File directory = new File("");//参数为空
        String rootPath = null;
        try {
            rootPath = directory.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(rootPath);
        return rootPath;
    }


    private static String getPackagePath(String rootDirectory,String subDirectory,String packageName){
        String[] packageArr = StringUtils.split(packageName,".");
        StringBuilder sourcePath = new StringBuilder();
        sourcePath.append(rootDirectory);
        if(StringUtils.isNotBlank(subDirectory)){
            sourcePath.append(File.separator+subDirectory);
        }
        for(String str: packageArr){
            sourcePath.append(File.separator+str);
        }
        return  sourcePath.toString();
    }

}