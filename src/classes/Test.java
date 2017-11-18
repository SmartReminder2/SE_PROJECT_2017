/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.ArrayList;
import java.util.*;
import javax.persistence.*;

/**
 *
 * @author kan
 */
public class Test {
    public static void main(String[] args) {
        
        UserAccount user1 = new UserAccount("testUsername1", "testPassword1", "02-123-4567");
        System.out.println(user1.getId());
        UserAccount user2 = new UserAccount("testUsername2", "testPassword2", "02-123-4567");
        System.out.println(user2.getId());
        UserAccount user3 = new UserAccount("testUsername3", "testPassword3", "02-123-4567");
        System.out.println(user3.getId());
        
        PersonalCalendar myCalendar = PersonalCalendar.getInstance();
        
        Date start = new Date(2017-1900, 11-1, 20, 9, 0);
        Date end = new Date(2017-1900, 11-1, 20, 12, 0);
        System.out.println(start.getYear());
        System.out.println(end.getMonth());
        Schedule sch1 = new Schedule(user1.getId(), "SE Project", "Don't forget!!", start, end, 15, true, true);
        
        start = new Date(2017-1900, 11-1, 20, 7, 0);
        end = new Date(2017-1900, 11-1, 20, 9, 0);
        Schedule sch2 = new Schedule(user1.getId(), "OOAD Project", "Don't forget2!!", start, end, 15, true, true);
        
        System.out.println("ID: " + sch1.getId());
        System.out.println("Title: " + sch1.getTitle());
        System.out.println("Detail: " + sch1.getDetail());
        System.out.println("Start: " + sch1.getBeginTime().getDate() + " " + sch1.getBeginTime().getMonth() + " " +  sch1.getBeginTime().getYear() + " " +  sch1.getBeginTime().getHours() + ":" +  sch1.getBeginTime().getMinutes());
        System.out.println("End: " + sch1.getFinishTime().getDate() + " " + sch1.getFinishTime().getMonth() + " " +  sch1.getFinishTime().getYear() + " " +  sch1.getFinishTime().getHours() + ":" +  sch1.getFinishTime().getMinutes());
        System.out.println("UserID: " + sch1.getUserId());
        System.out.println("");
        System.out.println("ID: " + sch2.getId());
        System.out.println("Title: " + sch2.getTitle());
        System.out.println("Detail: " + sch2.getDetail());
        System.out.println("Start: " + sch2.getBeginTime().getDate() + " " + sch2.getBeginTime().getMonth() + " " +  sch2.getBeginTime().getYear() + " " +  sch2.getBeginTime().getHours() + ":" +  sch2.getBeginTime().getMinutes());
        System.out.println("End: " + sch2.getFinishTime().getDate() + " " + sch2.getFinishTime().getMonth() + " " +  sch2.getFinishTime().getYear() + " " +  sch2.getFinishTime().getHours() + ":" +  sch2.getFinishTime().getMinutes());
        System.out.println("UserID: " + sch2.getUserId());
        
        myCalendar.addSchedule(sch1);
        myCalendar.addSchedule(sch2);
        myCalendar.showSchedule();
        
        GroupDetail group1 = new GroupDetail("SmartReminder", user1.getId());
        GroupCatalogue groupList = GroupCatalogue.getInstance();
        groupList.add(user2, group1);
        groupList.add(user3, group1);
        
        System.out.println("GroupID: " + group1.getId());
        
        ArrayList<UserAccount> list = new ArrayList<>();
        list = groupList.getAllUser(group1);
        
        for(int i = 0; i < list.size(); i++) {
            System.out.println("Member" + (i+1) + ": " + list.get(i).getUserName());
        }
        
        FriendCatalogue friendList = FriendCatalogue.getInstance();
        friendList.add(user2);
        friendList.add(user3);
        
        list = friendList.getFriendList();
        for(int i = 0; i < list.size(); i++) {
            System.out.println("Friend" + (i+1) + ": " + list.get(i).getUserName());
        }
        
        // Open a database connection
        // (create a new database if it doesn't exist yet):
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("./db/database.odb");
        EntityManager em = emf.createEntityManager();
 
        // Store 1000 Point objects in the database:
        em.getTransaction().begin();
        
        em.persist(sch1);
        em.persist(sch2);
                
        em.getTransaction().commit();
        
        
        
        // Find the number of Point objects in the database:
        Query q1 = em.createQuery("SELECT COUNT(sch) FROM Schedule sch");
        System.out.println("Total Points: " + q1.getSingleResult());
 
        // Find the average X value:
        Query q2 = em.createQuery("SELECT AVG(sch.timeBeforeAlert) FROM Schedule sch");
        System.out.println("Average X: " + q2.getSingleResult());
 
        // Retrieve all the Point objects from the database:
        TypedQuery<Schedule> query = em.createQuery("SELECT sch FROM Schedule sch", Schedule.class);
        List<Schedule> results = query.getResultList();

        for (int i = 0; i < results.size(); i++) {
            System.out.println(results.get(i).getBeginTime());
            System.out.println(results.get(i).getFinishTime());   
        }
        
        
        // Close the database connection:
        em.close();
        
        EntityManager em2 = emf.createEntityManager();
        em2.getTransaction().begin();
        
        em2.persist(group1);
                
        em2.getTransaction().commit();
        
        em2.close();
        
        emf.close();
        
        /*EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("C:/Users/kan/Documents/NetBeansProjects/SmartReminder/$objectdb/db/group_detail.odb");
        EntityManager em2 = emf2.createEntityManager();
        em2.getTransaction().begin();
        
        em2.persist(group1);
                
        em2.getTransaction().commit();
        
        em2.close();
        emf2.close();*/
    }
}
