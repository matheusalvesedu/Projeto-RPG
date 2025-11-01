package com.jogo.rpg.classes;

import com.jogo.rpg.classes.Inimigo;
import java.util.*;

public class InimigosPredefinidos {

    private static final Map<String, Inimigo> inimigosComuns = new HashMap<>();
    private static final Map<String, Inimigo> inimigosRaros = new HashMap<>();
    private static final Map<String, Inimigo> chefes = new HashMap<>();
    private static final Random random = new Random();

    /*
    Categoria   Vida     Ataque  Defesa	 Drop XP
    Comum	    60–100   10–20	 5–15	 20–40 XP
    Raro	    120–180  20–30	 15–25	 60–100 XP
    Chefe	    250–400  35–55	 25–45	 200–400 XP */
    static {
        // Inimigos Comuns
        inimigosComuns.put("ladrao", new Inimigo(
                "Ladrão de Rua",
                (short) 80,
                (short) 15,
                (short) 10,
                1,
                25,
                "Criminoso que age nas ruas e becos."
        ));
        inimigosComuns.put("bebado", new Inimigo(
                "Bêbado Briguento",
                (short) 70,
                (short) 12,
                (short) 8,
                1,
                20,
                "Um homem bêbado que ataca por impulso."
        ));
        inimigosComuns.put("lobo", new Inimigo(
                "Lobo",
                (short) 90,
                (short) 18,
                (short) 8,
                1,
                30,
                "Lobo selvagem. Rápido."
        ));

        // Inimigos Raros
        inimigosRaros.put("guarda_real", new Inimigo(
                "Guarda Real",
                (short) 160,
                (short) 28,
                (short) 22,
                1,
                90,
                "Guarda do Rei."
        ));
        inimigosRaros.put("urso", new Inimigo(
                "Urso",
                (short) 180,
                (short) 30,
                (short) 18,
                1,
                100,
                "Fera territorial. Melhor não mexer com ele..."
        ));
        inimigosRaros.put("fazendeiro", new Inimigo(
                "Fazendeiro Irritado",
                (short) 145,
                (short) 25,
                (short) 14,
                1,
                95,
                "Odeia que entrem nas terras dele, não está de brincadeira."
        ));

        // Chefes / Boss
        chefes.put("ogro_zak", new Inimigo(
                "Ogro Zak",
                (short) 400,
                (short) 50,
                (short) 40,
                1,
                400,
                "O monstro das lendas... existe."
        ));

        chefes.put("rei_morfeu", new Inimigo(
                "Rei Morfeu",
                (short) 300,
                (short) 45,
                (short) 35,
                1,
                350,
                "O Rei bonzinho..."
        ));
    }

    public static Inimigo getInimigoComum(String chave) {
        Inimigo base = inimigosComuns.get(chave);
        if (base == null) return null;
        return base.clone();
    }

    public static Inimigo getInimigoRaro(String chave) {
        Inimigo base = inimigosRaros.get(chave);
        if (base == null) return null;
        return base.clone();
    }

    public static Inimigo getChefe(String chave) {
        Inimigo base = chefes.get(chave);
        if (base == null) return null;
        return base.clone();
    }

    public static Inimigo getInimigoComumAleatorio() {
        Object[] valores = inimigosComuns.values().toArray();
        return ((Inimigo) valores[random.nextInt(valores.length)]).clone();
    }

    public static Inimigo getInimigoRaroAleatorio() {
        Object[] valores = inimigosRaros.values().toArray();
        return ((Inimigo) valores[random.nextInt(valores.length)]).clone();
    }

    public static Inimigo getChefeAleatorio() {
        Object[] valores = chefes.values().toArray();
        return ((Inimigo) valores[random.nextInt(valores.length)]).clone();
    }

    public static Set<String> listarInimigosComuns() {
        return inimigosComuns.keySet();
    }

    public static Set<String> listarInimigosRaros() {
        return inimigosComuns.keySet();
    }

    public static Set<String> listarChefes() {
        return chefes.keySet();
    }
}
