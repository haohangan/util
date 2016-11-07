package org.eva.core.demo.service;

import java.util.List;

import org.eva.core.demo.entity.PriceHistory;
import org.eva.core.demo.vo.DemoSearchVO;

public interface DemoService {
  Iterable<PriceHistory> listAll();
  
  List<PriceHistory> search(DemoSearchVO svo);
}
