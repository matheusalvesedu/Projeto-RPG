package com.jogo.rpg.jogo;

import com.jogo.rpg.classes.*;
import com.jogo.rpg.eventos.Evento;
import com.jogo.rpg.eventos.Local;
import com.jogo.rpg.inventario.Inventario;
import com.jogo.rpg.itens.Arma;
import com.jogo.rpg.itens.Pocao;
import com.jogo.rpg.mecanicas.Batalha;
import com.jogo.rpg.mecanicas.Historia;
import com.jogo.rpg.mecanicas.Navegacao;
import com.jogo.rpg.utils.Input;

import java.util.List;

public class Jogo {

    public void iniciarJogo() throws Exception {

        Historia historia = new Historia();
        String nome = historia.prologo();
        Personagem jogador = escolherClasse(nome);
        Arma arma = new Arma("Arma de fogo", "Arma de fogo", 20);
        jogador.getInventario().adicionarItem(arma);


        System.out.println("\n=== | Personagem criado com sucesso! | ===");
        System.out.println("Nome: " + jogador.getNome());
        System.out.println("Classe: " + jogador.getClass().getSimpleName());
        System.out.println("Vida: " + jogador.getVida() + " | Ataque: " + jogador.getAtaque() + " | Defesa: " + jogador.getDefesa() + " | Nivel: " + jogador.getNivel());
        System.out.println("==========================================");


        Local base = new Local("Base", "Sua base segura.", null);
        Local floresta = new Local("Floresta", "Uma floresta sombria cheia de perigos.", "inimigo");
        Local casa = new Local("Casa abandonada", "Uma casa velha e misteriosa.", "casa");
        Local igreja = new Local("Igreja", "Uma igreja antiga e sagrada.", "igreja");
        Local castelo = new Local("Castelo do Rei", "O castelo do rei Doido.", "castelo");
        Local caminho = new Local("Estrada sombria", "Uma estrada nao muito segura", "aleatorio");

// Definir caminhos
        base.setProximoLugares(List.of(floresta, casa, caminho));
        floresta.setProximoLugares(List.of(castelo, igreja, caminho));
        casa.setProximoLugares(List.of(floresta));
        igreja.setProximoLugares(List.of(castelo));
        castelo.setProximoLugares(List.of());
        caminho.setProximoLugares(List.of(floresta,base));


// Iniciar e
        Navegacao navegacao = new Navegacao(jogador, base);
        navegacao.iniciar();


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
