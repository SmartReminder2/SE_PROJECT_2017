/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import smartreminder.*;

/**
 *
 * @author kan
 */
public class FriendServices {
    
    //create an object of PersonalCalendar
    private static FriendServices instance = new FriendServices();
    
    private List<Friend> friendList = new ArrayList<>();
    
    //make the constructor private so that this class cannot be instantiated
    private FriendServices(){
    
        EntityManager em = SmartReminder.emf.createEntityManager();
 
        // Store 1000 Point objects in the database:
        em.getTransaction().begin();
        
        TypedQuery<Friend> query = em.createQuery("SELECT fnd FROM Friend fnd", Friend.class);
        friendList = query.getResultList();
        
        em.close();
        
    }
    
    //Get the only object available
    public static FriendServices getInstance(){
        return instance;
    }
    
    public void add(Friend friend) {
        boolean isValid = true;
        
        for (int i = 0; i < friendList.size(); i++) {
            if (friendList.get(i).getFriendAccount().getId() == friend.getFriendAccount().getId()) {
                isValid = false;
                break;
            }
        }
        
        if (isValid) {
            EntityManager em = SmartReminder.emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(friend);
            em.getTransaction().commit();
            // Close the database connection:
            em.close();

            friendList.add(friend);
        }
        else {
            System.out.println("This friend, \"" + friend.getFriendAccount().getUserName() + "\"," + " is already exist");
        }
    }
    
    public void delete(Friend friend) {
        EntityManager em = SmartReminder.emf.createEntityManager();
        Friend fnd = em.find(Friend.class, friend.getId());
 
        em.getTransaction().begin();
        em.remove(fnd);
        em.getTransaction().commit();
        // Close the database connection:
        em.close();
        friendList.remove(friend);
    }
    
    public ArrayList searchNewFriend(String userName) {
        
        ArrayList<UserAccount> returnList = new ArrayList<>();
        
        EntityManager em = SmartReminder.emf.createEntityManager();
 
        // Store 1000 Point objects in the database:
        em.getTransaction().begin();
        
        TypedQuery<UserAccount> query = em.createQuery("SELECT user FROM UserAccount user", UserAccount.class);
        List<UserAccount> userList = query.getResultList();
        
        em.close();
        
        for (int i = 0; i < userList.size(); i++) {
            if(userList.get(i).getUserName().equals(userName)) {
                returnList.add(userList.get(i));
            }
        }
        return returnList;
    }
    
    public ArrayList getFriendList() {
            
        return (ArrayList)friendList;
    }
}
