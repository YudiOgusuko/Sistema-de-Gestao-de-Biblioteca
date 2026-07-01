package biblioteca.service;

import biblioteca.enums.StatusEmprestimos;
import biblioteca.exception.EmprestimoNaoEncontradoException;
import biblioteca.exception.LivroIndisponivelException;
import biblioteca.exception.LivroNaoEncontradoException;
import biblioteca.exception.UsuarioNaoEncontradoException;
import biblioteca.model.Emprestimos;
import biblioteca.model.Livro;
import biblioteca.model.Usuario;
import biblioteca.repository.RepositorioEmprestimos;
import biblioteca.repository.RepositorioLivro;
import biblioteca.repository.RepositorioUsuario;
import biblioteca.util.ArquivoJson;

import java.time.LocalDate;
import java.util.List;

public class EmprestimoService {

    LocalDate localDate = LocalDate.now();

    RepositorioLivro repositorioLivro = new RepositorioLivro();
    RepositorioUsuario repositorioUsuario = new RepositorioUsuario();
    RepositorioEmprestimos repositorioEmprestimos = new RepositorioEmprestimos();


    public void realizarEmprestimo(String tituloLivro, String emailUsuario) throws LivroIndisponivelException {

        Livro livro = repositorioLivro.buscarPorTitulo(tituloLivro)
                .orElseThrow(() -> new LivroNaoEncontradoException("Não foi possível localizar o livro."));
        if(!livro.isDisponivel()) {
            throw new LivroIndisponivelException("Este livro ja este emprestado.");
        }
        livro.setDisponivel(false);


        Usuario usuario = repositorioUsuario.listarTodos().stream()
                .filter(i -> i.getEmail().equalsIgnoreCase(emailUsuario))
                .findFirst()
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Não foi possível localizar o usuário."));

        Emprestimos emprestimos = new Emprestimos(livro.getIsbn(), usuario.getId(), localDate,
                localDate.plusDays(14), StatusEmprestimos.ATIVO);

        repositorioLivro.atualizar(livro);
        repositorioEmprestimos.adicionar(emprestimos);
        repositorioEmprestimos.salvar();
    }

    public void realizarDevolucao(String tituloLivro) {
        Livro livro = repositorioLivro.listarTodos().stream()
                .filter(t -> t.getTitulo().equalsIgnoreCase(tituloLivro))
                .findFirst()
                .orElseThrow(() -> new LivroNaoEncontradoException("Não foi possível localizar o livro."));

        Emprestimos emprestimos = repositorioEmprestimos.buscarEmprestimosAtivosDoLivro(livro.getIsbn())
                .orElseThrow(() -> new EmprestimoNaoEncontradoException("Não foi possível localizar o empréstimo."));
        emprestimos.setDataDevolucaoReal(localDate);
        emprestimos.setStatus(StatusEmprestimos.DEVOLVIDO);
        livro.setDisponivel(true);

        repositorioEmprestimos.atualizar(emprestimos);
        repositorioEmprestimos.salvar();
        repositorioLivro.atualizar(livro);
        repositorioLivro.salvar();
    }

    public List<Emprestimos> listarEmprestimosAtivos() {
        return repositorioEmprestimos.listarAtivos();
    }

    public List<Emprestimos> listarEmprestimosDoUsuario(String idUsuario) {
        List<Emprestimos> lista = repositorioEmprestimos.listarTodos().stream()
                .filter(idUser -> idUser.getIdUsuario().equals(idUsuario))
                .toList();

        if(lista.isEmpty()) {
            System.out.println("Nenhum Empréstimo no Sistema.");
        }

        return lista;
    }

    public List<Emprestimos> listarEmprestimosAtrasados() {
        List<Emprestimos> lista = repositorioEmprestimos.listarTodos().stream()
                .filter(status -> status.getStatus().equals(StatusEmprestimos.ATIVO))
                .filter(devolucao -> devolucao.getDataPrevistaDevolucao().isBefore(localDate))
                .toList();

        if(lista.isEmpty()) {
            System.out.println("Nenhum Empréstimo Atrasado no Sistema.");
        }

        return lista;
    }
}
