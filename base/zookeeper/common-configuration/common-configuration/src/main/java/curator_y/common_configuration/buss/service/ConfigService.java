package curator_y.common_configuration.buss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import curator_y.common_configuration.buss.dao.ConfigRepository;
import curator_y.common_configuration.buss.entity.Config;

@Service
@Scope("prototype")
public class ConfigService {
   @Autowired
   private ConfigRepository repository; 
   
   public Iterable<Config> get(){
	   return repository.findAll();
   }
}
