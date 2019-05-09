package controllers.Account;

import com.fasterxml.jackson.databind.JsonNode;
import connect.connection;
import connect.connectionModel;
import play.mvc.BodyParser;
import play.mvc.Result;
import play.mvc.Results;
import scala.util.parsing.json.JSONObject$;

import java.sql.PreparedStatement;
import java.sql.Statement;

import static play.mvc.Controller.request;

public class AccountResources {


    @connection
    @BodyParser.Of(value = BodyParser.Json.class , maxLength = 100000)
    public static Result checkAccount() {

        JsonNode requestBody = request().body().asJson();
        String name = requestBody.path("name").asText();
        String phone = requestBody.path("phone").asText();
        String email = requestBody.path("email").asText();
        String query = "insert into m_sales values (name , phone , email) values ( '"+name+"' , '" +phone+ "' , '"+ email +"' )";
        System.out.println("Query : " + query);
        try {
            Statement startStatment = connectionModel.getConnection().createStatement();
            startStatment.execute(query);

        }catch (Exception e) {
            e.printStackTrace();
            return Results.internalServerError("01 , Failed" );
        }

        return Results.ok("00 ," + name);

    }

    //GET
    public static Result getAll(String name) {

        System.out.println("ASD");
        System.out.println("ASD");


        try {
            System.out.println("INCOMING NAME : " + name);
            if (name.equals("DIKI")) {
                return Results.ok("TAMPAN BGT 1");
            } else {
                return Results.ok("JELEK LU " + name);
            }

        }catch (Exception e) {
            return Results.internalServerError("ERORR CUY");
        }


    }



}