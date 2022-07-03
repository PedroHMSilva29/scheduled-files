package br.com.pehenmo.agendamento.scheduledfiles.csv.writer;

import br.com.pehenmo.agendamento.scheduledfiles.aws.config.AWSS3Response;
import br.com.pehenmo.agendamento.scheduledfiles.aws.factory.AWSS3ClientFactory;
import br.com.pehenmo.agendamento.scheduledfiles.aws.writer.WriteBucketS3;
import br.com.pehenmo.agendamento.scheduledfiles.csv.dto.FraudRequestDto;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component
public class CSVWriter {

    private static final String PATH = "./src/main/resources/response.csv";
    private static final Logger LOGGER = LoggerFactory.getLogger(CSVWriter.class);

    @Autowired
    private AWSS3ClientFactory factory;

    @Autowired
    private AWSS3Response response;

    public void write(List<FraudRequestDto> fraudRequests){

        try (var writer = Files.newBufferedWriter(Paths.get(PATH), StandardCharsets.UTF_8)) {

            writer.append("Direction,Year,Date,Weekday,Country,Commodity,Transport_Mode,Measure,Value,Cumulative\n");

            ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
            strategy.setType(FraudRequestDto.class);

            StatefulBeanToCsv<FraudRequestDto> builder = new StatefulBeanToCsvBuilder<FraudRequestDto>(writer)
                    .withQuotechar(com.opencsv.CSVWriter.NO_QUOTE_CHARACTER)
                    .withMappingStrategy(strategy)
                    .build();

            // escreve local
            builder.write(fraudRequests);

            // escreve no bucket S3
            WriteBucketS3.putObject(factory.generate(), response.getKeyName(), response.getBucketName(), PATH);


        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException |
                IOException ex) {
            LOGGER.error("error ao gerar",ex);
        }

    }
}
