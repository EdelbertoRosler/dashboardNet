/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 *
 * @author Guilherme
 */

@Entity(name="task")
@NamedQuery(name = "Todo.findAll", query = "SELECT t FROM task t")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "title")
    private String summary;
    private String description;

    // empty constructor for JPA instantiation
    public Todo() {
    }

    public Todo(int id) {
        this(id, "", "");
    }

    public Todo(int id, String summary, String description) {
        this.id = id;
        this.summary = summary;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Todo [id=" + id + ", summary=" + summary + ", description=" + description + "]";
    }

}
