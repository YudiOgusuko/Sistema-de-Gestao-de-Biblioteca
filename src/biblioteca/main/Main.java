package biblioteca.main;

import biblioteca.model.Livro;
import biblioteca.model.Usuario;
import biblioteca.service.EmprestimoService;
import biblioteca.service.LivroService;
import biblioteca.service.UsuarioService;

import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    static void main() {

        Scanner scanner = new Scanner(System.in);

        LivroService livroService = new LivroService();
        UsuarioService usuarioService = new UsuarioService();
        EmprestimoService emprestimoService = new EmprestimoService();

        System.out.println("=== SISTEMA DE BIBLIOTECA ===");

        boolean parar = false;

        while(!parar){
            System.out.printf(
                    "1. Cadastrar%n" +
                    "2. Buscar Livro%n" +
                    "3. Listar%n" +
                    "4. Realizar Empréstimo%n" +
                    "5. Realizar Devolução%n" +
                    "6. Relatório por Gênero%n" +
                    "0. Sair%n"
            );

            int opc = lerInt(scanner, "Sua Opção: ");
            linha();

            switch (opc) {

                case 1:
                    limparTela();
                    System.out.println("O que deseja cadastrar >>>");

                    System.out.printf(
                            "1. Livro%n" +
                            "2. Usuário%n"
                    );

                    int opcao = lerInt(scanner, "Sua Opção: ");
                    linha();

                    switch (opcao){

                        case 1:
                            limparTela();
                            System.out.println("Insira os Dados do Livro >>>");

                            String tituloLivro = lerString(scanner,"Título: ");
                            String autorLivro = lerString(scanner, "Autor: ");
                            int anoDePublicacaoLivro = lerInt(scanner, "Ano de Publicação: ");
                            String generoLivro = lerString(scanner, "Gênero: ");
                            livroService.cadastrarLivro(new Livro(tituloLivro, autorLivro, anoDePublicacaoLivro, generoLivro));
                            break;

                        case 2:
                            limparTela();
                            System.out.println("Insira os Dados do Usuário >>>");

                            String nomeUsuario = lerString(scanner, "Nome: ");
                            String emailUsusario = validarEmail(scanner, "E-mail: ");
                            usuarioService.cadastrarUsuario(new Usuario(nomeUsuario, emailUsusario));
                            break;

                        default:
                            limparTela();
                            System.out.println("Opção Invalida.");
                            break;
                    }
                    linha();
                    break;

                case 2:
                    limparTela();
                    System.out.println("Método de Busca >>>");
                    System.out.printf(
                            "1. Buscar por título%n" +
                            "2. Buscar por autor%n" +
                            "3. Busccar por gênero%n" +
                            "4. Buscar por Ano de Publicação%n"
                            );
                    int buscar = lerInt(scanner, "Forma de Busca: ");
                    linha();

                    switch (buscar){

                        case 1:
                            limparTela();
                            String titulo = lerString(scanner, "Título: ");
                            livroService.buscarPorTitulo(titulo).forEach(System.out::println);
                            break;

                        case 2:
                            limparTela();
                            String autor = lerString(scanner, "Autor: ");
                            livroService.buscarPorAutor(autor).forEach(System.out::println);
                            break;

                        case 3:
                            limparTela();
                            String genero = lerString(scanner, "Gênero: ");
                            livroService.buscarPorGenero(genero).forEach(System.out::println);
                            break;

                        case 4:
                            int anoDePublicacao = lerInt(scanner, "Ano de Publicação: ");
                            livroService.buscarPorAnoDePublicacao(anoDePublicacao).forEach(System.out::println);
                            break;

                        default:
                            limparTela();
                            System.out.println("Opção Invalida.");
                            break;
                    }
                    linha();
                    break;

                case 3:
                    limparTela();
                    System.out.println("O que deseja listar >>>");

                    System.out.printf(
                            "1. Listar Livros Disponíveis%n" +
                            "2. Listar Empréstimos Ativos%n" +
                            "3. Listar Emprétimos Atrasados%n" +
                            "4. Listar Tudo%n"
                            );

                    int listar = lerInt(scanner, "Sua Opção: ");
                    linha();

                    switch (listar){
                        case 1:
                            limparTela();
                            System.out.println("Livros Disponíveis >>>");
                            livroService.listarDisponiveis().forEach(System.out::println);
                            break;

                        case 2:
                            limparTela();
                            System.out.println("Empréstimos Ativos >>>");

                            emprestimoService.listarEmprestimosAtivos().forEach(System.out::println);
                            break;

                        case 3:
                            limparTela();
                            System.out.println("Empréstimos Atrasados >>>");

                            emprestimoService.listarEmprestimosAtrasados().forEach(System.out::println);
                            break;

                        case 4:
                            limparTela();
                            System.out.println("Livros >>>");
                            livroService.listarTodos().forEach(System.out::println);

                            System.out.printf("%nUsuários >>>%n");
                            usuarioService.listarTodos().forEach(System.out::println);
                            break;

                        default:
                            System.out.println("Opção Invalida.");
                            break;
                    }
                    linha();
                    break;


                case 4:
                    limparTela();
                    System.out.println("Informe o Título do Livro e o E-mail do Usuário >>>");

                    String idLivro = lerString(scanner, "Título: ");
                    String idUser = lerString(scanner, "E-mail do Usuário: ");
                    emprestimoService.realizarEmprestimo(idLivro, idUser);
                    linha();
                    break;

                case 5:
                    limparTela();
                    System.out.println("Insira o Título do livro que deseja devolver >>>");

                    String devolucaoLivro = lerString(scanner, "Título: ");
                    emprestimoService.realizarDevolucao(devolucaoLivro);
                    linha();
                    break;


                case 6:
                    limparTela();
                    System.out.println("Agrupamento por Gênero >>>");

                    livroService
                            .listarTodos()
                            .stream()
                            .collect(Collectors.groupingBy(Livro::getGenero, Collectors.counting()))
                            .forEach((chave, valor) ->
                                    System.out.println(chave+ " | "+ valor+ " Livros"));
                    linha();
                    break;

                case 0:
                    limparTela();
                    System.out.print("Encerrando. Por favor aguarde");

                    for(int c = 0; c < 3; c++) {
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.print(".");
                    }
                    parar = true;
                    break;

                default:
                    limparTela();
                    System.out.println("Opção Invalida.");
                    linha();
                    break;
            }
        }
    }

    public static void linha() {
        System.out.println("-".repeat(50));
    }

    public static void limparTela() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("\n".repeat(50));
        }
    }

    public static String validarEmail(Scanner scanner, String email) {
        String emailValidado = "";
        boolean ok = false;

        while(!ok) {
            String entrada = lerString(scanner, "E-mail: ");
            if(!entrada.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")){
                System.out.println("E-mail Inválido. Tente Novamente.");
            } else {
                emailValidado = entrada;
                ok = true;
            }
        }
        return emailValidado;
    }

    public static int lerInt(Scanner scanner, String txt) {
        int numero = 0;
        boolean ok = false;
        while(!ok) {
            System.out.print(txt);
            String entrada = scanner.nextLine().strip();
            if(entrada.trim().isEmpty()) {
                System.out.println("Esse campo é obrigatório.");
            }
            else if(entrada.matches(".*[a-zA-Z\\sÀ-ú].*")) {
                System.out.println("Por Favor, apenas números.");
            }
            else {
                try {
                    numero = Integer.parseInt(entrada);
                    ok = true;
                } catch (NumberFormatException e) {
                    System.out.println("Valor Inválido.");
                }
            }
        }
        return numero;
    }

    public static String lerString(Scanner scanner, String txt) {
        String valor = "";
        boolean ok = false;
        while(!ok) {
            System.out.print(txt);
            valor = scanner.nextLine().strip();
            if(valor.trim().isEmpty()) {
                System.out.println("Esse campo é obrigatório.");
            } else {
                ok = true;
            }
        }
        return valor;
    }
}

