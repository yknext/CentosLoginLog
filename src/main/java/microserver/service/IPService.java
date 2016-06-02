package microserver.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import microserver.model.IPInfo;

@Service("ipService")
public class IPService {
	
	String IP_INTERFACE = "http://ip.taobao.com/service/getIpInfo.php?ip={IP}";
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@Cacheable(value = "ipinfo",keyGenerator = "wiselyKeyGenerator", unless="#result == null")
	public IPInfo getIPInfo(String ip)
	{
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(IP_INTERFACE, String.class,ip);
		IPInfo ipInfo = null;
		try {
            ObjectMapper objectMapper=new ObjectMapper();
            ipInfo=objectMapper.readValue(result, IPInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        } 
		return ipInfo;
	}
	
}
