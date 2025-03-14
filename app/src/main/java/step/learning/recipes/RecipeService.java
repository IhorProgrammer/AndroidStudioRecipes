package step.learning.recipes;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import step.learning.recipes.RecipeFullItem;
import step.learning.recipes.recipes.RecipesResponse;

public class RecipeService {
    private static final String BASE_URL = "https://ittabirquest.000webhostapp.com/api/";
    private final  byte[] buffer = new byte[8096];


    public String load( String idRecipe ) {
        String url =  BASE_URL + "recipe?id=" + idRecipe;

        try (InputStream recipeStream = new URL( url ).openStream(); ) {
            String response = readString( recipeStream );
            return response;
        } catch (Exception ex) {
            Log.e( "RecipeService:load()",
                    ex.getMessage() == null ?  ex.getClass().getName() : ex.getMessage());
        }

        return null;
    }

    public String readString(InputStream stream ) throws IOException {
        ByteArrayOutputStream byteBuilder = new ByteArrayOutputStream();
        int len;
        while ( (len = stream.read(buffer)) != -1) {
            byteBuilder.write( buffer, 0 ,len );
        }
        String res = byteBuilder.toString();
        byteBuilder.close();
        return res;
    }

    public RecipeFullItem processResponse (String response ) {
        RecipeFullItem recipeItems = new RecipeFullItem();
        try {
            RecipeResponse recipeResponse = RecipeResponse.fromJsonString( response );
            recipeItems = recipeResponse.getData();

        } catch (IllegalAccessException ex) {
            Log.e( "RecipeService:processResponse", ex.getMessage() == null ?  ex.getClass().getName() : ex.getMessage());
        }
        return recipeItems;
    }

}
