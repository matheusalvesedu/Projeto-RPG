package com.jogo.rpg.inventario;

import com.jogo.rpg.itens.Arma;
import com.jogo.rpg.itens.Armadura;
import com.jogo.rpg.itens.Item;
import com.jogo.rpg.itens.ItemException;
import com.jogo.rpg.classes.Personagem;
import com.jogo.rpg.utils.Input;

import java.util.ArrayList;
import java.util.List;

public class Inventario implements Cloneable {

    private List<Item> itens;
    private int capacidadeMaxima;

    public Inventario() {
        this.itens = new ArrayList<>();
        this.capacidadeMaxima = 20;
    }

    // Construtor de copia
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

        // Tenta empilhar em slots existentes
        for (Item existente : itens) { // Loop para procurar item igual no invetário
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

        // Cria novos slots se necessário
        while (quantidadeRestante > 0) { // Se não achar o item ou der o limite de itens no slot.
            if (itens.size() >= capacidadeMaxima) {
                throw new ItemException("Inventário cheio! Não foi possível adicionar " + quantidadeRestante + " unidades de " + novoItem.getNome());
            }

            int adicionar = Math.min(quantidadeRestante, limite);
            Item novoSlot = novoItem.clone();
            novoSlot.setQuantidade(adicionar);
            itens.add(novoSlot);
            quantidadeRestante -= adicionar;
        }

        System.out.println("Adicionado: " + novoItem.getNome() + " x" + novoItem.getQuantidade());
    }

