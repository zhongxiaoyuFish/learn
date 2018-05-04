<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<generatorConfiguration>
    <!--数据库驱动-->
    <!-- 设置mysql驱动路径 -->
    <classPathEntry location="${mySqlDrivePath}"/>
    <context id="context1" targetRuntime="MyBatis3">
        <commentGenerator>
            <property name="suppressDate" value="true"/>
            <property name="suppressAllComments" value="true"/>
        </commentGenerator>
        <!--数据库链接地址账号密码-->
        <jdbcConnection driverClass="com.mysql.jdbc.Driver"
                        connectionURL="jdbc:mysql://${DBPath}"
                        userId="${DBUser}"
                        password="${DBPwd}"/>
        <javaTypeResolver>
            <property name="forceBigDecimals" value="false"/>
        </javaTypeResolver>
        <!--生成Model类存放位置-->
        <javaModelGenerator targetPackage="${poPackageName}" targetProject="${poPath}">
            <property name="enableSubPackages" value="true"/>
            <property name="trimStrings" value="true"/>
        </javaModelGenerator>
        <!--生成映射文件存放位置-->
        <sqlMapGenerator targetPackage="${mapPackageName}" targetProject="${mapPath}">
            <property name="enableSubPackages" value="true"/>
        </sqlMapGenerator>
        <!--生成Dao类存放位置-->
        <javaClientGenerator type="XMLMAPPER" targetPackage="${daoPackageName}" targetProject="${daoPath}">
            <property name="enableSubPackages" value="true"/>
        </javaClientGenerator>
        <!--生成对应表及类名-->
<#if tableSettings??>
    <#list tableSettings as ta >
                <table tableName="${ta.tableName}" domainObjectName="${ta.objectName}"
                       enableCountByExample="false" enableUpdateByExample="false"
                       enableDeleteByExample="false" enableSelectByExample="false"
                       selectByExampleQueryId="false"></table>
    </#list>
<#else>
    ${ta!""}
</#if>

    </context>
</generatorConfiguration>