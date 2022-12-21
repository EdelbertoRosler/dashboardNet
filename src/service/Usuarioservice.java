package service;

import dao.UsuarioDao;
import java.util.Optional;
import model.Usuario;

public class UsuarioService {

    private UsuarioDao usuarioDao;

    public UsuarioService() {
        this.usuarioDao = new UsuarioDao();
    }

    public boolean getUser(String nome, String senha){
        Optional<Usuario> usuario = usuarioDao.getByUser(nome);

        if (usuario.isPresent() &&usuario.get().getSenha().equals(senha)) {
            return true;
        }
        return false;
    }


}
