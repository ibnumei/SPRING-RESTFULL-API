package msjavamicro.restfull.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import msjavamicro.restfull.entity.User;
import msjavamicro.restfull.model.CategoryResponse;
import msjavamicro.restfull.model.CreateCategoryRequest;
import msjavamicro.restfull.model.WebResponse;
import msjavamicro.restfull.service.CategoryService;

@RestController
@EnableCaching
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping(
            path = "/api/category",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<CategoryResponse> create(User user, @RequestBody CreateCategoryRequest request) {
        CategoryResponse categoryResponse = categoryService.create(user, request);
        return WebResponse.<CategoryResponse>builder().data(categoryResponse).build();
    }

    @GetMapping(
            path = "/api/category/{categoryId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Cacheable(value = "category", key = "#categoryId")
    public WebResponse<CategoryResponse> get(User user, @PathVariable("categoryId") String categoryId) {
        CategoryResponse categoryResponse = categoryService.get(user, categoryId);
        return WebResponse.<CategoryResponse>builder().data(categoryResponse).build();
    }

    @GetMapping(
            path = "/api/category",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Cacheable(value = "all_category", key = "#user.username")
    public WebResponse<List<CategoryResponse>> list(User user) {
        List<CategoryResponse> categoryResponse = categoryService.list(user);
        return WebResponse.<List<CategoryResponse>>builder().data(categoryResponse).build();

    }
}
