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
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║         BATALHA INICIANDO!            ║");
        System.out.println("╚═══════════════════════════════════════╝");
        System.out.println("⚔️  " + jogador.getNome() + " VS " + inimigo.getNome() + " ⚔️");

        boolean jogadorVenceu = false;

        while (jogador.getVida() > 0 && inimigo.getVida() > 0) {

            // Loop do turno - repete até o jogador fazer uma ação válida
            boolean turnoExecutado = false;

            while (!turnoExecutado) {
                System.out.println("\n╔═══════════════════════════════════════╗");
                System.out.println("║         TURNO DO JOGADOR              ║");
                System.out.println("╠═══════════════════════════════════════╣");
                System.out.println("║ [1] ⚔️  Atacar                        ║");
                System.out.println("║ [2] 🎒 Usar Inventário                ║");
                System.out.println("║ [3] 🏃 Tentar Fugir                   ║");
                System.out.println("╚═══════════════════════════════════════╝");
                System.out.print("Escolha uma ação: ");

                try {
                    int escolha = Input.getUmInt();

                    switch (escolha) {
                        case 1:
                            // Atacar
                            realizarTurnoAtaque();
                            turnoExecutado = true; // Turno foi usado
                            break;

                        case 2:
                            // Abrir inventário
                            boolean usouItem = jogador.getInventario().abrirInventarioBatalha(jogador);

                            if (usouItem) {
                                // Usou item - inimigo ataca
                                System.out.println("\n" + jogador.getNome() + " usou um item!");
                                realizarAtaqueInimigo();
                                turnoExecutado = true; // Turno foi usado
                            } else {
                                // Cancelou - volta ao menu sem gastar turno
                                System.out.println("\nVoltando ao menu de ações...");
                                turnoExecutado = false; // NÃO gastou turno, repete o loop
                            }
                            break;

                        case 3:
                            // Tentar fugir
                            if (tentarFugir()) {
                                System.out.println("\n🏃 " + jogador.getNome() + " fugiu da batalha!");
                                return false; // Fugiu com sucesso
                            } else {
                                System.out.println("\n❌ " + jogador.getNome() + " não conseguiu fugir!");
                                realizarAtaqueInimigo();
                                turnoExecutado = true; // Turno foi usado (falhou na fuga)
                            }
                            break;

                        default:
                            System.out.println("❌ Opção inválida! Escolha 1, 2 ou 3.");
                            turnoExecutado = false; // Não gasta turno em opção inválida
                            break;
                    }

                } catch (Exception e) {
                    System.out.println("❌ Erro: " + e.getMessage());
                    System.out.println("Tente novamente...");
                    turnoExecutado = false; // Erro não gasta turno
                }
            }

            // Mostra status após o turno (se ambos ainda estão vivos)
            if (jogador.getVida() > 0 && inimigo.getVida() > 0) {
                System.out.println("\n╔═══════════════════════════════════════╗");
                System.out.println("║            STATUS DA BATALHA          ║");
                System.out.println("╠═══════════════════════════════════════╣");
                System.out.println("║ 💚 " + String.format("%-15s", jogador.getNome()) +
                        "HP: " + String.format("%3d/%3d", jogador.getVida(), jogador.getVidaMax()) + "     ║");
                System.out.println("║ 💔 " + String.format("%-15s", inimigo.getNome()) +
                        "HP: " + String.format("%3d     ", inimigo.getVida()) + "     ║");
                System.out.println("╚═══════════════════════════════════════╝");
            }
        }

        // Resultado da batalha
        System.out.println("\n╔═══════════════════════════════════════╗");
        if (jogador.getVida() > 0) {
            System.out.println("║          🎉 VITÓRIA! 🎉               ║");
            System.out.println("╠═══════════════════════════════════════╣");
            System.out.println("║ " + String.format("%-37s", jogador.getNome() + " venceu a batalha!") + "║");
            System.out.println("╚═══════════════════════════════════════╝");
            jogadorVenceu = true;
        } else {
            System.out.println("║          💀 DERROTA 💀                ║");
            System.out.println("╠═══════════════════════════════════════╣");
            System.out.println("║ " + String.format("%-37s", jogador.getNome() + " foi derrotado...") + "║");
            System.out.println("╚═══════════════════════════════════════╝");
        }

        return jogadorVenceu;
    }

    private void realizarTurnoAtaque() {
        int dadoJogador = Dado.rolarDado();
        int dadoInimigo = Dado.rolarDado();

        // Cálculo do dano
        int danoJogador = Math.max(0, (jogador.getAtaque() + dadoJogador) - inimigo.getDefesa());
        int danoInimigo = Math.max(0, (inimigo.getAtaque() + dadoInimigo) - jogador.getDefesa());

        System.out.println("\n🎲 Dados rolados:");
        System.out.println("   " + jogador.getNome() + ": " + dadoJogador +
                " | " + inimigo.getNome() + ": " + dadoInimigo);

        // Jogador ataca
        System.out.println("\n⚔️  Ataque de " + jogador.getNome() + ":");
        if (danoJogador > 0) {
            inimigo.tomarDano(danoJogador);
            System.out.println("   💥 Causou " + danoJogador + " de dano!");
        } else {
            System.out.println("   ❌ Errou o ataque!");
        }

        if (inimigo.getVida() <= 0) return;

        // Inimigo contra-ataca
        System.out.println("\n⚔️  Contra-ataque de " + inimigo.getNome() + ":");
        if (danoInimigo > 0) {
            jogador.tomarDano(danoInimigo);
            System.out.println("   💥 Causou " + danoInimigo + " de dano!");
        } else {
            System.out.println("   ❌ Errou o ataque!");
        }
    }

    private void realizarAtaqueInimigo() {
        int dadoInimigo = Dado.rolarDado();
        int danoInimigo = Math.max(0, (inimigo.getAtaque() + dadoInimigo) - jogador.getDefesa());

        System.out.println("\n🎲 " + inimigo.getNome() + " rola: " + dadoInimigo);
        System.out.println("⚔️  Ataque de " + inimigo.getNome() + ":");

        if (danoInimigo > 0) {
            jogador.tomarDano(danoInimigo);
            System.out.println("   💥 Causou " + danoInimigo + " de dano!");
        } else {
            System.out.println("   ❌ Errou o ataque!");
        }
    }

    private boolean tentarFugir() {
        System.out.println("\n🏃 Tentando fugir...");
        int chanceFuga = Dado.rolarDado();
        System.out.println("🎲 Rolou: " + chanceFuga + " (precisa 4+)");

        return chanceFuga >= 4; // 50% de chance (4, 5 ou 6)
    }
}