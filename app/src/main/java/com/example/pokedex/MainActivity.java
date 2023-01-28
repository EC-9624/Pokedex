package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecycleViewInterface{
    AssetManager assetManager;
    InputStream inputStream;
    ArrayList<PokemonModel> pokemonModelArrayList = new ArrayList<>();

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
            //test if model is loaded correctly
            Log.d("poke.length", "onCreate: " + pokemonModelArrayList.size());
            for(int i =0; i< pokemonModelArrayList.size();i++){
                PokemonModel pokemon = pokemonModelArrayList.get(i);
                Log.d("pokemon", "onCreate: " + pokemon.getEn_name() + ": "+pokemon.getSprites_url());
            }

            //Pass Model List to Recycler View AFTER creating The model
            Poke_RecycleViewAdapter adapter = new Poke_RecycleViewAdapter(this,pokemonModelArrayList,this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    //read JSON File From assets
    public String loadJson(){
        String json = null;
        try {
            inputStream = this.getAssets().open("testpoke.json");
            //BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
            //Log.d("json", "loadJson: " + json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);

        intent.putExtra("Name", pokemonModelArrayList.get(position).getJp_name());
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
}