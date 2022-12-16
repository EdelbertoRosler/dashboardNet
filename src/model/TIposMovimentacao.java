package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity(name = "TIPOS_MOVIMENTACAO")
public class TiposMovimentacao {
    @Id
    private int id;
    private String descricao;

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
