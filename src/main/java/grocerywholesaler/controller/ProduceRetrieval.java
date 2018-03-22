package grocerywholesaler.controller;

import grocerywholesaler.model.Produce;
import grocerywholesaler.service.ProduceService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller for produce retrieval
 */
@RestController
@RequestMapping(value = "/produce-retrieval")
@Api(value="grocerywholesaler", description="Retrieval of the produce data")
public class ProduceRetrieval {

    @Autowired
    ProduceService produceService;

    @RequestMapping(value = "/getProduceByUpdatedDate/{order}", method = RequestMethod.GET)
    @ApiOperation(value = "Returns list of produce ordered ASC or DESC by updated date")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Returned ordered list of produce"),
            @ApiResponse(code = 400, message = "Error, not recognised order value. asc or desc are allowed values")
    })
    public ResponseEntity<?> getProduceOrderedByUpdatedDate(@ApiParam(value = "order" , required = true, allowableValues="ASC, DESC") @PathVariable(value = "order") String order){
        List<Produce> produceList = produceService.getProduceByUpdatedDate(order);
        if (produceList.isEmpty()){
            return new ResponseEntity<>("Error, not recognised order value. asc or desc are allowed values", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(produceService.getProduceByUpdatedDate(order), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/searchByName/{name}", method = RequestMethod.GET)
    @ApiOperation(value = "Returns list of produce ordered ASC or DESC by updated date")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Search results returned"),
    })
    public ResponseEntity<List<Produce>> getProduceByName(@PathVariable(value = "name") String name){
        return new ResponseEntity<>(produceService.searchProduceByName(name), HttpStatus.OK);
    }
}
