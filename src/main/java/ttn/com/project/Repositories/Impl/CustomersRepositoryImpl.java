package ttn.com.project.Repositories.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ttn.com.project.Entity.Customers;
import ttn.com.project.Repositories.CustomersRepositoryCustom;
import ttn.com.project.dto.CustomersDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class CustomersRepositoryImpl implements CustomersRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<CustomersDto> getAllListCustomers(Pageable pageable) {
        List<CustomersDto> list = new ArrayList<>();
        Page<CustomersDto> page = new PageImpl<>(list, pageable, 0);
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM CUSTOMERS ORDER BY CUSTOMERS.ACCOUNT_NUMBER DESC");

            Query query = entityManager.createNativeQuery(sb.toString(), Customers.class);
            int total = 0;
            total = query.getResultList().size();
            if (pageable != null) {
                query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
                query.setMaxResults(pageable.getPageSize());
            }
            list = query.getResultList();
            page = new PageImpl<>(list, pageable, total);
        } catch (Exception ex) {
//            log.error(ex.getMessage(), ex);
        }
        return page;
    }
}
