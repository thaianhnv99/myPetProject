package ttn.com.project.Services;

import ttn.com.project.dto.BaseResponse;
import ttn.com.project.dto.CustomersDto;
import ttn.com.project.dto.GetListDataResponse;
import ttn.com.project.dto.GetSingleDataResponse;

public interface CustomersService {
    GetListDataResponse<CustomersDto> getAllListAccount(CustomersDto customersDto);

    GetSingleDataResponse<CustomersDto> getDataById(CustomersDto customersDto);

    GetSingleDataResponse<CustomersDto> createCustomers(CustomersDto customersDto);

    GetSingleDataResponse<CustomersDto> updateCustomers(CustomersDto customersDto);

    BaseResponse deleteCustomers(CustomersDto customersDto);
}
