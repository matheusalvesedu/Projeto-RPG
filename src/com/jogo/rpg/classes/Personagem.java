package com.jogo.rpg.classes;

import com.jogo.rpg.inventario.Inventario;

public abstract class Personagem implements Cloneable {

    protected String nome;
    protected short vida;
    protected short vidaMax;
    protected short ataque;
    protected short ataqueBase;
    protected short defesa;
    protected short defesaBase;
    protected Integer nivel;
    protected int xp;
    protected int xpParaProxNivel;
    protected Inventario inventario;

    public Personagem (String nome, short vida, short ataque, short defesa, Integer nivel) {
        this.nome = nome;
        this.vida = vida;
        this.vidaMax = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.nivel = nivel;
        this.xp = 0;
        this.xpParaProxNivel = 100;
        this.inventario = new Inventario();
    }

    public Personagem(Personagem outro) {
        this.nome = outro.nome;
        this.vida = outro.vida;
        this.ataque = outro.ataque;
        this.defesa = outro.defesa;
        this.nivel = outro.nivel;
        this.inventario = outro.inventario.clone();
    }

    public void tomarDano(int dano) {
        if(dano < 0) dano = 0;
        this.vida -= dano;
        if(this.vida < 0) this.vida = 0;
    }

    public void curarVida(int vida) {
        if(vida < 0) vida = 0;
        this.vida += vida;
        if(this.vida > this.vidaMax) vida = this.vidaMax;
    }

    public void subirNivel(){
        this.nivel++;

        this.vida += 10;
        this.ataque += 2;
        this.defesa += 2;
    }

    public void verificarNivel() {
        while(this.xp >= this.xpParaProxNivel) {
            this.xp -= this.xpParaProxNivel;
            subirNivel();
            this.xpParaProxNivel *= 1.2; // aumenta 20% o xp necessário para o prox nivel
        }
    }

    public void ganharXp(int quantidade) {
        this.xp += quantidade;

        verificarNivel();
    }

    public String getNome() {
        return this.nome;
    }

    public short getVida() {
        return this.vida;
    }

    public short getAtaque() {
        return this.ataque;
    }

    public short getDefesa() {
        return this.defesa;
    }

    public Integer getNivel() {
        return this.nivel;
    }

    public int getXp() {
        return this.xp;
    }

    public int getXpParaProxNivel() {
        return this.xpParaProxNivel;
    }
}
