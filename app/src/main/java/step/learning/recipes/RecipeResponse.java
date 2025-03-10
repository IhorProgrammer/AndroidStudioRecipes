package step.learning.recipes;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import step.learning.recipes.recipes.RecipeShortItem;
import step.learning.recipes.recipes.RecipesResponse;

public class RecipeResponse {
    private int status;
    private RecipeFullItem data;

    public static RecipeResponse fromJsonString( String jsonString ) throws IllegalAccessException {
        try {
            JSONObject root = new JSONObject( jsonString );
            int status = root.getInt("status");
            JSONObject recipe = root.getJSONObject( "data" );

            RecipeFullItem data = RecipeFullItem.fromJson(recipe);

            RecipeResponse response = new RecipeResponse();
            response.setStatus(status);
            response.setData(data);
            return response;
        }
        catch (Exception ex){
            throw new IllegalAccessException( ex.getMessage() );
        }

    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public RecipeFullItem getData() {
        return data;
    }

    public void setData(RecipeFullItem data) {
        this.data = data;
    }
}
