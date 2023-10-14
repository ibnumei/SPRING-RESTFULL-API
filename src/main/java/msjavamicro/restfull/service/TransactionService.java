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

import lombok.extern.slf4j.Slf4j;
import msjavamicro.restfull.entity.Category;
import msjavamicro.restfull.entity.Transaction;
import msjavamicro.restfull.entity.User;
import msjavamicro.restfull.model.TransactionResponse;
import msjavamicro.restfull.model.CategoryResponse;
import msjavamicro.restfull.model.CreateCategoryRequest;
import msjavamicro.restfull.model.CreateTransactionRequest;
import msjavamicro.restfull.model.TransactionHistoryRequest;
import msjavamicro.restfull.repository.CategoryRepository;
import msjavamicro.restfull.repository.TransactionRepository;
import msjavamicro.restfull.repository.UserRepository;

@Service
@Slf4j
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private UserRepository userRepository;

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

        if (request.getType().equals("debit")) {
            if (user.getBalance() > request.getAmount()) {
                user.setBalance(user.getBalance() - request.getAmount());
                userRepository.save(user);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nilai Pengeluaran lebih besar dari total balance saat ini");
            }
        } else {
            user.setBalance(request.getAmount() + user.getBalance());
            userRepository.save(user);
        }
        

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

    @Transactional(readOnly = true)
    public List<Object[]> getHistoricalTransaction(User user, TransactionHistoryRequest request)  {
        // Category category = categoryRepository.findFirstByUserAndId(user, request.getCategoryId())
        //         .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        List<Object[]> result = transactionRepository.historicalTransaction(request.getStartDate(), request.getEndDate(), user.getUsername());
        return result;
    }

}
