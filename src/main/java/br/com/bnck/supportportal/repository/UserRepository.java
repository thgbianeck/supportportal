package br.com.bnck.supportportal.repository;

import br.com.bnck.supportportal.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Criado utilizando IntelliJ IDEA.
 * Projeto: supportportal
 * Usuário: Thiago Bianeck (Bianeck)
 * Data: 22/02/2022
 * Modificado: 16/03/2022
 * Hora: 23:43
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);

    User findUserByEmail(String email);
}
