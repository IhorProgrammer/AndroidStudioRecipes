package step.learning.recipes;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import step.learning.recipes.ingredients.IngredientsItem;
import step.learning.recipes.recipes.RecipeShortItem;

public class RecipeFullItem {
    String title;
    String shortInfo;
    String imageURL;
    int servings;
    int preparationTime;
    String description;
    String tags;
    List<IngredientsItem> ingredients;

    public RecipeFullItem(String title, String shortInfo, String imageURL, int servings, int preparationTime, String description, String tags, List<IngredientsItem> ingredients) {
        this.title = title;
        this.shortInfo = shortInfo;
        this.imageURL = imageURL;
        this.servings = servings;
        this.preparationTime = preparationTime;
        this.description = description;
        this.tags = tags;
        this.ingredients = ingredients;
    }

    public RecipeFullItem() {}

    public static RecipeFullItem fromJson(JSONObject jsonObject) throws IllegalArgumentException {
        try {
            String title = jsonObject.getString("title");
            String shortInfo = jsonObject.getString("short_info");
            String imageUrl = jsonObject.getString("image_url");
            int servings = jsonObject.getInt("servings");
            int preparationTime = jsonObject.getInt("preparation_time");
            String description = jsonObject.getString("description");
            String tags = jsonObject.getString("tags");

            JSONArray ingredients = jsonObject.getJSONArray( "ingredients" );
            List<IngredientsItem> ingredientsItems = new ArrayList<>();
            for (int i = 0; i < ingredients.length(); i++) {
                ingredientsItems.add( IngredientsItem.fromJson( ingredients.getJSONObject(i)) );
            }

            RecipeFullItem recipeItem = new RecipeFullItem();
            recipeItem.setTitle( title );
            recipeItem.setShortInfo( shortInfo );
            recipeItem.setImageURL( imageUrl );
            recipeItem.setServings( servings );
            recipeItem.setPreparationTime( preparationTime );
            recipeItem.setDescription( description );
            recipeItem.setIngredients( ingredientsItems );
            recipeItem.setTags( tags );

            return recipeItem;
        }
        catch (Exception ex) {
            throw new IllegalArgumentException( ex.getMessage() ) ;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortInfo() {
        return shortInfo;
    }

    public void setShortInfo(String shortInfo) {
        this.shortInfo = shortInfo;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public List<IngredientsItem> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientsItem> ingredients) {
        this.ingredients = ingredients;
    }
}
