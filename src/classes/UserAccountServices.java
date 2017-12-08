/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import smartreminder.*;

/**
 *
 * @author kan
 */
public class UserAccountServices {
    
    
    public void update(){
        ObjectDBServices odb = new ObjectDBServices();
        EntityManager em = odb.openConnection();
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("./db/database.odb");
        //EntityManager em = emf.createEntityManager();
 
        // Store 1000 Point objects in the database:
        em.getTransaction().begin();
        
        TypedQuery<UserAccount> query = em.createQuery("SELECT user FROM UserAccount user", UserAccount.class);
        FillIdPasswordController.users = query.getResultList();
        
        odb.closeConnection();
    }
}
