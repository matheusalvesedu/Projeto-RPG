package com.jogo.rpg.mecanicas;

import com.jogo.rpg.classes.Personagem;
import com.jogo.rpg.eventos.Evento;
import com.jogo.rpg.itens.CatalogoItens;
import com.jogo.rpg.utils.Input;

public class Historia {

    public String prologo() {
        System.out.println("========================================================");
        System.out.println("||                O Último Herdeiro                   ||");
        System.out.println("========================================================");
        System.out.println("\nNo Reino de Cairon, outrora próspero, vive sob o peso da decadência.");
        System.out.println("As ruas do reino estão repletas de fome, e o povo esqueceu");
        System.out.println("as histórias que um dia moldaram seu destino.");

        System.out.println("\nEntre essas antigas histórias, há uma que ainda ecoa nos salões do castelo...");
        System.out.println("A lenda do Ogro Zak, uma criatura colossal que invadiu as muralhas de Cairon.");
        System.out.println("Dizem que o rei da época, com ajuda dos seus melhores guerreiros,");
        System.out.println("conseguiu aprisionar Zak nas masmorras do palácio.");

        System.out.println("\nMas o tempo passou... e o medo virou silêncio.");
        System.out.println("Nas sombras das ruas da capital, um jovem órfão luta para sobreviver.");
        System.out.println("Rápido, astuto e habilidoso, ele é conhecido apenas como 'O órfão de Cairon'.");
        System.out.print("Mas qual é o seu verdadeiro nome? ");

        String nome = Input.getUmString();

        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Não tem nome? Então vou te chamar de Indigente.");
            return "Indigente";
        }

