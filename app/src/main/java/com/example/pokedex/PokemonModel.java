package com.example.pokedex;

public class PokemonModel {
    private int id;
    private String en_name;
    private String jp_name;
    private String type_1;
    private String type_2;
    private String HP;
    private String attack;
    private String defense;
    private String sp_atk;
    private String sp_def;
    private String speed;
    private String img_url;
    private String sprites_url;
    private Boolean isFav = false;

    //constructor
    public PokemonModel(int id,String en_name,String jp_name,String type_1,String type_2,String HP
            ,String attack,String defense, String sp_atk, String sp_def,String speed )
    {
        this.id = id; this.en_name = en_name; this.jp_name = jp_name; this.type_1 = type_1;
        this.type_2 =type_2; this.HP = HP; this.attack = attack; this.defense = defense;
        this.sp_atk = sp_atk; this.sp_def = sp_def; this.speed = speed;

        this.img_url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + id+".png";
        this.sprites_url ="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+id+".png";
    }

    public int getId() {
        return id;
    }

    public String getEn_name() {
        return en_name;
    }

    public String getJp_name() {
        return jp_name;
    }

    public String getType_1() {
        return type_1;
    }

    public String getType_2() {
        return type_2;
    }

    public String getHP() {
        return HP;
    }

    public String getAttack() {
        return attack;
    }

    public String getDefense() {
        return defense;
    }

    public String getSp_atk() {
        return sp_atk;
    }

    public String getSp_def() {
        return sp_def;
    }

    public String getSpeed() {
        return speed;
    }

    public String getImg_url(){return  img_url;}

    public String getSprites_url(){return  sprites_url;}


    public Boolean getFav() {return isFav;}

    public void setFav(int id,Boolean fav) {
        if(getId() == id){
            isFav = fav;
        } else {
            isFav = getFav();
        }
    }
}
