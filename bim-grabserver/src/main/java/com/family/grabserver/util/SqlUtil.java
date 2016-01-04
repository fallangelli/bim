package com.family.grabserver.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by Administrator on 2015/12/5.
 */
public class SqlUtil {
  static Connection conn;
  static Statement stmt;
  static ResultSet rs;

  public static void truncateTable(String tableName) throws Exception {
    Properties props = new Properties();
    props.load(ClassLoader.getSystemResourceAsStream("jdbc.properties"));
    String driver = props.getProperty("jdbc.driver");
    String url = props.getProperty("jdbc.url");
    String user = props.getProperty("jdbc.username");
    String pass = props.getProperty("jdbc.password");

    try {
      Class.forName(driver);
      conn = DriverManager.getConnection(url, user, pass);
      stmt = conn.createStatement();
      String sql = "TRUNCATE " + tableName;
      stmt.execute(sql);

      System.out.println("删除" + tableName + "记录");

    } finally {
      if (rs != null) {
        rs.close();
      }
      if (stmt != null) {
        stmt.close();
      }
      if (conn != null) {
        conn.close();
      }
    }
  }

}