        return nome;
    }

    public void tutorial(Personagem jogador) throws Exception {
        System.out.println("\n--------------------------------------------------------");
        System.out.println("Você acabou de realizar uma entrega no Bar do Messias.");
        System.out.println("Messias, o dono do bar, agradece pelo serviço e joga uma moeda sobre o balcão.");
        System.out.println("Bom trabalho " + jogador.getNome() + ". Mas se fosse você, não ficava por aqui essa hora...");
        System.out.println("\nDo lado de fora, a noite cai sobre a cidade. Uma brisa fria sopra pela porta entreaberta.");
        System.out.println("Há duas opções: ficar no bar e descansar um pouco, ou sair e seguir pela viela ao lado.");
        System.out.println("Dizem que é perigosa, mas também é o caminho mais rápido de volta para casa.");
        System.out.println("\n[1] Ficar no bar por mais um tempo");
        System.out.println("[2] Ir embora pela viela");
        System.out.println("Escolha uma opção: ");

        int opcao = Input.getUmInt();

        if(opcao == 1) {
            System.out.println("\n\n\n\n\n\nVocê decide ficar mais um pouco, mas nada de interessante acontece.");
            System.out.println("O bar começa a esvaziar, e você percebe que está ficando tarde demais.");
            System.out.println("Só resta uma opção, ir embora pela viela.");
            Thread.sleep(3000);
        }

        System.out.println("\n--------------------------------------------------------");
        System.out.println("Você entra na viela escura. O som do bar se distancia.");
        System.out.println("As luzes se apagam uma a uma, e tudo o que resta é o eco dos seus passos.");
        Thread.sleep(2000);
        System.out.println("De repente, uma voz rouca interrompe o silêncio:");
        Thread.sleep(2000);
        System.out.println("“Ei... tem umas moedas aí pra compartilhar?”");
        Thread.sleep(2000);
        System.out.println("Um homem cambaleante surge das sombras. O cheiro de bebida é forte.");
        System.out.println("É um BÊBADO BRIGUENTO!");
        Thread.sleep(2000);
        System.out.println("Ele se aproxima, tropeçando, mas com o punho cerrado.");
        System.out.println("\nPrepare-se para o seu primeiro combate!");
        System.out.println("--------------------------------------------------------");

        Evento.entrarNaViela(jogador);
        if(jogador.getVida() <= 0) {
            return;
        }

        System.out.println("O bêbado agora está caido no chão, melhor sair dai antes que aparecam outros.");
        System.out.println("Você ve uma caixa de madeira no canto, deseja vasculhar?");
        System.out.println("\n[1] Vasculhar a caixa");
        System.out.println("[2] Ir para casa");

        opcao = Input.getUmInt();

        if(opcao == 1) {
            System.out.println("\n\n\n\n\n\nVocê se aproxima da caixa e examina com cuidado...");
            Thread.sleep(2000);
            System.out.println("Com um estalo, a tampa se abre, dentro há três pequenos frascos vermelhos!");
            Thread.sleep(2000);
            System.out.println("🧪 Você encontrou 3 Poções de Cura!");

            for (int i = 0; i < 3; i++) {
                jogador.getInventario().adicionarItem(CatalogoItens.getItem("pocao_cura"));
            }

            System.out.println("\nVocê guarda as poções com cuidado na sua bolsa.");
            Thread.sleep(2500);
        }

        System.out.println("Você chegou em casa, descanse pois amanhã tem uma entrega importante para o prefeito.");
    }

    public void irParaMercado() throws InterruptedException {
        System.out.println("\nVocê sai de casa correndo na direção do Mercado Central.");
        Thread.sleep(1500);
        System.out.println("Já passou do meio dia, o sol quente ilumina as ruas estreitas da cidade.");
        Thread.sleep(1500);
    }

    public void mercadoCentral() throws InterruptedException {
        System.out.println("\nVocê chega ao Mercado Central. O barulho das barracas e das negociações toma conta do ambiente.");
        Thread.sleep(1500);
        System.out.println("Entre vendedores e clientes, você localiza o ajudante do prefeito com a encomenda pronta.");
        Thread.sleep(1500);

        System.out.println("\nAjudante do prefeito: \"Ah, você deve ser o entregador do dia! Aqui está o pacote que deve ser entregue à Casa do Prefeito.\"");
        Thread.sleep(1500);
        System.out.println("Você pega cuidadosamente o pacote e confere se está tudo em ordem.");
        Thread.sleep(1500);

        System.out.println("📦 Entrega recebida! Agora é hora de seguir para a Casa do Prefeito.");
        Thread.sleep(1500);
    }

    public void irParaPrefeito() throws InterruptedException {
        System.out.println("\nVocê começa a caminhar pelas ruas da cidade em direção à Casa do Prefeito.");
        Thread.sleep(1500);
        System.out.println("A rua está relativamente calma, mas você sabe que em qualquer viela pode haver problemas.");
        Thread.sleep(1500);
        System.out.println("Você segura firme o pacote e mantém os olhos atentos a qualquer movimento suspeito.");
        Thread.sleep(1500);
        System.out.println("\nDe repente, um homem encapuzado bloqueia o seu caminho em uma rua estreita.");
        Thread.sleep(1500);
        System.out.println("Bandido: \"Ei, rapaz! Entrega aí! Não quero te machucar, só quero o pacote...\"");
        Thread.sleep(1500);

        System.out.println("\nVocê percebe que ele está armado com uma adaga enferrujada e que não parece ter boas intenções.");
        Thread.sleep(1500);
        System.out.println("Prepare-se para a batalha!");
        Thread.sleep(1000);
    }

    public void casaPrefeito() throws InterruptedException {
        System.out.println("\nFinalmente, você chega à Casa do Prefeito, um prédio imponente no centro da cidade.");
        Thread.sleep(1500);
        System.out.println("Guardas olham para você com curiosidade, mas permitem a passagem após verem o pacote.");
        Thread.sleep(1500);

        System.out.println("\nPrefeito: \"Ah, vejo que trouxe a encomenda. Muito bem! Um pouco atrasado... porém entregou.\"");
        Thread.sleep(1500);
        System.out.println("Ele sorri e lhe entrega algumas moedas como recompensa pelo serviço.");
        Thread.sleep(1500);

        System.out.println("\nMissão concluída! Você a entrega com sucesso.");
        Thread.sleep(1500);
    }


}