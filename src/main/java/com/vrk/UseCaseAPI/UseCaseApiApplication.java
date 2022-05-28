package com.vrk.UseCaseAPI;

import java.util.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.vrk.UseCaseAPI.TimerTask.CustomTimerTask;

@SpringBootApplication
@ComponentScan({"com.*"})
//@ComponentScan(basePackages = {"com.vrk.UseCaseAPI.*"})
@EnableScheduling

public class UseCaseApiApplication {

	@Autowired
    public Timer timer;

    @Autowired
    public CustomTimerTask task;
    
	public static void main(String[] args) {
		SpringApplication.run(UseCaseApiApplication.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
    public void startScheduler()
    {
        
        timer.schedule(task,1000,1000);

    }

}





