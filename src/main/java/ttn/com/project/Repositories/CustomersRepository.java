package ttn.com.project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ttn.com.project.Entity.Customers;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CustomersRepository extends JpaRepository<Customers, Integer> {
    Customers findById(int accountNumber);

    Customers findByEmail(String email);

    @Modifying
    @Query(value = "delete from customers where id = :id", nativeQuery = true)
    int deleteByCustomers(@Param("id") int id);
}
