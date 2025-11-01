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
        if(this.vida > this.vidaMax) this.vida = this.vidaMax;
    }

    public void subirNivel(){
        this.nivel++;

        this.vida += 10;
        this.ataque += 2;
        this.defesa += 2;
        System.out.println("Você subiu para o nivel: " + this.nivel);
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
        System.out.println("Você ganhou " + quantidade + " de XP!");

        verificarNivel();
    }


    public short getVidaMax() {return this.vidaMax; };

    public String getNome() {
        return this.nome;
    };

    public short getVida() {
        return this.vida;
    };

    public short getAtaque() {
        return this.ataque;
    };

    public short getAtaqueBase() { return this.ataqueBase; };

    public short getDefesa() {
        return this.defesa;
    };

    public short getDefesaBase() { return this.defesaBase; };

    public Integer getNivel() {
        return this.nivel;
    }

    public int getXp() {
        return this.xp;
    }

    public int getXpParaProxNivel() {
        return this.xpParaProxNivel;
    }

    public Inventario getInventario() {
        return this.inventario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setVida(short vida) {
        this.vida = vida;
    }

    public void setVidaMax(short vidaMax) {
        this.vidaMax = vidaMax;
    }

    public void setAtaque(short ataque) {
        this.ataque = ataque;
    }

    public void setAtaqueBase(short ataqueBase) {
        this.ataqueBase = ataqueBase;
    }

    public void setDefesa(short defesa) {
        this.defesa = defesa;
    }

    public void setDefesaBase(short defesaBase) {
        this.defesaBase = defesaBase;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    @Override
    public String toString() {
        return "\n🧙 PERSONAGEM" +
                "\n--------------------------" +
                "\nNome: " + nome +
                "\nVida: " + vida + "/" + vidaMax +
                "\nAtaque: " + ataque +
                "\nDefesa: " + defesa +
                "\nNível: " + nivel +
                "\nXP: " + xp +
                "\n--------------------------";
    }

}
