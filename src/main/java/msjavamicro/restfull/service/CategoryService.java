package msjavamicro.restfull.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.transaction.annotation.Transactional;
import msjavamicro.restfull.entity.Category;
import msjavamicro.restfull.entity.User;
import msjavamicro.restfull.model.CategoryResponse;
import msjavamicro.restfull.model.CreateCategoryRequest;
import msjavamicro.restfull.repository.CategoryRepository;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public CategoryResponse create(User user, CreateCategoryRequest request) {
        validationService.validate(request);

        Category category = new Category();
        category.setId(UUID.randomUUID().toString());
        category.setCategoryName(request.getCategoryName());
        category.setUser(user);

        categoryRepository.save(category);

        return toCategoryResponse(category);
    }

    private CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .build();
    }

    @Transactional(readOnly = true)
    public CategoryResponse get(User user, String id) {
        Category category = categoryRepository.findFirstByUserAndId(user, id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        return toCategoryResponse(category);
    }

    @Transactional(readOnly = true)
    public List<CategoryResponse> list(User user){

        List<Category> categories = categoryRepository.findAllByUser(user);
        return categories.stream().map(this::toCategoryResponse).toList();
    }
}
