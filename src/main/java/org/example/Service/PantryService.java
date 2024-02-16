package org.example.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.List;
import org.example.Model.Pantry;


public class PantryService {
    List<Pantry> pantryList;
     public PantryService() {
         pantryList = new ArrayList<>();
     }

     public List<Pantry> getAllIngredients(){
         return pantryList;
     }

     public void addIngredient(Pantry ingredient_details){
         pantryList.add(ingredient_details);
     }
}
