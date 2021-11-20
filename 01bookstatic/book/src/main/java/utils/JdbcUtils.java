package utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();

    static {
        try{
            Properties properties = new Properties();
            //读取jdbc.properties属性配置文件
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //从流中加载数据
            properties.load(inputStream);
            //创建数据库连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取数据库连接池中的连接
     * @return 如果返回null 说明获取连接失败，有值就是获取连接成功
     */
    public static Connection getConnection(){
        Connection conn = conns.get();
        if (conn == null){
            try{
                //从数据库连接池获取连接
                conn = dataSource.getConnection();
                //保存到ThreadLocal对象中，供后面jdbc使用
                conns.set(conn);
                //设置手动事务管理
                conn.setAutoCommit(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * 提交事务并关闭释放
     */
    public static void commitAndClose(){
        Connection connection = conns.get();
        if (connection != null ){
            try{
                connection.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //一定要执行remove操作，否则就会出错。（因为tomcat服务器底层使用了线程池技术）
        conns.remove();
    }

    /**
     * 回滚
     */

    public static void rollbackAndClose(){
        Connection connection = conns.get();
        if (connection != null ){
            try{
                connection.rollback();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //一定要执行remove操作，否则就会出错。（因为tomcat服务器底层使用了线程池技术）
        conns.remove();
    }

//    /**
//     * 关闭连接，放回数据库连接池、、提交事务并关闭释放
//     * @param conn
//     */
//    public static void close (Connection conn){
//        if(conn != null){
//            try{
//                conn.close();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//    }
}
