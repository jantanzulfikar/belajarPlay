package controllers.Account;

import com.fasterxml.jackson.databind.JsonNode;
import play.mvc.BodyParser;
import play.mvc.Result;
import play.mvc.Results;
import scala.util.parsing.json.JSONObject$;

import static play.mvc.Controller.request;

public class AccountResources {

    @BodyParser.Of(value = BodyParser.Json.class , maxLength = 100000)
    public static Result checkAccount() {


        JsonNode requestBody = request().body().asJson();
        String name = requestBody.path("name").asText();
        return Results.ok("200 ," + name);


    }

    //GET
    public static Result getAll(String name) {

        System.out.println("ASD");
        System.out.println("ASD");

        try {
            System.out.println("INCOMING NAME : " + name);
            if (name.equals("DIKI")) {
                return Results.ok("TAMPAN");
            } else {
                return Results.ok("JELEK LU " + name);
            }

        }catch (Exception e) {
            return Results.internalServerError("ERORR CUY");
        }


    }



}