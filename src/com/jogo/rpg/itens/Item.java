package com.jogo.rpg.itens;

import com.jogo.rpg.classes.Personagem;

public abstract class Item implements Comparable<Item>, Cloneable {

    protected String nome;
    protected String descricao;
    protected String efeito;
    protected Integer quantidade;
    protected Integer limite;

    public Item(String nome, String descricao, String efeito, Integer quantidade, Integer limite) {
        this.nome = nome;
        this.descricao = descricao;
        this.efeito = efeito;
        this.quantidade = quantidade;
        this.limite = limite;
    }

    protected Item() {
    }

    /**
     * Método abstrato que define o comportamento ao usar o item
     * Deve ser implementado por cada tipo de item específico
     */
    public abstract void aplicarEfeito(Personagem personagem) throws ItemException;

    /**
     * Método abstrato para obter o tipo do item
     */
    public abstract String getTipo();

    /**
     * Usa o item, aplicando seu efeito e diminuindo a quantidade
     */
    public void usarItem(Personagem personagem) throws ItemException {
        if (this.quantidade <= 0) {
            throw new ItemException("Item " + nome + " esgotado!");
        }

        aplicarEfeito(personagem);
        this.quantidade--;
    }

    /**
     * Adiciona quantidade ao item respeitando o limite
     */
    public void adicionarQuantidade(int qtd) throws ItemException {
        if (this.quantidade + qtd > limite) {
            throw new ItemException("Limite do item excedido! Máximo: " + limite);
        }
        this.quantidade += qtd;
    }

    /**
     * Verifica se o item pode ser usado
     */
    public boolean podeUsar() {
        return this.quantidade > 0;
    }

    /**
     * Verifica se está no limite máximo
     */
    public boolean estaNoLimite() {
        return this.quantidade >= this.limite;
    }

    /**
     * Retorna informações detalhadas do item
     */
    public String getInformacaoCompleta() {
        StringBuilder info = new StringBuilder();
        info.append("═══════════════════════════════\n");
        info.append("📦 ").append(nome).append(" x").append(quantidade).append("\n");
        info.append("───────────────────────────────\n");
        info.append("📝 Tipo: ").append(getTipo()).append("\n");
        info.append("📝 Descrição: ").append(descricao).append("\n");
        info.append("✨ Efeito: ").append(efeito).append("\n");
        info.append("📈 Limite: ").append(quantidade).append("/").append(limite).append("\n");
        info.append("═══════════════════════════════");
        return info.toString();
    }

    // Getters e Setters
    public Integer getQuantidade() {
        return quantidade;
    }

    public String getEfeito() {
        return efeito;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getLimite() {
        return this.limite;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public void setEfeito(String efeito) {
        this.efeito = efeito;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setLimite(Integer limite) {
        this.limite = limite;
    }

    @Override
    public String toString() {
        return getTipo() + ": " + nome + " x" + quantidade;
    }

    @Override
    public int hashCode () {
        int ret  = 1;
        ret = ret * 13 + this.nome.hashCode() ;
        ret = ret * 13 + this.descricao.hashCode();
        ret = ret * 13 +this.quantidade.hashCode();
        ret = ret *13 + this.efeito.hashCode();
        if (ret < 0 ) ret -= ret ;
        return ret ;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;

        Item item = (Item) obj;
        return this.nome.equals(item.nome);
    }

    @Override
    public int compareTo(Item outro) {
        if (outro == null) return 1;

        // Primeiro compara por tipo
        int comparacaoTipo = this.getTipo().compareTo(outro.getTipo());
        if (comparacaoTipo != 0) return comparacaoTipo;

        // Depois por nome
        return this.nome.compareTo(outro.nome);
    }

    @Override
    public abstract Item clone();
}



