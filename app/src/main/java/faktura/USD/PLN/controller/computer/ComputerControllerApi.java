package faktura.USD.PLN.controller.computer;

import faktura.USD.PLN.model.Computer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/computers", produces = {"application/json;charset=UTF-8"})
@Api(tags = {"invoice-controller"})
public interface ComputerControllerApi {

    @ApiOperation("Get list of all computers")
    @GetMapping
    ResponseEntity<List<Computer>> getAll();

    @ApiOperation("Get list of all computers from date")
    @GetMapping(path = "/{date}")
    ResponseEntity<List<Computer>> getById(@PathVariable String date);

}
