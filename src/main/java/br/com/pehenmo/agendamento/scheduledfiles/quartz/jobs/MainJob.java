package br.com.pehenmo.agendamento.scheduledfiles.quartz.jobs;

import br.com.pehenmo.agendamento.scheduledfiles.csv.service.FraudService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainJob.class);

    @Autowired
    private FraudService service;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        try{
            service.managerCSV();
            LOGGER.info("Job finalizado com sucesso");
        }catch (Exception e){
            LOGGER.info("Erro ao processar Job", e);
        }

    }
}
