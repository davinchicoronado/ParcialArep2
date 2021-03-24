/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arem;

import spark.Request;
import spark.Response;
import static spark.Spark.*;


/**
 *
 * @author David Coronado
 */
public class SparkWebServer {
    public static void main(String[] args) {
        port(getPort());
        
       
        get("/cos", (req, res) -> inputDataPage(req, res));
        get("/log", (req, res) -> inputDataPage2(req, res));

        
    }
    private static String inputDataPage(Request req, Response res) {
        
        Double num = Double.parseDouble(req.queryParams("value"));
        APICalculator calculadora = new APICalculator();
        
        res.type("application/json");
        
       String pageContent=

                "{"+
                   " \"operation\": \"cos\","+
                "\"input\":"+ num +
                 " \"output\":" +calculadora.calculateCos(num) +
                "}";
       return pageContent;
       
        
    }
    
    private static String inputDataPage2(Request req, Response res) {
        Double num = Double.parseDouble(req.queryParams("number"));
        APICalculator calculadora = new APICalculator();
        res.type("application/json");
        
       String pageContent=
                "{"+
                   " \"operation\": \"log\","+
                "\"input\":"+ num +
                 " \"output\":" +calculadora.calculateLog(num) +
                "}";

        return pageContent;
    
    
    }

    

    private static int getPort() {
            if (System.getenv("PORT") != null) {
                    return Integer.parseInt(System.getenv("PORT"));
            }
            return 4567; //returns default port if heroku-port isn't set(i.e. on localhost)
 }

}
