package microserver.task;


import java.io.File;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import microserver.entity.SSHLoginPO;
import microserver.log.LogFileTailer;
import microserver.log.LogFileTailerListener;
import microserver.service.SSHLoginService;
import microserver.util.Constants;
import microserver.util.DateUtil;

/**
 * ssh登陆日志抓取任务
 * @author next
 * 
 */
@Component
public class SSHLoginTask implements LogFileTailerListener{
	
	@Autowired
	SSHLoginService sSHLoginService;
	/**
	 * 登陆日志文件
	 */
	String fileName = "/var/log/secure";
	
	 private Logger log = LoggerFactory.getLogger(getClass());
	
	public void getSecureTask()
	{
		LogFileTailer tailer = new LogFileTailer(new File(fileName), 1000L, true);    
        tailer.setTailing(true);    
        tailer.addLogFileTailerListener(this);    
        tailer.start();    
	}

	@Override
	public void newLogFileLine(String line) {
		try {
			//消息监听 符合条件的消息
			if(null != line && !line.isEmpty() && line.contains("sshd") && line.contains("password") && line.split(" ").length==14)
			{
				log.debug("log line:", line);
				//格式化
				line = line.replace("invalid user ", "");
				//失败日志
				//Jun  1 19:19:47 next sshd[12104]: Failed password for root from 211.144.94.236 port 11708 ssh2
				//成功日志
				//May 29 20:37:25 next sshd[17719]: Accepted password for kang.yang from 192.168.200.141 port 50728 ssh2
					String[] logLine = line.split(" ");
					Date date = DateUtil.DateForamt(logLine[0], logLine[1], logLine[2]);
					SSHLoginPO sshLoginPO = new SSHLoginPO();
					sshLoginPO.setDomain(logLine[3]);
					sshLoginPO.setFromIp(logLine[10]);
					sshLoginPO.setFromPort(Integer.valueOf(logLine[12]));
					sshLoginPO.setLogin_date(date);
					sshLoginPO.setSshdId(Integer.valueOf(logLine[4].replace("sshd[", "").replace("]:", "")));
					if(line.contains("Failed password"))
					{
						sshLoginPO.setStatus(Constants.LOGIN_FAIL);
					}
					if(line.contains("Accepted password"))
					{
						sshLoginPO.setStatus(Constants.LOGIN_SUCCESS);
					}
					sshLoginPO.setUserName(logLine[8]);
					sSHLoginService.saveSSHLogin(sshLoginPO);
			}
		} catch (NumberFormatException e) {
			log.debug("log line:", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			log.debug("log line:", e.getMessage());
			e.printStackTrace();
		}
	}
	
}
