package com.jogo.rpg.classes;

import com.jogo.rpg.classes.Inimigo;
import java.util.*;

public class InimigosPredefinidos {

    private static final Map<String, Inimigo> inimigosComuns = new HashMap<>();
    private static final Map<String, Inimigo> chefes = new HashMap<>();
    private static final Random random = new Random();

    static {
        // -------------------
        // 🌿 Inimigos Comuns
        // -------------------
        inimigosComuns.put("goblin", new Inimigo(
                "Goblin Selvagem",
                (short) 35,
                (short) 6,
                (short) 2,
                1,
                20
        ));

        inimigosComuns.put("planta", new Inimigo(
                "Planta Selvagem",
                (short) 40,
                (short) 8,
                (short) 1,
                2,
                30
        ));

        inimigosComuns.put("golem", new Inimigo(
                "Golem de Pedra",
                (short) 80,
                (short) 10,
                (short) 6,
                4,
                60
        ));

        inimigosComuns.put("vampiro", new Inimigo(
                "Vampiro Sombrio",
                (short) 70,
                (short) 12,
                (short) 3,
                5,
                75
        ));

        // -------------------
        // 👑 Chefes / Bosses
        // -------------------
        chefes.put("ogro_zak", new Inimigo(
                "Ogro Zak, o Devorador de Reis",
                (short) 150,
                (short) 18,
                (short) 7,
                6,
                250
        ));

        chefes.put("rei_doido", new Inimigo(
                "O Rei Doido",
                (short) 120,
                (short) 15,
                (short) 5,
                7,
                300
        ));
    }

    // ----------------------------------------
    // Métodos de acesso
    // ----------------------------------------

    /** Retorna uma cópia de um inimigo comum pelo nome */
    public static Inimigo getInimigoComum(String chave) {
        Inimigo base = inimigosComuns.get(chave);
        if (base == null) return null;
        return base.clone();
    }

    /** Retorna uma cópia de um chefe pelo nome */
    public static Inimigo getChefe(String chave) {
        Inimigo base = chefes.get(chave);
        if (base == null) return null;
        return base.clone();
    }

    /** Retorna um inimigo comum aleatório */
    public static Inimigo getInimigoComumAleatorio() {
        Object[] valores = inimigosComuns.values().toArray();
        return ((Inimigo) valores[random.nextInt(valores.length)]).clone();
    }

    /** Retorna um chefe aleatório (caso queira eventos especiais) */
    public static Inimigo getChefeAleatorio() {
        Object[] valores = chefes.values().toArray();
        return ((Inimigo) valores[random.nextInt(valores.length)]).clone();
    }

    /** Retorna lista de nomes disponíveis (útil para debug ou menus) */
    public static Set<String> listarInimigosComuns() {
        return inimigosComuns.keySet();
    }

    public static Set<String> listarChefes() {
        return chefes.keySet();
    }
}
