package com.jogo.rpg.eventos;

import com.jogo.rpg.classes.Personagem;
import com.jogo.rpg.itens.ItemException;
import com.jogo.rpg.utils.Input;

import java.util.List;

public class Local {
    private String nome;
    private String descricao;
    private String tipoEvento; // "inimigo", "item", "igreja", "casa", "castelo"
    private List<Local> proximoLugares;

    public Local(String nome, String descricao, String tipoEvento) {
        this.nome = nome;
        this.descricao = descricao;
        this.tipoEvento = tipoEvento;
    }

    public void setProximoLugares(List<Local> proximoLugares) {
        this.proximoLugares = proximoLugares;
    }

    public List<Local> getProximoLugares() {
        return proximoLugares;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void entrar(Personagem jogador) {
        System.out.println("\n🏞️ Você chegou em: " + nome);
        System.out.println(descricao);

        // Executa o evento do lugar
        if (tipoEvento != null) {
            switch (tipoEvento) {
                case "inimigo" -> Evento.encontrarInimigo(jogador);
                case "item" -> {
                    try {
                        Evento.encontrarItem(jogador);
                    } catch (ItemException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "igreja" -> Evento.entrarIgreja(jogador);
                case "casa" -> Evento.entrarCasa(jogador);
                case "castelo" -> Evento.entrarCastelo(jogador);
                case "aleatorio" -> Evento.eventoAleatorio(jogador);
            }
        }

        // Checa se o jogador morreu
        if (jogador.getVida() <= 0) {
            System.out.println("💀 Você morreu! Voltando ao último checkpoint...");
            jogador.curarVida(jogador.getVidaMax());
            return; // permanece no mesmo lugar
        }

        // Exibe opções de navegação
        if (proximoLugares == null || proximoLugares.isEmpty()) {
            System.out.println("⚔️ Não há mais lugares para explorar a partir daqui.");
            return;
        }

        System.out.println("\nPara onde deseja ir?");
        for (int i = 0; i < proximoLugares.size(); i++) {
            System.out.println((i + 1) + " - " + proximoLugares.get(i).getNome());
        }
        System.out.println("0 - 🏠 Voltar ao menu principal");

        int escolha = -1;
        try {
            escolha = Input.getUmInt();
        } catch (Exception e) {
            System.out.println("❌ Entrada inválida.");
            return;
        }

        if (escolha == 0) {
            System.out.println("🔙 Retornando ao menu principal...");
            return; // Sai da função, voltando para quem chamou (ex: Navegação)
        }

        escolha -= 1; // ajustar índice
        if (escolha >= 0 && escolha < proximoLugares.size()) {
            Local proximoLugar = proximoLugares.get(escolha);
            proximoLugar.entrar(jogador); // chamada recursiva
        } else {
            System.out.println("Escolha inválida, permanecendo no lugar atual.");
            entrar(jogador); // repete o mesmo lugar
        }
    }
}
