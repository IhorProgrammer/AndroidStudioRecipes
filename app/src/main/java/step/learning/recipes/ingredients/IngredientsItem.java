package step.learning.recipes.ingredients;

import org.json.JSONObject;

import java.util.List;

import step.learning.recipes.RecipeFullItem;
import step.learning.recipes.recipes.RecipeShortItem;

public class IngredientsItem {
    String name;
    String quantity;
    String unit;

    public IngredientsItem(String name, String quantity, String unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    public IngredientsItem() {
    }

    public static IngredientsItem fromJson(JSONObject jsonObject) throws IllegalArgumentException {
        try {
            String name = jsonObject.getString("name");
            String quantity = jsonObject.getString("quantity");
            String unit = jsonObject.getString("unit");

            IngredientsItem ingredientsItem = new IngredientsItem();
            ingredientsItem.setName( name );
            ingredientsItem.setQuantity( quantity );
            ingredientsItem.setUnit( unit );
            return ingredientsItem;
        }
        catch (Exception ex) {
            throw new IllegalArgumentException( ex.getMessage() ) ;
        }
    }


    public String toString() {
        return getName() + " - " + getQuantity() + " " + getUnit();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
