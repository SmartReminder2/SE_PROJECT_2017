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
public class FriendCatalogue {
    
    //create an object of PersonalCalendar
    private static FriendCatalogue instance = new FriendCatalogue();
    
    private ArrayList<UserAccount> friendList = new ArrayList<>();
    
    //make the constructor private so that this class cannot be instantiated
    private FriendCatalogue(){}
    
    //Get the only object available
    public static FriendCatalogue getInstance(){
        return instance;
    }
    
    public void add(UserAccount user) {
        friendList.add(user);
    }
    
    public void delete(UserAccount user) {
        friendList.remove(user);
    }
    
    public ArrayList search(String userName) {
        
        ArrayList<UserAccount> list = new ArrayList<>();
        for (int i = 0; i < friendList.size(); i++) {
            if(friendList.get(i).getUserName().equals(userName)) {
                list.add(friendList.get(i));
            }
        }
        return list;
    }
    
    public ArrayList getFriendList() {
            
        return friendList;
    }
}
