package com.jogo.rpg.itens;

import com.jogo.rpg.classes.Personagem;

public class Pocao extends Item {

    private int valorCura;

    public Pocao(String nome, String descricao, Integer quantidade, Integer limite, int valorCura) {
        super(nome, descricao, "Restaura " + valorCura + " HP", quantidade, limite);
        this.valorCura = valorCura;
    }

    public Pocao(String nome, int valorCura, Integer quantidade) {
        super(nome, "Uma poção que restaura pontos de vida", "Restaura " + valorCura + " HP", quantidade, 99);
        this.valorCura = valorCura;
    }

    @Override
    public void aplicarEfeito(Personagem personagem) throws ItemException {
        short vidaAntes = personagem.getVida();
        personagem.curarVida(valorCura);
        short vidaDepois = personagem.getVida();
        int curaReal = vidaDepois - vidaAntes;

        System.out.println("🧪 " + personagem.getNome() + " bebeu " + nome + "!");
        System.out.println("   HP restaurado: " + curaReal + " (" + vidaDepois + "/" + personagem.getVida() + ")");
    }

    @Override
    public String getTipo() {
        return "Poção";
    }

    public int getValorCura() {
        return valorCura;
    }

    public void setValorCura(int valorCura) {
        this.valorCura = valorCura;
        this.efeito = "Restaura " + valorCura + " HP";
    }

    @Override
    public Item clone() {
        return new Pocao(this.nome, this.descricao, this.quantidade, this.limite, this.valorCura);
    }
}