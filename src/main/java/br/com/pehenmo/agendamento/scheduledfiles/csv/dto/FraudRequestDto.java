package br.com.pehenmo.agendamento.scheduledfiles.csv.dto;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FraudRequestDto {

    @CsvBindByName(column = "Direction",required = true)
    @CsvBindByPosition(position = 0)
    private String direction;

    @CsvBindByName(column = "Year",required = true)
    @CsvBindByPosition(position = 1)
    private String year;

    @CsvBindByName(column = "Date",required = true)
    @CsvBindByPosition(position = 2)
    private String date;

    @CsvBindByName(column = "Weekday",required = true)
    @CsvBindByPosition(position = 3)
    private String weekday;

    @CsvBindByName(column = "Country",required = true)
    @CsvBindByPosition(position = 4)
    private String country;

    @CsvBindByName(column = "Commodity",required = true)
    @CsvBindByPosition(position = 5)
    private String commodity;

    @CsvBindByName(column = "Transport_Mode",required = true)
    @CsvBindByPosition(position = 6)
    private String transportMode;

    @CsvBindByName(column = "Measure",required = true)
    @CsvBindByPosition(position = 7)
    private String measure;

    @CsvBindByName(column = "Value",required = true)
    @CsvBindByPosition(position = 8)
    private String value;

    @CsvBindByName(column = "Cumulative",required = true)
    @CsvBindByPosition(position = 9)
    private String cumulative;
}
