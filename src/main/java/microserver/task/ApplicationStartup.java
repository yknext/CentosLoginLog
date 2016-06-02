package microserver.task;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
/**
 * 自启
 * @author next
 */
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		//启动日志读取线程
		SSHLoginTask sshLoginTask = event.getApplicationContext().getBean(SSHLoginTask.class);
		sshLoginTask.getSecureTask();
	}
}
