package com.jogo.rpg.eventos;

import com.jogo.rpg.classes.Personagem;
import com.jogo.rpg.classes.Inimigo;
import com.jogo.rpg.classes.InimigosPredefinidos;
import com.jogo.rpg.itens.CatalogoItens;
import com.jogo.rpg.itens.Item;
import com.jogo.rpg.itens.ItemException;
import com.jogo.rpg.itens.Pocao;
import com.jogo.rpg.mecanicas.Batalha;
import com.jogo.rpg.mecanicas.Historia;

import java.util.Random;

public class Evento {

    private static final Random random = new Random();

    public static void entrarNaViela(Personagem jogador) {
        Inimigo inimigo = InimigosPredefinidos.getInimigoComum("bebado");

        System.out.println("\nVocê está sendo atacado por um " + inimigo.getNome() + "!");
        Batalha batalha = new Batalha(jogador, inimigo);
        boolean venceu = false;
        try {
            venceu = batalha.iniciarBatalha();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (venceu) {
            jogador.ganharXp(inimigo.getDropDeXp());
        } else {
            System.out.println("💀 Você foi derrotado...");
        }
    }

    public static void irParaMercado() throws InterruptedException {
        Historia historia = new Historia();
        historia.irParaMercado();
    }

    public static void mercadoCentral() throws InterruptedException {
        Historia historia = new Historia();
        historia.mercadoCentral();
    }

    public static void irParaPrefeito(Personagem jogador) throws InterruptedException {
        Historia historia = new Historia();
        historia.irParaPrefeito();

        Inimigo inimigo = InimigosPredefinidos.getInimigoComum("ladrao");
        Batalha batalha = new Batalha(jogador, inimigo);
        boolean venceu = false;
        try {
            venceu = batalha.iniciarBatalha();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (venceu) {
            System.out.println("\nO bandido está derrotado. Você respira aliviado, segurando firme o pacote.");
            jogador.ganharXp(inimigo.getDropDeXp());
        } else {
            System.out.println("💀 Você foi derrotado...");
        }
    }

    public static void casaPrefeito(Personagem jogador) throws InterruptedException {
        Historia historia = new Historia();
        historia.casaPrefeito();
    }
}