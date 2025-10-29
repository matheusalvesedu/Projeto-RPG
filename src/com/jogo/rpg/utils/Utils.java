package com.jogo.rpg.utils;

import java.awt.desktop.SystemEventListener;

public class Utils {
    public static void limparConsole(String texto) {
        try {
           if(System.getProperty("os.name").toLowerCase().contains("windows")) {
               new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
           } else {
               System.out.print("\033[H\033[2J");
               System.out.flush();
           }

           System.out.println(texto);
        } catch (Exception e) {
            System.out.println("Não foi possível limpar o console. " + e);
        }
    }
}
