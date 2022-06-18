package br.com.pehenmo.agendamento.scheduledfiles;

import org.quartz.SchedulerException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ScheduledFilesApplication {

	public static void main(String[] args) throws SchedulerException {
		SpringApplication.run(ScheduledFilesApplication.class, args);
	}

}
