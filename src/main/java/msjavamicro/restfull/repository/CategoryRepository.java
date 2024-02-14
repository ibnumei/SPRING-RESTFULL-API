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

    // // Mencari kategori pertama dengan nama "Belanja"
    // Optional<Category> shoppingCategory = categoryRepository.findFirstByUserAndCategoryName(user, "Belanja");

    // if (shoppingCategory.isPresent()) {
    //     // Kategori ditemukan, lakukan sesuatu dengan shoppingCategory.get()
    // } else {
    //     // Kategori tidak ditemukan, lakukan tindakan alternatif
    // }

    // // Mencari semua kategori milik pengguna
    // List<Category> allCategories = categoryRepository.findAllByUser(user);

    // if (allCategories.isEmpty()) {
    //     // Tidak ada kategori ditemukan
    // } else {
    //     // Tampilkan atau proses semua kategori dalam allCategories
    // }
}
