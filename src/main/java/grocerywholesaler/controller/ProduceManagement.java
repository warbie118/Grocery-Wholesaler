package grocerywholesaler.controller;

import grocerywholesaler.model.Produce;
import grocerywholesaler.model.ProducePriceOnly;
import grocerywholesaler.service.ProduceService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller for produce management
 */
@RestController
@RequestMapping(value = "/produce-management")
@Api(value="grocerywholesaler", description="Management of the produce data")
public class ProduceManagement {

    @Autowired
    ProduceService produceService;

    @RequestMapping(value = "/set", method = RequestMethod.POST)
    @ApiOperation(value = "Sets the produce data. If any existing data will replace it with input")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully set the produce"),
            @ApiResponse(code = 400, message = "Error setting the produce")
    })
    public ResponseEntity<List<Produce>> setProduce(@RequestBody List<Produce> produce){
        produceService.setData(produce);
        return new ResponseEntity<>(produce, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/setPrice", method = RequestMethod.PATCH)
    @ApiOperation(value = "Sets the price of the given produce by name")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully set new price"),
            @ApiResponse(code = 400, message = "Error setting price of produce. Produce not found")
    })
    public ResponseEntity<?> setProducePrice(@RequestBody ProducePriceOnly priceUpdate){
        Produce updatedProduce = produceService.updatePrice(priceUpdate);
        if (updatedProduce == null){
            return new ResponseEntity<>("Error setting price of produce. Produce not found", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(updatedProduce, HttpStatus.OK);
        }
    }
}
