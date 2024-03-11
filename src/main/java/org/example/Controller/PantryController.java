package org.example.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import org.example.Model.Market;
import org.example.Model.Pantry;
import org.example.Service.PantryService;
import org.example.Service.MarketService;
import java.util.List;
import org.example.Exception.MarketException;
import org.example.Exception.PantryException;
//import java.util.Objects;


public class PantryController {
    //Javalin app = Javalin.create(/*config*/).get("/", ctx -> ctx.result("Hello World???")).start(7070);

    PantryService pantry_service;
    MarketService market_service;

    public PantryController() {
        this.pantry_service = new PantryService();
        this.market_service = new MarketService();
    }

    public Javalin getAPI() {
        Javalin app = Javalin.create();

        app.get("/health/", context -> {
                    context.result("the server is Up");
                }
        );

//        app.get("/pantry/", PantryController::getAllIngredientsHandler);
        app.get("/pantry/", context -> {
            List<Pantry> pantry_list = pantry_service.getAllIngredients();
            context.json(pantry_list);
            }
        ); //getAllIngredientsHandler must be static in order to be used here

        app.post("/pantry", context -> {
            try{
                ObjectMapper om = new ObjectMapper();
                Pantry p = om.readValue(context.body(), Pantry.class);
                pantry_service.addIngredient(p);
                context.status(201);
            }catch(JsonProcessingException | PantryException e){
                context.result(e.getMessage());
                context.status(400);
            }
        });
/////////////////////////////////////////////////////////////////////////////////////////////////
//        app.post("/pantry/", PantryController::postIngredientHandler);
        app.get("/market/", context -> {
            List<Market> market_list = market_service.getAllMarkets();
            context.json(market_list);
            }
        );

        app.post("/market", context -> {
            try{
                ObjectMapper om = new ObjectMapper();
                Market m = om.readValue(context.body(), Market.class);
                market_service.addMarket(m);
                context.status(201);
            }catch(JsonProcessingException | MarketException e){
                context.result(e.getMessage());
                context.status(400);
            }
        });

        return app;
    }

//    public static void getAllIngredientsHandler(Context context) {
//        List<Pantry> pantryList = pantry_service.getAllIngredients();
//        //context.result(pantryList.toString());
//        context.json(pantryList);
//
//    }
//
//    public static void postIngredientHandler(Context context) {
//        ObjectMapper om = new ObjectMapper();
//
//        try {
//            Pantry cc = om.readValue(context.body(), Pantry.class);
//            //Pantry new_ingredient = new Pantry("Water", 5, "Gallon");
//            //pantryService.addIngredient(new_ingredient);
//            pantry_service.addIngredient(cc);
//            context.status(201); //post success
//        } catch (JsonProcessingException e) {
//            context.status(400);
//        }
//    }
}
