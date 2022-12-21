package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.Optional;
import model.TipoMovimentacao;


public class TipoMovimentacaoDao implements Dao {
    
    //Acesso ao unidade de persistência
    private static final String PERSISTENCE_UNIT = "financas";

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);

        return factory.createEntityManager();
    }

    @Override
    public Optional<TipoMovimentacao> get(int id) {
        EntityManager em = getEntityManager();
        TipoMovimentacao tipoMov;
        
        try {
            tipoMov = em.createQuery(
                            "SELECT y FROM tipo_movimentacao WHERE t.id = :id", TipoMovimentacao.class).
                    setParameter("id",id).getSingleResult();   
        } catch(Exception e){
            return Optional.empty();
        } finally {
            em.close();
        }   
        
        return Optional.of(tipoMov);    }

    @Override
    public List<TipoMovimentacao> getAll() {
        
        EntityManager em = getEntityManager();
        
        List todos = em.createNamedQuery("TipoMovimentacao.findAll").getResultList();
        
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
