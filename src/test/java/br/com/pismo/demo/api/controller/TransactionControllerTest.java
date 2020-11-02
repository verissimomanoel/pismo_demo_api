/*
 * TransactionControllerTest.java
 *
 * This is a free software.
 */
package br.com.pismo.demo.api.controller;

import br.com.pismo.demo.api.account.Account;
import br.com.pismo.demo.api.account.AccountController;
import br.com.pismo.demo.api.account.AccountRepository;
import br.com.pismo.demo.api.security.ClaimResolve;
import br.com.pismo.demo.api.security.KeyToken;
import br.com.pismo.demo.api.security.Token;
import br.com.pismo.demo.api.security.TokenBuilder;
import br.com.pismo.demo.api.transaction.OperationType;
import br.com.pismo.demo.api.transaction.Transaction;
import br.com.pismo.demo.api.transaction.TransactionRepository;
import br.com.pismo.demo.api.transaction.TransactionTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Implementation test for controller class {@link AccountController}.
 *
 * @author Manoel Veríssimo dos Santos Neto
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class TransactionControllerTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private KeyToken keyToken;

    @MockBean
    private TransactionRepository transactionRepository;

    @MockBean
    private AccountRepository accountRepository;

    @BeforeEach
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .apply(springSecurity()).build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void whenCreate_thenReturnTransaction()
            throws Exception {

        TransactionTO transactionTO = getTransactionTOMock(OperationType.PAGAMENTO);
        Transaction transaction = getTransaction(OperationType.PAGAMENTO);
        Account account = getAccountMock();
        String token = getToken();

        String id = "123";
        when(accountRepository.findById(id)).thenReturn(Optional.of(account));
        when(transactionRepository.save(transaction)).thenReturn(transaction);

        mvc.perform(post("/api/v1/transactions").with(csrf()).header("Authorization", token)
                .content(asJsonString(transactionTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private String getToken() {
        TokenBuilder builder = new TokenBuilder(keyToken);
        builder.addName("Name");
        builder.addLogin("email@gmail.com");
        builder.addParam(ClaimResolve.PARAM_USER, "123");

        Token accessToken = builder.buildAccess();

        return "Bearer " + accessToken.getValue();
    }

    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Return an instance of transactionTO.
     *
     * @return
     */
    private TransactionTO getTransactionTOMock(OperationType operationType) {
        TransactionTO transactionTO = new TransactionTO();
        transactionTO.setOperationType(operationType.getId());
        transactionTO.setAmount(BigDecimal.valueOf(1000));
        transactionTO.setAccountId(getAccountMock().getId());

        return transactionTO;
    }

    /**
     * Return an instance of transactionTO.
     *
     * @return
     */
    private Transaction getTransaction(OperationType operationType) {
        Transaction transaction = new Transaction();
        transaction.setOperationType(operationType);
        transaction.setAmount(BigDecimal.valueOf(1000));

        return transaction;
    }

    /**
     * Return an instance of account.
     *
     * @return
     */
    private Account getAccountMock() {
        Account account = new Account();
        account.setId("123");
        account.setDocumentNumber("112233");

        return account;
    }
}
