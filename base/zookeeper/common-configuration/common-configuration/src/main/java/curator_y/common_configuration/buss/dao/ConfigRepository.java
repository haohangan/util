package curator_y.common_configuration.buss.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import curator_y.common_configuration.buss.entity.Config;

@Repository
@Scope("prototype")
public interface ConfigRepository extends CrudRepository<Config, Integer> {

}
