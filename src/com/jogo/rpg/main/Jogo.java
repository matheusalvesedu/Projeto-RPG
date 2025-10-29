package com.jogo.rpg.main;

import com.jogo.rpg.classes.*;
import com.jogo.rpg.utils.Utils;

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
        System.out.print("Insira o nome do seu personagem: ");
        String nome = scanner.nextLine();

        Personagem jogador = escolherClasse(nome);

        Utils.limparConsole("");

        System.out.println("\n=== | Personagem criado com sucesso! | ===");
        System.out.println("Nome: " + jogador.getNome());
        System.out.println("Classe: " + jogador.getClass().getSimpleName());
        System.out.println("Vida: " + jogador.getVida() + " | Ataque: " + jogador.getAtaque() + " | Defesa: " + jogador.getDefesa());
        System.out.println("==========================================");


        // batalha de teste (inicial):
        Inimigo bandido = new Inimigo("Bandido", (short)70, (short)40, (short)10, 1);
        System.out.println("\nUm " + bandido.getNome() + " quer te roubar!");

        batalhar(jogador, bandido);
    }

    private Personagem escolherClasse(String nome) {
        System.out.println("\nEscolha sua classe:");
        System.out.println("[1] Guerreiro");
        System.out.println("[2] Ranger");
        System.out.println("[3] Ladrão");
        System.out.println("[4] Especialista");

        int opcao;
        do {
            System.out.print("> ");
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
            System.out.println("\nSeu HP: " + jogador.getVida() + " | HP do " + inimigo.getNome() + ": " + inimigo.getVida());
            System.out.println("[1] Atacar");
            System.out.println("[X] Usar item");
            System.out.println("[3] Fugir");

            System.out.print("> ");
            int acao = scanner.nextInt();
            scanner.nextLine();

            if (acao == 3) {
                if (random.nextInt(20) > 10) {
                    Utils.limparConsole("=== | BATALHA | ====");
                    System.out.println("Você fugiu da batalha.");
                    return;
                } else {
                    Utils.limparConsole("=== | BATALHA | ====");
                    System.out.println("Não conseguiu fugir! O inimigo te ataca.");
                }
            } else if(acao == 2) {
                // abrirInventario(jogador);
                continue;
            }

            int dadoJogador = random.nextInt(10);
            System.out.println("Você tirou " + dadoJogador + " no dado.");
            int danoJogador = jogador.getAtaque() + dadoJogador - inimigo.getDefesa();
            if(danoJogador > 0) inimigo.tomarDano(danoJogador);
            System.out.println("Seu ataque causou " + danoJogador + " de dano!");

            if(inimigo.getVida() <= 0) {
                System.out.println("\nVocê derrotou o " + inimigo.getNome() + "!");
                jogador.ganharXp(50);
                return;
            }

            int dadoInimigo = random.nextInt(10);
            System.out.println("O " + inimigo.getNome() + " tirou " + dadoInimigo + " no dado.");
            int danoInimigo = inimigo.getAtaque() + dadoInimigo - jogador.getDefesa();
            if(danoInimigo > 0) jogador.tomarDano(danoInimigo);
            if(danoInimigo < 0) {danoInimigo = 0;}
            System.out.println("O inimigo causou " + danoInimigo + " de dano!");

            if(jogador.getVida() <= 0) {
                System.out.println("\nVocê foi derrotado por " + inimigo.getNome() + "...");
                return;
            }

        }
    }

    public static void main(String[] args) {
        new Jogo().iniciarJogo();
    }
}
