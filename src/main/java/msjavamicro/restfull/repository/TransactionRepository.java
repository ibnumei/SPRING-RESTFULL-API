package msjavamicro.restfull.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import msjavamicro.restfull.entity.Transaction;
import msjavamicro.restfull.entity.User;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String>{
    
    List<Transaction> findAllByUser(User user);
}
