package com.wj.blog.common.util.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Collections;

/**
 * mybatis-plus代码自动生成
 *
 * @author w
 * @since 2018/12/13
 */
public class CodeGenerator {

    // 基础信息配置
    /**
     * 数据库连接字符
     */
    private static final String URL = "jdbc:mysql://localhost:3306/blog?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&allowPublicKeyRetrieval=true";

    /**
     * 示例：private static final String PARENT_PACKAGE_NAME = "com.example.mybatisplusdemo";
     */
    private static final String PARENT_PACKAGE_NAME = "com.wj";

    /**
     * 数据库用户名
     */
    private static final String USERNAME = "root";
    /**
     * 数据库密码
     */
    private static final String PASSWORD = "wj1324";
    /**
     * 项目根路径
     */
    private static final String PROJECT_ROOT_PATH = System.getProperty("user.dir");

    public static void main(String[] args) {

        // 包路径
        String packagePath = PROJECT_ROOT_PATH + "/src/main/java";

        // XML文件的路径
        String mapperXmlPath = PROJECT_ROOT_PATH + "/src/main/resources/mybatis/mapper";


        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
                .globalConfig(builder ->
                        // 设置作者名称
                        builder.author("w")
                                // 开启swagger
                                .enableSwagger()
                                // 是否覆盖
                                .fileOverride()
                                .dateType(DateType.TIME_PACK)
                                // 禁止打开输出目录。注释掉则生成完毕后，自动打开生成的文件目录。
                                .disableOpenDir()
                                // 指定输出目录。如果指定，Windows生成至D盘根目录下，Linux or MAC 生成至 /tmp 目录下。
                                .outputDir(packagePath)

                                .commentDate("yyyy-MM-dd")
                )
                .packageConfig(builder ->
                        // 设置父包名
                        builder.parent(PARENT_PACKAGE_NAME)
                                // 设置父包模块名
                                .moduleName("blog")
                                // 设置xml生成路径
                                .pathInfo(Collections.singletonMap(OutputFile.mapperXml, mapperXmlPath))
                )
                .strategyConfig(builder ->
                        // 设置表名
                        builder.addInclude("record")
                                // entity
                                .entityBuilder()
                                .enableChainModel()
                                // 开启生成实体时生成字段注解。
                                .enableTableFieldAnnotation()
                                // 逻辑删除字段名(数据库)。
                                .logicDeleteColumnName("is_delete")
                                // 会在实体类的该字段属性前加注解[@TableLogic]
                                .logicDeletePropertyName("isDelete")
                                // 会在实体类的该字段上追加注解[@TableField(value = "create_time", fill = FieldFill.INSERT)]
                                .addTableFills(new Column("create_time", FieldFill.INSERT))
                                // 会在实体类的该字段上追加注解[@TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)]
                                .addTableFills(new Column("update_time", FieldFill.INSERT_UPDATE))
                                .naming(NamingStrategy.underline_to_camel)
                                .idType(IdType.ASSIGN_ID)
                                // mapper
                                .mapperBuilder()
                                // 会在mapper.xml文件生成[通用查询映射结果]配置。
                                .enableBaseResultMap()
                                // 会在mapper.xml文件生成[通用查询结果列 ]配置
                                .enableBaseColumnList()
                                // controller
                                .controllerBuilder()
                                // 开启驼峰转连字符
                                .enableHyphenStyle()
                                // 会在控制类中加[@RestController]注解。
                                .enableRestStyle()
                                .serviceBuilder()
                                .formatServiceFileName("%sService")
                                .formatServiceImplFileName("%sServiceImpl"))
                // 使用Freemarker引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();

    }
}
