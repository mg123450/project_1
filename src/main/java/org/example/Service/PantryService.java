package org.example.Service;
import java.util.List;
import java.util.ArrayList;

import org.example.Exception.MarketException;
import org.example.Exception.PantryException;
import org.example.Main;
import org.example.Model.Pantry;


public class PantryService {
    List<Pantry> pantry_list;
     public PantryService() {
         pantry_list = new ArrayList<>();
     }


     public List<Pantry> getAllIngredients(){
         Main.log.info("All ingredients: "+pantry_list);
         return pantry_list;
     }

     public void addIngredient(Pantry p) throws PantryException{
         Main.log.info("adding an ingredient");
         if (p.getIngredient().isBlank()){
             Main.log.warn("No ingredient provided");
             throw new PantryException("ingredient name required");
         }

         Main.log.info("Added: " +"Ingredient: "+ p.getIngredient() + "Amount: " + p.getAmount() + "Unit: " + p.getUnit()+ "Source: " + p.getSource());
         pantry_list.add(p);
     }
}
