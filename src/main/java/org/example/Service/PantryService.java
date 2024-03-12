package org.example.Service;
import java.util.List;
import java.util.ArrayList;

import org.example.Exception.MarketException;
import org.example.Exception.PantryException;
import org.example.DAO.PantryDAO;
import org.example.Main;
import org.example.Model.Pantry;
import java.sql.Connection;


public class PantryService {
//    List<Pantry> pantry_list;
     PantryDAO pd;
     Connection conn;

     public PantryService(PantryDAO pantry_dao) {
//         pantry_list = new ArrayList<>();
         this.pd = pantry_dao;
     }

    public void addIngredient(Pantry p) throws PantryException{
        Main.log.info("adding an ingredient");
        if (p.getIngredient().isBlank()){
            Main.log.warn("No ingredient provided");
            throw new PantryException("ingredient name required");
        }

        Main.log.info("Added: " +"Ingredient: "+ p.getIngredient() + "Amount: " + p.getAmount() + "Unit: " + p.getUnit()+ "Source: " + p.getSource());
        pd.addIngredient(p);
    }

    public void modifyIngredient(String ingredient_name, Pantry p) {
        Main.log.info("modifying "+ingredient_name);
        Pantry existing_ingredient = pd.getIngredient(ingredient_name);

        if(existing_ingredient !=null) {
            existing_ingredient.setIngredient(p.getIngredient());
            existing_ingredient.setAmount(p.getAmount());
            existing_ingredient.setUnit(p.getUnit());
            existing_ingredient.setSource(p.getSource());
            pd.modifyIngredient(existing_ingredient);
        }
    }

    public void deleteIngredient(String ingredient_name) { //delete entry by ingredient name
        Main.log.info("deleting "+ingredient_name);
        List<Pantry> pantry_list = pd.getAllIngredients();
        for (Pantry ingredient : pantry_list) {
            if (ingredient.getIngredient().equals(ingredient_name)) {
                pd.deleteIngredient(ingredient);}
        }
    }

    public Pantry getIndgredient(String ingredient_name) {
        List<Pantry> pantry_list = pd.getAllIngredients();
        for (int i = 0; i < pantry_list.size(); i++) {
            Pantry p = pantry_list.get(i);
            if (p.getIngredient() == ingredient_name) {
                return p;}
        }
        return null;
    }
     public List<Pantry> getAllIngredients(){
         List<Pantry> pantry_list = pd.getAllIngredients();
         Main.log.info("All ingredients: "+pantry_list);
         return pantry_list;
     }

//saveMarket,getMarketById
}
