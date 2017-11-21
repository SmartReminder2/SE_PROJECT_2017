/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreminder;

import classes.UserAccount;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javax.persistence.*;

/**
 * FXML Controller class
 *
 * @author User
 */
public class SignUpController implements Initializable {

    private TextField id_field;
    private PasswordField password_field;
    
    public static String username;
    public static String password;
    public static String tel;
    @FXML
    private TextField username_fill;
    @FXML
    private PasswordField password_fill;
    @FXML
    private PasswordField password_confirm;
    @FXML
    private TextField tel_fill;
    @FXML
    private Pane error_pane;
    @FXML
    private Circle circle8;
    @FXML
    private Circle circle5;
    @FXML
    private Circle circle3;
    @FXML
    private Circle circle1;
    @FXML
    private Circle circle6;
    @FXML
    private Circle circle7;
    @FXML
    private Circle circle4;
    @FXML
    private Circle circle2;
    @FXML
    private Circle circle10;
    @FXML
    private Circle circle11;
    @FXML
    private Circle circle12;
    @FXML
    private Circle circle9;
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    }    

    @FXML
    private void onSignUpClick(ActionEvent event) {
        
        if(username_fill.getText().equals("") || password_fill.getText().equals("") || tel_fill.getText().equals("") || !password_confirm.getText().equals(password_fill.getText())) {
            error_pane.setVisible(true);
            SmartReminder.primaryPane.getChildren().clear();
            SmartReminder.primaryPane.getChildren().add(SmartReminder.signUpPage);
            
            System.out.println(username);
            System.out.println(password);
            System.out.println(tel);
        }
        else {
            username = username_fill.getText();
            password = password_fill.getText();
            tel = tel_fill.getText();
            
            UserAccount user = new UserAccount(username, password, tel);

            EntityManager em = SmartReminder.emf.createEntityManager();

            // Store 1000 Point objects in the database:
            em.getTransaction().begin();

            em.persist(user);

            em.getTransaction().commit();

            // Close the database connection:
            em.close();
            //emf.close();
            
            error_pane.setVisible(false);
            SmartReminder.primaryPane.getChildren().clear();
            SmartReminder.primaryPane.getChildren().add(SmartReminder.fillIdPassword);
            
            System.out.println(username);
            System.out.println(password);
            System.out.println(tel);
        }
        
        //SmartReminder.primaryPane.getChildren().clear();
        //SmartReminder.primaryPane.getChildren().add(SmartReminder.fillIdPassword);
    }

    @FXML
    private void onBackClick(ActionEvent event) {
        error_pane.setVisible(false);
        SmartReminder.primaryPane.getChildren().clear();
        SmartReminder.primaryPane.getChildren().add(SmartReminder.fillIdPassword);
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
