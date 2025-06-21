package br.com.alura.literalura.main;

import br.com.alura.literalura.model.DadosBuscados;
import br.com.alura.literalura.model.DadosLivro;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.repository.SerieRepository;
import br.com.alura.literalura.service.ConsumoAPI;
import br.com.alura.literalura.service.ConverteDados;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private Scanner scanner = new Scanner(System.in);
    private final String ENDERECO = "https://gutendex.com/books/?";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();
    private String json;
    private SerieRepository repository;

    public Main(SerieRepository repository) {
        this.repository = repository;
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    1 - Buscar livro pelo título
                    2 - Listar livros armazenados
                    3 - Listar autores dos livros armazenados
                    4 - Listar autores em determinado ano
                    5 - Listar livros em determinado idioma
                    
                    0 - Sair
                    """;

            try {
                System.out.println(menu);
                System.out.print("Escolha uma das opçoes acima: ");
                opcao = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                scanner.nextLine();
            }

            switch (opcao) {
                case 1:
                    buscarLivroPeloTitulo();
                    break;
                case 2:
                    listarLivrosArmazenados();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Escolha somente uma das opçoes abaixo.\n");
            }
        }
    }

    private void buscarLivroPeloTitulo() {
        System.out.print("Insira o titulo do livro que você deseja buscar: ");
        var nomeLivro = scanner.nextLine();
        Optional<Livro> livroEncontrado = repository.findByTituloContainingIgnoreCase(nomeLivro);

        if (livroEncontrado.isPresent()) {
            System.out.println(livroEncontrado.get());
        } else {
            DadosLivro dadosLivro = getDadosLivro(nomeLivro);
            Livro livro = new Livro(dadosLivro);
            System.out.println(livro);
            repository.save(livro);
        }
    }

    private void listarLivrosArmazenados() {
    }

    private DadosLivro getDadosLivro(String nomeLivro) {
        json = consumoAPI.obterDados(ENDERECO + "search=" + nomeLivro.replace(" ", "+"));
        DadosBuscados dadosBuscados = conversor.obterDados(json, DadosBuscados.class);
        DadosLivro dadosLivro = dadosBuscados.livrosBuscados().getFirst();
        return dadosLivro;
    }
}