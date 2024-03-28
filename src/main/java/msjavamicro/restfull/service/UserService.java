package msjavamicro.restfull.service;

import java.util.Objects;

import msjavamicro.restfull.entity.DbUser;
import msjavamicro.restfull.repository.UserRepositoryV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.transaction.annotation.Transactional;

import msjavamicro.restfull.entity.Category;
import msjavamicro.restfull.entity.User;
import msjavamicro.restfull.model.CategoryResponse;
import msjavamicro.restfull.model.CreateCategoryRequest;
import msjavamicro.restfull.model.CreateTransactionRequest;
import msjavamicro.restfull.model.RegisterUserRequest;
import msjavamicro.restfull.model.UpdateUserBalanceRequest;
import msjavamicro.restfull.model.UserResponse;
import msjavamicro.restfull.repository.CategoryRepository;
import msjavamicro.restfull.repository.UserRepository;
import msjavamicro.restfull.security.BCrypt;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRepositoryV2 userRepositoryV2;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private  TransactionService transactionService;

    @Transactional
    public void register(RegisterUserRequest request) {
        validationService.validate(request);

        if (userRepository.existsById(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already registered");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        user.setName(request.getName());
        user.setBalance(0);

        userRepository.save(user);

        CreateCategoryRequest category = new CreateCategoryRequest();
        category.setCategoryName("TopUp");
        CategoryResponse categoryResponse = categoryService.create(user, category);

    }

    @Transactional
    public void registerV2(RegisterUserRequest request) {
        validationService.validate(request);

        if (userRepositoryV2.existsById(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already registered");
        }

        DbUser user = DbUser.builder()
                .username(request.getUsername())
                .password(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()))
                .name(request.getName())
                .balance(0)
                .build();
        userRepositoryV2.save(user);

        CreateCategoryRequest category = new CreateCategoryRequest();
        category.setCategoryName("TopUp");
        CategoryResponse categoryResponse = categoryService.create(user, category);

    }

    public UserResponse get(User user) {
        return UserResponse.builder()
                .username(user.getUsername())
                .name(user.getName())
                .balance(user.getBalance())
                .build();
    }

    @Transactional
    public UserResponse update(User user, UpdateUserBalanceRequest request) {
        validationService.validate(request);

        Category category = categoryRepository.findFirstByUserAndCategoryName(user, "TopUp")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category TopUp Tidak ditemukan untuk user ini"));
                
        CreateTransactionRequest transaction = new CreateTransactionRequest();
        transaction.setCategoryId(category.getId());
        transaction.setType("credit");
        transaction.setAmount(request.getBalance());
        transaction.setDescription("TopUp Balance");
        transactionService.create(user, transaction);

        return UserResponse.builder()
                .username(user.getUsername())
                .name(user.getName())
                .balance(user.getBalance())
                .build();
    }
}
