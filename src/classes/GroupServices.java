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
    
    private List<GroupMember> groupList = new ArrayList<>();
    
    private GroupServices(){
    
        EntityManager em = SmartReminder.emf.createEntityManager();
 
        // Store 1000 Point objects in the database:
        em.getTransaction().begin();
        
        TypedQuery<GroupMember> query = em.createQuery("SELECT gMem FROM GroupMember gMem", GroupMember.class);
        groupList = query.getResultList();
        
        em.close();
        
    }
    
    public static GroupServices getInstance(){
        return instance;
    }
    
    public void create(String groupName) {
        
        boolean isValid = true;
        
        for (int i = 0; i < groupList.size(); i++) {
            if ( (groupList.get(i).getGroupDetail().getGroupName().equals(groupName)) && 
                (groupList.get(i).getGroupDetail().getCreaterAccount().getId() == SmartReminder.myAccount.getId())
                ) {
                isValid = false;
                break;
            }
        }
        if (isValid) {
            GroupDetail newGroup = new GroupDetail(groupName, SmartReminder.myAccount);
            EntityManager em = SmartReminder.emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(newGroup);
            em.getTransaction().commit();
            // Close the database connection:
            em.close();
            
            GroupMember newMember = new GroupMember(SmartReminder.myAccount, newGroup);
            em = SmartReminder.emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(newMember);
            em.getTransaction().commit();
            // Close the database connection:
            em.close();
            groupList.add(newMember);
            System.out.println("Creating group success!!");
        }
        else {
            System.out.println("This group's name is used.");
        }
        
    }
    
    public void delete(String groupName) {
        GroupDetail group = null;
        ArrayList<GroupMember> list = getMyGroupList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getGroupDetail().getGroupName().equals(groupName)) {
                group = list.get(i).getGroupDetail();
                break;
            }
        }
        
        if ( (group != null) && group.getCreaterAccount().getId() == SmartReminder.myAccount.getId()) {
            ArrayList<GroupMember> members = getMembers(group);
            for (int i = 0; i < members.size(); i++) {
                EntityManager em = SmartReminder.emf.createEntityManager();
                GroupMember gMem = em.find(GroupMember.class, members.get(i).getId());
                em.getTransaction().begin();
                em.remove(gMem);
                em.getTransaction().commit();
                // Close the database connection:
                em.close();
                groupList.remove(members.get(i));
            }

            EntityManager em = SmartReminder.emf.createEntityManager();
            GroupDetail g = em.find(GroupDetail.class, group.getId());
            em.getTransaction().begin();
            em.remove(g);
            em.getTransaction().commit();
            // Close the database connection:
            em.close();
            
            System.out.println(group.getGroupName() + " is deleted.");
        }
        else {
            System.out.println("You're not allowed to delete this group.");
        }
        
    }
    
    public void addMember (String userName, String groupName, String createrUserName) {
        
        boolean isValid = true;
        
        long createrId = 0;
        
        ArrayList<Friend> list = SmartReminder.myFriendServices.getFriendList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getFriendAccount().getUserName().equals(createrUserName)) {
                createrId = list.get(i).getFriendAccount().getId();
            }
        }
        
        GroupDetail group = null;
        ArrayList<GroupMember> gList = getMyGroupList();
        for (int i = 0; i < gList.size(); i++) {
            if ( (gList.get(i).getGroupDetail().getGroupName().equals(groupName)) && 
                (gList.get(i).getGroupDetail().getCreaterAccount().getId() == createrId) 
                ) {
                group = gList.get(i).getGroupDetail();
                if (gList.get(i).getUserAccount().getUserName().equals(userName)) {
                    isValid = false;
                    break;
                }
            }
        }
        
        if (isValid) {
            
            UserAccount account = null;
            ArrayList<Friend> fndList = SmartReminder.myFriendServices.getFriendList();
            for (int i = 0; i < fndList.size(); i++) {
                if (fndList.get(i).getFriendAccount().getUserName().equals(userName)) {
                    account = fndList.get(i).getFriendAccount();
                }
            }
            
            GroupMember newMember = new GroupMember(account, group);
            
            EntityManager em = SmartReminder.emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(newMember);
            em.getTransaction().commit();
            // Close the database connection:
            em.close();
            groupList.add(newMember);
            System.out.println("Adding new member success!!");
        }
        else {
            System.out.println("This member is already exist.");
        }
    }

    public ArrayList getMyGroupList() {
        ArrayList<GroupMember> list = new ArrayList<>();
        for (int i = 0; i < groupList.size(); i++) {
            if (groupList.get(i).getUserAccount().getId() == SmartReminder.myAccount.getId()) {
                list.add(groupList.get(i));
            }
        }
        return list;
    }
    
    public ArrayList getMembers(GroupDetail group) {
        ArrayList<GroupMember> members = new ArrayList<>();
        for (int i = 0; i < groupList.size(); i++) {
            if (groupList.get(i).getGroupDetail().getId() == group.getId()) {
                members.add(groupList.get(i));
            }
            
        }
        return members;
    }
    
}
