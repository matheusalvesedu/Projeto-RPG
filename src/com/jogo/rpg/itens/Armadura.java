package com.jogo.rpg.itens;

import com.jogo.rpg.classes.Personagem;

public class Armadura extends Item {

    private int bonusDefesa;
    private boolean equipada;

    public Armadura(String nome, String descricao, Integer quantidade, Integer limite, int bonusDefesa) {
        super(nome, descricao, "+" + bonusDefesa + " Defesa", quantidade, limite);
        this.bonusDefesa = bonusDefesa;
        this.equipada = false;
    }

    // Construtor simplificado
    public Armadura(String nome, int bonusDefesa) {
        super(nome, "Uma armadura que oferece proteção", "+" + bonusDefesa + " Defesa", 1, 1);
        this.bonusDefesa = bonusDefesa;
        this.equipada = false;
    }

    @Override
    public void aplicarEfeito(Personagem personagem) throws ItemException {
        if (equipada) {
            desequipar(personagem);
        } else {
            equipar(personagem);
        }
    }

    public void equipar(Personagem personagem) throws ItemException {
        if (equipada) {
            throw new ItemException(nome + " já está equipada!");
        }

        personagem.setDefesa((short)bonusDefesa);
        equipada = true;

        System.out.println("🛡️ " + personagem.getNome() + " equipou " + nome + "!");
        System.out.println("   Defesa: +" + bonusDefesa);
    }

    public void desequipar(Personagem personagem) throws ItemException {
        if (!equipada) {
            throw new ItemException(nome + " não está equipada!");
        }
        personagem.setDefesa( (short) -bonusDefesa);
        equipada = false;
        System.out.println("🛡️ " + personagem.getNome() + " desequipou " + nome);
        System.out.println("   Defesa: -" + bonusDefesa);
    }

    @Override
    public String getTipo() {
        return "Armadura";
    }

    public int getBonusDefesa() {
        return bonusDefesa;
    }

    public boolean isEquipada() {
        return equipada;
    }

    public void setBonusDefesa(int bonusDefesa) {
        this.bonusDefesa = bonusDefesa;
        this.efeito = "+" + bonusDefesa + " Defesa";
    }

    @Override
    public String toString() {
        String status = equipada ? " [EQUIPADA]" : "";
        return getTipo() + ": " + nome + status + " (+" + bonusDefesa + " DEF)";
    }

    @Override
    public Item clone() {
        Armadura clone = new Armadura(this.nome, this.descricao, this.quantidade, this.limite, this.bonusDefesa);
        clone.equipada = this.equipada;
        return clone;
    }
}