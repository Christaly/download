package com.java.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * JDBC工具类 使用Druids连接池
 */
public class JDBCUtils {
    private static DataSource ds;

    public static void main(String[] args) {

    }

    static {
        try {
            //1.加载配置文件
            Properties pro = new Properties();
            pro.load(new FileInputStream(new File("/Users/rmy/IdeaProjects/jsp-servelet/out/production/day2-11/druids.properties")));
            System.out.println(pro.getProperty("username"));
            URL resource = JDBCUtils.class.getClassLoader().getResource("druids.properties");
            System.out.println(resource);
            InputStream resourceAsStream = JDBCUtils.class.getClassLoader().getResourceAsStream("druids.properties");
            pro.load(resourceAsStream);
            //2.创建连接池对象并初始化连接池对象
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接池对象
     */
    public static DataSource getDataSource() {
        return ds;
    }

    /**
     * 获取连接conn对象
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * 关闭连接
     */

}

