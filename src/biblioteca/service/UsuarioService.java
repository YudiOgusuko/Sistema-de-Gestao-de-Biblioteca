package biblioteca.service;

import biblioteca.exception.UsuarioComEmailDuplicadoException;
import biblioteca.exception.UsuarioNaoEncontradoException;
import biblioteca.model.Usuario;
import biblioteca.repository.RepositorioUsuario;

import java.util.Comparator;
import java.util.List;

public class UsuarioService {

    RepositorioUsuario repositorioUsuario = new RepositorioUsuario();

    public void cadastrarUsuario(Usuario usuario) throws UsuarioComEmailDuplicadoException {
        boolean algumBate = repositorioUsuario.listarTodos().stream()
                .anyMatch(email -> email.getEmail().equalsIgnoreCase(usuario.getEmail()));

        if(algumBate){
            throw new UsuarioComEmailDuplicadoException("E-mail de Usuário ja existente.");
        }

        repositorioUsuario.adicionar(usuario);
        repositorioUsuario.salvar();
    }

    public Usuario buscarPorId(String id) throws UsuarioNaoEncontradoException{
        return repositorioUsuario.buscarPorId(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado: " + id));
    }

    public List<Usuario> listarTodos() {
        List<Usuario> lista = repositorioUsuario.listarTodos().stream()
                .sorted(Comparator.comparing(Usuario::getNome))
                .toList();

        if(lista.isEmpty()) {
            System.out.println("Nenhum Usuário no Sistema.");
        }

        return lista;
    }
}
