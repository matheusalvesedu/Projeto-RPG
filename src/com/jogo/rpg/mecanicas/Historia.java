package com.jogo.rpg.mecanicas;

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

    public void introducao(String nomeJogador) {
        System.out.println("\n--------------------------------------------------------");
        System.out.println("\nBem-vindo(a), " + nomeJogador + ".");
        System.out.println("Você vive no Reino de Cairon, um lugar governado com mão de ferro pelo Rei... ");
        System.out.println("...e assombrado pela lenda do Ogro Zak, 'O Devorador de Reis'.");
        System.out.println("Dizem que o antigo exército do Rei mal conseguiu prender a fera");
        System.out.println("nas masmorras profundas abaixo do castelo, onde ela definha há décadas.");
        System.out.println("As crianças são avisadas: 'Seja bom, ou Orc virá buscá-lo'.");
        System.out.println("Para você, sempre foi apenas uma história de ninar macabra.");
        System.out.println("Até hoje.");
    }

    public void chamadoReal() {
        System.out.println("\n--------------------------------------------------------");
        System.out.println("Um arauto real, com o brasão do Rei Doido, bate à sua porta improvisada.");
        System.out.println("Seu estômago gela. Pessoas como você só são chamadas ao castelo por um motivo...");
        System.out.println("\n'O Rei Doido exige sua presença', diz o guarda, sem cerimônia.");
        System.out.println("'Uma missão de grande honra foi designada a você.'");
        System.out.println("Você engole em seco. 'Honra' é uma palavra que os nobres usam muito antes de");
        System.out.println("mandar alguém para a morte.");
        System.out.println("Você é levado pelos corredores frios do castelo...");
    }

    public void aMissaoFalsa(String nomeJogador) {
        System.out.println("\n--------------------------------------------------------");
        System.out.println("O salão do trono é vasto e escuro. O Rei Doido está sentado no trono,");
        System.out.println("seu rosto escondido nas sombras, exceto por um sorriso fino.");
        System.out.println("\n'Então este é " + nomeJogador + ". O(A) sobrevivente das ruas', diz ele, com desdém.");
        System.out.println("'Temos um problema. Um ninho de goblins tem atacado as rotas de suprimento.");
        System.out.println("Eles se escondem nas catacumbas antigas, perto das masmorras.'");
        System.out.println("\n'Leve este destacamento', ele gesticula para 4 guardas de elite. 'Limpe o ninho.'");
        System.out.println("'Seu sucesso trará glória... e talvez um lugar melhor que as sarjetas.'");
        System.out.println("O sorriso dele não alcança seus olhos.");
    }

    public void aEmboscada() {
        System.out.println("\n--------------------------------------------------------");
        System.out.println("Os guardas o levam para as profundezas úmidas do castelo.");
        System.out.println("Não há goblins. Apenas o cheiro de mofo e desespero.");
        System.out.println("Vocês chegam a um grande portão de ferro. O capitão se vira para você.");
        System.out.println("\n'O Rei manda lembranças', diz ele, enquanto os outros sacam suas espadas.");
        System.out.println("'Ordens do Rei! Matem-no e joguem o corpo no fosso!'");
        System.out.println("\nÉ UMA EMBOSCADA!");
        // <-- CHAMADA PARA O MÉTODO DE BATALHA (Jogador vs Guardas) -->

        System.out.println("\n...Após uma luta brutal, você está ferido. O último guarda, morrendo, ri.");
        System.out.println("'Tolo... o Rei não queria você morto... ainda. Ele queria você... aqui.'");
        System.out.println("O guarda puxa uma alavanca. O chão sob seus pés se abre.");
        System.out.println("Você cai na escuridão total.");
    }

    public void nasMasmorras() {
        System.out.println("\n--------------------------------------------------------");
        System.out.println("...Você acorda em pedra fria. O ar é fétido, cheira a sangue e carne podre.");
        System.out.println("Seu equipamento se foi... exceto por uma adaga e uma poção que estavam");
        System.out.println("escondidas em sua bota. (Inventário Atualizado!)");
        System.out.println("\nUm rugido gutural ecoa, sacudindo a poeira do teto.");
        System.out.println("Do outro lado de uma vasta arena de ossos, dois olhos vermelhos se abrem na escuridão.");
        System.out.println("A lenda é real. Orc está aqui.");
        System.out.println("Você não foi jogado aqui para morrer. Você foi jogado aqui como... alimento.");
        System.out.println("Orc se levanta, sua sombra cobrindo toda a sala.");
        System.out.println("'CARNE FRESCA...', ruge a criatura.");
    }

    public void vitoriaSobreOgro() {
        System.out.println("\n--------------------------------------------------------");
        System.out.println("Com um último golpe desesperado, Orc cai de joelhos, e então desaba.");
        System.out.println("O silêncio é ensurdecedor.");
        System.out.println("Você recupera o fôlego, exausto. Ao se aproximar da criatura caída,");
        System.out.println("você nota algo estranho.");
        System.out.println("\nPreso em sua armadura rústica, há um pedaço de pano bordado.");
        System.out.println("É um brasão. O brasão da antiga família real, que todos pensavam estar extinta.");
        System.out.println("O Ogro... ele não era um monstro qualquer. Ele era o último guardião.");
        System.out.println("\nEm sua mão gigante e fechada, ele segura um medalhão.");
        System.out.println("Você o abre. Dentro... está o mesmo símbolo da sua marca de nascença.");
    }

    public void aFugaEConfronto() {
        System.out.println("\n--------------------------------------------------------");
        System.out.println("O medalhão... a marca... o brasão. A verdade o atinge como um raio.");
        System.out.println("O Ogro não estava preso. Ele estava *protegendo* esta câmara.");
        System.out.println("Você encontra uma passagem secreta atrás do 'trono' de ossos do Ogro.");
        System.out.println("Ela sobe, e sobe... até que você chuta uma grade e cai no salão do trono.");
        System.out.println("\nO Rei Doido está lá, comemorando com sua corte.");
        System.out.println("Ele congela ao vê-lo. O sangue do ogro cobre você.");
        System.out.println("'IMPOSSÍVEL!', ele grita. 'Ninguém sobrevive ao Guardião!'");
    }

    public void aRevelacao(String nomeJogador) {
        System.out.println("\n--------------------------------------------------------");
        System.out.println("'Guardião?', você pergunta, sua voz rouca de fúria.");
        System.out.println("Você joga o medalhão com o brasão real aos pés do trono.");
        System.out.println("\nO Rei Doido recua como se tivesse sido queimado.");
        System.out.println("'Então... você sabe.'");
        System.out.println("'Eu tentei tanto', ele sibila, seu rosto se contorcendo de ódio.");
        System.out.println("'Papai sempre disse que você era o especial, mesmo sendo o mais novo.'");
        System.out.println("'Mesmo quando eu orquestrei a 'Praga' para limpar a linha de sucessão...'");
        System.out.println("'...o Velho Ogro, leal até o fim, conseguiu contrabandear você para fora do castelo.'");
        System.out.println("\nO Rei se levanta do trono, tirando a coroa e revelando a *mesma* marca de nascença que você.");
        System.out.println("'Eu sou o primogênito. O trono é meu por direito!', ele grita.");
        System.out.println("'Você não é " + nomeJogador + ", o órfão.'");
        System.out.println("\n'Você é o Príncipe Perdido. E eu sou seu irmão mais velho.'");
        System.out.println("'E agora', ele diz, sacando sua lâmina banhada em veneno, 'EU VOU TERMINAR O QUE COMECEI.'");
    }

    public void finalAto1() {
        System.out.println("\n--------------------------------------------------------");
        System.out.println("O Rei Doido avança.");
        System.out.println("Os guardas da corte cercam você. A batalha pela sua vida,");
        System.out.println("pelo seu nome e pelo futuro do Reino Doido...");
        System.out.println("...está apenas começando.");
        System.out.println("\n[FIM DO ATO I - PREPARE-SE PARA A BATALHA FINAL!]");
        // <-- CHAMADA PARA A BATALHA FINAL (Jogador vs Rei) -->
    }
}