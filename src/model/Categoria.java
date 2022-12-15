package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "categoria")
public class Categoria {
    @Id
    private int id;
    private String descricao;
}
