package data.mysql.json;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.validation.Validated;

import java.util.Optional;

@Validated
@JdbcRepository(dialect = Dialect.MYSQL)
public interface AlertRepository extends CrudRepository<AlertEntity, Long> {

    Optional<Alert> find(Long id);

}
