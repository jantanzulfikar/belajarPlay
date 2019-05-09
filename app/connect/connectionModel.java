package connect;

import java.sql.Connection;

public class connectionModel {

    private static java.sql.Connection connect;

    public static void setConnection(java.sql.Connection con) {
        connectionModel.connect = con;

    }

    public static  java.sql.Connection getConnection () {
        return connect;
    }

    public static void disconnect () {

        try {
            connect.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }


}