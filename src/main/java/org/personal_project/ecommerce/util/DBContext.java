package org.personal_project.ecommerce.util;

import java.sql.Connection;

public class DBContext {
    private static final ThreadLocal<Connection> connectionHolder = new ThreadLocal<>();

    public static Connection getConnection(){
        return connectionHolder.get();
    }
    public static void setConnection(Connection conn){connectionHolder.set(conn);}
    public static void removeConnection(){connectionHolder.remove();}
}
