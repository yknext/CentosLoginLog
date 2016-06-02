package microserver.rest;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import microserver.entity.SSHLoginPO;
import microserver.model.IPInfo;
import microserver.service.*;
import microserver.util.Constants;

@RestController
public class LoginLogRest {
	
	@Autowired
	SSHLoginService sSHLoginService;
	
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	HttpServletRequest httpRequest;
	
	@Autowired
	IPService iPService;
	
	@RequestMapping(value="/ip",method=RequestMethod.GET)
	public String getPublicIp()
	{
		String host = httpRequest.getRemoteHost();
		IPInfo ipInfo = iPService.getIPInfo(host);
		ObjectMapper objectMapper=new ObjectMapper();
		String result ="";
		StringWriter str=new StringWriter();
        try {
            objectMapper.writeValue(str, ipInfo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
		return result;
	}
	
	@RequestMapping(value="/getLoginLog/{status}/{page}/{size}",method=RequestMethod.GET)
	public String LoginWithUserNameAndPassword(@PathVariable String status,@PathVariable String page,@PathVariable String size)
	{
		//没时间写界面将就着看
		int ipage=0;
		int isize=40;
		int istatus=-1;
		try{
			ipage = Integer.parseInt(page);
			if(ipage>0)
			{
				ipage --;
			}
			isize = Integer.parseInt(size);
			istatus = Integer.parseInt(status);
		}catch(Exception e)
		{
			e.printStackTrace();
			return "ERROR";
		}
		List<SSHLoginPO> list = sSHLoginService.getSSHLoginByPage(ipage,isize,istatus);
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append("<body>");
		sb.append("<table border=\"1\">");
		
		sb.append("<tr>");
		sb.append("<th>id</th>");
		sb.append("<th>domain</th>");
		sb.append("<th>user</th>");
		sb.append("<th>ip</th>");
		sb.append("<th>port</th>");
		sb.append("<th>from</th>");
		sb.append("<th>status</th>");
		sb.append("<th>date</th>");
		sb.append("</tr>");

		for(SSHLoginPO log : list)
		{
			sb.append("<tr>");
			sb.append("<th>"+log.getId()+"</th>");
			sb.append("<th>"+log.getDomain()+"</th>");
			sb.append("<th>"+log.getUserName()+"</th>");
			sb.append("<th>"+log.getFromIp()+"</th>");
			sb.append("<th>"+log.getFromPort()+"</th>");
			IPInfo ipInfo = iPService.getIPInfo(log.getFromIp());
			if(ipInfo != null && ipInfo.getCode()==0)
			{
				sb.append("<th>"+
					ipInfo.getData().getCountry()+"-"+
					ipInfo.getData().getArea()+"-"+
					ipInfo.getData().getRegion()+"-"+
					ipInfo.getData().getCity()+"-"+
					ipInfo.getData().getIsp()
				+"</th>");
			}
			else
			{
				sb.append("<th>未知</th>");
			}
			
			String logStatus = log.getStatus() == Constants.LOGIN_SUCCESS ? "Login Success":"Login Fail";
			sb.append("<th>"+logStatus+"</th>");
			sb.append("<th>"+simpleDateFormat.format(log.getLogin_date())+"</th>");
			sb.append("</tr>");
		}
		sb.append("</table>");
		sb.append("</body>");
		sb.append("</html>");
		
		
		return sb.toString();
	}
	
	
	
	
	
}
