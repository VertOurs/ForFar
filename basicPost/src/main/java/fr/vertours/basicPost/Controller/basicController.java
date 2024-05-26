package fr.vertours.basicPost.Controller;

import fr.vertours.basicPost.annotation.CsvToObjects;
import fr.vertours.basicPost.dto.VacanceDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("basic")
public class basicController {

    @PostMapping(value = "/validate", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Void> postController(@RequestParam(name = "PLANNING") String planning,
                                               @CsvToObjects(keyClass = VacanceDto.class) List<VacanceDto> vacanceDtos) {
        log.info("{} passed by validate endpoint", planning);
        log.info("dtos size : {}", vacanceDtos.size());
        vacanceDtos.forEach(vacanceDto -> log.info("dto: {}", vacanceDto));

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/welcome")
    public ResponseEntity<String> welcome(@RequestParam(name = "NAME", defaultValue = "Old Boy") String name) {
        log.info("{} passed by welcome endpoint", name);
        return ResponseEntity.ok("Welcome " + name);
    }




}
