package grocerywholesaler.controller;

import grocerywholesaler.model.Produce;
import grocerywholesaler.service.ProduceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/data-management")
@Api(value="grocerywholesaler", description="Management of the produce data")
public class DataManagementController {

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
        return new ResponseEntity<List<Produce>>(produce, HttpStatus.CREATED);
    }
}
