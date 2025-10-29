package com.jogo.rpg.main;

import com.jogo.rpg.classes.*;

import java.util.Random;
import java.util.Scanner;

public class Jogo {

    private Scanner scanner;
    private Random random;

    public Jogo() {
        scanner = new Scanner(System.in);
        random = new Random();
    }

    public void iniciarJogo() {
        System.out.println("=== | BEM-VINDO AO RPG | ====");
        System.out.println("Insira o nome do seu personagem: ");]
        String nome = scanner.nextLine();

        Personagem jogador = escolherClasse(nome);

        System.out.println("\n=== | Personagem criado com sucesso! | ===");
        System.out.println("Nome: " + jogador.getNome());
        System.out.println("Classe: " + jogador.getClass().getSimpleName());
        System.out.println("Vida: " + jogador.getVida() + " | Ataque: " + jogador.getAtaque() + " | Defesa: " + jogador.getDefesa());
        System.out.println("\n==========================================");


    }

    private Personagem escolherClasse(String nome) {
        System.out.println("\nEscolha sua classe:");
        System.out.println("[1] Guerreiro");
        System.out.println("[2] Ranger");
        System.out.println("[3] Ladrão");
        System.out.println("[4] Especialista");

        int opcao;
        do {
            System.out.println("> ");
            opcao = scanner.nextInt();
        } while(opcao < 1 || opcao > 4);

        return switch (opcao) {
            case 1 -> new Guerreiro(nome);
            case 2 -> new Ranger(nome);
            case 3 -> new Ladrao(nome);
            case 4 -> new Especialista(nome);
            default -> new Guerreiro(nome);
        };
    }

    private void batalhar(Personagem jogador, Inimigo inimigo) {
        System.out.println("=== | INÍCIO DA BATALHA | ====");
        while(jogador.getVida() > 0 && inimigo.getVida() > 0) {

        }
    }
}
