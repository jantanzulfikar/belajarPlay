package controllers.Account;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import connect.connection;
import connect.connectionModel;
import play.api.libs.json.Json;
import play.mvc.BodyParser;
import play.mvc.Result;
import play.mvc.Results;
import scala.util.parsing.json.JSONObject$;

import javax.inject.Inject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import play.api.libs.json.jackson.*;

import static play.mvc.Controller.request;


public class AccountResources {



    @connection(pgsql = "org.postgresql.Driver")
    @BodyParser.Of(value = BodyParser.Json.class , maxLength = 100000)
    public static Result checkAccount() {

        JsonNode requestBody = request().body().asJson();
        String name = requestBody.path("name").asText();
        String phone = requestBody.path("phone").asText();
        String email = requestBody.path("email").asText();
        String query = "insert into m_sales (name , phone , email) values ( '"+name+"' , '" +phone+ "' , '"+ email +"' )";
        System.out.println("Query : " + query);
        try {
            System.out.println("Check : " + connectionModel.getConnection());
            Statement startStatment = connectionModel.getConnection().createStatement();
            startStatment.execute(query);
            startStatment.close();
            connectionModel.disconnect();

        }catch (Exception e) {
            e.printStackTrace();
            connectionModel.disconnect();
            return Results.internalServerError("01 , Failed" );
        }
        Gson gson = new Gson();
        Map<String , String > results = new HashMap<>();
        results.put("name" , name);
        results.put("phone" , phone);
        results.put("email" , email);
        return Results.ok(gson.toJson(results));

    }





    //GET
    @connection(pgsql = "org.postgresql.Driver")
    public static Result getAll(String phone) {

        try {


            System.out.println("incoming Phone : " + phone);
            String sql = "select name , phone , email  from m_sales where phone = '" + phone + "'";
            System.out.println("SQL : " + sql);
            Statement statmeStatement = connectionModel.getConnection().createStatement();
            ResultSet result = statmeStatement.executeQuery(sql);
            Map<String , String > resultObject = new HashMap<>();
            if (result.next()) {
                System.out.println("name : " + result.getString("name"));
                resultObject.put("Name" , result.getString("name"));
                resultObject.put("phone" , result.getString("phone"));
                resultObject.put("email" , result.getString("email"));
            }
            Gson gson = new Gson();
            statmeStatement.close();
            connectionModel.disconnect();
            return Results.ok(gson.toJson(resultObject));


        }catch (Exception e) {
            connectionModel.disconnect();
            e.printStackTrace();
            return Results.internalServerError("ERORR CUY");
        }


    }




}