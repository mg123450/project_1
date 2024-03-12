package org.example.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import org.example.Model.Market;
import org.example.Model.Pantry;
import org.example.Service.PantryService;
import org.example.Service.MarketService;

import java.sql.Connection;
import java.util.List;
import org.example.Exception.MarketException;
import org.example.Exception.PantryException;
import org.example.DAO.*;;
//import java.util.Objects;


public class PantryController {
    //Javalin app = Javalin.create(/*config*/).get("/", ctx -> ctx.result("Hello World???")).start(7070);

    PantryService ps;
    MarketService ms;

    public PantryController(PantryService pantry_service, MarketService market_service) {
        this.ps = pantry_service;
        this.ms = market_service;
    }

    public Javalin getAPI() {
        Javalin app = Javalin.create();

        app.get("/health/", context -> {
                    context.result("the server is Up");
                }
        );
        /////////////////////////////////////////////////////////////////////////////////////////////////
//        app.get("/pantry/", PantryController::getAllIngredientsHandler);
        app.get("/pantry", context -> {
            List<Pantry> pantry_list = ps.getAllIngredients();
            context.json(pantry_list);
            }
        );

        app.post("/pantry", context -> {
            try{
                ObjectMapper om = new ObjectMapper();
                Pantry p = om.readValue(context.body(), Pantry.class);
                ps.addIngredient(p);
                context.status(201);
            }catch(JsonProcessingException | PantryException e){
                context.result(e.getMessage());
                context.status(400);
            }
        });


        app.get("/pantry/{ingredient_name}", context -> {
            String ingredient_name = context.pathParam("ingredient_name");
            Pantry p = ps.getIndgredient(ingredient_name);
            if(p == null){
                context.status(404);
            }else{
                context.json(p);
                context.status(200);
            }
        }
        );

        app.delete("/pantry/{ingredient_name}", context -> {
            String ingredient_name = context.pathParam("ingredient_name");
            ps.deleteIngredient(ingredient_name);
            context.status(200);
        }
        );

        app.put("/pantry/{ingredient_name}" , context -> {
            String ingredient_name = context.pathParam("ingredient_name");

            ObjectMapper om = new ObjectMapper();
            Pantry updated_ingredient = om.readValue(context.body(), Pantry.class);
            ps.modifyIngredient(ingredient_name, updated_ingredient);
            context.status(200);
            context.result("Modified the ingredient: "+ingredient_name);
        }
        );
        /////////////////////////////////////////////////////////////////////////////////////////////////
//        app.post("/pantry/", PantryController::postIngredientHandler);
        app.get("/market/", context -> {
            List<Market> market_list = ms.getAllMarkets();
            context.json(market_list);
            }
        );

        app.post("/market", context -> {
            try{
                ObjectMapper om = new ObjectMapper();
                Market m = om.readValue(context.body(), Market.class);
                ms.addMarket(m);
                context.status(201);
            }catch(JsonProcessingException | MarketException e){
                context.result(e.getMessage());
                context.status(400);
            }
        });

        app.get("/market/{market_id}", context -> {
            int market_id = Integer.parseInt(context.pathParam("market_id"));

            Market m = ms.getMarketById(market_id);
            if (m == null) {
            context.json(m);}
            else {context.status(404);}
        }
        );

        app.delete("/market/{market_id}", context -> {
            int market_id = Integer.parseInt(context.pathParam("market_id"));
            ms.deleteMarket(market_id);
            context.status(200);
                }
        );

        return app;
    }



//    public static void getAllIngredientsHandler(Context context) {
//        List<Pantry> pantryList = ps.getAllIngredients();
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
//            ps.addIngredient(cc);
//            context.status(201); //post success
//        } catch (JsonProcessingException e) {
//            context.status(400);
//        }
//    }
}
