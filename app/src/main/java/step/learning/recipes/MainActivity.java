package step.learning.recipes;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecipeAdapter recipeAdapter;
    private RecipeService recipeService;
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    private List<RecipeItem> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EditText searchEditText = findViewById(R.id.searchEditText);
        Button searchButton = findViewById(R.id.searchButton);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recipeService = new RecipeService();
        // Dummy data
        recipeList = new ArrayList<>();
//        recipeList.add(new RecipeItem(1, "Тайське карі з куркою", R.drawable.sample_image));
//        recipeList.add(new RecipeItem(2, "Тайське карі не з куркою", R.drawable.sample_image));
        recipeAdapter = new RecipeAdapter(recipeList, this);
        recyclerView.setAdapter(recipeAdapter);

        update("");

        searchButton.setOnClickListener(v -> {
            String query = searchEditText.getText().toString();
            update(query);
        });
    }

    private void update( String search ) {
        if( executorService.isShutdown() ) return;
        CompletableFuture
                .supplyAsync( () -> this.recipeService.load(search), executorService )
                .thenApplyAsync( this.recipeService::processResponse )
                .thenAcceptAsync( this::displayRecipeItem );
    }

    private void displayRecipeItem(List<RecipeItem> recipeItems) {
        runOnUiThread( () -> {
            recipeList = recipeItems;
            recipeAdapter.setRecipeList(recipeList);
            recipeAdapter.notifyDataSetChanged();
        });

    }



}

