package com.jogo.rpg.classes;

public class Inimigo extends Personagem {

    private int dropDeXp;

    public Inimigo(String nome, short vida, short ataque, short defesa, Integer nivel, int dropDeXp) {
        super(nome, vida, ataque, defesa, nivel);
        this.dropDeXp = dropDeXp;
    }

    public int getDropDeXp() {
        return dropDeXp;
    }

    @Override
    public Inimigo clone(){
        return new Inimigo(
                this.nome,
                this.vida,
                this.ataque,
                this.defesa,  // ← Corrigido: era this.ataque
                this.nivel,
                this.dropDeXp
        );  // ← Faltava o ponto e vírgula
    }

}