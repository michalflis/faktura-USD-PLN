package faktura.USD.PLN.controller.computer;

import faktura.USD.PLN.model.Computer;
import faktura.USD.PLN.service.ComputerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;



@Slf4j
@CrossOrigin(origins = "https://festive-stonebraker-cdee15.netlify.app")
@RequiredArgsConstructor
@RestController
public class ComputerController implements ComputerControllerApi {

    private final ComputerService computerService;

    @Override
    public ResponseEntity<List<Computer>> getAll() {
        log.debug("Getting all computers from Database");
        return ResponseEntity.ok()
                .body(computerService.getAll());
    }

    @Override
    public ResponseEntity<List<Computer>> getById(@PathVariable String date) {
        log.debug("Getting computers from: {} from Database", date);
        try {
            return ResponseEntity.ok()
                    .body(computerService.getByDate(LocalDate.parse(date)));
        } catch (Exception e) {
            log.error("Exception: {} occurred while getting computers from: {} from Database", e, date);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
