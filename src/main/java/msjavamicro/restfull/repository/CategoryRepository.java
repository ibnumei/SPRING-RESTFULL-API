package msjavamicro.restfull.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import msjavamicro.restfull.entity.Category;
import msjavamicro.restfull.entity.User;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String>  {
    Optional<Category> findFirstByUserAndId(User user, String id);

    Optional<Category> findFirstByUserAndCategoryName(User user, String categoryName);
    
    List<Category> findAllByUser(User user);
}
