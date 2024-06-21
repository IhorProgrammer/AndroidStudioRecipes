package step.learning.recipes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import step.learning.recipes.ingredients.IngredientsAdapter;
import step.learning.recipes.ingredients.IngredientsItem;
import step.learning.recipes.recipes.RecipeShortItem;
import step.learning.recipes.recipes.RecipesService;

public class RecipeActivity extends AppCompatActivity {

    private ImageView recipeImage;
    private TextView recipeTitle;
    private TextView recipeShortInfo;
    private TextView recipeServings;
    private TextView recipePreparationTime;
    //private TextView recipeTags;
    private TextView recipeDescription;
    private RecyclerView recipeIngredients;

    private List<RecipeShortItem> recipeList;
    private RecipeService recipeService;
    private IngredientsAdapter ingredientsAdapter;

    ExecutorService executorService = Executors.newSingleThreadExecutor();



    public static void start(Context context, String id) {
        Intent intent = new Intent(context, RecipeActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recipe);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        recipeImage = findViewById(R.id.recipe_image);
        recipeTitle = findViewById(R.id.recipe_title);
        recipeShortInfo = findViewById(R.id.recipe_short_info);
        recipeServings = findViewById(R.id.recipe_servings);
        recipePreparationTime = findViewById(R.id.recipe_preparation_time);
        // recipeTags = findViewById(R.id.recipe_tags);
        recipeDescription = findViewById(R.id.recipe_description);
        recipeIngredients = findViewById(R.id.recipe_ingredients);

        recipeIngredients.setLayoutManager(new LinearLayoutManager(this));
        ingredientsAdapter = new IngredientsAdapter(new ArrayList<>(), this);
        recipeIngredients.setAdapter(ingredientsAdapter);

        recipeService = new RecipeService();
        update( getIntent().getStringExtra("id") );
    }


    private void update( String search ) {
        if( executorService.isShutdown() ) return;
        CompletableFuture
                .supplyAsync( () -> this.recipeService.load(search), executorService )
                .thenApplyAsync( this.recipeService::processResponse )
                .thenAcceptAsync( this::displayRecipeItem );
    }

    private void displayRecipeItem(RecipeFullItem recipeItems) {
        runOnUiThread( () -> {
            setRecipe(recipeItems);
        });
    }

    private void setRecipe(RecipeFullItem recipeFullItem) {
        recipeTitle.setText( recipeFullItem.title );
        recipeShortInfo.setText( boldNormalText( getResources().getString(R.string.recipe_activity_short_info) ,recipeFullItem.shortInfo ) );
        recipeServings.setText( boldNormalText(getResources().getString(R.string.recipe_activity_servings), recipeFullItem.servings + "" ) );
        recipePreparationTime.setText( boldNormalText(getResources().getString(R.string.recipe_activity_preparation_time), recipeFullItem.preparationTime + getResources().getString(R.string.recipe_activity_time)) );
        //recipeTags.setText( boldNormalText(getResources().getString(R.string.recipe_activity_tags), recipeFullItem.tags ) );
        recipeDescription.setText(recipeFullItem.description);

        Glide.with(this)
                .load( "https://ittabirquest.000webhostapp.com/image/recipes/" + recipeFullItem.getImageURL() )
                .thumbnail(0.1f) // Завантаження мініатюри розміром 10% від повного зображення
                .error(R.drawable.errorl_load_image) // Зображення для помилки
                .into(recipeImage);; // ImageView, в який вставляється зображення

        ingredientsAdapter.setRecipeList( recipeFullItem.getIngredients() );
        ingredientsAdapter.notifyDataSetChanged();
    }

    private SpannableStringBuilder boldNormalText(String bold, String normal) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(bold + " ");
        builder.setSpan(new StyleSpan(Typeface.BOLD), 0, bold.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append(normal);
        return builder;
    }
}