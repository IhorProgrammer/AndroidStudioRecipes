package step.learning.recipes.recipes;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecipesResponse {
    private int status;
    private List<RecipeShortItem> data;

    public static RecipesResponse fromJsonString(String jsonString ) throws IllegalAccessException {
        try {
            JSONObject root = new JSONObject( jsonString );
            int status = root.getInt("status");
            JSONArray arr = root.getJSONArray( "data" );

            List<RecipeShortItem> data = new ArrayList<>();
            for (int i = 0; i < arr.length(); i++) {
                data.add(RecipeShortItem.fromJson(arr.getJSONObject(i)));
            }

            RecipesResponse response = new RecipesResponse();
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

    public List<RecipeShortItem> getData() {
        return data;
    }

    public void setData(List<RecipeShortItem> data) {
        this.data = data;
    }
}

