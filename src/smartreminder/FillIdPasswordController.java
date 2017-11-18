/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreminder;

import classes.UserAccount;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javax.persistence.*;

/**
 * FXML Controller class
 *
 * @author 58010622
 */
public class FillIdPasswordController implements Initializable {

    @FXML
    private TextField id_field;
    @FXML
    private PasswordField password_field;
    
    public static String username = "";   
    public static String password;
    
    static public TextField changeid_field;
    static public PasswordField changpassword_field;
    
    
    private List<UserAccount> users;
    @FXML
    private Label sign_up;
    @FXML
    private Circle circle1;
    @FXML
    private Circle circle2;
    @FXML
    private Circle circle3;
    @FXML
    private Circle circle4;
    @FXML
    private Circle circle5;
    @FXML
    private Circle circle7;
    @FXML
    private Circle circle6;
    @FXML
    private Circle circle8;
    @FXML
    private Circle circle12;
    @FXML
    private Circle circle11;
    @FXML
    private Circle circle9;
    @FXML
    private Circle circle10;
    @FXML
    private Circle circle13;
    @FXML
    private Circle circle14;
    @FXML
    private Circle circle16;
    @FXML
    private Circle circle15;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        changeid_field = id_field;
        changpassword_field = password_field;
        
        EntityManager em = SmartReminder.emf.createEntityManager();
 
        // Store 1000 Point objects in the database:
        em.getTransaction().begin();
        
        TypedQuery<UserAccount> query = em.createQuery("SELECT user FROM UserAccount user", UserAccount.class);
        users = query.getResultList();
        
        em.close();
        //SmartReminder.emf.close();
        animation_circle(circle1,1.0,0.1,1800);
        animation_circle(circle2,1.0,0.1,1800);
        animation_circle(circle3,1.0,0.1,1800);
        animation_circle(circle4,1.0,0.1,1800);
        
        animation_circle(circle5,1.0,0.1,1200);
        animation_circle(circle6,1.0,0.1,1200);
        animation_circle(circle7,1.0,0.1,1200);
        animation_circle(circle8,1.0,0.1,1200);
        
        animation_circle(circle9,1.0,0.1,800);
        animation_circle(circle10,1.0,0.1,800);
        animation_circle(circle11,1.0,0.1,800);
        animation_circle(circle12,1.0,0.1,800);
        
        animation_circle(circle13,1.0,0.1,1500);
        animation_circle(circle14,1.0,0.1,1500);
        animation_circle(circle15,1.0,0.1,1500);
        animation_circle(circle16,1.0,0.1,1500);
    }
    @FXML
    private void btnLogin(ActionEvent event) {
        System.out.println(users.size());
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getUserName().equalsIgnoreCase(id_field.getText()) && users.get(i).getPassword().equalsIgnoreCase(password_field.getText())) {
                SmartReminder.myAccount = users.get(i);
                System.out.println(SmartReminder.myAccount.getUserName());
                
                HomePageController.tmpUsernameMenu.setText(SmartReminder.myAccount.getUserName());
                System.out.println(HomePageController.tmpUsernameMenu.getText());
                System.out.println("Login Successfully!!");
                HomePageController.updateFriendList();
                SmartReminder.primaryStage.getScene().setRoot(SmartReminder.homePage);
                return;
            }
        }
        
        System.out.println("Incorrect username or password");
        SmartReminder.primaryPane.getChildren().clear();
        SmartReminder.primaryPane.getChildren().add(SmartReminder.errorLogin);
    }

    @FXML
    private void onSignUp(MouseEvent event) {
        if(event.getClickCount() == 1) {
            SmartReminder.primaryPane.getChildren().clear();
            SmartReminder.primaryPane.getChildren().add(SmartReminder.signUpPage);
        }
    }
        
    void animation_circle(Circle c,double x,double y,int time)
    {
        FadeTransition ft = new FadeTransition(Duration.millis(time), c);
        ft.setFromValue(x);
        ft.setToValue(y);
        ft.setCycleCount(Timeline.INDEFINITE);
        ft.setAutoReverse(true);
        ft.play();
    }
    
}
