/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreminder;

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
    @FXML
    private Pane selectCal_Pane;
    @FXML
    private ComboBox<?> year_list;
    @FXML
    private ComboBox<?> month_list;
    
    static public ListView<String> friend_listTemp;
    static public ListView<String> friendinGroup_listTemp;
    static public ListView<String> group_listTemp;
    static ObservableList<String> groupList_name = FXCollections.observableArrayList ("Handsome","Lolicon"); 
    static ObservableList<String> friendlist_name; 
    Map<String, List<String>> dictGroup  = new HashMap<String, List<String>>();;
    @FXML
    private Pane deleteGroup_pane;
    @FXML
    private Label nameDelete_label;
    @FXML
    private Pane deleteFriend_pane;
    @FXML
    private Label nameDelete_label1;
    
    @FXML
    private ListView<String> friendinGroup_list;
     @FXML
    private ListView<String> friend_list;
    @FXML
    private ListView<String> group_list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        friendinGroup_listTemp = friendinGroup_list;
        friend_listTemp = friend_list;
        group_listTemp = group_list;
        setInit();
    }   
    static void setInit()
    {
        friendlist_name = HomePageController.friendList_name;
        friend_listTemp.setItems(friendlist_name);
        group_listTemp.setItems(groupList_name); 
    }

    String select_GroupName;
    String select_Friendname;
    String select_listInGroup;
    ObservableList<String> friend_observableList;
    ArrayList friends;
    void changeFriendInGroup()
    {
        friends = (ArrayList) dictGroup.get(select_GroupName);
        friend_observableList = FXCollections.observableList(friends);
        friendinGroup_list.setItems(friend_observableList);
    }
    @FXML
    private void onClickedGroupList(MouseEvent event) {
        select_GroupName = group_listTemp.getSelectionModel().getSelectedItem();
        if(event.getClickCount() > 1){
            // if Dubble click Delete Group
            if (dictGroup.containsKey(select_GroupName) ){
               dictGroup.get(select_GroupName).clear();
               int index = groupList_name.indexOf(select_GroupName);
               groupList_name.remove(index);
               select_GroupName = group_listTemp.getSelectionModel().getSelectedItem();
              
            }
         }
        else // on Click
        {
            //if dictGroup is Null then Create dictGroup[select_GroupName]
            if (!dictGroup.containsKey(select_GroupName) ){
                dictGroup.put(select_GroupName, new ArrayList<String>());
            } 
        }
        // Change friends in Group follow Group name
        changeFriendInGroup();
    }
    Boolean duplicateFriend;
    @FXML
    private void onClickedFriendList(MouseEvent event) {
        duplicateFriend = false;
        
        if(event.getClickCount() > 1){
           
            select_Friendname = friend_list.getSelectionModel().getSelectedItem();
            if (dictGroup.containsKey(select_GroupName) ){
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
            changeFriendInGroup();
           
        }
            
    }
    
    @FXML
    private void onClickedFriendinGroupList(MouseEvent event) { 
        int index=0;
        // Dubble Click
        if(event.getClickCount() > 1&& !select_GroupName.equals("")){
            select_listInGroup = friendinGroup_listTemp.getSelectionModel().getSelectedItem();
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
               //select_GroupName = group_listTemp.getSelectionModel().getSelectedItem();
            }
            changeFriendInGroup();
            System.out.print("5555");
        }
    }
    @FXML
    private void addGroup(ActionEvent event) {
        groupList_name.add(groupName.getText());
    }
    
    
    @FXML
    private void list_Action(ActionEvent event) {
    }

    @FXML
    private void mlist_Action(ActionEvent event) {
    }

    @FXML
    private void deleteGroup(ActionEvent event) {
    }

    @FXML
    private void cancleDeleteGroup(ActionEvent event) {
        deleteGroup_pane.setVisible(false);
    }

    @FXML
    private void deleteFriend(ActionEvent event) {
    }

    @FXML
    private void cancleDeleteFriend(ActionEvent event) {
        deleteFriend_pane.setVisible(false);
        
    }

    

    
    
}
