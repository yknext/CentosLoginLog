package microserver.model;

import java.io.Serializable;

public class IPInfo implements Serializable{

//	{
//		"code" : 0,
//		"data" : {
//			"country" : "中国",
//			"country_id" : "CN",
//			"area" : "西南",
//			"area_id" : "500000",
//			"region" : "四川省",
//			"region_id" : "510000",
//			"city" : "成都市",
//			"city_id" : "510100",
//			"county" : "",
//			"county_id" : "-1",
//			"isp" : "电信",
//			"isp_id" : "100017",
//			"ip" : "182.139.168.246"
//		}
//	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int code;
	Data data;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	
}

