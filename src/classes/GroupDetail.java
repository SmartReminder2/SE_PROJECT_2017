/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author kan
 */

@Entity
public class GroupDetail implements Serializable {
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    
    private String GroupName;
    private UserAccount createrAccount;

    public GroupDetail() {
    }

    public GroupDetail(String GroupName, UserAccount createrAccount) {
        this.GroupName = GroupName;
        this.createrAccount = createrAccount;
    }

    public void setGroupName(String GroupName) {
        this.GroupName = GroupName;
    }

    public long getId() {
        return id;
    }
    
    public String getGroupName() {
        return GroupName;
    }

    public UserAccount getCreaterAccount() {
        return createrAccount;
    }
}
