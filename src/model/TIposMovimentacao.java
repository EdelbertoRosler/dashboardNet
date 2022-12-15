package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "tipos_movimentacao")
public class TIposMovimentacao {
    @Id
    private int id;
    private String descricao;
}
