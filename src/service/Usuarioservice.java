package service;

import dao.UsuarioDao;
import model.Usuario;

public class Usuarioservice {

    private UsuarioDao usuarioDao;

    public Usuarioservice() {
        this.usuarioDao = new UsuarioDao();
    }

    public boolean getUser(String nome, String senha){
        Usuario usuario = usuarioDao.getByUser(nome);

        if (usuario.getSenha().equals(senha)){
            System.out.println("Funcionou");
            System.out.println("Usuario: " + usuario.getNome());
            System.out.println("Senha: " + usuario.getSenha());
            return true;
        }

        return false;
    }


}
