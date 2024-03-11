package org.example.Service;

import org.example.Exception.MarketException;
import org.example.Main;
import org.example.Model.Market;
import java.util.List;
import java.util.ArrayList;
import java.util.*;
import org.example.DAO.MarketDAO;


public class MarketService {
//    List<Market> market_list;
    MarketDAO marketDAO;
    public MarketService(MarketDAO marketDAO) {
        this.marketDAO = new marketDAO;
    }

    public void addMarket(Market m) throws MarketException {
        Main.log.info("adding a market");

        List<Market> market_list = marketDAO.getAllMarket();

        if (m.getMarketName().isBlank()) {
            Main.log.warn("empty market name");
            throw new MarketException("market name required");
        }

        if (market_list.contains(m)){
            Main.log.warn("the market, " + m.getMarketName() + ", exists already");
            throw new MarketException("market already exists");
        }else {
            marketDAO.addMarket(m);
        }
    }

    public Market getMarketById(int id) throws MarketException {
        Market m = marketDAO.getMarketById(id);
        if (m == null) {
            throw new MarketException("not matching ID in market_id");
        } else {
            return m;
        }
    }

    public List<Market> getAllMarkets() {
        List<Market> market_list = marketDAO.getAllMarket();
        return market_list;
    }
}
