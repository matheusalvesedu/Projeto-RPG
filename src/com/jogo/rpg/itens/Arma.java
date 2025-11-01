package com.jogo.rpg.itens;

import com.jogo.rpg.classes.Personagem;

public class Arma extends Item {

    // Atributo específico da classe Arma
    private int ataqueBonus;

    public Arma(String nome, String descricao, String efeito, Integer quantidade, Integer limite, int ataqueBonus) {
        super(nome, descricao, efeito, quantidade, limite);
        this.ataqueBonus = ataqueBonus;
    }

    public Arma(String nome, String descricao, int ataqueBonus) {
        // Armas são únicas, então quantidade e limite = 1
        super(nome, descricao, "Aumenta o ataque em " + ataqueBonus, 1, 1);
        this.ataqueBonus = ataqueBonus;
    }

    public Arma(Arma a) {
        super(a.nome, a.descricao, a.efeito, a.quantidade, a.limite);

        this.ataqueBonus = a.ataqueBonus;
    }

    @Override
    public void aplicarEfeito(Personagem personagem) throws ItemException {
        try {
            // Aumenta o ataque do personagem
            int ataqueAtual = personagem.getAtaque();
            personagem.setAtaque( (short) (ataqueAtual + this.ataqueBonus));
            System.out.println("⚔️ " + personagem.getNome() + " equipou " + this.nome);
            System.out.println("✨ Ataque aumentado em +" + this.ataqueBonus);

        } catch (Exception e) {
            throw new ItemException("Erro ao equipar arma: " + e.getMessage());
        }
    }

    @Override
    public String getTipo() {
        return "Arma";
    }

    public int getAtaqueBonus() {
        return ataqueBonus;
    }

    public void setAtaqueBonus(int ataqueBonus) {
        this.ataqueBonus = ataqueBonus;
    }

    @Override
    public Item clone() {
        return new Arma(
                this.nome,
                this.descricao,
                this.efeito,
                this.ataqueBonus,
                this.quantidade,
                this.limite
        );
    }

    @Override
    public String getInformacaoCompleta() {
        StringBuilder info = new StringBuilder();
        info.append("═══════════════════════════════\n");
        info.append("⚔️ ").append(nome).append(" x").append(quantidade).append("\n");
        info.append("───────────────────────────────\n");
        info.append("📝 Tipo: ").append(getTipo()).append("\n");
        info.append("📝 Descrição: ").append(descricao).append("\n");
        info.append("✨ Efeito: ").append(efeito).append("\n");
        info.append("💪 Bônus de Ataque: +").append(ataqueBonus).append("\n");
        info.append("📈 Limite: ").append(quantidade).append("/").append(limite).append("\n");
        info.append("═══════════════════════════════");
        return info.toString();
    }

    @Override
    public String toString() {
        return getTipo() + ": " + nome + " (+" + ataqueBonus + " ATK) x" + quantidade;
    }
}