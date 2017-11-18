/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author kan
 */
public class GroupCalendar extends Calendar {
    
    //create an object of PersonalCalendar
    private static GroupCalendar instance = new GroupCalendar();
    
    private ArrayList<Schedule> scheduleList = new ArrayList<>();
    
    private List<Schedule> schedules;
    
    //make the constructor private so that this class cannot be instantiated
    private GroupCalendar(){}
    
    //Get the only object available
    public static GroupCalendar getInstance(){
        return instance;
    }
    
    @Override
    public void addSchedule(Schedule schedule) {
        scheduleList.add(schedule);
    }
    
    @Override
    public void editSchedule() {
        
        
    }
    
    @Override
    public List<Schedule> getSchedule(Date date, UserAccount user) {
        return schedules;
    }
    
    @Override
    public void showSchedule() {
        for (int i = 0; i < scheduleList.size(); i++) {
            System.out.println(scheduleList.get(i).getTitle());
        }
    }
    
}
