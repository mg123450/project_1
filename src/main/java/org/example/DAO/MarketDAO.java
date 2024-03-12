package org.example.DAO;

import org.example.Model.Market;
import org.example.Model.Pantry;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class MarketDAO {
    Connection conn;
    public MarketDAO(Connection conn){this.conn=conn;}

    public List<Market> getAllMarket(){
        List<Market> market_results = new ArrayList<>();
        try{
            PreparedStatement ps = conn.prepareStatement("select * from market");
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                int market_id = resultSet.getInt("market_id");
                String market_name = resultSet.getString("market_name");
                Market m = new Market(market_id, market_name);
                market_results.add(m);
            }
        }catch(Throwable e){
            e.printStackTrace();
        }
        return market_results;
    }

    public void addMarket(Market m){
        System.out.println("add a market");

        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO market (market_id, market_name) values (?, ?)");
            ps.setInt(1, m.getMarketID());
            ps.setString(2, m.getMarketName());


            System.out.println(m.getMarketID()+": "+m.getMarketName());
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Market getMarketById(int id){
        try{
            PreparedStatement ps = conn.prepareStatement(
                    "select * from market where market_id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int market_id = rs.getInt("market_id");
                String market_name = rs.getString("market_name");
                Market m = new Market(market_id, market_name);
                return m;
            }else{
                return null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public String getMarketByName(String name){
        try{
            PreparedStatement ps = conn.prepareStatement("select * from market where market_name = ?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                name = rs.getString("market_name");
                return name;
            }else {
                return null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;

    }

    public void deleteMarket(Market m) {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE from market where market_name = ?");
            ps.setInt(1, m.getMarketID());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
