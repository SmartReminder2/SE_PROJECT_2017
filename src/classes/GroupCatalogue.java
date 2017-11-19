/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.ArrayList;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author kan
 */

@Entity
public class GroupCatalogue implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    
    private UserAccount userAccount;
    private GroupDetail groupDetail;
    
    private GroupCatalogue(){}

    public GroupCatalogue(UserAccount userAccount, GroupDetail groupDetail) {
        this.userAccount = userAccount;
        this.groupDetail = groupDetail;
    }
    
    
    /*public void add(UserAccount user, GroupDetail group) {
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
    }*/

    public long getId() {
        return id;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public GroupDetail getGroupDetail() {
        return groupDetail;
    }
    
}
