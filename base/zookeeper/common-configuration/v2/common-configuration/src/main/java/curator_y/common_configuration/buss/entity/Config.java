package curator_y.common_configuration.buss.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "CONFIG")
public class Config implements Serializable {
	private static final long serialVersionUID = 7374050604919167825L;
    
	private Integer id;
	
	@NotBlank(message="服务名称不能为空")
	private String name;// 命名
	
	@NotBlank(message="服务url不能为空")
	private String url;// url
	private int type;// 0：http|1：rpc
	
	@NotBlank(message="服务app不能为空")
	private String appName;// 属于哪个app
	
	private String previlege;// 权限验证

	private Date date;// 创建日期

	@Id
	@GeneratedValue//自增长 AUTO_INCREMENT
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getPrevilege() {
		return previlege;
	}

	public void setPrevilege(String previlege) {
		this.previlege = previlege;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
