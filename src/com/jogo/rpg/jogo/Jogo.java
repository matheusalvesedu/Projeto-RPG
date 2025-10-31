package com.jogo.rpg.jogo;

import com.jogo.rpg.classes.*;
import com.jogo.rpg.inventario.Inventario;
import com.jogo.rpg.itens.Pocao;
import com.jogo.rpg.mecanicas.Batalha;
import com.jogo.rpg.mecanicas.Historia;
import com.jogo.rpg.utils.Input;

public class Jogo {

    public void iniciarJogo() throws Exception {

        Historia historia = new Historia();
        String nome = historia.prologo();
        Personagem jogador = escolherClasse(nome);

        System.out.println("\n=== | Personagem criado com sucesso! | ===");
        System.out.println("Nome: " + jogador.getNome());
        System.out.println("Classe: " + jogador.getClass().getSimpleName());
        System.out.println("Vida: " + jogador.getVida() + " | Ataque: " + jogador.getAtaque() + " | Defesa: " + jogador.getDefesa() + " | Nivel: " + jogador.getNivel());
        System.out.println("==========================================");

        Inimigo crackudo = new Inimigo("Crackudo André", (short)60, (short)25, (short)15, 1, 100);
        Batalha primeiraBatalha = new Batalha(jogador, crackudo);
        Boolean resultadoBatalha = primeiraBatalha.iniciarBatalha();
    }

    private Personagem escolherClasse(String nome) throws Exception {
        System.out.println("\nEscolha sua classe:");
        System.out.println("[1] Guerreiro");
        System.out.println("[2] Ranger");
        System.out.println("[3] Ladrão");
        System.out.println("[4] Especialista");

        int opcao;
        do {
            System.out.print("> ");
            opcao = Input.getUmInt();
        } while(opcao < 1 || opcao > 4);

        return switch (opcao) {
            case 1 -> new Guerreiro(nome);
            case 2 -> new Ranger(nome);
            case 3 -> new Ladrao(nome);
            case 4 -> new Especialista(nome);
            default -> new Guerreiro(nome);
        };
    }

    public static void main(String[] args) throws Exception {
        new Jogo().iniciarJogo();
    }
}
