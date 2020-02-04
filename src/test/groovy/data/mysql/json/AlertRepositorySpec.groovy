package data.mysql.json

import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.runtime.config.SchemaGenerate
import io.micronaut.test.annotation.MicronautTest
import io.micronaut.test.support.TestPropertyProvider
import org.testcontainers.containers.MySQLContainer
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
class AlertRepositorySpec extends Specification implements TestPropertyProvider {

    @Shared
    @AutoCleanup
    MySQLContainer mysqlContainer = new MySQLContainer<>('mysql:5.7')

    @Override
    Map<String, String> getProperties() {
        mysqlContainer.start()

        return [
            'datasources.default.url'              : mysqlContainer.getJdbcUrl(),
            'datasources.default.username'         : mysqlContainer.getUsername(),
            'datasources.default.password'         : mysqlContainer.getPassword(),
            'datasources.default.schema-generate'  : SchemaGenerate.CREATE_DROP.name(),
            'datasources.default.dialect'          : Dialect.MYSQL.name(),
            'datasources.default.driver-class-name': 'com.mysql.cj.jdbc.Driver',
        ]
    }

    @Inject
    AlertRepository alertRepository

    void 'save and read an entity with json format works'() {
        given:
        AlertEntity alertEntity = new AlertEntity()
        alertEntity.sensorId = sensorId
        alertEntity.gps = gps

        when:
        alertRepository.save(alertEntity)

        then:
        alertEntity
        alertEntity.id

        when:
        Optional<AlertEntity> optReadAlertEntity = alertRepository.findById(alertEntity.id)

        then:
        optReadAlertEntity.isPresent()

        when:
        AlertEntity readAlertEntity = optReadAlertEntity.get()

        then:
        readAlertEntity.sensorId == sensorId
        readAlertEntity.gps == gps

        where:
        sensorId = 'ABC'
        gps = [lat: -40.123d, lng: 25.987d]
    }

    void 'save and read as DTO with json format works'() {
        given:
        AlertEntity alertEntity = new AlertEntity()
        alertEntity.sensorId = sensorId
        alertEntity.gps = gps

        when:
        alertRepository.save(alertEntity)

        then:
        alertEntity
        alertEntity.id

        when:
        Optional<Alert> optReadAlert = alertRepository.find(alertEntity.id)

        then:
        optReadAlert.isPresent()

        when:
        Alert readAlert = optReadAlert.get()

        then:
        readAlert.sensorId == sensorId
        readAlert.gps == gps

        where:
        sensorId = 'ABC'
        gps = [lat: -40.123d, lng: 25.987d]
    }
}
