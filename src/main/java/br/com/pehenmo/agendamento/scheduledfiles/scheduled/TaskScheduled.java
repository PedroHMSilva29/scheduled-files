package br.com.pehenmo.agendamento.scheduledfiles.scheduled;

/**
@Component
@EnableScheduling
public class TaskScheduled {

    private final long SEGUNDO = 1000;
    private final long MINUTO = SEGUNDO * 60;
    private final long HORA = MINUTO * 60;
    //@Scheduled(fixedRate = MINUTO)


    @Scheduled(cron = "0 * * ? * *")
    public void verificaPorHora() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        System.out.println("Passou 1 minuto!!! " + java.time.LocalDateTime.now().format(formatter));
    }


}
 **/