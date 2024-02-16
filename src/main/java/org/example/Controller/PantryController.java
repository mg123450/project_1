package org.example.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.example.Model.Pantry;
import org.example.Service.PantryService;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;


public class PantryController {
    //Javalin app = Javalin.create(/*config*/).get("/", ctx -> ctx.result("Hello World???")).start(7070);

    static PantryService pantryService;

    public PantryController(){
        this.pantryService = new PantryService();
    }
    public Javalin getAPI(){
        Javalin app = Javalin.create();

        app.get("/health/", context -> {
            context.result("the server is Up");
            }
        );

        app.get("/pantry/", PantryController::getAllIngredientsHandler);
        app.post("/pantry/", PantryController::postIngredientHandler);



        return app;
    }

    public static void getAllIngredientsHandler(Context context){
        List<Pantry> pantryList = pantryService.getAllIngredients();
        context.result(pantryList.toString());

    }

    public static void postIngredientHandler(Context context){
       //Objects om = new ObjectMapper();

        Pantry cc = om.readValue(context.body(), Pantry.class);
        Pantry new_ingredient = new Pantry("Water", 5, "Gallon");
        pantryService.addIngredient(new_ingredient);
    }
}
