package com.jogo.rpg.eventos;

import com.jogo.rpg.classes.Personagem;
import com.jogo.rpg.classes.Inimigo;
import com.jogo.rpg.classes.InimigosPredefinidos;
import com.jogo.rpg.itens.CatalogoItens;
import com.jogo.rpg.itens.Item;
import com.jogo.rpg.itens.ItemException;
import com.jogo.rpg.itens.Pocao;
import com.jogo.rpg.mecanicas.Batalha;

import java.util.Random;

public class Evento {

    private static final Random random = new Random();

    public static void encontrarInimigo(Personagem jogador) {
        // Agora pega um inimigo aleatório dos predefinidos
        Inimigo inimigo = InimigosPredefinidos.getInimigoComumAleatorio();

        System.out.println("\n⚔️ Você foi atacado por um " + inimigo.getNome() + "!");
        Batalha batalha = new Batalha(jogador, inimigo);
        boolean venceu = false;
        try {
            venceu = batalha.iniciarBatalha();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (venceu) {
            System.out.println("🏆 Você derrotou o " + inimigo.getNome() + "!");
            // Agora usa o XP do inimigo específico
            jogador.ganharXp(inimigo.getDropDeXp());
        } else {
            System.out.println("💀 Você foi derrotado...");
        }
    }

    public static void encontrarItem(Personagem jogador) throws ItemException {
        Item item = CatalogoItens.getItemAleatorio();
        if (item != null) {
            try {
                jogador.getInventario().adicionarItem(item);
                System.out.println("\n🎒 Você encontrou um item: " + item.getNome() + "!");
            } catch (ItemException e){
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("\n🪶 Você não encontrou nada útil...");
        }
    }




    // 🏚️ Evento: Entrar em uma casa
    public static void entrarCasa(Personagem jogador) {
        System.out.println("\n🏚️ Você entrou em uma casa abandonada...");
        System.out.println("Há uma velha lareira e um baú coberto de poeira.");
        int chance = random.nextInt(100);

        if (chance < 50) {

            System.out.println("Dentro do baú, você encontrou uma poção!");
            try {
                Pocao pocao = (Pocao) CatalogoItens.getItem("pocao_cura");
                jogador.getInventario().adicionarItem(pocao);

            } catch (ItemException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("O baú estava vazio... e um inimigo apareceu!!");
            encontrarInimigo(jogador);
        }
    }

    // ⛪ Evento: Entrar em uma igreja
    public static void entrarIgreja(Personagem jogador) {
        System.out.println("\n⛪ Você entrou em uma antiga igreja. O ar é calmo e sagrado...");
        System.out.println("Você sente suas forças se renovando.");
        jogador.curarVida(20);
        System.out.println("❤️ Sua vida foi restaurada em 20 pontos!");
    }

    // 👑 Evento: Entrar no castelo
    public static void entrarCastelo(Personagem jogador) {
        System.out.println("\n🏰 Você chega aos portões do castelo do rei...");
        System.out.println("Guardas olham para você com desconfiança.");
        System.out.println("Um deles se aproxima e diz: 'O rei está esperando por você...'");
    }

    // 🎲 Evento aleatório (exploração)
    public static void eventoAleatorio(Personagem jogador) {
        int sorte = random.nextInt(100);

        if (sorte < 40) {
            encontrarInimigo(jogador);
        } else if (sorte < 70) {
            try {
                encontrarItem(jogador);
            } catch (ItemException e) {
                throw new RuntimeException(e);
            }
        } else if (sorte < 85) {
            entrarCasa(jogador);
        } else {
            entrarIgreja(jogador);
        }
    }
}