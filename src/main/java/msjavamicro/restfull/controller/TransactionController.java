package msjavamicro.restfull.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import msjavamicro.restfull.model.CategoryResponse;
import msjavamicro.restfull.model.CreateTransactionRequest;
import msjavamicro.restfull.model.TransactionResponse;
import msjavamicro.restfull.model.WebResponse;
import msjavamicro.restfull.service.TransactionService;
import msjavamicro.restfull.entity.User;


@RestController
public class TransactionController {
 
    @Autowired
    private TransactionService transactionService;

    @PostMapping(
            path = "/api/category/{categoryId}/transaction",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<TransactionResponse> create(User user,
                                               @RequestBody CreateTransactionRequest request,
                                               @PathVariable("categoryId") String categoryId) {
        request.setCategoryId(categoryId);
        TransactionResponse transactionResponse = transactionService.create(user, request);
        return WebResponse.<TransactionResponse>builder().data(transactionResponse).build();
    }

    @GetMapping(
            path = "/api/transaction",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<TransactionResponse>> list(User user) {
        List<TransactionResponse> transactionResponses = transactionService.list(user);
        return WebResponse.<List<TransactionResponse>>builder().data(transactionResponses).build();

    }
}
