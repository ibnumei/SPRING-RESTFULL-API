package msjavamicro.restfull.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import msjavamicro.restfull.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String>{
    
}
