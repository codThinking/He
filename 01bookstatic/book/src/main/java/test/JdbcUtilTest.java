package test;

import org.junit.Test;
import utils.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcUtilTest {

    @Test
    public void testJdbcUtils(){

        for (int i = 0; i < 100; i++) {
            Connection connection = JdbcUtils.getConnection();
            System.out.println(connection);
            JdbcUtils.close(connection);
        }

    }
}
