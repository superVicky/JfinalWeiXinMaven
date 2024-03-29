package com.javen.weixin.weboauth2;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.PropKit;
import com.jfinal.weixin.sdk.api.AccessToken;
import com.jfinal.weixin.sdk.api.AccessTokenApi;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.SnsAccessToken;
import com.jfinal.weixin.sdk.api.SnsAccessTokenApi;
import com.jfinal.weixin.sdk.api.SnsApi;
import com.jfinal.weixin.sdk.jfinal.ApiController;
/**
 * @author Javen
 * 2015年12月5日下午2:20:44
 *
 */
public class RedirectUri extends ApiController{
	/**
	 * 如果要支持多公众账号，只需要在此返回各个公众号对应的  ApiConfig 对象即可
	 * 可以通过在请求 url 中挂参数来动态从数据库中获取 ApiConfig 属性值
	 */
	public ApiConfig getApiConfig() {
		ApiConfig ac = new ApiConfig();
		
		// 配置微信 API 相关常量
		ac.setToken(PropKit.get("token"));
		ac.setAppId(PropKit.get("appId"));
		ac.setAppSecret(PropKit.get("appSecret"));
		
		/**
		 *  是否对消息进行加密，对应于微信平台的消息加解密方式：
		 *  1：true进行加密且必须配置 encodingAesKey
		 *  2：false采用明文模式，同时也支持混合模式
		 */
		ac.setEncryptMessage(PropKit.getBoolean("encryptMessage", false));
		ac.setEncodingAesKey(PropKit.get("encodingAesKey", "setting it in config file"));
		return ac;
	}
	
	public void index() {
		//用户同意授权，获取code
		String code=getPara("code");
		if (code!=null) {
			String appId=ApiConfigKit.getApiConfig().getAppId();
			String secret=ApiConfigKit.getApiConfig().getAppSecret();
			//通过code换取网页授权access_token
			SnsAccessToken snsAccessToken=SnsAccessTokenApi.getSnsAccessToken(appId,secret,code);
			String json=snsAccessToken.getJson();
			System.out.println("json>>"+json);
			String token=snsAccessToken.getAccessToken();
System.out.println("token:"+token);
			String openId=snsAccessToken.getOpenid();
			//拉取用户信息(需scope为 snsapi_userinfo)
			ApiResult apiResult=SnsApi.getUserInfo(token, openId);
System.out.println("openId:"+openId);	
			JSONObject jsonObject=JSON.parseObject(apiResult.getJson());
System.out.println(jsonObject.toString());
			String nickname=jsonObject.getString("nickname");
			String sex=jsonObject.getString("sex");
			String city=jsonObject.getString("city");
			String province=jsonObject.getString("province");
			String country=jsonObject.getString("country");
			String headimgurl=jsonObject.getString("headimgurl");
			
			System.out.println("nickname:"+nickname);
			try {
				System.out.println("nickname:"+URLEncoder.encode(nickname, "utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("sex:"+sex);//用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
			System.out.println("city:"+city);//城市
			System.out.println("province:"+province);//省份
			System.out.println("country:"+country);//国家
			System.out.println("headimgurl:"+headimgurl);
			
			renderText("apiResult:"+apiResult.getJson());
		}else {
			renderText("eeeee");
		}
	}
	
	
	public void oauth(){
		try {
			String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
			String appid=getApiConfig().getAppId();
			String redirect_uri=URLEncoder.encode("http://javen.tunnel.mobi/my_weixin/oauth2", "utf-8");
			String state="1";
			String scope="snsapi_userinfo";//snsapi_base
			url=url.replace("APPID", appid);
			url=url.replace("REDIRECT_URI", redirect_uri);
			url=url.replace("SCOPE", scope);
			url=url.replace("STATE", state);
			
			
			redirect(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
