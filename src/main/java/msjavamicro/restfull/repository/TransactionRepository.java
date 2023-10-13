package msjavamicro.restfull.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import msjavamicro.restfull.entity.Transaction;
import msjavamicro.restfull.entity.User;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String>{

    List<Transaction> findAllByUser(User user);

    @Query(value = "SELECT t.username AS username, c.category_name AS category_name, t.type AS type, t.amount AS amount, t.description AS description, t.created_date AS created_date " +
                    "FROM transaction t INNER JOIN category c ON c.id = t.category_id " +
                    "WHERE t.created_date BETWEEN :param1 AND :param2 AND t.username = :param3", nativeQuery = true)
    List<Object[]> historicalTransaction(String param1, String param2, String param3);
        
}
