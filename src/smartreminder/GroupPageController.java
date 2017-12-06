/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreminder;

import classes.*;
import java.net.URL;
import static java.sql.JDBCType.NULL;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author 58010622
 */
public class GroupPageController implements Initializable {

    @FXML
    private Pane friendListPane;
    @FXML
    private TextField groupName;
    
    static public ListView<String> tmpFriend_list;
    static public ListView<String> tmpFriendInGroup_list;
    static public ListView<String> tmpGroup_list;
    
    static ObservableList<String> groupList_name = FXCollections.observableArrayList (); 
    static ObservableList<String> tmpFriendList_name;
    static ObservableList<String> friendInList_name = FXCollections.observableArrayList ();
    
    Map<String, List<String>> dictGroup  = new HashMap<String, List<String>>();;
    @FXML
    private Pane deleteGroup_pane;
    @FXML
    private Pane deleteFriend_pane;
    
    //private ListView<String> friendinGroup_list;
     @FXML
    private ListView<String> friend_list;
    @FXML
    private ListView<String> group_list;
    @FXML
    private ListView<String> friendInGroup_list;

    static String select_GroupName;
<<<<<<< HEAD
    static String select_Friendname;
    static String select_listInGroup;
=======
    static String select_FriendName;
    static String select_FriendInGroupName;
>>>>>>> master
    ObservableList<String> friend_observableList;
    ArrayList friends;
    @FXML
    private Label groupNameDelete_label;
    @FXML
    private Label FndInGpNameDelete_label;
    
    static GroupDetail tmpGroupDetail;
    
