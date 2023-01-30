package com.example.pokedex;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Poke_RecycleViewAdapter extends RecyclerView.Adapter<Poke_RecycleViewAdapter.MyviewHolder> {
    private final RecycleViewInterface recycleViewInterface;

    Context context;
     ArrayList<PokemonModel> pokemonModels;

    public Poke_RecycleViewAdapter(Context context, ArrayList<PokemonModel> pokemonModels,
                                   RecycleViewInterface recycleViewInterface){
        this.context =context;
        this.pokemonModels = pokemonModels;
        this.recycleViewInterface = recycleViewInterface;
    }
    @NonNull
    @Override
    public Poke_RecycleViewAdapter.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the layout (Giving look to rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recylcerview_row,parent,false);

        return new Poke_RecycleViewAdapter.MyviewHolder(view,recycleViewInterface);
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(@NonNull Poke_RecycleViewAdapter.MyviewHolder holder, int position) {

        //assign values to the viewHolder
        holder.tv_Name.setText(pokemonModels.get(position).getJp_name());
        holder.tvType_1.setText(pokemonModels.get(position).getType_1());
        holder.TvType_2.setText(pokemonModels.get(position).getType_2());
        holder.tv_id.setText("#"+String.format("%03d", pokemonModels.get(position).getId()));
        Glide.with(holder.imageView).load(pokemonModels.get(position).getSprites_url()).into(holder.imageView);
        //holder.imageView.setBackgroundColor(holder.itemView.getResources().getColor(getCardColor(position),null));


    }



    @Override
    public int getItemCount() {
        //Number of item to display
        return pokemonModels.size();
    }

    public void filterList(ArrayList<PokemonModel> filteredList) {
        pokemonModels = filteredList;

        notifyDataSetChanged();
    }

    public static class MyviewHolder extends RecyclerView.ViewHolder{
        //grab the view from recycle view layout file
        ImageView imageView;
        TextView tv_Name;
        TextView tvType_1;
        TextView TvType_2;
        TextView tv_id;
        CardView cardView;
        ImageButton imageButton;

        public MyviewHolder(@NonNull View itemView ,RecycleViewInterface recycleViewInterface) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            imageView = itemView.findViewById(R.id.imageView);
            tv_Name = itemView.findViewById(R.id.tv_name);
            tvType_1 = itemView.findViewById(R.id.tv_type1);
            TvType_2 = itemView.findViewById(R.id.tv_type2);
            tv_id = itemView.findViewById(R.id.tv_id);
            imageButton = itemView.findViewById(R.id.imageButton1);
            imageButton.setImageResource(R.drawable.ic_baseline_star_border_24);
            imageButton.setTag(R.drawable.ic_baseline_star_border_24);

            //set item click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recycleViewInterface != null){
                        int pos =getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION){
                            recycleViewInterface.onItemClicked(pos);
                        }
                    }
                }
            });

            // set imgBtn click listener
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recycleViewInterface != null){
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            recycleViewInterface.onFavClicked(pos);
                        }
                    }

                    Integer resource = (Integer) imageButton.getTag();
                    Log.d("","onClick: "+ resource);
                    if(resource == R.drawable.ic_baseline_star_border_24){
                        imageButton.setImageResource(R.drawable.ic_baseline_star_24);
                        imageButton.setTag(R.drawable.ic_baseline_star_24);
                    } else {
                        imageButton.setImageResource(R.drawable.ic_baseline_star_border_24);
                        imageButton.setTag(R.drawable.ic_baseline_star_border_24);
                    }
                }
            });

        }

    }
}
