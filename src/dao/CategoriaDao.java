/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.Optional;
import model.Categoria;
import model.Movimentacao;
import model.Usuario;

public class CategoriaDao implements Dao {
    
    //Acesso ao uniidade de persistĂȘncia
    private static final String PERSISTENCE_UNIT = "financas";

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);

        return factory.createEntityManager();
    }

    @Override
    public Optional<Categoria> get(int id) {
        EntityManager em = getEntityManager();
        Categoria categoria;
        
        try {
            categoria = em.createQuery(
                            "SELECT c FROM categoria c WHERE c.id = :id", Categoria.class).
                    setParameter("id",id).getSingleResult();   
        } catch(Exception e){
            return Optional.empty();
        } finally {
            em.close();
        }   
        
        return Optional.of(categoria);    }

    @Override
    public List<Categoria> getAll() {
        
        EntityManager em = getEntityManager();
        
        List todos = em.createNamedQuery("Categoria.findAll").getResultList();
        
        System.out.println(todos.size());
        
        em.close();
        
        return todos;        
        
    }

    @Override
    public void save(Object t) {
        
        EntityManager em = getEntityManager();
        
        try {
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();    
        } catch(Exception e){
            em.getTransaction().rollback();
        } finally {
            em.close();
        }            
    }

    @Override
    public void update(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
