package DAO;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DatabaseHikari {
    private static DataSource dataSource;

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "LAB_PA";
    private static final String PASSWORD = "LAB_PA";

    public static DataSource getDataSource()  {
        if (dataSource == null) {

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(URL);
            config.setUsername(USER);
            config.setPassword(PASSWORD);

            config.setMaximumPoolSize(32);
            config.setAutoCommit(false);

            dataSource = new HikariDataSource(config);
        }
        return dataSource;
    }
}
