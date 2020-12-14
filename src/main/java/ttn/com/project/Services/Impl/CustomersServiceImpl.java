package ttn.com.project.Services.Impl;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ttn.com.project.Entity.Customers;
import ttn.com.project.Repositories.CustomersRepository;
import ttn.com.project.Repositories.CustomersRepositoryCustom;
import ttn.com.project.Services.CustomersService;
import ttn.com.project.dto.BaseResponse;
import ttn.com.project.dto.CustomersDto;
import ttn.com.project.dto.GetListDataResponse;
import ttn.com.project.dto.GetSingleDataResponse;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class CustomersServiceImpl implements CustomersService {
    Logger log = LoggerFactory.getLogger(CustomersServiceImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomersRepositoryCustom customersRepositoryCustom;

    @Autowired
    private CustomersRepository customersRepository;

    @Override
    public GetListDataResponse<CustomersDto> getAllListAccount(CustomersDto customersDto) {
        GetListDataResponse<CustomersDto> getListDataResponse = new GetListDataResponse<>();
        try {
            Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);
            if (customersDto.getPageNumber() != null && customersDto.getPageSize() != null) {
                pageable = PageRequest.of(customersDto.getPageNumber(), customersDto.getPageSize());
            }
            Page<CustomersDto> page = customersRepositoryCustom.getAllListCustomers(pageable);
            getListDataResponse.setResult(page.getContent(), page.getTotalElements(), page.getTotalPages());
        } catch (Exception e) {
            getListDataResponse.setFailed();
        }
        return getListDataResponse;
    }

    @Override
    public GetSingleDataResponse<CustomersDto> getDataById(CustomersDto customersDto) {
        GetSingleDataResponse<CustomersDto> getSingleDataResponse = new GetSingleDataResponse<>();
        try {
            if (customersDto != null) {
                Customers customers = customersRepository.findById(customersDto.getId());
                if (customers != null) {
                    getSingleDataResponse.setResult(modelMapper.map(customers, CustomersDto.class));
                } else {
                    getSingleDataResponse.setResult(null);
                }
            }
        } catch (Exception e) {
            getSingleDataResponse.setResult(null);
        }
        return getSingleDataResponse;
    }

    @Override
    public GetSingleDataResponse<CustomersDto> createCustomers(CustomersDto customersDto) {
        GetSingleDataResponse<CustomersDto> getSingleDataResponse = new GetSingleDataResponse<>();

        try {
            Customers customersByEmail = customersRepository.findByEmail(customersDto.getEmail());
            if (customersByEmail != null) {
                getSingleDataResponse.setFailed("003", "Email đã tồn tại trong hệ thống");
            } else {
                Customers customersSave = customersRepository.save(modelMapper.map(customersDto, Customers.class));
                getSingleDataResponse.setResult(modelMapper.map(customersSave, CustomersDto.class));
            }
        } catch (Exception e) {
            getSingleDataResponse.setFailed();
        }
        return getSingleDataResponse;
    }

    @Override
    public GetSingleDataResponse<CustomersDto> updateCustomers(CustomersDto customersDto) {
        GetSingleDataResponse<CustomersDto> getSingleDataResponse = new GetSingleDataResponse<>();
        if (StringUtils.isNotEmpty(String.valueOf(customersDto.getAccountNumber()))) {
            try {
                Customers customers = customersRepository.findById(customersDto.getId());
                Customers customersByEmail = customersRepository.findByEmail(customersDto.getEmail());
                if (customers == null) {
                    getSingleDataResponse.setFailed("003", "Account không tồn tại trong hệ thống");
                } else if (customersByEmail != null && !customersByEmail.getEmail().equals(customers.getEmail())) {
                    getSingleDataResponse.setFailed("003", "Email đã tồn tại trong hệ thống");
                } else {
                    Customers customersSave = customersRepository.save(modelMapper.map(customersDto, Customers.class));
                    getSingleDataResponse.setResult(modelMapper.map(customersSave, CustomersDto.class));
                }
            } catch (Exception e) {
                getSingleDataResponse.setFailed();
            }
        } else {
            getSingleDataResponse.setFailed();
        }
        return getSingleDataResponse;
    }

    @Override
    public BaseResponse deleteCustomers(CustomersDto customersDto) {
        BaseResponse baseResponse = new BaseResponse();
        if (StringUtils.isNotEmpty(String.valueOf(customersDto.getAccountNumber()))) {
            try {
                Customers customers = customersRepository.findById(customersDto.getId());
                if (customers != null) {
                    customersRepository.deleteByCustomers(customersDto.getId());
                    baseResponse.setSuccess();
                } else {
                    baseResponse.setFailed("002", "Không tồn tại account");
                    return baseResponse;
                }
            } catch (Exception e) {
                baseResponse.setFailed();
                log.error(e.getMessage());
            }
        } else {
            baseResponse.setFailed("003", "Account number is empty");
        }
        return baseResponse;
    }
}
