package ttn.com.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ttn.com.project.Services.CustomersService;
import ttn.com.project.dto.BaseResponse;
import ttn.com.project.dto.CustomersDto;
import ttn.com.project.dto.GetListDataResponse;
import ttn.com.project.dto.GetSingleDataResponse;

@RestController
@CrossOrigin
@RequestMapping("/customers")
public class CustomersController {

    @Autowired
    private CustomersService customersService;

    @PostMapping("/getAllList")
    public ResponseEntity<?> getAllListAccount(@RequestBody CustomersDto customersDto) {
        GetListDataResponse<CustomersDto> getListDataResponse = new GetListDataResponse<>();
        getListDataResponse = customersService.getAllListAccount(customersDto);
        return new ResponseEntity<>(getListDataResponse, HttpStatus.OK);
    }

    @PostMapping("/getInfoById")
    public ResponseEntity<?> getInfoById(@RequestBody CustomersDto customersDto) {
        GetSingleDataResponse<CustomersDto> getSingleDataResponse = new GetSingleDataResponse<>();
        getSingleDataResponse = customersService.getDataById(customersDto);
        return new ResponseEntity<>(getSingleDataResponse, HttpStatus.OK);
    }

    @PostMapping("/createCustomers")
    public ResponseEntity<?> createAccount(@RequestBody CustomersDto customersDto) {
        GetSingleDataResponse<CustomersDto> getSingleDataResponse = new GetSingleDataResponse<>();
        getSingleDataResponse = customersService.createCustomers(customersDto);
        return new ResponseEntity<>(getSingleDataResponse, HttpStatus.OK);
    }

    @PostMapping("/updateCustomers")
    public ResponseEntity<?> updateAccount(@RequestBody CustomersDto customersDto) {
        GetSingleDataResponse<CustomersDto> getSingleDataResponse = new GetSingleDataResponse<>();
        getSingleDataResponse = customersService.updateCustomers(customersDto);
        return new ResponseEntity<>(getSingleDataResponse, HttpStatus.OK);
    }

    @PostMapping("/deleteById")
    public ResponseEntity<?> deleteByAccountNumber(@RequestBody CustomersDto customersDto) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse = customersService.deleteCustomers(customersDto);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
