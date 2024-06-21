package step.learning.recipes;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeService {
    private static final String BASE_URL = "https://ittabirquest.000webhostapp.com/api/";
    private final  byte[] buffer = new byte[8096];


    public String load( String params ) {
        String url =  BASE_URL + "recipes";
        if( !params.isEmpty() ) url += "?name=" + params;

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

    public List<RecipeItem> processResponse ( String response ) {
        List<RecipeItem> recipeItems = new ArrayList<>();
        try {
            RecipeResponse chatResponse = RecipeResponse.fromJsonString( response );
            for ( RecipeItem recipe: chatResponse.getData()) {
                recipeItems.add( recipe ) ;
            }
        } catch (IllegalAccessException ex) {
            Log.e( "RecipeService:processResponse", ex.getMessage() == null ?  ex.getClass().getName() : ex.getMessage());
        }
        return recipeItems;
    }


}

