package org.eva.core.demo.dao;

import java.util.List;

import org.eva.core.demo.entity.PriceHistory;
import org.springframework.data.repository.CrudRepository;

public interface PriceHistoryRepository extends CrudRepository<PriceHistory, Long> {
	List<PriceHistory> findByitemName(String ItemName);
}
