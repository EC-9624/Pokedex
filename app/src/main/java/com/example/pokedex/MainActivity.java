package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity {
    AssetManager assetManager;
    InputStream inputStream;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assetManager = getResources().getAssets();
        try {
            JSONArray array = new JSONArray(loadJson());
            Log.d("array.length", "onCreate: " + array.length());
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
                String defence = base.getString("Defence");
                String sp_atk = base.getString("Sp. Attack");
                String sp_def = base.getString("Sp. Defense");
                String speed = base.getString("Speed");
            }


        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    public String loadJson(){
        String json = null;
        try {
            inputStream = this.getAssets().open("pokedex.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

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


}