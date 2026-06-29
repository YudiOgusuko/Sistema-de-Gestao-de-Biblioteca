package biblioteca.repository;

import biblioteca.model.Livro;
import biblioteca.util.ArquivoJson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositorioLivro {

    private final List<Livro> livros;

    public RepositorioLivro(){
        Type tipo = new TypeToken<List<Livro>>(){}.getType();
        List<Livro> carregados = ArquivoJson.carregar("src/biblioteca/dados/livros.json", tipo);
        this.livros = carregados != null ? carregados : new ArrayList<>();
    }

    public void adicionar(Livro livro){
        livros.add(livro);
    }

    public Optional<Livro> buscarPorIsbn(String isbn){
        return livros.stream()
                .filter(idLivro -> idLivro.getIsbn().equals(isbn))
                .findFirst();
    }

    public Optional<Livro> buscarPorTitulo(String titulo) {
        return livros.stream()
                .filter(t -> t.getTitulo().equalsIgnoreCase(titulo))
                .findFirst();
    }

    public List<Livro> listarTodos(){
        return livros;
    }

    public List<Livro> listarDisponiveis(){
        return livros.stream()
                .filter(Livro::isDisponivel)
                .toList();
    }

    public void atualizar(Livro livroAtualizado) {
        livros.removeIf(l -> l.getIsbn().equals(livroAtualizado.getIsbn()));
        livros.add(livroAtualizado);
        salvar();
    }

    public void salvar() {
        ArquivoJson.salvar(livros, "src/biblioteca/dados/livros.json");
    }


}
