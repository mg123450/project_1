package org.example;
import io.javalin.Javalin;
import org.example.Controller.PantryController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.example.Model.*;
import org.example.Service.*;
import org.example.DAO.*;
import org.example.Util.ConnectionSingleton;
import java.sql.Connection;

public class Main {
    public static Logger log = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        //Javalin app = Javalin.create(/*config*/).get("/", ctx -> ctx.result("Hello World???")).start(7070);

        Connection conn = ConnectionSingleton.getConnection();

        PantryDAO pd = new PantryDAO(conn);
        MarketDAO md = new MarketDAO(conn);

        PantryService ps = new PantryService(pd);
        MarketService ms = new MarketService(md);

        PantryController pc = new PantryController(ps, ms);

        Javalin api = pc.getAPI();



        api.start(9002);

        System.out.println("Hello world!!!!");
    }


}