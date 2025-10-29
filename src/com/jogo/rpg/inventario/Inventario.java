package com.jogo.rpg.inventario;

import com.jogo.rpg.items.Item;
import com.jogo.rpg.items.ItemException;

import java.util.ArrayList;
import java.util.List;

public class Inventario implements Cloneable {

    private List<Item> itens;
    private int capacidadeMaxima; // total de slots

    public Inventario() {
        this.itens = new ArrayList<>();
        this.capacidadeMaxima = 20;
    }

    public Inventario(Inventario outro) {
        this.itens = new ArrayList<>();
        this.capacidadeMaxima = outro.capacidadeMaxima;
        for (Item item : outro.itens) {
            this.itens.add(item.clone());
        }
    }

    public void adicionarItem(Item novoItem) throws ItemException {
        if (novoItem == null) {
            throw new ItemException("Não é possível adicionar item null");
        }

        if (novoItem.getQuantidade() <= 0) {
            throw new ItemException("Quantidade deve ser maior que zero");
        }

        int quantidadeRestante = novoItem.getQuantidade();
        int limite = novoItem.getLimite();

        for (Item existente : itens) {
            if (existente.equals(novoItem)) {
                int espacoDisponivel = limite - existente.getQuantidade();

                if (espacoDisponivel > 0) {
                    int adicionar = Math.min(quantidadeRestante, espacoDisponivel);
                    existente.adicionarQuantidade(adicionar);
                    quantidadeRestante -= adicionar;

                    if (quantidadeRestante <= 0) {
                        System.out.println("✓ Empilhado: " + novoItem.getNome() + " x" + novoItem.getQuantidade());
                        return;
                    }
                }
            }
        }

        while (quantidadeRestante > 0) {
            if (itens.size() >= capacidadeMaxima) {
                throw new ItemException("Inventário cheio! Não foi possível adicionar " + quantidadeRestante + " unidades de " + novoItem.getNome());
            }

            int adicionar = Math.min(quantidadeRestante, limite);
            Item novoSlot = novoItem.clone();
            novoSlot.setQuantidade(adicionar);
            itens.add(novoSlot);
            quantidadeRestante -= adicionar;
        }

        System.out.println("✓ Adicionado: " + novoItem.getNome() + " x" + novoItem.getQuantidade());
    }

    public void removerItem(String nome, int quantidade) throws ItemException {
        if (quantidade <= 0) {
            throw new ItemException("Quantidade para remover inválida!");
        }

        int restante = quantidade;

        for (int i = 0; i < itens.size(); i++) {
            Item item = itens.get(i);

            if (item.getNome().equals(nome)) {
                int qtdNoSlot = item.getQuantidade();

                if (qtdNoSlot <= restante) {

                    restante -= qtdNoSlot;
                    itens.remove(i);
                    i--; // ajusta índice, já que a lista diminuiu
                } else {
                    // Só diminui a quantidade
                    item.setQuantidade(qtdNoSlot - restante);
                    restante = 0;
                    break;
                }
            }
        }

        if (restante > 0) {
            throw new ItemException("Itens insuficientes para remover: faltam " + restante);
        }
    }

    public void listarItens() {

    }

    @Override
    public Inventario clone() {
        return new Inventario(this);
    }

    public List<Item> getItens() {
        return itens;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }
}
