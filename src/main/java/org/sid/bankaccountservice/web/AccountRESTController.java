package org.sid.bankaccountservice.web;

import org.sid.bankaccountservice.dto.BankAccountRequestDTO;
import org.sid.bankaccountservice.dto.BankAccountResponseDTO;
import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.mappers.AccountMapper;
import org.sid.bankaccountservice.repositories.BankAccountRepository;
import org.sid.bankaccountservice.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")//=>pr acceder a ce web service => /api/bankAccount
public class AccountRESTController {
    private BankAccountRepository bankAccountRepository;
    private AccountService accountService;
    private AccountMapper accountMapper;

    //faire l'injection des depandance par le "constructor" ou bien avec l'annotation "@Autowired"
    public AccountRESTController(BankAccountRepository bankAccountRepository, AccountService accountService, AccountMapper accountMapper){
        this.bankAccountRepository=bankAccountRepository;
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    //creer Rest api
    // retourne liste des compte:
    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts(){
        return bankAccountRepository.findAll();
    }
    // retourne compte donnée id:
    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccounts(@PathVariable String id){
        return bankAccountRepository.findById(id).orElseThrow(()->new RuntimeException(String.format("Account %s not found",id)));
    }
    //save account :
    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO){
        return accountService.addAccount(requestDTO);
    }
    //Update Account :
    @PutMapping("/bankAccounts/{id}")//modifier ts les attributs
    public BankAccount update(@PathVariable String id, @RequestBody BankAccount bankAccount){
        BankAccount account = bankAccountRepository.findById(id).orElseThrow();
        if(account.getBalance()!=null)
            account.setBalance(bankAccount.getBalance());
        if(account.getCreateAt()!=null)
            account.setCreateAt(new Date());
        if(account.getType()!=null)
            account.setType(bankAccount.getType());
        if(account.getCurrency()!=null)
            account.setCurrency(bankAccount.getCurrency());
        return bankAccountRepository.save(account);
    }
    //Delete Account :
    @DeleteMapping("/bankAccouts/{id}")
    public void DeleteAccount(@PathVariable String id){
        bankAccountRepository.deleteById(id);
    }
}
