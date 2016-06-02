package microserver.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * ssh登陆日志对象
 * @author next
 *
 */
@Entity
@Table(name="ssh_login_log")
public class SSHLoginPO {
	/**
	 * 自增Id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	/**
	 * 登陆时间
	 */
	Date login_date;
	/**
	 * 域
	 */
	String domain;
	/**
	 * sshId
	 */
	Integer sshdId;
	/**
	 * 登陆用户名
	 */
	String userName;
	/**
	 * 来源IP
	 */
	String fromIp;
	/**
	 * 来源端口
	 */
	Integer fromPort;
	/**
	 * 状态 -1 失败 0 成功
	 */
	Integer status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getLogin_date() {
		return login_date;
	}

	public void setLogin_date(Date login_date) {
		this.login_date = login_date;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Integer getSshdId() {
		return sshdId;
	}

	public void setSshdId(Integer sshdId) {
		this.sshdId = sshdId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFromIp() {
		return fromIp;
	}

	public void setFromIp(String fromIp) {
		this.fromIp = fromIp;
	}


	public Integer getFromPort() {
		return fromPort;
	}

	public void setFromPort(Integer fromPort) {
		this.fromPort = fromPort;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
