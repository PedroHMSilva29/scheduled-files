package br.com.pehenmo.agendamento.scheduledfiles.csv.reader;

import br.com.pehenmo.agendamento.scheduledfiles.csv.dto.FraudRequestDto;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CSVReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(CSVReader.class);

    public List<FraudRequestDto> read() {

        InputStream input = getClass().getClassLoader().getResourceAsStream("target.csv");

        var parser = new CSVParserBuilder()
                .withSeparator(',')
                .build();

        var reader = new CSVReaderBuilder(new InputStreamReader(input))
                .withSkipLines(1)
                .withCSVParser(parser)
                .build();

        try {

            List<FraudRequestDto> dtoList = reader.readAll().stream().map(data -> {
                FraudRequestDto newDto = new FraudRequestDto();
                newDto.setDirection(data[0]);
                newDto.setYear(data[1]);
                newDto.setDate(data[2]);
                newDto.setWeekday(data[3]);
                newDto.setCountry(data[4]);
                newDto.setCommodity(data[5]);
                newDto.setTransportMode(data[6]);
                newDto.setMeasure(data[7]);
                newDto.setValue(data[8]);
                newDto.setCumulative(data[9]);
                return newDto;
            }).collect(Collectors.toList());

            //dtoList.forEach(System.out::println);

            return dtoList;

        } catch (CsvException | IOException e) {
            LOGGER.error("Erro ao parsear arquivo CSV", e);

        }

        return null;
    }
}
