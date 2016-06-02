package microserver.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import microserver.entity.SSHLoginPO;
import microserver.repository.SSHLoginReposity;

@Service("sshLoginService")
public class SSHLoginService {
	
	@Autowired
	SSHLoginReposity sshLoginReposity;
	
	@Transactional
	public void saveSSHLogin(SSHLoginPO login)
	{
		sshLoginReposity.save(login);
	}
	/**
	 * 获取最新的登陆记录
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<SSHLoginPO> getSSHLoginByPage(int page,int size,int status)
	{
		PageRequest pageRequest = new PageRequest(page, size);
		Page<SSHLoginPO> pageList = sshLoginReposity.findSSHLoginByDateDesc(status,pageRequest);
		return pageList;
	}
	
}
