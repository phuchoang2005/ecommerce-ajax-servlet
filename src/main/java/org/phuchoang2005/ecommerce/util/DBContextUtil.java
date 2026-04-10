package org.phuchoang2005.ecommerce.util;

import java.sql.Connection;

public class DBContextUtil {
    private static final ThreadLocal<Connection> connectionHolder = new ThreadLocal<>();

    public static Connection getConnection(){
        return connectionHolder.get();
    }
    public static void setConnection(Connection conn){connectionHolder.set(conn);}
    public static void removeConnection(){connectionHolder.remove();}
}
