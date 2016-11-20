package curator_y.common_configuration.buss.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import curator_y.common_configuration.buss.entity.Config;

@Repository
@Scope("prototype")
public interface ConfigPageSortRepository extends PagingAndSortingRepository<Config, Integer> {
       List<Config> findByAppName(String appName,Pageable pageable);
       
}
