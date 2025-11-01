package com.jogo.rpg.jogo;

import com.jogo.rpg.classes.*;
import com.jogo.rpg.eventos.Evento;
import com.jogo.rpg.eventos.Local;
import com.jogo.rpg.inventario.Inventario;
import com.jogo.rpg.itens.Arma;
import com.jogo.rpg.itens.ItemException;
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
        armaInicial(jogador); // Adiciona a arma inicial baseado na classe

        System.out.println("\n=== | Personagem criado com sucesso! | ===");
        System.out.println("Nome: " + jogador.getNome());
        System.out.println("Classe: " + jogador.getClass().getSimpleName());
        System.out.println("Vida: " + jogador.getVida() + " | Ataque: " + jogador.getAtaque() + " | Defesa: " + jogador.getDefesa() + " | Nivel: " + jogador.getNivel());
        System.out.println("==========================================");

        Local barMessias = new Local("Bar do Messias", "Bar do Messias, o melhor saquê da cidade.", null);
        Local viela = new Local("Viela", "Uma viela... escura.", "inimigo");
        Local casa = new Local("Casa", "Uma casa.. escura.", "casa");
        Local rua = new Local("Rua", "Uma rua... normal.", "irParaMercado");
        Local mercadoCentral = new Local("Mercado Central", "Mercado onde se tem tudo.", "mercadoCentral");
        Local rua2 = new Local("Rua", "Uma rua... normal.", "irParaPrefeito");
        Local casaPrefeito = new Local("Casa Prefeito", "Casa do Prefeito, luxuosa.", "casaPrefeito");
        Local floresta = new Local("Floresta", "Uma floresta densa e assustadora.", "floresta");
        Local casaAbandonada = new Local("Casa Abandonada", "Casa abandonada, cheia de pó.", null);
        Local fimFloresta = new Local("Floresta", "Já da para enxergar o moinho daqui.", null);
        Local moinho = new Local("Moinho", "Ele é muito alto", "moinho");
        Local campos = new Local("Campos", "Campos de plantação", "campos");
        Local caverna = new Local("Caverna", "Caverna inexplorada...", "caverna");

        // Definir caminhos
        barMessias.setProximoLugares(List.of(viela));
        viela.setProximoLugares(List.of(casa));
        casa.setProximoLugares(List.of(rua));
        rua.setProximoLugares(List.of(mercadoCentral));
        mercadoCentral.setProximoLugares(List.of(rua2));
        rua2.setProximoLugares(List.of(casaPrefeito));

        historia.tutorial(jogador);

        Navegacao navegacao = new Navegacao(jogador, casa);

        System.out.println("Você acordou, se sente renovado após uma longa noite.");
        System.out.println("Mas... você está atrasado para entrega do prefeito. Vá logo!");

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

    private void armaInicial(Personagem jogador) throws ItemException {
        Arma arma = switch(jogador.getClass().getSimpleName()) {
            case "Guerreiro" -> new Arma("Espada de Madeira", "Uma espada fraca, usada como brinquedo pelas crianças.", 5);
            case "Ranger" -> new Arma("Estilingue", "Um estilingue feito à mão, pequeno e leve.", 4);
            case "Ladrao" -> new Arma("Faca de Pão", "Pequena e suja de manteiga.", 6);
            case "Especialista" -> new Arma("Soco Inglês", "Soco Inglês achado na frente de um bar.", 5);
            default -> null;
        };

        if(arma != null) {
            jogador.getInventario().adicionarItem(arma);
        };
    }
}
