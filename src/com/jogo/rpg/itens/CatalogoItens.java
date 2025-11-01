package com.jogo.rpg.itens;


import java.util.*;

public class CatalogoItens {

    private static final Map<String, Item> itens = new HashMap<>();

    static {
        itens.put("pocao_cura", new Pocao("Poção de Cura", 20,1));
        itens.put("armadura_couro", new Armadura("Armadura de Couro", 3));
    }

    // Retorna uma cópia nova para evitar modificar o item base
    public static Item getItem(String chave) {
        Item base = itens.get(chave);
        if (base == null) return null;
        return base.clone(); // ✅ chama o clone() específico da subclasse
    }

    public static Item getItemAleatorio() {
        List<Item> lista = new ArrayList<>(itens.values());
        if (lista.isEmpty()) return null;
        Item base = lista.get(new Random().nextInt(lista.size()));
        return base.clone(); // retorna uma cópia
    }

}
