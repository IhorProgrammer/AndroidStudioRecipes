package step.learning.recipes;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class RecipeActivity extends AppCompatActivity {

    private ImageView recipeImage;
    private TextView recipeTitle;
    private TextView recipeShortInfo;
    private TextView recipeServings;
    private TextView recipePreparationTime;
    private TextView recipeTags;
    private TextView recipeDescription;
    private RecyclerView recipeIngredients;

    private IngredientsAdapter ingredientsAdapter;


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
        recipeTags = findViewById(R.id.recipe_tags);
        recipeDescription = findViewById(R.id.recipe_description);
        recipeIngredients = findViewById(R.id.recipe_ingredients);

        recipeIngredients.setLayoutManager(new LinearLayoutManager(this));
        ingredientsAdapter = new IngredientsAdapter(new ArrayList<>(), this);
        recipeIngredients.setAdapter(ingredientsAdapter);

        setRecipe();
    }

    private void setRecipe() {
        recipeTitle.setText("Сирники");
        recipeShortInfo.setText( boldNormalText( getResources().getString(R.string.recipe_activity_short_info) ,"Традиційні сирники з родзинками") );
        recipeServings.setText( boldNormalText(getResources().getString(R.string.recipe_activity_servings), "4" ) );
        recipePreparationTime.setText( boldNormalText(getResources().getString(R.string.recipe_activity_preparation_time), "10" + getResources().getString(R.string.recipe_activity_time)) );
        recipeTags.setText( boldNormalText(getResources().getString(R.string.recipe_activity_tags), "овсянка, фрукти, мед, сніданок") );
        List<IngredientsItem> ingredientsItemsList = new ArrayList<>();
        ingredientsItemsList.add(new IngredientsItem("сир",     "500",  "г"));
        ingredientsItemsList.add(new IngredientsItem("яйце",    "1",    "шт"));
        ingredientsItemsList.add(new IngredientsItem("цукор",   "2",    "ст.л."));
        ingredientsItemsList.add(new IngredientsItem("родзинки","50",   "г"));
        ingredientsItemsList.add(new IngredientsItem("борошно", "3",    "ст.л."));
        ingredientsItemsList.add(new IngredientsItem("олія",    "2",    "ст.л."));

        ingredientsAdapter.setRecipeList(ingredientsItemsList);
        ingredientsAdapter.notifyDataSetChanged();

        recipeDescription.setText("Сирники - це смачний полуденок. Для приготування, змішайте сир, яйце, цукор і родзинки. Додайте борошно і добре перемішайте. Сформуйте маленькі сирники і обсмажте їх на розігрітій сковороді з обох боків до золотистої скоринки. Подавайте гарячими з медом або сметаною.");
    }

    private SpannableStringBuilder boldNormalText(String bold, String normal) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(bold + " ");
        builder.setSpan(new StyleSpan(Typeface.BOLD), 0, bold.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append(normal);
        return builder;
    }
}