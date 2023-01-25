package com.example.pokedex;

public class PokemonModel {
    private int id;
    private String en_name;
    private String jp_name;
    private String[] type;
    private String HP;
    private String attack;
    private String defence;
    private String sp_atk;
    private String sp_def;
    private String speed;

    //constructor
    public PokemonModel(int id,String en_name,String jp_name,String[] type,String HP
            ,String attack,String defence, String sp_atk, String sp_def,String speed )
    {
        this.id = id; this.en_name = en_name; this.jp_name = jp_name; this.type = type;
        this.HP = HP; this.attack = attack; this.defence = defence; this.sp_atk = sp_atk;
        this.sp_def = sp_def; this.speed = speed;
    }
}
