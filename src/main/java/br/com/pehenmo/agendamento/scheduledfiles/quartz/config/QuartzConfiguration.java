package br.com.pehenmo.agendamento.scheduledfiles.quartz.config;

import br.com.pehenmo.agendamento.scheduledfiles.quartz.jobs.MainJob;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import javax.annotation.PostConstruct;
import java.util.UUID;

import static org.quartz.CronScheduleBuilder.cronSchedule;

@Configuration
@EnableAutoConfiguration
public class QuartzConfiguration {

    Logger logger = LoggerFactory.getLogger(getClass());
    private static final String EXP = "0/10 * * * * ?";

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        logger.info("Hello world from Spring...");
    }

    @Bean
    public SpringBeanJobFactory springBeanJobFactory() {
        AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
        logger.debug("Configuring Job factory");

        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public SchedulerFactoryBean scheduler(Trigger trigger, JobDetail job) {

        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setConfigLocation(new ClassPathResource("quartz.properties"));

        logger.debug("Setting the Scheduler up");
        schedulerFactory.setJobFactory(springBeanJobFactory());
        schedulerFactory.setJobDetails(job);
        schedulerFactory.setTriggers(trigger);
        // Comment the following line to use the default Quartz job store.
        //schedulerFactory.setDataSource(quartzDataSource);

        return schedulerFactory;
    }

    @Bean
    public JobDetailFactoryBean jobDetail() {

        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();

        jobDetailFactory.setJobClass(MainJob.class);
        jobDetailFactory.setName("JobDetailFactoryBean-"+UUID.randomUUID().toString());
        jobDetailFactory.setGroup("simpleJobGroup-frauds");
        jobDetailFactory.setDescription("Invoke Sample Job service");
        jobDetailFactory.setDurability(true);

        return jobDetailFactory;
    }

    @Bean
    public CronTrigger trigger(JobDetail job) {

        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("CronTriggerName-"+UUID.randomUUID().toString(), "CronTriggerGroup"+UUID.randomUUID().toString())
                .withSchedule(cronSchedule(EXP))
                .forJob(job)
                .build();

        return trigger;
    }

    // @Bean
    //@QuartzDataSource
    //@ConfigurationProperties(prefix = "spring.datasource")
    //public DataSource quartzDataSource() {
    //    return DataSourceBuilder.create().build();
    //}

}
