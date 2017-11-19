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
public class GroupServices {
    //create an object of PersonalCalendar
    private static GroupServices instance = new GroupServices();
    
    private List<GroupCatalogue> groupList = new ArrayList<>();
    
    private GroupServices(){
    
        EntityManager em = SmartReminder.emf.createEntityManager();
 
        // Store 1000 Point objects in the database:
        em.getTransaction().begin();
        
        TypedQuery<GroupCatalogue> query = em.createQuery("SELECT gCat FROM GroupCatalogue gCat", GroupCatalogue.class);
        groupList = query.getResultList();
        
        em.close();
        
    }
    
    public static GroupServices getInstance(){
        return instance;
    }
    
    public void create(GroupCatalogue groupCat) {
        
        boolean isValid = true;
        
        for (int i = 0; i < groupList.size(); i++) {
            if (groupList.get(i).getGroupDetail().getGroupName().equals(groupCat.getGroupDetail().getGroupName())) {
                isValid = false;
                break;
            }
        }
        if (isValid) {
            EntityManager em = SmartReminder.emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(groupCat);
            em.getTransaction().commit();
            // Close the database connection:
            em.close();
            groupList.add(groupCat);
            System.out.println("Creating group success!!");
        }
        else {
            System.out.println("This group's name is used");
        }
        
    }
    
    public void delete(GroupCatalogue groupCat) {
        EntityManager em = SmartReminder.emf.createEntityManager();
        GroupCatalogue gCat = em.find(GroupCatalogue.class, groupCat.getId());
 
        em.getTransaction().begin();
        em.remove(gCat);
        em.getTransaction().commit();
        // Close the database connection:
        em.close();
        groupList.remove(groupCat);
    }
    
    public ArrayList getMyGroupList() {
        ArrayList<GroupCatalogue> list = new ArrayList<>();
        for (int i = 0; i < groupList.size(); i++) {
            if (groupList.get(i).getUserAccount().getId() == SmartReminder.myAccount.getId()) {
                list.add(groupList.get(i));
            }
        }
        return list;
    }
    
}
