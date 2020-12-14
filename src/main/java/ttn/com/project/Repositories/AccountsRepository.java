package ttn.com.project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ttn.com.project.Entity.Accounts;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, String> {

    Accounts findByUsername(String username);
}
