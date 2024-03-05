import java.util.*;

public class QuizProgramacao {
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();
    private static int pontuacao = 0;
    private static Set<Integer> perguntasRespondidas = new HashSet<>();

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════╗");
        System.out.println("║       Bem-vindo ao Quiz de         ║");
        System.out.println("║         Programação!                ║");
        System.out.println("║                                    ║");
        System.out.println("║ Um desafio empolgante para testar  ║");
        System.out.println("║ seus conhecimentos em programação! ║");
        System.out.println("║                                    ║");
        System.out.println("║       Desenvolvido por: Ronny      ║");
        System.out.println("║      Data de criação: 05/03/24     ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.println();

        exibirMenu();
    }

    private static void exibirMenu() {
        while (true) {
            System.out.println("\u001B[35m   (҂`_´)");
            System.out.println("\u001B[36m   <,︻╦̵̵̿╤─ ҉     ~  •");
            System.out.println("\u001B[34m█۞███████]▄▄▄▄▄▄▄▄▄▄▃ ●●●");
            System.out.println("\u001B[33m▂▄▅█████████▅▄▃▂…");
            System.out.println("\u001B[32m[███████████████████]");
            System.out.println("\u001B[31m◥⊙▲⊙▲⊙▲⊙▲⊙▲⊙▲⊙");

            System.out.println("\u001B[35m╔══════════════════════════════╗");
            System.out.println("\u001B[36m║         Menu Principal        ║");
            System.out.println("\u001B[34m╠══════════════════════════════╣");
            System.out.println("\u001B[33m║ 1 - Iniciar                   ║");
            System.out.println("\u001B[32m║ 2 - Sair                      ║");
            System.out.println("\u001B[31m╚══════════════════════════════╝");
            System.out.print("\u001B[0mEscolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    escolherMateria();
                    break;
                case 2:
                    System.out.println("Até logo!");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void escolherMateria() {
        System.out.println("#═════════════════════════════════#");
        System.out.println("#     Escolha a Matéria           #");
        System.out.println("#════════════════════════════════ #");

        System.out.println("#═══════════════════════════════════#");
        System.out.println("# 1 - Java                        #");
        System.out.println("# 2 - Python                      #");
        System.out.println("# 3 - HTML                        #");
        System.out.println("#═════════════════════════════════#");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                jogarQuiz(3, perguntasJava());
                break;
            case 2:
                jogarQuiz(3, perguntasPython());
                break;
            case 3:
                jogarQuiz(3, perguntasHTML());
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
                escolherMateria();
        }
    }

    private static void jogarQuiz(int numPerguntas, List<Pergunta> perguntas) {
        Collections.shuffle(perguntas);
        for (int i = 0; i < numPerguntas; i++) {
            Pergunta pergunta = selecionarPergunta(perguntas);
            perguntasRespondidas.add(perguntas.indexOf(pergunta));
            System.out.println("Pergunta " + (i + 1) + ": " + pergunta.getPergunta());
            for (int j = 0; j < pergunta.getOpcoes().size(); j++) {
                System.out.println((char) ('A' + j) + ") " + pergunta.getOpcoes().get(j));
            }
            System.out.print("Sua resposta: ");
            int resposta = scanner.nextInt();

            if (resposta == pergunta.getResposta()) {
                System.out.println("Resposta correta! +10 pontos\n");
                pontuacao += 10;
            } else {
                System.out.println("Resposta incorreta. A resposta correta é " + (char) ('A' + pergunta.getResposta() - 1) + "\n");
                pontuacao -= 5;
                if (pontuacao < 0) {
                    pontuacao = 0;
                }
            }
        }

        perguntasRespondidas.clear();
        exibirResultado();
    }

    private static Pergunta selecionarPergunta(List<Pergunta> perguntas) {
        List<Pergunta> perguntasDisponiveis = new ArrayList<>(perguntas);
        perguntasDisponiveis.removeAll(perguntasRespondidas);
        Collections.shuffle(perguntasDisponiveis);
        return perguntasDisponiveis.get(0);
    }

    private static void exibirResultado() {
        System.out.println("#═════════════════════════════════#");
        System.out.println("#         Fim do Quiz!             #");
        System.out.println("#                                  #");
        System.out.println("# Sua pontuação final é: " + pontuacao + "           #");
        System.out.println("#═════════════════════════════════#");
        pontuacao = 0;
        exibirMenu();
    }

    private static List<Pergunta> perguntasJava() {
        List<Pergunta> perguntas = new ArrayList<>();
        perguntas.add(new Pergunta("Qual palavra-chave é usada para definir uma classe em Java?",
                Arrays.asList("class", "int", "function", "string"), 1));
        perguntas.add(new Pergunta("Qual dos seguintes tipos de dados em Java é usado para valores de ponto flutuante?",
                Arrays.asList("float", "string", "boolean", "char"), 0));
        perguntas.add(new Pergunta("O que o método 'System.out.println()' faz em Java?",
                Arrays.asList("Imprime uma mensagem no console", "Lê uma entrada do console", "Imprime uma mensagem em uma caixa de diálogo", "Lê uma entrada de um arquivo"), 0));
        return perguntas;
    }

    private static List<Pergunta> perguntasPython() {
        List<Pergunta> perguntas = new ArrayList<>();
        perguntas.add(new Pergunta("Qual é o símbolo usado para comentar uma linha em Python?",
                Arrays.asList("//", "#", "/*", "--"), 1));
        perguntas.add(new Pergunta("Qual é a função usada para obter o tamanho de uma lista em Python?",
                Arrays.asList("len()", "size()", "length()", "count()"), 0));
        perguntas.add(new Pergunta("Qual é o operador usado para concatenar duas strings em Python?",
                Arrays.asList("+", "&", "++", ","), 0));
        return perguntas;
    }

    private static List<Pergunta> perguntasHTML() {
        List<Pergunta> perguntas = new ArrayList<>();
        perguntas.add(new Pergunta("Qual é a tag usada para criar uma lista não ordenada em HTML?",
                Arrays.asList("<ul>", "<ol>", "<li>", "<li>"), 0));
        perguntas.add(new Pergunta("Qual é a tag usada para criar um link em HTML?",
                Arrays.asList("<a>", "<link>", "<href>", "<url>"), 0));
        perguntas.add(new Pergunta("Qual é a tag usada para definir o título de uma página em HTML?",
                Arrays.asList("<title>", "<h1>", "<header>", "<head>"), 0));
        return perguntas;
    }
}

class Pergunta {
    private String pergunta;
    private List<String> opcoes;
    private int resposta;

    public Pergunta(String pergunta, List<String> opcoes, int resposta) {
        this.pergunta = pergunta;
        this.opcoes = opcoes;
        this.resposta = resposta;
    }

    public String getPergunta() {
        return pergunta;
    }

    public List<String> getOpcoes() {
        return opcoes;
    }

    public int getResposta() {
        return resposta;
    }
}
