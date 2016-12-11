package drop.level.module.user.repository;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import drop.level.module.user.entity.DLUser;

@Repository
@Scope("prototype")
public interface DLUserRepository extends CrudRepository<DLUser, String> {

	DLUser findByUserName(String userName);
}
