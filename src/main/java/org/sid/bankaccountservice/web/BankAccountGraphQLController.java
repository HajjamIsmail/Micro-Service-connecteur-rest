package org.sid.bankaccountservice.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.bankaccountservice.dto.BankAccountRequestDTO;
import org.sid.bankaccountservice.dto.BankAccountResponseDTO;
import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.repositories.BankAccountRepository;
import org.sid.bankaccountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BankAccountGraphQLController {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountService accountService;

    //Methode qui retourne une liste des bankAccount
    @QueryMapping//quand on a une méthode de type query => automatiquement vas executer la méthode(accountsList)
    public List<BankAccount> accountsList(){
        return bankAccountRepository.findAll();
    }

    //Methode qui retounr un bankAccount dans le id fournit dans le paramatre
    @QueryMapping//quand on a une méthode de type query => automatiquement vas executer la méthode(accountsList)
    public BankAccount bankAccountById(@Argument String id){
        //s'il existe vas retourner un account
        //sinon vas générer une exception
        return bankAccountRepository.findById(id).orElseThrow(()->new RuntimeException(String.format("Account %s not found",id)));
    }

    //MutationMapping => dédié pr les operations de modification => update, insert, delete

    //Methode qui ajoute un bankAccount
    @MutationMapping
    public BankAccountResponseDTO addAccount(@Argument BankAccountRequestDTO bankAccountRequestDTO){
        return accountService.addAccount(bankAccountRequestDTO);
    }

    @MutationMapping
    public BankAccountResponseDTO updateAccount(@Argument String id, @Argument BankAccountRequestDTO bankAccountRequestDTO){
        return accountService.updateAccount(id, bankAccountRequestDTO);
    }

    @MutationMapping
    public void deleteAccount(@Argument String id){
        bankAccountRepository.deleteById(id);
    }
}



