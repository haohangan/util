package curator_y.common_configuration.buss.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import curator_y.common_configuration.buss.dao.ConfigHistoryRepository;
import curator_y.common_configuration.buss.dao.ConfigPageSortRepository;
import curator_y.common_configuration.buss.entity.Config;
import curator_y.common_configuration.buss.entity.ConfigHistory;

@Service
@Scope("prototype")
public class ConfigService {
   
   @Autowired
   private ConfigPageSortRepository repository; 
   
   @Autowired
   private ConfigHistoryRepository historyResposity;
   
   public Page<Config> get(Pageable pageable){
	   return repository.findAll(pageable);
   }
   
   public List<Config> get(String appName,Pageable pageable){
	   return repository.findByAppName(appName,pageable);
   }
   
   public void insert(Config config){
	   config.setDate(new Date());
	   repository.save(config);
	   ConfigHistory history = new ConfigHistory();
	   BeanUtils.copyProperties(config, history);
	   historyResposity.save(history);
   }
}
