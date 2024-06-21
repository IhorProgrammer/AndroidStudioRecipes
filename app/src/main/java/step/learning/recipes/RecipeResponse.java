package step.learning.recipes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecipeResponse {
    private int status;
    private List<RecipeItem> data;

    public static RecipeResponse fromJsonString( String jsonString ) throws IllegalAccessException {
        try {
            JSONObject root = new JSONObject( jsonString );
            int status = root.getInt("status");
            JSONArray arr = root.getJSONArray( "data" );

            List<RecipeItem> data = new ArrayList<>();
            for (int i = 0; i < arr.length(); i++) {
                data.add(RecipeItem.fromJson(arr.getJSONObject(i)));
            }

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

    public List<RecipeItem> getData() {
        return data;
    }

    public void setData(List<RecipeItem> data) {
        this.data = data;
    }
}

