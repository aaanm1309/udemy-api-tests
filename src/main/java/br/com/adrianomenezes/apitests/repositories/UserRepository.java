package br.com.adrianomenezes.apitests.repositories;

import br.com.adrianomenezes.apitests.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
