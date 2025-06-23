package br.com.alura.literalura.main;

import br.com.alura.literalura.model.*;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.service.ConsumoAPI;
import br.com.alura.literalura.service.ConverteDados;

import java.util.*;

public class Main {
    private Scanner scanner = new Scanner(System.in);
    private final String ENDERECO = "https://gutendex.com/books/?";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();
    private String json;
    private LivroRepository livroRepository;
    private AutorRepository autorRepository;

    public Main(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void exibeMenu() {
        int opcao = -1;
        while (opcao != 0) {
            var menu = """
                    1 - Buscar livro pelo título
                    2 - Listar livros armazenados
                    3 - Listar autores dos livros armazenados
                    4 - Listar autores em determinado ano
                    5 - Listar livros em determinado idioma
                    
                    0 - Sair
                    """;

                System.out.println(menu);
                System.out.print("Escolha uma das opçoes acima: ");
                opcao = scanner.nextInt();
                scanner.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivroPeloTitulo();
                    break;
                case 2:
                    listarLivrosArmazenados();
                    break;
                case 3:
                    listarAutoresArmazenados();
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
        Optional<Livro> livroEncontrado = livroRepository.findByTituloContainingIgnoreCase(nomeLivro);
        DadosLivro dadosLivro = getDadosLivro(nomeLivro);
        DadosAutor dadosAutor = dadosLivro.autores().getFirst();
        Optional<Autor> autorEncontrado = autorRepository.findByNome(dadosAutor.nome());
        Livro livro;

        if (livroEncontrado.isPresent()) {
            System.out.println(livroEncontrado.get());
        } else if (autorEncontrado.isPresent()) {
            livro = new Livro(dadosLivro, autorEncontrado.get());
            System.out.println(livro);
            livroRepository.save(livro);
        } else {
            Autor novoAutor = new Autor(dadosAutor);
            livro = new Livro(dadosLivro, novoAutor);
            autorRepository.save(novoAutor);
            System.out.println(livro);
            livroRepository.save(livro);
        }
    }

    private void listarLivrosArmazenados() {
        List<Livro> livrosArmazenados = livroRepository.findAll();
        livrosArmazenados.forEach(System.out::println);
    }

    private void listarAutoresArmazenados() {
        List<Autor> autoresArmazenados = autorRepository.findAll();
        autoresArmazenados.forEach(System.out::println);
    }

    private DadosLivro getDadosLivro(String nomeLivro) {
        try {
            json = consumoAPI.obterDados(ENDERECO + "search=" + nomeLivro.replace(" ", "+"));
            DadosBuscados dadosBuscados = conversor.obterDados(json, DadosBuscados.class);
            DadosLivro dadosLivro = dadosBuscados.livrosBuscados().getFirst();
            return dadosLivro;
        } catch (NoSuchElementException e) {
            System.out.println("Livro não encontrado.");
            exibeMenu();
            return null;
        }
    }
}