package biblioteca.repository;

import biblioteca.model.Usuario;
import biblioteca.util.ArquivoJson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositorioUsuario {

    private final List<Usuario> usuarios;

    public RepositorioUsuario(){
        Type tipo = new TypeToken<List<Usuario>>(){}.getType();
        List<Usuario> carregados = ArquivoJson.carregar("src/biblioteca/dados/usuarios.json", tipo);
        this.usuarios = carregados != null ? carregados : new ArrayList<>();
    }

    public void adicionar(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Optional<Usuario> buscarPorId(String id) {
        return usuarios.stream()
                .filter(idUser -> idUser.getId().equals(id))
                .findFirst();
    }

    public List<Usuario> listarTodos() {
        return usuarios;
    }

    public void atualizar(Usuario usuarioAtualizado) {
        usuarios.removeIf(l -> l.getId().equals(usuarioAtualizado.getId()));
        usuarios.add(usuarioAtualizado);
        salvar();
    }

    public void salvar() {
        ArquivoJson.salvar(usuarios, "src/biblioteca/dados/usuarios.json");
    }
}
