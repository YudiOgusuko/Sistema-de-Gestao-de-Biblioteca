package biblioteca.repository;

import biblioteca.enums.StatusEmprestimos;
import biblioteca.model.Emprestimos;
import biblioteca.util.ArquivoJson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositorioEmprestimos {

    private final List<Emprestimos> emprestimos;

    public RepositorioEmprestimos(){
        Type tipo = new TypeToken<List<Emprestimos>>(){}.getType();
        List<Emprestimos> carregados = ArquivoJson.carregar("src/biblioteca/dados/emprestimos.json", tipo);
        this.emprestimos = carregados != null ? carregados : new ArrayList<>();
    };

    public void adicionar(Emprestimos emprestimo) {
        emprestimos.add(emprestimo);
        }

    public Optional<Emprestimos> buscarPorId(String id) {
        return emprestimos.stream()
                .filter(idEmprestimo -> idEmprestimo.getId().equals(id))
                .findFirst();
    }

    public List<Emprestimos> listarTodos() {
        return emprestimos;
    }

    public List<Emprestimos> listarAtivos() {
        return emprestimos.stream()
                .filter(ativos -> ativos.getStatus() == StatusEmprestimos.ATIVO)
                .toList();
    }

    public Optional<Emprestimos> buscarEmprestimosAtivosDoUsuario(String idUsuario) {
        return emprestimos.stream()
                .filter(ativo -> ativo.getStatus()== StatusEmprestimos.ATIVO)
                .filter(idUser -> idUser.getIdUsuario().equals(idUsuario))
                .findFirst();
    }

    public Optional<Emprestimos> buscarEmprestimosAtivosDoLivro(String isbn) {
        return emprestimos.stream()
                .filter(ativos -> ativos.getStatus()== StatusEmprestimos.ATIVO)
                .filter(idLivro -> idLivro.getIsbnLivro().equals(isbn))
                .findFirst();
    }

    public void atualizar(Emprestimos emprestimoAtualizado) {
        emprestimos.removeIf(l -> l.getId().equals(emprestimoAtualizado.getId()));
        emprestimos.add(emprestimoAtualizado);
        salvar();
    }

    public void salvar() {
        ArquivoJson.salvar(emprestimos, "src/biblioteca/dados/emprestimos.json");
    }
}
