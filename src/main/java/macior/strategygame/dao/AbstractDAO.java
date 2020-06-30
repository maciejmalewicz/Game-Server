package macior.strategygame.dao;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Optional;

public abstract class AbstractDAO <T> {

    protected final Context context;

    @Autowired
    public AbstractDAO(Context context){
        this.context = context;
    }

    public void add (T object){
        EntityManager manager = context.entityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        try {
            manager.persist(object);
            manager.flush();
            transaction.commit();
        } catch (Exception exc){
            transaction.rollback();
        }
        manager.clear();
    }

    public void delete (int id){
        EntityManager manager = context.entityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        try {
            T object = getById(id).get();
            if (object != null){
                manager.remove(object);
                transaction.commit();
            }
        } catch (Exception exc){
            transaction.rollback();
        }
        manager.clear();
    }

    public void update (int id, T toUpdate){
        EntityManager manager = context.entityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        try {
            T object = getById(id).get();
            if (object != null){
                manager.merge(toUpdate);
                transaction.commit();
            }
        } catch (Exception exc){
            transaction.rollback();
        }
        manager.clear();
    }

    public abstract Optional<T> getById(int id);

}
