package msjavamicro.restfull.repository;

import msjavamicro.restfull.entity.DbUser;
import msjavamicro.restfull.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryV2 extends JpaRepository<DbUser, String> {

    Optional<DbUser> findFirstByToken(String token);
}
