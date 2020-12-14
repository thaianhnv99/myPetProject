package ttn.com.project.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ttn.com.project.dto.CustomersDto;

public interface CustomersRepositoryCustom {
    Page<CustomersDto> getAllListCustomers(Pageable pageable);
}
