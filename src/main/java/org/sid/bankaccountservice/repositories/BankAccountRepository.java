package org.sid.bankaccountservice.repositories;

import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource//demande a spring au demarrage de lancer un web service rest ful qui permet de gerer une entite de type "BankAcount" => c-a-d crud
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
    //chercher ts les compte qui ont le meme Account type :
    //cette annotation permet d'appeler la methode par path et non pas par son nom
    //pour y acceder tapez l'url : http://localhost:8081/bankAccounts/search/byType?t=CURRENT_ACCOUNT
    @RestResource(path = "/byType")
    List<BankAccount> findByType(@Param("t") AccountType type);
}
