package com.jogo.rpg.eventos;

import com.jogo.rpg.classes.Personagem;
import com.jogo.rpg.itens.ItemException;
import com.jogo.rpg.utils.Input;

import java.util.List;
import java.util.Objects;

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

    public void entrar(Personagem jogador) throws InterruptedException {
        System.out.println("\nVocê chegou em: " + nome);
        System.out.println(descricao);

        // Executa o evento do lugar
        if (tipoEvento != null) {
            switch (tipoEvento) {
                case "irParaMercado" -> Evento.irParaMercado();
                case "mercadoCentral" -> Evento.mercadoCentral();
                case "irParaPrefeito" -> Evento.irParaPrefeito(jogador);
                case "casaPrefeito" -> Evento.casaPrefeito(jogador);
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

    @Override
    public String toString() {
        return nome + " - " + descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Local)) return false;
        Local local = (Local) o;
        return Objects.equals(nome, local.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
