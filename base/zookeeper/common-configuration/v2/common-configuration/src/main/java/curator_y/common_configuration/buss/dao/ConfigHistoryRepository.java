package curator_y.common_configuration.buss.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import curator_y.common_configuration.buss.entity.ConfigHistory;

@Repository
@Scope("prototype")
public interface ConfigHistoryRepository extends CrudRepository<ConfigHistory, Integer> {

}
