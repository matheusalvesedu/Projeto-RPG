package com.jogo.rpg.classes;

public class Inimigo extends Personagem {

    private int dropDeXp;

    public Inimigo(String nome, short vida, short ataque, short defesa, Integer nivel, int dropDeXp) {
        super(nome, vida, ataque, defesa, nivel);
        this.dropDeXp = dropDeXp;
    }
}
