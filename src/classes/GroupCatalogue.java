/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.ArrayList;

/**
 *
 * @author kan
 */
public class GroupCatalogue {
    
    private static GroupCatalogue instance = new GroupCatalogue();
    
    private ArrayList<UserAccount> userList = new ArrayList<>();
    private ArrayList<GroupDetail> groupList = new ArrayList<>();
    
    private GroupCatalogue(){}
    
    public static GroupCatalogue getInstance(){
        return instance;
    }
    
    public void add(UserAccount user, GroupDetail group) {
        userList.add(user);
        groupList.add(group);
    }
    
    public void delete(UserAccount user, GroupDetail group) {
        userList.remove(user);
        groupList.remove(group);
    }
    
    public ArrayList getAllUser(GroupDetail group) {
        
        ArrayList<UserAccount> list = new ArrayList<>();
        
        for(int i = 0; i < groupList.size(); i++) {
            if(groupList.get(i).getId() == group.getId()) {
                list.add(userList.get(i));
            }
        }
            
        return list;
    }
}
