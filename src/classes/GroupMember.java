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
public class GroupMember implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    
    private UserAccount userAccount;
    private GroupDetail groupDetail;
    
    private GroupMember(){}

    public GroupMember(UserAccount userAccount, GroupDetail groupDetail) {
        this.userAccount = userAccount;
        this.groupDetail = groupDetail;
    }

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
