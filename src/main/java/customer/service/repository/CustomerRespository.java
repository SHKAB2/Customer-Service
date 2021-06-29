package customer.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import customer.service.entity.Customer;

@Repository
public interface CustomerRespository extends JpaRepository<Customer, Long> {

	

}
