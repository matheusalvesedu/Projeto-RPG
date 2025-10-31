package com.jogo.rpg.mecanicas;

import com.jogo.rpg.classes.Inimigo;
import com.jogo.rpg.classes.Personagem;
import com.jogo.rpg.utils.Dado;
import com.jogo.rpg.utils.Input;

public class Batalha {

    private Personagem jogador;
    private Inimigo inimigo;

    public Batalha(Personagem jogador, Inimigo inimigo) {
        this.jogador = jogador;
        this.inimigo = inimigo;
    }

    public boolean iniciarBatalha() throws Exception {
        System.out.println("\nBatalha iniciando!");
        System.out.println("Jogador: " + jogador.getNome() + " VS Inimigo: " + inimigo.getNome());

        boolean jogadorVenceu = false;

        while (jogador.getVida() > 0 && inimigo.getVida() > 0) {
            System.out.println("\n--- Turno do Jogador ---");
            System.out.println("[1] Atacar");
            System.out.println("[2] Usar Inventário");
            System.out.println("[3] Tentar Fugir");
            System.out.print("Escolha uma ação: ");

            int escolha = Input.getUmInt();

            switch (escolha) {
                case 1:
                    realizarTurnoAtaque();
                    break;

                case 2:
                    jogador.getInventario().listarItens();
                    System.out.println("\n[Inventário aberto]");
//                  realizarAtaqueInimigo();
                    break;

                case 3:
                    if (tentarFugir()) {
                        System.out.println("\n" + jogador.getNome() + " fugiu da batalha!");
                        return false; // Retorna false indicando que fugiu
                    } else {
                        System.out.println("\n" + jogador.getNome() + " não conseguiu fugir!");
                        realizarAtaqueInimigo();
                    }
                    break;

                default:
                    System.out.println("Opção inválida! Turno perdido.");
                    realizarAtaqueInimigo();
                    break;
            }

            if (jogador.getVida() > 0 && inimigo.getVida() > 0) {
                System.out.println("\nVida do Jogador: " + jogador.getVida());
                System.out.println("Vida do Inimigo: " + inimigo.getVida());
            }
        }

        if (jogador.getVida() > 0) {
            System.out.println("\n" + jogador.getNome() + " venceu a batalha!");
            jogadorVenceu = true;
        } else {
            System.out.println("\n" + jogador.getNome() + " foi derrotado...");
        }

        return jogadorVenceu;
    }

    private void realizarTurnoAtaque() {
        int dadoJogador = Dado.rolarDado();
        int dadoInimigo = Dado.rolarDado();

        // Cálculo do dano
        int danoJogador = Math.max(0, (jogador.getAtaque() + dadoJogador) - inimigo.getDefesa());
        int danoInimigo = Math.max(0, (inimigo.getAtaque() + dadoInimigo) - jogador.getDefesa());

        System.out.println("\nDado: " + jogador.getNome() + " rola " + dadoJogador + " | " + inimigo.getNome() +
                " rola " + dadoInimigo);


        // Jogador ataca
        if (danoJogador > 0) {
            inimigo.tomarDano(danoJogador);
            System.out.println(jogador.getNome() + " causa " + danoJogador + " de dano!");
        } else {
            System.out.println(jogador.getNome() + " errou o ataque!");
        }

        if (inimigo.getVida() <= 0) return;

        // Inimigo contra-ataca
        if (danoInimigo > 0) {
            jogador.tomarDano(danoInimigo);
            System.out.println(inimigo.getNome() + " causa " + danoInimigo + " de dano!");
        } else {
            System.out.println(inimigo.getNome() + " errou o ataque!");
        }
    }

    private void realizarAtaqueInimigo() {
        int dadoInimigo = Dado.rolarDado();
        int danoInimigo = Math.max(0, (inimigo.getAtaque() + dadoInimigo) - jogador.getDefesa());

        System.out.println("\nDado: " + inimigo.getNome() + " rola " + dadoInimigo);

        if (danoInimigo > 0) {
            jogador.tomarDano(danoInimigo);
            System.out.println(inimigo.getNome() + " causa " + danoInimigo + " de dano!");
        } else {
            System.out.println(inimigo.getNome() + " errou o ataque!");
        }
    }

    private boolean tentarFugir() {
        // TODO: Implementar lógica de fuga
        // TODO: Pode ser baseada em dado, agilidade do personagem, etc.
        // Exemplo: int dado = Dado.rolarDado();
        // return dado >= 4; // 50% de chance

        int chanceFuga = Dado.rolarDado();
        return chanceFuga >= 4; // 50% de chance de fugir (dado 4, 5 ou 6)
    }
}