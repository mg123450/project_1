package org.example;
import io.javalin.Javalin;
import org.example.Controller.PantryController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.example.Model.*;
import org.example.Service.*;
import org.example.DAO.*;

public class Main {
    public static Logger log = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        //Javalin app = Javalin.create(/*config*/).get("/", ctx -> ctx.result("Hello World???")).start(7070);


        PantryController pantryController = new PantryController();



        Javalin api = pantryController.getAPI();



        api.start(9002);

        System.out.println("Hello world!!!!");
    }


}