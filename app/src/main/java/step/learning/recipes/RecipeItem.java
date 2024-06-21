package step.learning.recipes;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecipeItem {
    private String title;
    private String shortInfo;
    private int id;
    private String imageURL;

    public RecipeItem(String title, String shortInfo, int id, String imageURL) {
        this.title = title;
        this.shortInfo = shortInfo;
        this.id = id;
        this.imageURL = imageURL;
    }

    public RecipeItem() {}

    public static RecipeItem fromJson(JSONObject jsonObject) throws IllegalArgumentException {
        try {
            int id = jsonObject.getInt("id");
            String title = jsonObject.getString("title");
            String shortInfo = jsonObject.getString("short_info");
            String imageUrl = jsonObject.getString("image_url");

            RecipeItem recipeItem = new RecipeItem();
            recipeItem.setId( id );
            recipeItem.setTitle( title );
            recipeItem.setShortInfo( shortInfo );
            recipeItem.setImageURL( imageUrl );
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getFullImageURL () {
        return "https://ittabirquest.000webhostapp.com/image/recipes/" + imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}

