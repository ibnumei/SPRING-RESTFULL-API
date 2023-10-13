package msjavamicro.restfull.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import msjavamicro.restfull.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
