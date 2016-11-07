package org.eva.core.demo.vo;

import org.eva.core.common.vo.BaseSearchVO;
import org.hibernate.validator.constraints.NotBlank;

public class DemoSearchVO extends BaseSearchVO{
	@NotBlank(message="菜名不能为空啊")
    private String itemName;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
    
    
}
