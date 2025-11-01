package com.jogo.rpg.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.io.*;

public class Input {
    private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public static String getUmString() {
        String ret = null;

        try {
            ret = input.readLine();
        } catch (IOException erro) { /*Sem erro*/ }

        return ret;
    }

    public static short getUmShort() throws Exception {
        short ret = (short)0;

        try {
            ret = Short.parseShort(input.readLine());
        } catch(IOException erro) { /*Sem erro*/ }
        catch (NumberFormatException erro) {
            throw new Exception("Short Inválido!");
        }

        return ret;
    }

    public static int getUmInt() throws Exception {
        int ret = 0;

        try {
            ret = Integer.parseInt(input.readLine());
        } catch(IOException erro) { /*Sem erro*/ }
        catch (NumberFormatException erro) {
            throw new Exception("Inteiro Inválido!");
        }

        return ret;
    }
}
