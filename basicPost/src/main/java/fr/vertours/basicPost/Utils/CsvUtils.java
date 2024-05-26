package fr.vertours.basicPost.Utils;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
public class CsvUtils {

    private CsvUtils() {throw new IllegalAccessError("Utility class");}

    public static <T> List<T> csvToObjects(byte[] csv, Class<? extends T> type) {
        if (csv == null || csv.length == 0) {
            throw new IllegalArgumentException("CSV content is null or empty");
        }
        CSVReader reader = new CSVReaderBuilder(
                new InputStreamReader(new ByteArrayInputStream(csv), StandardCharsets.UTF_8))
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                .withSkipLines(1)
                .build();
        List<T> parsedDto = new CsvToBeanBuilder<T>(reader)
                .withType(type)
                .withSkipLines(1)
                .build().parse();
        log.debug("Generation of a Dto List, with value : {}", parsedDto.toString());
        return parsedDto;

    }
}
