package com.jogo.rpg.mecanicas;

import com.jogo.rpg.classes.Personagem;
import com.jogo.rpg.eventos.Local;
import com.jogo.rpg.utils.Input;

public class Navegacao {

    private final Personagem jogador;
    private Local LocalAtual;

    public Navegacao(Personagem jogador, Local LocalInicial) {
        this.jogador = jogador;
        this.LocalAtual = LocalInicial;
    }

    public void iniciar() throws InterruptedException {
        boolean jogando = true;
        String escolha;

        while (jogando) {
            System.out.println("\nO que deseja fazer?");
            System.out.println("[1] Explorar / Avançar");
            System.out.println("[2] Ver Inventário");
            System.out.println("[3] Ver Status");
            System.out.println("[0] Sair do jogo");
            System.out.print("> ");

            escolha = Input.getUmString().trim();

            if (escolha.isEmpty()) {
                System.out.println("⚠️ Nenhuma opção digitada. Tente novamente.");
                continue;
            }

            switch (escolha) {
                case "1":
                    navegar();
                    break;
                case "2":
                    mostrarInventario();
                    break;
                case "3":
                    mostrarStatus();
                    break;
                case "0":
                    System.out.println("Saindo do jogo...");
                    jogando = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void navegar() throws InterruptedException {
        if (LocalAtual.getProximoLugares().isEmpty()) {
            System.out.println("Não há mais locais para explorar daqui!");
            return;
        }

        System.out.println("\nLocais disponíveis:");
        for (int i = 0; i < LocalAtual.getProximoLugares().size(); i++) {
            System.out.println((i + 1) + "️⃣ " + LocalAtual.getProximoLugares().get(i).getNome());
        }

        System.out.print("Escolha um local para ir: ");
        String entrada = Input.getUmString().trim();

        if (entrada.isEmpty()) {
            System.out.println("⚠️ Nenhuma opção digitada. Tente novamente.");
            return;
        }

        int opcao;
        try {
            opcao = Integer.parseInt(entrada) - 1; // usuário digita 1-based
        } catch (NumberFormatException e) {
            System.out.println("❌ Entrada inválida! Digite o número do local.");
            return;
        }

        if (opcao < 0 || opcao >= LocalAtual.getProximoLugares().size()) {
            System.out.println("❌ Escolha inválida!");
            return;
        }

        LocalAtual = LocalAtual.getProximoLugares().get(opcao);
        System.out.println("\nIndo para " + LocalAtual.getNome() + "...");
        LocalAtual.entrar(jogador);
    }

    private void mostrarInventario() {
        System.out.println("\n🎒 INVENTÁRIO:");
        jogador.getInventario().abrirInventario(jogador);
    }

    private void mostrarStatus() {
        System.out.println("\n📊 STATUS DO JOGADOR:");
        System.out.println(jogador);
    }
}
