package org.sid.bankaccountservice.service;

import org.sid.bankaccountservice.dto.BankAccountRequestDTO;
import org.sid.bankaccountservice.dto.BankAccountResponseDTO;
import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.mappers.AccountMapper;
import org.sid.bankaccountservice.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional//utiliser l'annotation de spring => ca veux dire ttes les methodes sont transactionnel
public class AccountServiceImpl implements AccountService{
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountMapper accountMapper;
    //transferer les objet dto vers l'entite
    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {
        BankAccount bankAccount=BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createAt(new Date())
                .balance(bankAccountDTO.getBalance())
                .type(bankAccountDTO.getType())
                .currency(bankAccountDTO.getCurrency())
                .build();
        //enregistre bankAccountResponseDTO et le recuperer
        BankAccount saveBankAcctount = bankAccountRepository.save(bankAccount);
        //mapping entre les objets
        BankAccountResponseDTO bankAccountResponseDTO = accountMapper.fromBankAccount(saveBankAcctount);
        return bankAccountResponseDTO;
    }

    @Override
    public BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO) {
        BankAccount bankAccount=BankAccount.builder()
                .id(id)
                .createAt(new Date())
                .balance(bankAccountDTO.getBalance())
                .type(bankAccountDTO.getType())
                .currency(bankAccountDTO.getCurrency())
                .build();
        //enregistre bankAccountResponseDTO et le recuperer
        BankAccount saveBankAcctount = bankAccountRepository.save(bankAccount);
        //mapping entre les objets
        BankAccountResponseDTO bankAccountResponseDTO = accountMapper.fromBankAccount(saveBankAcctount);
        return bankAccountResponseDTO;
    }
}
