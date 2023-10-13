package msjavamicro.restfull.service;

import java.security.Timestamp;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import msjavamicro.restfull.entity.Category;
import msjavamicro.restfull.entity.Transaction;
import msjavamicro.restfull.entity.User;
import msjavamicro.restfull.model.TransactionResponse;
import msjavamicro.restfull.model.CategoryResponse;
import msjavamicro.restfull.model.CreateCategoryRequest;
import msjavamicro.restfull.model.CreateTransactionRequest;
import msjavamicro.restfull.repository.CategoryRepository;
import msjavamicro.restfull.repository.TransactionRepository;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public TransactionResponse create(User user, CreateTransactionRequest request) {
        validationService.validate(request);

        Category category = categoryRepository.findFirstByUserAndId(user, request.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        Transaction transaction = new Transaction();
        transaction.setId(UUID.randomUUID().toString());
        transaction.setCategory(category);
        transaction.setUser(user);
        transaction.setType(request.getType());
        transaction.setAmount(request.getAmount());
        transaction.setDescription(request.getDescription());
        transactionRepository.save(transaction);

        return toTransactionResponse(transaction);
    }

    private TransactionResponse toTransactionResponse(Transaction transaction) {
        return TransactionResponse.builder()
                .id(transaction.getId())
                .type(transaction.getType())
                .amount(transaction.getAmount())
                .description(transaction.getDescription())
                .build();
    }

    @Transactional(readOnly = true)
    public List<TransactionResponse> list(User user){

        List<Transaction> transactions = transactionRepository.findAllByUser(user);
        return transactions.stream().map(this::toTransactionResponse).toList();
    }
}
