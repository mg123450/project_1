package org.example.Service;
import java.util.List;
import java.util.ArrayList;

import org.example.Exception.MarketException;
import org.example.Exception.PantryException;
import org.example.DAO.PantryDAO;
import org.example.Main;
import org.example.Model.Pantry;


public class PantryService {
//    List<Pantry> pantry_list;
     PantryDAO pantryDAO;

     public PantryService(PantryDAO pd) {
//         pantry_list = new ArrayList<>();
         this.pantryDAO = new PantryDAO;
     }

    public void addIngredient(Pantry p) throws PantryException{
        Main.log.info("adding an ingredient");
        if (p.getIngredient().isBlank()){
            Main.log.warn("No ingredient provided");
            throw new PantryException("ingredient name required");
        }

        Main.log.info("Added: " +"Ingredient: "+ p.getIngredient() + "Amount: " + p.getAmount() + "Unit: " + p.getUnit()+ "Source: " + p.getSource());
        pantryDAO.addIngredient(p);
    }

    public void modifyIngredient(String ingredient_name, Pantry p) {
        Main.log.info("modifying "+ingredient_name);
        Pantry existing_ingredient = pantryDAO.getIngredient(ingredient_name);

        if(existing_ingredient !=null) {
            existing_ingredient.setIngredient(p.getIngredient());
            existing_ingredient.setAmount(p.getAmount());
            existing_ingredient.setUnit(p.getUnit());
            existing_ingredient.setSource(p.getSource());
            pantryDAO.modifyIngredient(existing_ingredient);
        }
    }

    public void deleteIngredient(String ingredient_name) { //delete entry by ingredient name
        Main.log.info("deleting "+ingredient_name);
        List<Pantry> pantry_list = pantryDAO.getAllIngredients();
        for (Pantry ingredient : pantry_list) {
            if (ingredient.getIngredient().equals(ingredient_name)) {
                pantryDAO.deleteIngredient(ingredient);
            }
        }
    }

     public List<Pantry> getAllIngredients(){
         List<Pantry> pantry_list = pantryDAO.getAllIngredients();
         Main.log.info("All ingredients: "+pantry_list);
         return pantry_list;
     }

//saveMarket,getMarketById
}
