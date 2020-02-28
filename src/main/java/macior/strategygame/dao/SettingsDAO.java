package macior.strategygame.dao;

import macior.strategygame.models.User;
import macior.strategygame.models.settings_management.Settings;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Repository("settingsDAO")
public class SettingsDAO {

    @Autowired
    private Context context;


    //explanation - wanna add or update actually
    public int updateSettings(Settings settings, User user){
        //regular stuff
        EntityManager manager = context.entityManager();
        settings.setUser(user);
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        //HERE I create a totally new object, set it's id (user) and search for it
        Settings settings1 = new Settings();
        settings1.setUser(user);

        //I can't use parameter settings because hibernate will not differ objects
        //like he will think: i found "settings" and returned settings1, so they are identical, though they aren't
        settings1 = manager.find(Settings.class, settings1);
        boolean hasSettingsAlready = settings1 != null;
        manager.clear();

        //I can't just merge, cuz it inserts null all the time
        try {
            if (!hasSettingsAlready){
                manager.persist(settings);
            }
            manager.merge(settings);
            manager.flush();
            transaction.commit();
            return 0;
        } catch (Exception exc){
            exc.printStackTrace();
            transaction.rollback();
            return -1;
        } finally {
            manager.clear();
        }

    }

    public Settings getSettings(int usersId){
//        User user = new User();
//        user.setId(usersId);
        EntityManager manager = context.entityManager();
        return manager.find(Settings.class, usersId);
    }
}
