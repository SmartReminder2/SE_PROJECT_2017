/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreminder;

import classes.PersonalCalendar;
import classes.Schedule;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import static smartreminder.ProfilePageController.imageProfileTemp;
import static smartreminder.ProfilePageController.img;
import static smartreminder.SmartReminder.timeTablePage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AddScheduleController implements Initializable {


    @FXML
    private AnchorPane timeTable_pane;
    static  AnchorPane timeTable_paneTemp;
    public static int xPos = 208;
    public static int yPos = 3;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
        timeTable_paneTemp = timeTable_pane;
        // TODO
    }    
    static void setTimeTable()
    {

        timeTable_paneTemp.getChildren().clear();
        timeTable_paneTemp.getChildren().add(SmartReminder.timeTablePage);
        
        ArrayList<Color> colorList = new ArrayList();
        colorList.add(Color.CYAN);
        colorList.add(Color.ORANGE);
        colorList.add(Color.PALEGREEN);
        System.out.println("setTimeTable");
        List<Schedule> list = new ArrayList<>();
        
        if (HomePageController.isPersonal) {
            list = SmartReminder.myCalendar.getSchedule(SmartReminder.beginTime, SmartReminder.myAccount);
        }
        else {
            list = SmartReminder.groupCalendar.getAllSchedules(GroupPageController.select_GroupName, GroupPageController.createrUsername);
        }
        
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getTitle());
            System.out.println("222");
        }
        
        for (int j = 0; j < list.size(); j++) {
            
            int beginPhase = PersonalCalendar.getPhase(list.get(j).getBeginTime());
            int finishPhase = PersonalCalendar.getPhase(list.get(j).getFinishTime());
            if(finishPhase == 0) {
                finishPhase = 48;
            }
 
            int[] num = new int[48];
            for (int i = beginPhase; i < finishPhase; i++) {
                num[i] = 1;
            }
            /*for (int i = 0; i < num.length; i++) {
                System.out.println(num[i]);
            }*/
            
            int firstPos = beginPhase;
            int lastPos = finishPhase - 1;
            System.out.println(firstPos + " " + lastPos);
            int height = 62;
            for (int i = 0; i < 48; i++) {

                if(num[i] == 1) {
                    System.out.println(i);
                    Rectangle rect = new Rectangle(xPos, yPos, 600, height);
                    if (HomePageController.isPersonal) {
                        rect.setFill(colorList.get(j % 3));
                    }
                    else {
                        rect.setFill(Color.GRAY);
                    }
                    rect.setVisible(true);
                    timeTable_paneTemp.getChildren().add(rect);
                }

                if((i == Math.ceil((lastPos - firstPos)/2.0) + firstPos) && HomePageController.isPersonal) {
                    /*String text = list.get(0).getTitle().substring(0, 8);
                    text = text.concat(" ..");
                    System.out.println(text);*/
                    Button btn = new Button("EDIT");
                    Label label = new Label(list.get(j).getTitle());
                    label.setLayoutX(xPos + 10);
                    label.setLayoutY(yPos - 20);
                    label.setVisible(true);
                    
                    btn.setLayoutX(xPos + 10);
                    btn.setLayoutY(yPos);
                    btn.setVisible(true);
                    
                    timeTable_paneTemp.getChildren().add(btn);
                    timeTable_paneTemp.getChildren().add(label);
                    
                    final long tmpI = list.get(j).getId();
                    
                    btn.setOnAction(new EventHandler<ActionEvent>()
                    {
                        @Override
                        public void handle(ActionEvent e)
                        {
                            System.out.println("Hello World" + tmpI);
                            AddingScheduleController.tmpId = tmpI;
                            AddingScheduleController.setInit();
                            SmartReminder.primaryStage.getScene().setRoot(SmartReminder.addingSchedulePage);
                        }
                        
                    }
                    
                    );
                    
                    
                    System.out.println(firstPos + " " + lastPos);
                    System.out.println("btn" + i);
                }

                if(i < 22) {
                    if(i % 2 == 0) {
                        yPos += height;
                        height = 64;
                    }
                    else {
                        yPos += height;
                        height = 62;
                    }
                }
                else {
                    if(i % 2 == 0) {
                        yPos += height;
                        height = 63;
                    }
                    else {
                        yPos += height;
                        height = 61;
                    }
                }
            }
            xPos = 208;
            yPos = 3;
        }
        
        
        
    }
    
    @FXML
    private void adding(ActionEvent event) {
        AddingScheduleController.tmpId = 0;
        AddingScheduleController.setInit();
        SmartReminder.primaryStage.getScene().setRoot(SmartReminder.addingSchedulePage);  
    }

    @FXML
    private void backBtn(ActionEvent event) {
        SmartReminder.primaryStage.getScene().setRoot(SmartReminder.homePage);  
    }
    
}
