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
        
        if (groupName.matches("[a-zA-Z0-9]+")) {
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
        else {
            System.out.println("Invalid group's name");
        }
        
        
        
    }
    
    public void delete(String groupName, String createrUserName) {
        
        
        if (createrUserName.equals(SmartReminder.myAccount.getUserName())) {
<<<<<<< HEAD
=======
            
            GroupDetail group = new GroupDetail();
            for (int i = 0; i < groupList.size(); i++) {
                if ( (groupList.get(i).getGroupDetail().getGroupName().equals(groupName)) && 
                    (groupList.get(i).getGroupDetail().getCreaterAccount().getUserName().equals(createrUserName)) 
                ) {
                    group = groupList.get(i).getGroupDetail();
                }
            }
            System.out.println("Group name: " + group.getGroupName());
            System.out.println("Creater name: " + group.getCreaterAccount().getUserName() + " ID: " + group.getCreaterAccount().getId());
            
>>>>>>> master
            ArrayList<GroupMember> members = getMembers(groupName, createrUserName);
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
<<<<<<< HEAD
            GroupDetail group = new GroupDetail();
            for (int i = 0; i < groupList.size(); i++) {
                if ( (groupList.get(i).getGroupDetail().getGroupName().equals(groupName)) && 
                    (groupList.get(i).getGroupDetail().getCreaterAccount().getUserName().equals(createrUserName)) 
                ) {
                    group = groupList.get(i).getGroupDetail();
                }
            }
=======
>>>>>>> master
            
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
        
        GroupDetail group = null;
        ArrayList<GroupMember> gList = getMyGroupList();
        for (int i = 0; i < gList.size(); i++) {
            if ( (gList.get(i).getGroupDetail().getGroupName().equals(groupName)) && 
                (gList.get(i).getGroupDetail().getCreaterAccount().getUserName().equals(createrUserName)) 
            ) {
                group = gList.get(i).getGroupDetail();
                ArrayList<GroupMember> memberList = getMembers(group.getGroupName(), group.getCreaterAccount().getUserName());
                for (int j = 0; j < memberList.size(); j++) {
                    System.out.println(memberList.get(j).getUserAccount().getUserName() + " " + userName);
                    if (memberList.get(j).getUserAccount().getUserName().equals(userName)) {
                        isValid = false;
                        break;
                    }
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
<<<<<<< HEAD
=======
    
    public void deleteMember (String userName, String groupName, String createrUserName) {
        
        if (createrUserName.equals(SmartReminder.myAccount.getUserName())) {

            ArrayList<GroupMember> members = getMembers(groupName, createrUserName);
            for (int i = 0; i < members.size(); i++) {
                if (userName.equals(members.get(i).getUserAccount().getUserName())) {
                    EntityManager em = SmartReminder.emf.createEntityManager();
                    GroupMember gMem = em.find(GroupMember.class, members.get(i).getId());
                    em.getTransaction().begin();
                    em.remove(gMem);
                    em.getTransaction().commit();
                    // Close the database connection:
                    em.close();
                    groupList.remove(members.get(i));
                    System.out.println(members.get(i).getUserAccount().getUserName() + " is deleted.");
                }
            }
        }
        else {
            System.out.println("You're not allowed to delete this member.");
        }
    }
>>>>>>> master

    public ArrayList getMyGroupList() {
        ArrayList<GroupMember> list = new ArrayList<>();
        for (int i = 0; i < groupList.size(); i++) {
            if (groupList.get(i).getUserAccount().getId() == SmartReminder.myAccount.getId()) {
                list.add(groupList.get(i));
            }
        }
        return list;
    }
    
    public ArrayList getMembers(String groupName, String createrUserName) {
        ArrayList<GroupMember> members = new ArrayList<>();
        for (int i = 0; i < groupList.size(); i++) {
            //System.out.println(groupList.get(i).getGroupDetail().getCreaterAccount() + " " + createrUserName);
            if ( (groupList.get(i).getGroupDetail().getGroupName().equals(groupName)) && 
                (groupList.get(i).getGroupDetail().getCreaterAccount().getUserName().equals(createrUserName)) 
                ) {
                members.add(groupList.get(i));
            }
            
        }
        return members;
    }
    
}
