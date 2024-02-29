package org.example.Model;

import java.util.Objects;

public class Pantry {
    public String ingredient;
    public double amount;
    public String unit;
    public String source;

    public Pantry() {

    }
    public Pantry(String ingredient, double amount, String unit, String source) {
        this.ingredient = ingredient;
        this.amount = amount;
        this.unit = unit;
        this.source = source;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSource() {
        return source;
    } //the market the ingredient is from

    public void setSource(String unit) {
        this.source = source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pantry pantry = (Pantry) o;
        return Double.compare(amount, pantry.amount) == 0 && Objects.equals(ingredient, pantry.ingredient) && Objects.equals(unit, pantry.unit )&& Objects.equals(source, pantry.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredient, amount, unit, source);
    }

    @Override
    public String toString() {
        return "Pantry{" +
                "ingredient='" + ingredient + '\'' +
                ", amount=" + amount +
                ", unit='" + unit + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
