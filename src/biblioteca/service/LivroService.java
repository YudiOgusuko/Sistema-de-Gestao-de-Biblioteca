package biblioteca.service;

import biblioteca.exception.LivroJaCadastradoException;
import biblioteca.exception.LivroNaoEncontradoException;
import biblioteca.model.Livro;
import biblioteca.repository.RepositorioLivro;

import java.util.Comparator;
import java.util.List;

public class LivroService  {

    RepositorioLivro repositorioLivro = new RepositorioLivro();

    public void cadastrarLivro(Livro livro) throws LivroJaCadastradoException {
        if (repositorioLivro.buscarPorIsbn(livro.getIsbn()).isPresent() ||
                repositorioLivro.listarTodos().stream()
                        .anyMatch(t -> t.getTitulo().equalsIgnoreCase(livro.getTitulo()))) {
            throw new LivroJaCadastradoException("ERRO: Este livro já esta cadastrado no sistema!");
        }

        repositorioLivro.adicionar(livro);
        repositorioLivro.salvar();
    }

    public Livro buscarPorIsbn(String isbn) {
        return repositorioLivro.buscarPorIsbn(isbn)
                .orElseThrow(() -> new LivroNaoEncontradoException("Livro não encontrado: "+ isbn));
    }

    public List<Livro> listarTodos() {
       return repositorioLivro.listarTodos().stream()
               .sorted(Comparator.comparing(Livro::getTitulo))
               .toList();
    }

    public List<Livro> listarDisponiveis() {
        return repositorioLivro.listarDisponiveis().stream()
                .sorted(Comparator.comparing(Livro::getTitulo))
                .toList();
    }

    public List<Livro> buscarPorAutor(String autor) {
        List<Livro> livro = repositorioLivro.listarTodos().stream()
                .filter(a -> a.getAutor().equalsIgnoreCase(autor))
                .sorted(Comparator.comparing(Livro::getTitulo))
                .toList();

        if(livro.isEmpty()) {
            System.out.printf("Nenhum livro do autor '%s' encontrado.%n", autor);
        }
        return livro;
    }

    public List<Livro> buscarPorGenero(String genero)  {
        List<Livro> livro = repositorioLivro.listarTodos().stream()
                .filter(g -> g.getGenero().equalsIgnoreCase(genero))
                .sorted(Comparator.comparing(Livro::getTitulo))
                .toList();

        if(livro.isEmpty()) {
            System.out.printf("Nenhum livro do gênero '%s' encontrado.%n", genero);
        }
        return livro;
    }

    public List<Livro> buscarPorTitulo(String titulo) {
        List<Livro> livro = repositorioLivro.listarTodos().stream()
                .filter(t -> t.getTitulo().equalsIgnoreCase(titulo))
                .sorted(Comparator.comparing(Livro::getTitulo))
                .toList();

        if(livro.isEmpty()) {
            System.out.printf("Nenhum livro com título '%s' encontrado.%n", titulo);
        }
        return livro;
    }

    public List<Livro> buscarPorAnoDePublicacao(int ano) {
        List<Livro> livro = repositorioLivro.listarTodos().stream()
                .filter(data -> data.getAnoDePublicacao() == ano)
                .sorted(Comparator.comparing(Livro::getTitulo))
                .toList();

        if(livro.isEmpty()) {
            System.out.printf("Nenhum livro do ano '%d' encontrado.%n", ano);
        }
        return livro;
    }
}
