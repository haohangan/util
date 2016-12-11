package drop.level.module.user.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "dl_user", schema = "text")
public class DLUser  implements Serializable {
	private static final long serialVersionUID = -3048642230233161189L;
	
	private String userId;
	private String userName;
	private String password;

	private Date joinDate;

	@Id
	@GeneratedValue(generator="system_uuid")//strategy=GenerationType.SEQUENCE,
	@GenericGenerator(name="system_uuid",strategy="uuid")
	@Column(name="user_id")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name="user_name",length=100,unique=true)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name="password",length=100)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="join_date")
	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

}