    public void removerItem(String nome, int quantidade) throws ItemException {
        if (quantidade <= 0) {
            throw new ItemException("Quantidade para remover inválida!");
        }

        int restante = quantidade;

        for (int i = 0; i < itens.size(); i++) {
            Item item = itens.get(i);

            if (item.getNome().equalsIgnoreCase(nome)) {
                int qtdNoSlot = item.getQuantidade();

                if (qtdNoSlot <= restante) {
                    restante -= qtdNoSlot;
                    itens.remove(i);
                    i--;
                } else {
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
        if (itens.isEmpty()) {
            System.out.println("\nInventário vazio!");
            return;
        }

        System.out.println("\n📦 === | INVENTÁRIO | === 📦");
        System.out.println("Slots usados: " + itens.size() + "/" + capacidadeMaxima);
        System.out.println("─────────────────────────────");

        for (int i = 0; i < itens.size(); i++) {
            Item item = itens.get(i);

            // Mostra status de equipamento se for armadura
            String statusEquip = "";
            if (item instanceof Armadura) {
                Armadura armadura = (Armadura) item;
                if (armadura.isEquipada()) {
                    statusEquip = " [EQUIPADA]";
                }
            }

            System.out.println((i + 1) + ". " + item.getTipo() + ": " + item.getNome() + " x" + item.getQuantidade() + statusEquip);
            System.out.println("   └─ " + item.getDescricao());
            System.out.println("   └─ Efeito: " + item.getEfeito());
        }
        System.out.println("─────────────────────────────");
    }

    /**
     * Abre o inventário durante a batalha e permite usar um item
     */
    public boolean abrirInventarioBatalha(Personagem jogador) {
        if (itens.isEmpty()) {
            System.out.println("\n📦 Inventário vazio! Nenhum item para usar.");
            return false;
        }

        while (true) {
            listarItens();
            System.out.println("0. Voltar (cancelar)");
            System.out.print("\nEscolha um item para usar (número): ");

            try {
                int escolha = Input.getUmInt();

                if (escolha == 0) {
                    System.out.println("Voltando ao menu de batalha...");
                    return false;
                }

                if (escolha < 1 || escolha > itens.size()) {
                    System.out.println("❌ Opção inválida! Tente novamente.");
                    continue;
                }

                Item itemEscolhido = itens.get(escolha - 1);

                // Verifica se pode usar o item
                if (!itemEscolhido.podeUsar()) {
                    System.out.println("❌ Este item não pode ser usado agora!");
                    continue;
                }

                // Usa o item através do método aplicarEfeito
                itemEscolhido.usarItem(jogador);

                // Remove o item se a quantidade chegou a zero
                if (itemEscolhido.getQuantidade() <= 0) {
                    itens.remove(itemEscolhido);
                    System.out.println("(" + itemEscolhido.getNome() + " consumido completamente)");
                }

                return true;

            } catch (ItemException e) {
                System.out.println("❌ Erro ao usar item: " + e.getMessage());
                // Continua no loop para tentar novamente
            } catch (Exception e) {
                System.out.println("❌ Erro: " + e.getMessage());
            }
        }
    }

    /**
     * Abre o inventário fora de batalha (para equipar armaduras, organizar, etc.)
     */
    public void abrirInventario(Personagem jogador) {
        if (itens.isEmpty()) {
            System.out.println("\n📦 Inventário vazio!");
            return;
        }

        while (true) {
            listarItens();
            System.out.println("\n1. Usar/Equipar item");
            System.out.println("2. Ver detalhes de um item");
            System.out.println("3. Descartar item");
            System.out.println("0. Voltar");
            System.out.print("\nEscolha uma opção: ");

            try {
                int opcao = Input.getUmInt();

                if (opcao == 0) {
                    System.out.println("Saindo do inventário...");
                    return;
                }

                switch (opcao) {
                    case 1:
                        usarItemInventario(jogador);
                        break;

                    case 2:
                        verDetalhesItem();
                        break;

                    case 3:
                        descartarItem();
                        break;

                    default:
                        System.out.println("❌ Opção inválida!");
                }

            } catch (Exception e) {
                System.out.println("❌ Erro: " + e.getMessage());
            }
        }
    }

    private void usarItemInventario(Personagem jogador) throws Exception {
        System.out.print("\nNúmero do item para usar: ");
        int escolha = Input.getUmInt();

        if (escolha < 1 || escolha > itens.size()) {
            System.out.println("❌ Item inválido!");
            return;
        }

        Item item = itens.get(escolha - 1); // -1, pois no menu mostrado ao jogador começa pelo 1, mas o indice é o anterior

        if (!item.podeUsar()) {
            System.out.println("❌ Este item não pode ser usado!");
            return;
        }

        item.usarItem(jogador);

        if (item.getQuantidade() <= 0) {
            itens.remove(item);
            System.out.println("(" + item.getNome() + " consumido completamente)");
        }
    }

    private void verDetalhesItem() throws Exception {
        System.out.print("\nNúmero do item: ");
        int escolha = Input.getUmInt();

        if (escolha < 1 || escolha > itens.size()) {
            System.out.println("❌ Item inválido!");
            return;
        }

        Item item = itens.get(escolha - 1);
        System.out.println("\n" + item.getInformacaoCompleta());

        System.out.println("\nPressione ENTER para continuar...");
        Input.getUmString();
    }

    private void descartarItem() throws Exception {
        System.out.print("\nNúmero do item para descartar: ");
        int escolha = Input.getUmInt();

        if (escolha < 1 || escolha > itens.size()) {
            System.out.println("❌ Item inválido!");
            return;
        }

        Item item = itens.get(escolha - 1);

        System.out.print("Tem certeza que deseja descartar " + item.getNome() + "? (S/N): ");
        String confirmacao = Input.getUmString();

        if (confirmacao.equalsIgnoreCase("S")) {
            itens.remove(item);
            System.out.println("✓ " + item.getNome() + " descartado!");
        } else {
            System.out.println("Descarte cancelado.");
        }
    }

    /**
     * Verifica se tem um item específico no inventário
     */
    public boolean temItem(String nome) {
        for (Item item : itens) {
            if (item.getNome().equalsIgnoreCase(nome)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna a quantidade total de um item no inventário
     */
    public int quantidadeItem(String nome) {
        int total = 0;
        for (Item item : itens) {
            if (item.getNome().equalsIgnoreCase(nome)) {
                total += item.getQuantidade();
            }
        }
        return total;
    }

    /**
     * Retorna um item específico do inventário pelo nome
     */
    public Item getItem(String nome) {
        for (Item item : itens) {
            if (item.getNome().equalsIgnoreCase(nome)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Retorna todas as armaduras equipadas
     */
    public List<Armadura> getArmadurasEquipadas() {
        List<Armadura> equipadas = new ArrayList<>();
        for (Item item : itens) {
            if (item instanceof Armadura) {
                Armadura armadura = (Armadura) item;
                if (armadura.isEquipada()) {
                    equipadas.add(armadura);
                }
            }
        }
        return equipadas;
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

    public boolean estaVazio() {
        return itens.isEmpty();
    }
}