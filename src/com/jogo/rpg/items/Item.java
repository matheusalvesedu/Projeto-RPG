package com.jogo.rpg.items;

public class Item implements Comparable<Item>, Cloneable {

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

    public void adicionarQuantidade(int qtd) throws ItemException
    {
        if (this.quantidade + qtd > limite)  throw new ItemException("Item esgotado!");
        this.quantidade += qtd ;
    }

    public void usarItem() throws Exception
    {
            this.quantidade-- ;
    }

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

    public Integer getLimite(){
        return this.limite;
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
    public boolean equals(Object obj)
    {
        if ( this == obj ) return true ;
        if ( obj == null ) return false ;
        if (this.getClass() != obj.getClass()) return false ;
        Item item = (Item)obj;
        return this.nome.equals(item.nome);
    }

    @Override
    public int compareTo(Item outro) {
        return this.nome.compareTo(outro.nome);
    }


    public Item clone() {
        return new Item(this.nome, this.descricao, this.efeito, this.quantidade, this.limite);
    }
}
