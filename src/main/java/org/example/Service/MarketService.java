package org.example.Service;

import org.example.Exception.MarketException;
import org.example.Main;
import org.example.Model.Market;
import java.util.List;
import java.util.ArrayList;
import java.util.*;
import org.example.DAO.MarketDAO;
import org.example.Model.Pantry;

import java.sql.Connection;


public class MarketService {
//    List<Market> market_list;
    MarketDAO md;
    public MarketService(MarketDAO market_dao) {
        this.md = market_dao;
    }

    public void addMarket(Market m) throws MarketException {
        Main.log.info("adding a market");

        List<Market> market_list = md.getAllMarket();

        if (m.getMarketName().isBlank()) {
            Main.log.warn("empty market name");
            throw new MarketException("market name required");
        }

        if (market_list.contains(m)){
            Main.log.warn("the market, " + m.getMarketName() + ", exists already");
            throw new MarketException("market already exists");
        }else {
            md.addMarket(m);
        }
    }

    public void deleteMarket(int market_id) { //delete entry by market_id
        Main.log.info("deleting market"+market_id);
        List<Market> market_list = md.getAllMarket();
        for (int i = 0; i < market_list.size(); i++) {
            Market m = market_list.get(i);
            if (m.getMarketID() == market_id) {
                md.deleteMarket(m);}
        }
    }

    public Market getMarketById(int id)  {
        Market m = md.getMarketById(id);

        return m;

    }

    public List<Market> getAllMarkets() {
        List<Market> market_list = md.getAllMarket();
        return market_list;
    }
}
