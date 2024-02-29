package org.example.Model;

import java.util.Objects;

public class Market {
    public String market_name;

    public Market() {

    }
    public Market(String market_name) {
        this.market_name = market_name;
    }

    public String getMarketName() {
        return market_name;
    }

    public void setMarketName(String market_name) {
        this.market_name = market_name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Market market = (Market) o;
        return Objects.equals(market_name, market.market_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(market_name);
    }

    @Override
    public String toString() {
        return "Market{" +
                "market_name='" + market_name + '\'' +
                '}';
    }
}
