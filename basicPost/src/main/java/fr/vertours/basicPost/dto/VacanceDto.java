package fr.vertours.basicPost.dto;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import lombok.Data;

import java.time.LocalDate;

@Data
public class VacanceDto {

    @CsvBindByPosition(position = 0)
    String uidEvent;

    @CsvBindByPosition(position = 1)
    String uidUser;

    @CsvBindByPosition(position = 2)
    String uidUserBis;

    @CsvDate(value = "dd/MM/yyyy HH:mm")
    @CsvBindByPosition(position = 3)
    LocalDate startDate;

    @CsvBindByPosition(position = 4)
    String uidActivity;
}
