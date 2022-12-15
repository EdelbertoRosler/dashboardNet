package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;

import java.time.LocalDate;

@Entity(name = "movimentacao")
@NamedQuery(name = "Movimentacao.findAll", query = "SELECT m FROM movimentacao m")
public class Movimentacao {
    @Id
    private int id;
    private int tipo;
    private int categoria;
    private LocalDate data;
    private double valor;
    private String descricao;
    private String pago;
}
