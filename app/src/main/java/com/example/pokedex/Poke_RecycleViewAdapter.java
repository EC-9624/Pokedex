package com.example.pokedex;

import android.annotation.SuppressLint;
import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class Poke_RecycleViewAdapter extends RecyclerView.Adapter<Poke_RecycleViewAdapter.MyviewHolder> implements Filterable {
    private final RecycleViewInterface recycleViewInterface;

    Context context;
     ArrayList<PokemonModel> pokemonModelArrayList;
     ArrayList<PokemonModel> filteredPokemonList = new ArrayList<>();


    public Poke_RecycleViewAdapter(Context context, ArrayList<PokemonModel> pokemonModels,
                                   RecycleViewInterface recycleViewInterface){
        this.context =context;
        this.pokemonModelArrayList = pokemonModels;
        //copy The Full arraylist to use in filter
        this.filteredPokemonList = pokemonModels;
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
        holder.tv_Name.setText(pokemonModelArrayList.get(position).getEn_name());
        holder.tvType_1.setText(pokemonModelArrayList.get(position).getType_1());
        holder.TvType_2.setText(pokemonModelArrayList.get(position).getType_2());
        holder.tv_id.setText("#"+String.format("%03d", pokemonModelArrayList.get(position).getId()));
        Glide.with(holder.imageView).load(pokemonModelArrayList.get(position).getSprites_url()).into(holder.imageView);
        //holder.imageView.setBackgroundColor(holder.itemView.getResources().getColor(getCardColor(position),null));


    }


    @Override
    public int getItemCount() {
        //Number of item to display
        return pokemonModelArrayList.size();
    }

    @Override
    public Filter getFilter() {
        return pokeFilter;
    }

    private final Filter pokeFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();

            if(charSequence == null || charSequence.length() == 0){
                results.values = filteredPokemonList;
                results.count = filteredPokemonList.size();
            } else {

                String pattern = charSequence.toString().toLowerCase().trim();
                ArrayList<PokemonModel> pokemonModels = new ArrayList<>();
                for(PokemonModel pokemon : filteredPokemonList){
                    if(pokemon.getEn_name().toLowerCase().contains(pattern)
                            || pokemon.getType_1().toLowerCase().contains(pattern)
                            ||(pokemon.getType_2() != null && pokemon.getType_2().toLowerCase().contains(pattern))){
                        pokemonModels.add(pokemon);
                    }
                }
                results.values = pokemonModels;
                results.count = pokemonModels.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            pokemonModelArrayList = (ArrayList<PokemonModel>) filterResults.values;
            notifyDataSetChanged();
        }
    };

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
