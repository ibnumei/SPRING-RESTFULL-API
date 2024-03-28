package msjavamicro.restfull.repository;

import msjavamicro.restfull.entity.Category;
import msjavamicro.restfull.entity.DbCategory;
import msjavamicro.restfull.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepositoryV2 extends JpaRepository<DbCategory, Long>  {

    List<DbCategory> findByUserId(Long idUser);
}
