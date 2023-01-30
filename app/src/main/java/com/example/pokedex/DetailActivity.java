package com.example.pokedex;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokemon_detail);

        String name = getIntent().getStringExtra("Name");
        String hp = getIntent().getStringExtra("Hp");
        String atk = getIntent().getStringExtra("Attack");
        String def = getIntent().getStringExtra("Defense");
        String sp_atk = getIntent().getStringExtra("Sp_Atk");
        String sp_def = getIntent().getStringExtra("Sp_Def");
        String speed = getIntent().getStringExtra("Speed");
        String img_url = getIntent().getStringExtra("img_url");
        String type1 = getIntent().getStringExtra("Type_1");
        String type2 = getIntent().getStringExtra("Type_2");

        //TextView
        TextView tvName = findViewById(R.id.tvDetail_name);
        TextView tvHp = findViewById(R.id.tvHp);
        TextView tvAtk = findViewById(R.id.tvAtk);
        TextView tvDef = findViewById(R.id.tvDef);
        TextView tvSpAtk = findViewById(R.id.tv_SpAtk);
        TextView tvSpDef = findViewById(R.id.tv_SpDef);
        TextView tvSpeed = findViewById(R.id.tv_speed);
        TextView tvType1 = findViewById(R.id.tvDetail_type1);
        TextView tvType2 = findViewById(R.id.tvDetail_type2);
        ImageView img = findViewById(R.id.imageView3);


        tvName.setText(name);
        tvType1.setText(type1);
        tvType2.setText(type2);
        tvHp.setText("HP:   "+ "\t" + hp);
        tvAtk.setText("Atk:  " + "\t"+ atk);
        tvDef.setText("Def:  "+ "\t" + def);
        tvSpAtk.setText("Sp.Atk:  " + "\t" + sp_atk);
        tvSpDef.setText("Sp.Def:  " +"\t" + sp_def);
        tvSpeed.setText("Speed:  " + "\t" + speed );
        Glide.with(img).load(img_url).into(img);

        //progress bar
        ProgressBar hpBar = findViewById(R.id.tv_HpBar);
        ProgressBar atkBar = findViewById(R.id.tv_AtkBar);
        ProgressBar defBar = findViewById(R.id.tv_DefBar);
        ProgressBar spAtkbar = findViewById(R.id.tv_SpAtkBar);
        ProgressBar spDefbar = findViewById(R.id.tv_SpDefBar);
        ProgressBar speedBar = findViewById(R.id.tv_speedBar);

        hpBar.setProgress(Integer.parseInt(hp));
        atkBar.setProgress(Integer.parseInt(atk));
        defBar.setProgress(Integer.parseInt(def));
        spAtkbar.setProgress(Integer.parseInt(sp_atk));
        spDefbar.setProgress(Integer.parseInt(sp_def));
        speedBar.setProgress(Integer.parseInt(speed));

        CardView cardView = findViewById(R.id.cv2);
        cardView.setCardBackgroundColor(ContextCompat.getColor(this, R.color.lightgray));



    }

    private int getCardColor() {

        int result = 0;
        switch (getIntent().getStringExtra("Type_1")){
            case "Normal": result = R.color.normal; break;
            case "Fire": result = R.color.fire;break;
            case "Water": result = R.color.water; break;
            case "Electric" : result = R.color.eletric; break;
            case "Grass" : result = R.color.grass; break;
            case "Ice" : result = R.color.ice; break;
            case "Fighting" : result = R.color.fighting; break;
            case "Bug" : result = R.color.bug; break;
            case "Poison" : result = R.color.poison; break;
            case "Ground" : result = R.color.ground; break;
            case "Flying" : result = R.color.flying; break;
            case "Psychic" : result = R.color.psychic; break;
            case "Rock" : result = R.color.rock; break;
            case "Ghost" : result = R.color.ghost; break;
            case "Dragon" : result = R.color.dragon; break;
            case "Dark" : result = R.color.dark; break;
            case "Steel" : result = R.color.steel; break;
            case "Fairy" : result = R.color.fairy; break;
        }
        return result;

    }
}