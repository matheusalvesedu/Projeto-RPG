package com.jogo.rpg.utils;

import java.util.Random;

public class Dado {
    private static Random random = new Random() ;
    private static int NUMERO_LADOS = 20;


    public static int rolarDado(){
        return  random.nextInt(NUMERO_LADOS) + 1 ;
    }


}
