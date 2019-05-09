package connect;

import play.libs.F;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

import java.sql.*;

public class connect extends Action<connection> {
    @Override
    public F.Promise<Result> call(Http.Context ctx) throws Throwable {

        try {
            connection con = configuration;
            String pgsql = con.pgsql();
            String host = con.host();
            String dbname = con.databasename();
            String port = con.port();
            String schema = con.schema();
            String username = con.username();
            String password = con.password();

            String jdbcString = "jdbc:postgresql://" + host +":" + port + "/" + dbname + "?currentSchema=" + schema
                    + "&user=" + username + "&password=" + password + "&ssl=true&sslmode=require";

            Class.forName(pgsql);
            connectionModel.setConnection(DriverManager.getConnection(jdbcString));
            ctx.args.put("connect" ,  connectionModel.getConnection());
            return delegate.call(ctx);


        }catch (Exception e) {
            e.printStackTrace();
            return F.Promise.promise(()-> status(200 , "FAIL TO CONNECT DB"));
        }


    }


    //String jdbc =







}