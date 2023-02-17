package com.cs;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: xiao kun
 * Date: 2019-04-25
 * Time: 08:40
 */
public class CodeGenerator {


    /**
     * 输出公共输出目录
     */
    private static final String OUTPUT_DIR = "/src/main/java";

    /**
     * 数据库地址
     */
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cs-admin?useUnicode=true&useSSL=false&characterEncoding=utf8&&serverTimezone=UTC";

    /**
     * 数据库账号
     */
    private static final String DB_USER_NAME = "root";

    /**
     * 数据库密码
     */
    private static final String DB_PASSWORD = "freeloop";

    /**
     * 父包名(项目包名)
     */
    private static final String PARENT_PACKAGE = "com.cs.admin";

    /**
     * 项目路径
     */
//  private static final String PROJECT_PATH = "D:\\workspace\\cs-admin\\";
    private static final String PROJECT_PATH = "D:\\archs\\";

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator generator = new AutoGenerator();
        generator.setGlobalConfig(globalConfig());

        // 数据源配置
        generator.setDataSource(dataSourceConfig());

        // 包配置
        PackageConfig pc = new PackageConfig();

        // 所创建包的父包名
        pc.setParent(PARENT_PACKAGE);
        pc.setModuleName(scanner("模块名"));
        generator.setPackageInfo(pc);

        // 自定义配置
        generator.setCfg(defineSelf());

        // 配置模板
        generator.setTemplate(templateConfig());

        // 策略配置
        generator.setStrategy(strategyConfig(pc));
        generator.execute();
    }


    /************************************************************/
    /**
     * 配置模板
     *
     * @return 模板
     */
    public static TemplateConfig templateConfig() {
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        templateConfig.setController("/templates/controller.java.vm");
        return templateConfig;
    }

    /**
     * 创建包配置
     *
     * @param pc 包配置
     * @return StrategyConfig
     */
    public static StrategyConfig strategyConfig(PackageConfig pc) {
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setSuperControllerClass("BaseController");
        strategy.setSuperControllerClass("com.tyyz.admin.common.controller.BaseController");
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        return strategy;
    }

    /**
     * 自定义配置
     *
     * @return InjectionConfig
     */
    public static InjectionConfig defineSelf() {
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };

        String templatePath = "/templates/mapper.xml.vm";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return PROJECT_PATH + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
//        cfg.setFileCreate(new IFileCreate() {
//            @Override
//            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
//                // 判断自定义文件夹是否需要创建
//                checkDir(filePath);
//                if (fileType == FileType.MAPPER) {
//                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
//                    return !new File(filePath).exists();
//                }
//                // 允许生成模板文件
//                return true;
//            }
//        });
        cfg.setFileOutConfigList(focList);
        return cfg;
    }


    /**
     * 配置全局
     *
     * @return GlobalConfig
     */
    public static GlobalConfig globalConfig() {
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        // 代码生成路径
        gc.setOutputDir(PROJECT_PATH + OUTPUT_DIR);
        gc.setAuthor("free loop");
        gc.setOpen(false);
        // 实体属性 Swagger2 注解
        gc.setSwagger2(true);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columList
        gc.setBaseColumnList(true);
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        gc.setServiceName("%sService");
        return gc;
    }


    /**
     * 配置数据库源
     *
     * @return 数据源
     */
    public static DataSourceConfig dataSourceConfig() {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setUrl(DB_URL);
        dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(DB_USER_NAME);
        dsc.setPassword(DB_PASSWORD);
        return dsc;
    }


    /**
     * 终端输入
     *
     * @param tip 输入数据
     * @return string
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + ":");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (ipt != null) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }


}