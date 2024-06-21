package step.learning.recipes;

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
