package service;

import dao.CategoriaDao;
import dao.UsuarioDao;
import java.util.List;
import java.util.Optional;
import model.Categoria;
import model.Usuario;

public class CategoriaService {

    private CategoriaDao categoriaDao;

    public CategoriaService() {
        this.categoriaDao = new CategoriaDao();
    }

    public List<Categoria> getAll(){
        return categoriaDao.getAll();
    }


}
