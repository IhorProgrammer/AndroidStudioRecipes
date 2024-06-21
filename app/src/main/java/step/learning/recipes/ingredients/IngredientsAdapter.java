package step.learning.recipes.ingredients;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import step.learning.recipes.R;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {
    private List<IngredientsItem> ingredientsList;

    public void setRecipeList(List<IngredientsItem> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    private Context context;

    public IngredientsAdapter(List<IngredientsItem> ingredientsList, Context context) {
        this.ingredientsList = ingredientsList;
        this.context = context;
    }

    @NonNull
    @Override
    public IngredientsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_item, parent, false);
        return new IngredientsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsAdapter.ViewHolder holder, int position) {
        IngredientsItem ingredient = ingredientsList.get(position);
        holder.textViewListItem.setText(ingredient.toString());
    }

    @Override
    public int getItemCount() {
        return ingredientsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewListItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewListItem = itemView.findViewById(R.id.textViewListItem);
        }
    }
}

