package decimal.test.repository;

import decimal.test.domain.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface TestRepo extends JpaRepository<Test,Integer> {

    List<Test> findByName(String name);

}
