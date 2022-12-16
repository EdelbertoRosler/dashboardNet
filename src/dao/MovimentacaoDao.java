/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.Optional;
import model.Movimentacao;

public class MovimentacaoDao implements Dao {
    
    //Acesso ao uniidade de persistência
    private static final String PERSISTENCE_UNIT = "financas";

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);

        return factory.createEntityManager();
    }

    @Override
    public Optional get(int id) {
        EntityManager em = getEntityManager();
        Movimentacao movimentacao;
        
        try {
            movimentacao = em.createQuery(
                            "SELECT m FROM movimentacao m WHERE m.id = :id", Movimentacao.class).
                    setParameter("id", id).getSingleResult();   
        } catch(Exception e){
            return Optional.empty();
        } finally {
            em.close();
        }   
        
        return Optional.of(movimentacao); 
    }

    @Override
    public List<Movimentacao> getAll() {
        
        EntityManager em = getEntityManager();
        
        List todos = em.createNamedQuery("Movimentacao.findAll").getResultList();
        
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