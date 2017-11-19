/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author kan
 */
public class GroupDetail{
    
    private String GroupName;
    private UserAccount createrAccount;

    public GroupDetail() {
    }

    public GroupDetail(String GroupName, UserAccount createrId) {
        this.GroupName = GroupName;
        this.createrAccount = createrId;
    }

    public void setGroupName(String GroupName) {
        this.GroupName = GroupName;
    }

    public String getGroupName() {
        return GroupName;
    }

    public UserAccount getCreaterAccount() {
        return createrAccount;
    }
}
