package step.learning.recipes;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {
    private List<RecipeItem> recipeList;

    public void setRecipeList(List<RecipeItem> recipeList) {
        this.recipeList = recipeList;
    }

    private Context context;

    public RecipeAdapter(List<RecipeItem> recipeList, Context context) {
        this.recipeList = recipeList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecipeItem recipe = recipeList.get(position);
        holder.recipeTitle.setText(recipe.getTitle());
        holder.recipeDetail.setText(recipe.getShortInfo());
        // Отримання зображення з URL та вставка його у ImageView
        Glide.with(holder.imageView.getContext())
                .load(recipe.getFullImageURL())
                .thumbnail(0.1f) // Завантаження мініатюри розміром 10% від повного зображення
                .error(R.drawable.errorl_load_image) // Зображення для помилки
                .into(holder.imageView);; // ImageView, в який вставляється зображення

        holder.detailsButton.setOnClickListener(v -> {
            // Handle details button click
            Intent intent = new Intent(context, RecipeActivity.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView recipeTitle;
        TextView recipeDetail;
        Button detailsButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            recipeTitle = itemView.findViewById(R.id.recipeTitle);
            recipeDetail = itemView.findViewById(R.id.recipeDetail);
            detailsButton = itemView.findViewById(R.id.detailsButton);
        }
    }
}

