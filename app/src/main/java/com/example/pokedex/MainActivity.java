package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.PorterDuff;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
//import android.widget.SearchView;
import  androidx.appcompat.widget.SearchView;

import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements RecycleViewInterface{
    AssetManager assetManager;
    InputStream inputStream;
    ArrayList<PokemonModel> pokemonModelArrayList = new ArrayList<>();
    ArrayList<PokemonModel> favList = new ArrayList<>();
    private Poke_RecycleViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.mRecylcerview);
        assetManager = getResources().getAssets();

        try {
            //Building PokemonModel put it in arraylist
            JSONArray array = new JSONArray(loadJson());

            for(int i = 0; i < array.length();i++){
                JSONObject object = array.getJSONObject(i);
                int id = object.getInt("id");
                JSONObject name = object.getJSONObject("name");
                String english = name.getString("english");
                String japanese = name.getString("japanese");
                JSONArray typelist = object.getJSONArray("type");
                String type_1 = typelist.getString(0);
                String type_2 = null;
                if(typelist.length() == 2)  type_2 = typelist.getString(1);
                JSONObject base = object.getJSONObject("base");
                String HP = base.getString("HP");
                String attack = base.getString("Attack");
                String defense = base.getString("Defense");
                String sp_atk = base.getString("Sp. Attack");
                String sp_def = base.getString("Sp. Defense");
                String speed = base.getString("Speed");

                pokemonModelArrayList.add(new PokemonModel(id,english,japanese,type_1,type_2,HP,attack,defense,sp_atk,sp_def,speed));
            }

            //Pass Model List to Recycler View AFTER creating The model
            adapter = new Poke_RecycleViewAdapter(this,pokemonModelArrayList,this);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);

        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    //read JSON File From assets
    public String loadJson(){
        String json = null;
        try {
            inputStream = this.getAssets().open("pokedex.json");
            //BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, StandardCharsets.UTF_8);
            //Log.d("json", "loadJson: " + json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);

        intent.putExtra("Name", pokemonModelArrayList.get(position).getEn_name());
        intent.putExtra("Jpname",pokemonModelArrayList.get(position).getJp_name());
        intent.putExtra("Type_1",pokemonModelArrayList.get(position).getType_1());
        intent.putExtra("Type_2",pokemonModelArrayList.get(position).getType_2());
        intent.putExtra("Hp",pokemonModelArrayList.get(position).getHP());
        intent.putExtra("Attack",pokemonModelArrayList.get(position).getAttack());
        intent.putExtra("Defense",pokemonModelArrayList.get(position).getDefense());
        intent.putExtra("Sp_Atk",pokemonModelArrayList.get(position).getSp_atk());
        intent.putExtra("Sp_Def",pokemonModelArrayList.get(position).getSp_def());
        intent.putExtra("Speed",pokemonModelArrayList.get(position).getSpeed());
        intent.putExtra("img_url",pokemonModelArrayList.get(position).getImg_url());


        startActivity(intent);
    }


    //create menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //inflate
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_menu,menu);

        MenuItem searchItem = menu.findItem(R.id.actionSearch);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search a pokemon...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                    adapter.getFilter().filter(newText);
                return true;
            }
        });
        MenuItem favItem = menu.findItem(R.id.action_Fav);
        Drawable drawable = favItem.getIcon();
        drawable.mutate();


        favItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {


                if(favItem.isChecked() == false){
                    favItem.setIcon(R.drawable.ic_baseline_star_24_white);
                    favItem.setChecked(!favItem.isChecked());
                    favFilter();

                } else if (favItem.isChecked() == true){
                    favItem.setIcon(R.drawable.ic_baseline_star_border_24_white);
                    favItem.setChecked(!favItem.isChecked());
                    changeFavFilter();

                }

                return false;
            }
        });

        return true;
    }

    private void favFilter(){

        for(PokemonModel pokemon : pokemonModelArrayList){
            if(pokemon.getFav() == true ){
                if(!favList.contains(pokemon)){
                    favList.add(pokemon);
                }
            }
        }
        adapter.filterList(favList);
    }
    private void changeFavFilter(){
        adapter.filterList(pokemonModelArrayList);
    }

}