package br.com.pehenmo.agendamento.scheduledfiles.csv.service;

import br.com.pehenmo.agendamento.scheduledfiles.csv.dto.FraudRequestDto;
import br.com.pehenmo.agendamento.scheduledfiles.csv.reader.CSVReader;
import br.com.pehenmo.agendamento.scheduledfiles.csv.writer.CSVWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Component
public class FraudService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FraudService.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private List<FraudRequestDto> fraudRequests;

    @Autowired
    private CSVReader csvReader;

    @Autowired
    private CSVWriter csvWriter;

    private void readCSV(){
        this.setFraudRequests(csvReader.read());
        LOGGER.info("Sucesso - ao processar arquivos");
    }

    private void writeCSV() {
        csvWriter.write(fraudRequests);
        LOGGER.info("Sucesso - ao gerar arquivo");
    }

    private void filterCSV(){

        List<FraudRequestDto> filterList = fraudRequests
                .stream()
                .filter(map -> "Monday".equals(map.getWeekday()))
                .collect(Collectors.toList());

        this.fraudRequests = filterList;

        LOGGER.info("Sucesso - ao filtrar fraudRequests" );
    }

    public void managerCSV(){
        this.readCSV();
        this.filterCSV();
        this.writeCSV();
    }

    public void setFraudRequests(List<FraudRequestDto> fraudRequests) {
        this.fraudRequests = fraudRequests;
    }
}
