package com.jogo.rpg.classes;

public class Inimigo extends Personagem {

    private int dropDeXp;
    protected String descricao;

    public Inimigo(String nome, short vida, short ataque, short defesa, Integer nivel, int dropDeXp, String descricao) {
        super(nome, vida, ataque, defesa, nivel);
        this.dropDeXp = dropDeXp;
        this.descricao = descricao;
    }

    // construtor de copia
    public Inimigo(Inimigo i) {
        super(i.nome, i.vida, i.ataque, i.defesa, i.nivel);
        this.dropDeXp = i.dropDeXp;
        this.descricao = i.descricao;
    }

    public int getDropDeXp() {
        return dropDeXp;
    }

    public void setDropDeXp(int dropDeXp) { this.dropDeXp = dropDeXp; };

    @Override
    public Inimigo clone(){
        return new Inimigo(
                this.nome,
                this.vida,
                this.ataque,
                this.defesa,
                this.nivel,
                this.dropDeXp,
                this.descricao
        );
    }

}