    static String createrUsername = new String();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        tmpFriendInGroup_list = friendInGroup_list;
        tmpFriend_list = friend_list;
        tmpGroup_list = group_list;
        setInit();
    }   
    static void setInit()
    {
        tmpFriendList_name = HomePageController.friendList_name;
        tmpFriend_list.setItems(tmpFriendList_name);
        tmpFriendInGroup_list.setItems(friendInList_name);
        tmpGroup_list.setItems(groupList_name); 
    }

    void changeFriendInGroup()
    {
        friends = (ArrayList) dictGroup.get(select_GroupName);
        friend_observableList = FXCollections.observableList(friends);
        friendInGroup_list.setItems(friend_observableList);
    }
    @FXML
    private void onClickedGroupList(MouseEvent event) {
<<<<<<< HEAD
        String[] str = tmpGroup_list.getSelectionModel().getSelectedItem().split(" by:");
        System.out.println(str[0]);
        System.out.println(str[str.length-1]);
        select_GroupName = str[0];
        createrUsername = str[str.length-1];
                
        ArrayList<Friend> fndList = SmartReminder.myFriendServices.getFriendList();
        UserAccount account = null;
        for (int i = 0; i < fndList.size(); i++) {
            if (fndList.get(i).getFriendAccount().getUserName().equals(createrUsername)) {
                account = fndList.get(i).getFriendAccount();
            }
        }
        tmpGroupDetail = new GroupDetail(select_GroupName, account);
        if (tmpGroupDetail != null) {
            updateFriendInList();
=======
        if (!tmpGroup_list.getSelectionModel().isEmpty()) {
            String[] str = tmpGroup_list.getSelectionModel().getSelectedItem().split(" by:");
            System.out.println(str[0]);
            System.out.println(str[str.length-1]);
            select_GroupName = str[0];
            createrUsername = str[str.length-1];
            
            ArrayList<Friend> fndList = SmartReminder.myFriendServices.getFriendList();
            UserAccount account = null;
            for (int i = 0; i < fndList.size(); i++) {
                if (fndList.get(i).getFriendAccount().getUserName().equals(createrUsername)) {
                    account = fndList.get(i).getFriendAccount();
                }
            }
            tmpGroupDetail = new GroupDetail(select_GroupName, account);
            if (tmpGroupDetail != null) {
                updateFriendInList();
            }
>>>>>>> master
        }
        
        if(event.getClickCount() > 1){
             // if Double click Delete Group
            if (!tmpGroup_list.getSelectionModel().isEmpty()) {
                
                
                groupNameDelete_label.setText(select_GroupName);
                deleteGroup_pane.setVisible(true);
                    /*if (dictGroup.containsKey(select_GroupName) ){
                       dictGroup.get(select_GroupName).clear();
                       int index = groupList_name.indexOf(select_GroupName);
                       groupList_name.remove(index);
                       select_GroupName = tmpGroup_list.getSelectionModel().getSelectedItem();

                    }*/
<<<<<<< HEAD
            }
            else {
                
            }
=======
            }
            else {
                
            }
>>>>>>> master
        }
        else // on Click
        {
                //if dictGroup is Null then Create dictGroup[select_GroupName]
                /*if (!dictGroup.containsKey(select_GroupName) ){
                    dictGroup.put(select_GroupName, new ArrayList<String>());
                } */
        }
            
            // Change friends in Group follow Group name
            //changeFriendInGroup();
        
    }
    Boolean duplicateFriend;
    @FXML
    private void onClickedFriendList(MouseEvent event) {
        duplicateFriend = false;
<<<<<<< HEAD
        select_Friendname = tmpFriend_list.getSelectionModel().getSelectedItem();
=======
        select_FriendName = tmpFriend_list.getSelectionModel().getSelectedItem();
>>>>>>> master
        if(event.getClickCount() > 1){
            if (!tmpFriend_list.getSelectionModel().isEmpty() && tmpGroup_list.getSelectionModel().getSelectedItem() != null) {
                /*String[] str = tmpGroup_list.getSelectionModel().getSelectedItem().split(" by:");
                System.out.println(str[0]);
                System.out.println(str[str.length-1]);
                select_GroupName = str[0];
                createrUsername = str[str.length-1];*/
                        
<<<<<<< HEAD
                SmartReminder.myGroupServices.addMember(select_Friendname, select_GroupName, createrUsername);
                if (tmpGroupDetail != null) {
                    updateFriendInList();
                }
                /*if (dictGroup.containsKey(select_GroupName) ){
                    //Add friend in Group
                    for(String name : dictGroup.get(select_GroupName))
                    {
                        if(name.equals(select_Friendname)){
                            duplicateFriend = true;
                            System.out.println("duplicateFriend!!");
                            break;
                        }

                    }
                    if(!duplicateFriend)
                        dictGroup.get(select_GroupName).add(select_Friendname);
                } 
                else
                {
                    //if dictGroup is Null then Create dictGroup[select_GroupName] & Add friend in Group
                    dictGroup.put(select_GroupName, new ArrayList<String>())  ;
                    dictGroup.get(select_GroupName).add(select_Friendname);
                }
                // Change friends in Group follow Group name
                changeFriendInGroup();*/
            }
            else {
                System.out.println("Not selected group or group yet.");
            }
            
        }
 
    }
    
    @FXML
    private void onClickedFriendinGroupList(MouseEvent event) { 
        int index=0;
        // Dubble Click
        if(event.getClickCount() > 1&& !select_GroupName.equals("")){
            select_listInGroup = tmpFriendInGroup_list.getSelectionModel().getSelectedItem();
            if (dictGroup.containsKey(select_GroupName) ){
               for(String name : dictGroup.get(select_GroupName))
               {
                   if(name.equals(select_listInGroup))
                   {
                       break;
                   }
                   index++;
               }
               dictGroup.get(select_GroupName).remove(index); 
               //select_GroupName = tmpGroup_list.getSelectionModel().getSelectedItem();
=======
                SmartReminder.myGroupServices.addMember(select_FriendName, select_GroupName, createrUsername);
                if (tmpGroupDetail != null) {
                    updateFriendInList();
                }
                /*if (dictGroup.containsKey(select_GroupName) ){
                    //Add friend in Group
                    for(String name : dictGroup.get(select_GroupName))
                    {
                        if(name.equals(select_FriendName)){
                            duplicateFriend = true;
                            System.out.println("duplicateFriend!!");
                            break;
                        }

                    }
                    if(!duplicateFriend)
                        dictGroup.get(select_GroupName).add(select_FriendName);
                } 
                else
                {
                    //if dictGroup is Null then Create dictGroup[select_GroupName] & Add friend in Group
                    dictGroup.put(select_GroupName, new ArrayList<String>())  ;
                    dictGroup.get(select_GroupName).add(select_FriendName);
                }
                // Change friends in Group follow Group name
                changeFriendInGroup();*/
            }
            else {
                System.out.println("Not selected group or group yet.");
>>>>>>> master
            }
            
        }
<<<<<<< HEAD
    }    
    
