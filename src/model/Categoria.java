package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;

@Entity(name = "categoria")
@NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM categoria c")
public class Categoria {
    @Id
    private int id;
    private String descricao;

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
    
}
