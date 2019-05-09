package connect;


import play.mvc.With;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@With(connect.class)
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface connection {

    //set database connection
    String pgsql () default "org.postgresql.Driver";
    String host () default "ec2-54-221-198-156.compute-1.amazonaws.com";
    String port () default "5432";
    String databasename () default  "dbjn87qk4schhf";
    String schema () default  "public";
    String username () default "drbhfomddickrh";
    String password () default "a3cd49ba2d038c2fefd11bedca165b0abf55e78882d9b88b8a9d9d4df7f23503";

}