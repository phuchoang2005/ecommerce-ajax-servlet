package com.personal_project.ecommerce.util;

import java.sql.Connection;

public class DBContext {
    private static final ThreadLocal<Connection> conncetionHolder = new ThreadLocal<>();

    public static Connection getConnection(){return conncetionHolder.get();}
    public static void setConnection(Connection conn){conncetionHolder.set(conn);}
    public static void removeConnection(){conncetionHolder.remove();}
}
