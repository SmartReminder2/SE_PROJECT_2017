/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreminder;

import classes.*;
import java.util.Date;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.persistence.*;
import javax.swing.Timer;

/**
 *
 * @author 58010622
 */
public class SmartReminder extends Application {
    
    public static Parent loginPage;
    public static Parent fillIdPassword;
    public static Parent signUpPage;
    public static Parent errorLogin;
    public static Parent homePage;
    public static Parent profilePage;
    public static Parent groupPage;
    public static Parent addSchedulePage;
    public static Parent timeTablePage;
    public static Parent addingSchedulePage;
    
    public static Stage primaryStage;
    public static Pane primaryPane;
    public static Pane secondaryPane;
    //public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb://161.246.6.212:80/database.odb;user=admin;password=admin");
    //public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("./db/database.odb");
    public static PersonalCalendar myCalendar = PersonalCalendar.getInstance();
    public static GroupCalendar groupCalendar = GroupCalendar.getInstance();
    public static FriendServices myFriendServices = FriendServices.getInstance();
    public static GroupServices myGroupServices = GroupServices.getInstance();
    public static UserAccountServices myUserAccountServices = new UserAccountServices();
    public static UserAccount myAccount;
    public static Date beginTime;
    public static Date finishTime;
    public static Timer tmpT;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        /*UserAccount tmpUser = new UserAccount("testid", "testpass", "095-852-9865");
        
        EntityManager em = emf.createEntityManager();
 
        // Store 1000 Point objects in the database:
        em.getTransaction().begin();
        
        em.persist(tmpUser);
                
        em.getTransaction().commit();
        
        // Close the database connection:
        em.close();
        emf.close();*/
        myCalendar.showSchedule();
        primaryStage = stage;
        loginPage = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        fillIdPassword = FXMLLoader.load(getClass().getResource("FillIdPassword.fxml"));
        signUpPage = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        errorLogin = FXMLLoader.load(getClass().getResource("ErrorLogin.fxml"));
        homePage = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        profilePage = FXMLLoader.load(getClass().getResource("ProfilePage.fxml"));
        groupPage = FXMLLoader.load(getClass().getResource("GroupPage.fxml"));
        addSchedulePage = FXMLLoader.load(getClass().getResource("AddSchedule.fxml"));
        timeTablePage = FXMLLoader.load(getClass().getResource("TimeTable.fxml"));
        addingSchedulePage = FXMLLoader.load(getClass().getResource("AddingSchedule.fxml"));

        Scene scene = new Scene(loginPage);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Smart Reminder");
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                // Do what you want when the application is stopping
                tmpT.stop();
                System.out.println("asdas");
                //emf.close();
            }
        }));
        
    }
    
     
    
}
