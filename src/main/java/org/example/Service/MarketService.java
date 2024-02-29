package org.example.Service;

import org.example.Exception.MarketException;
import org.example.Main;
import org.example.Model.Market;
import java.util.List;
import java.util.ArrayList;
import java.util.*;


public class MarketService {
    List<Market> market_list;

    public MarketService() {
        this.market_list = new ArrayList<>();
    }

    public void addMarket(Market m) throws MarketException {
        Main.log.info("adding a market");

        if (m.getMarketName().isBlank()) {
            Main.log.warn("empty market name");
            throw new MarketException("market name required");
        }

        if (market_list.contains(m)){
            Main.log.warn("the market, " + m.getMarketName() + ", exists already");
            throw new MarketException("market already exists");
        }else {
            market_list.add(m);
        }
    }

    public List<Market> getAllMarkets() {
        Main.log.info("markets list: " + market_list);
        return market_list;
    }
}
