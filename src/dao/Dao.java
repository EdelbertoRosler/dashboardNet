/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Guilherme
 */
public interface Dao<T> {
    
    Optional<T> get(int id);
    
    List<T> getAll();
    
    void save(T t);
    
    void update(T t);
    
    void delete(T t);
}

