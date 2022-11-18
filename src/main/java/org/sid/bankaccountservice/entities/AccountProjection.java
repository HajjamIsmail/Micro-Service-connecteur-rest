package org.sid.bankaccountservice.entities;

import org.sid.bankaccountservice.enums.AccountType;
import org.springframework.data.rest.core.config.Projection;

//dans ce cas on vas faire une projetion des champs souhaité => avec annotation @Projection => en spécifiant la class
//pour y acceder à cette projection il faut mentionné l'url suivant : "http://localhost:8081/bankAccounts?projection=p1
@Projection(types = BankAccount.class, name="p1")
public interface AccountProjection {
    public String getId();
    public AccountType getType();
    public Double getBalance();
}
