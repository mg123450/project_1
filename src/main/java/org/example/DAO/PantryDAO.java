package org.example.DAO;

import org.example.Model.Pantry;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class PantryDAO {
    Connection conn;
    public PantryDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Pantry> getAllIngredients() {
        List<Pantry> resultPantry = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from pantry");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String ingredient = rs.getString("ingredient");
                double amount = rs.getDouble("amount");
                String unit = rs.getString("unit");
                String source = rs.getString("source"); //aka market_name
                Pantry p = new Pantry(ingredient, amount, unit, source);
                resultPantry.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultPantry;
    }

    public void addIngredient(Pantry p) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO pantry (ingredient, amount, unit, source) values (?, ?, ?, ?)");
            ps.setString(1, p.getIngredient());
            ps.setDouble(2, p.getAmount());
            ps.setString(3, p.getUnit());
            ps.setString(4, p.getSource());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Pantry getIngredient(String ingredient_o) {
        try {
            PreparedStatement ps = conn.prepareStatement("select * from pantry where ingredient = ?");
            ps.setString(1, ingredient_o);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String ingredient = rs.getString("ingredient");
                double amount = rs.getDouble("amount");
                String unit = rs.getString("unit");
                String source = rs.getString("source"); //aka market_name

                Pantry p = new Pantry(ingredient, amount, unit, source);
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
///
    public void modifyIngredient(Pantry p) {
        try {
            PreparedStatement ps = conn.prepareStatement(" UPDATE pantry Set ingredient = ?, amount = ?, unit = ?, source = ?" +
                    " WHERE ingredient = ?");
            ps.setString(1, p.getIngredient());
            ps.setDouble(2, p.getAmount());
            ps.setString(3,p.getUnit());
            ps.setString(4,p.getSource());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteIngredient(Pantry p) {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE from pantry where ingredient = ?");
            ps.setString(1, p.getIngredient());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
