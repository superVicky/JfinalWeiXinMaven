package com.javen.weixin.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.javen.weixin.util.HttpUtil;
import com.jfinal.weixin.sdk.utils.HttpUtils;

/**
 * @author Javen
 * @Email javenlife@126.com
 * 身份证号码查询
 */
public class IdCardService {
	/*<API>
	<RESULTS>0</RESULTS>
	<MESSAGE>接口查询成功</MESSAGE>
	<SIX>男</SIX>
	<BIRTHDAY>1992-10-02</BIRTHDAY>
	<NONGLI>壬申(猴)年九月初七</NONGLI>
	<WEEK>星期五</WEEK>
	<LUNAR>壬申(猴)</LUNAR>
	<ADDRESS>湖北省 武汉市 新洲区</ADDRESS>
	<IDCARD>420117199210027116</IDCARD>
	</API>*/
	
	public static String getIdCard(String idCard){
		if (idCard.length()==15 || idCard.length()==18) {
			String url="http://www.gpsso.com/webservice/idcard/idcard.asmx/SearchIdCard";
			Map<String, Object> params=new HashMap<String, Object>();
			params.put("IdCard", idCard);
			
			try {
				InputStream is= HttpUtil.httpUrlPostToIs(url, params);

				SAXReader saxReader=new SAXReader();
				
				Document document = saxReader.read(is);
				Element array = document.getRootElement();
				Element RESULTS=array.element("RESULTS");
				String results=RESULTS.getText();
				if (results.equals("0")) {
					String six=array.element("SIX").getText();
					String birthday=array.element("BIRTHDAY").getText();
					String non=array.element("NONGLI").getText();
					String lun=array.element("LUNAR").getText();
					String address=array.element("ADDRESS").getText();
					String idc=array.element("IDCARD").getText();
					
					return "\ue151性别:"+six+"\n\ue437生日:"+birthday+"\n"+getTu(lun)+"农历:"+non+"\n\ue036地址:"+address;
				}else {
					return "身份证号码输入错误";
				}
			} catch (Exception e) {
				return "身份证号码输入错误";
			}
			
		}else {
			return "身份证号码查询，请输入15位或18位身份证。";
		}
	}
	
	private static String getTu(String lun){
		lun=lun.substring(3,4);
		String sm=null;
		if (lun.equals("猴")) {
			sm="\ue109";
		}else if (lun.equals("兔")) {
			sm="\ue52c";
		}else if (lun.equals("羊")) {
			sm="\ue52c";
		}else if (lun.equals("猪")) {
			sm="\ue10b";
		}else if (lun.equals("马")) {
			sm="\ue134";
		}else if (lun.equals("蛇")) {
			sm="\ue52d";
		}else if (lun.equals("龙")) {
			sm="\ue443";
		}else if (lun.equals("鸡")) {
			sm="\ue52e";
		}else if (lun.equals("牛")) {
			sm="\ue52b";
		}else if (lun.equals("虎")) {
			sm="\ue050";
		}else if (lun.equals("鼠")) {
			sm="\ue528";
		}else if(lun.equals("狗")){
			sm="\ue052";
		}
		return sm;
		
	}
	
	 public static String getGuide(){
		  StringBuffer buffer = new StringBuffer();  
	      buffer.append("\ue301 身份证查询使用操作指南").append("\n\n");  
	      buffer.append("案例:身份证542336199308041994").append("\n\n");  
	      buffer.append("\ue151性别:男\n");
	      buffer.append("\ue437生日:1993-08-04\n");
	      buffer.append("\ue52e农历:癸酉(鸡)年六月十七\n");
	      buffer.append("\ue036地址:西藏自治区 日喀则地区 聂拉木县\n\n");
	      
	      buffer.append("回复“?”显示主菜单");  
	      return buffer.toString();  
	  }
	
	public static void main(String[] args) {
		System.out.println(getIdCard("420117199210027116"));
		System.out.println(getTu("壬申(猴)"));
	}
}