=======
 
    }
>>>>>>> master
    
    @FXML
    private void createGroup(ActionEvent event) {
        SmartReminder.myGroupServices.create(groupName.getText());
        updateGroupList();
    }
    
    @FXML
    private void createGroup(ActionEvent event) {
        SmartReminder.myGroupServices.create(groupName.getText());
        updateGroupList();
    }

    @FXML
    private void deleteGroup(ActionEvent event) {
        SmartReminder.myGroupServices.delete(select_GroupName, createrUsername);
        updateGroupList();
<<<<<<< HEAD
=======
        updateFriendInList();
>>>>>>> master
        deleteGroup_pane.setVisible(false);
    }

    @FXML
    private void cancleDeleteGroup(ActionEvent event) {
        deleteGroup_pane.setVisible(false);
    }

    @FXML
    private void deleteFriend(ActionEvent event) {
        SmartReminder.myGroupServices.deleteMember(select_FriendInGroupName, select_GroupName, createrUsername);
        updateFriendInList();
        deleteFriend_pane.setVisible(false);
    }

    @FXML
    private void cancleDeleteFriend(ActionEvent event) {
        deleteFriend_pane.setVisible(false);
        
    }

    public static void updateGroupList() {
        groupList_name.clear();
        ArrayList<GroupMember> list = SmartReminder.myGroupServices.getMyGroupList();
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Point " + list.get(i).getUserAccount().getUserName());
            groupList_name.add(list.get(i).getGroupDetail().getGroupName() + " by:" + list.get(i).getGroupDetail().getCreaterAccount().getUserName());
        }
    }
    
    public static void updateFriendInList() {
        friendInList_name.clear();
        ArrayList<GroupMember> list = SmartReminder.myGroupServices.getMembers(select_GroupName, createrUsername);
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).getUserAccount().getUserName().equals(SmartReminder.myAccount.getUserName())) {
                friendInList_name.add(list.get(i).getUserAccount().getUserName());
            }
        }
    }
<<<<<<< HEAD
=======

    @FXML
    private void onClickedFriendInGroupList(MouseEvent event) {
        int index=0;
        // Double Click
        if (!tmpFriendInGroup_list.getSelectionModel().isEmpty()) {
            select_FriendInGroupName = tmpFriendInGroup_list.getSelectionModel().getSelectedItem();
        }
        if(event.getClickCount() > 1 && !tmpFriendInGroup_list.getSelectionModel().isEmpty()){
            FndInGpNameDelete_label.setText(select_FriendInGroupName);
            deleteFriend_pane.setVisible(true);
            
            /*if (dictGroup.containsKey(select_GroupName) ){
               for(String name : dictGroup.get(select_GroupName))
               {
                   if(name.equals(select_FriendInGroupName))
                   {
                       break;
                   }
                   index++;
               }
               dictGroup.get(select_GroupName).remove(index); 
               //select_GroupName = tmpGroup_list.getSelectionModel().getSelectedItem();
            }
            changeFriendInGroup();
            System.out.print("5555");*/
        }
    }
>>>>>>> master
  
}
