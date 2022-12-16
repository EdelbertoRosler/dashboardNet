package service;

import dao.UsuarioDao;
import java.util.Optional;
import model.Usuario;

public class Usuarioservice {

    private UsuarioDao usuarioDao;

    public Usuarioservice() {
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
