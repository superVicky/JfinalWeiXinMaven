
package com.jfinal.weixin.demo;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.javen.weixin.template.DataItem;
import com.javen.weixin.template.TempItem;
import com.javen.weixin.template.TempToJson;
import com.jfinal.kit.PropKit;
import com.jfinal.log.Logger;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.CallbackIpApi;
import com.jfinal.weixin.sdk.api.SnsAccessTokenApi;
import com.jfinal.weixin.sdk.api.TemplateMsgApi;
import com.jfinal.weixin.sdk.jfinal.MsgController;
import com.jfinal.weixin.sdk.msg.in.InImageMsg;
import com.jfinal.weixin.sdk.msg.in.InLinkMsg;
import com.jfinal.weixin.sdk.msg.in.InLocationMsg;
import com.jfinal.weixin.sdk.msg.in.InShortVideoMsg;
import com.jfinal.weixin.sdk.msg.in.InTextMsg;
import com.jfinal.weixin.sdk.msg.in.InVideoMsg;
import com.jfinal.weixin.sdk.msg.in.InVoiceMsg;
import com.jfinal.weixin.sdk.msg.in.event.InCustomEvent;
import com.jfinal.weixin.sdk.msg.in.event.InFollowEvent;
import com.jfinal.weixin.sdk.msg.in.event.InLocationEvent;
import com.jfinal.weixin.sdk.msg.in.event.InMassEvent;
import com.jfinal.weixin.sdk.msg.in.event.InMenuEvent;
import com.jfinal.weixin.sdk.msg.in.event.InQrCodeEvent;
import com.jfinal.weixin.sdk.msg.in.event.InShakearoundUserShakeEvent;
import com.jfinal.weixin.sdk.msg.in.event.InTemplateMsgEvent;
import com.jfinal.weixin.sdk.msg.in.event.InVerifyFailEvent;
import com.jfinal.weixin.sdk.msg.in.event.InVerifySuccessEvent;
import com.jfinal.weixin.sdk.msg.in.speech_recognition.InSpeechRecognitionResults;
import com.jfinal.weixin.sdk.msg.out.OutCustomMsg;
import com.jfinal.weixin.sdk.msg.out.OutMusicMsg;
import com.jfinal.weixin.sdk.msg.out.OutNewsMsg;
import com.jfinal.weixin.sdk.msg.out.OutTextMsg;

/**
 * 
 * @author Javen
 * 将此 DemoController 在YourJFinalConfig 中注册路由，
 * 并设置好weixin开发者中心的 URL 与 token ，使 URL 指向该
 * DemoController 继承自父类 WeixinController 的 index
 * 方法即可直接运行看效果，在此基础之上修改相关的方法即可进行实际项目开发
 */
public class WeixinMsgController extends MsgController {
//	String configId = getPara("configId");
//	ApiConfigModel am = ApiConfigModel.am.findConfigByConfigId(configId);
	
	
	public static String nearbyContent;//附近
	public static String location;//地理位置114.037125,22.645319
	public static String weahterContent;
	public static String Regex = "[\\+ ~!@#%^-_=]?";
	static Logger logger = Logger.getLogger(WeixinMsgController.class);
//	private static final String helpStr = "\t发送 help 可获得帮助，发送\"视频\" 可获取视频教程，发送 \"美女\" 可看美女，发送 music 可听音乐 ，发送新闻可看JFinal新版本消息。公众号功能持续完善中";

	private static final String helpStr = "么么哒  美女等你好久了哦!! \n\n\t发送 help 可获得帮助，发送 \"美女\" 可看美女，发送 music 可听音乐 。\n\n"
			+ "1、人脸识别"+"\n"
			+ "2、在线翻译"+"\n"
			+ "3、天气查询"+"\n"
			+ "4、公交查询"+"\n"
			+ "7、附近查询"+"\n"
			+ "8、开发者模式"+"\n"
			+ "9、QQ咨询"+"\n\n"
			+ "10、获取资料密码"+"\n\n"
			+ "11、jssdkDemo "+"\n\n"
			
			+ "公众号功能持续完善中\n\n"
			+ "微信交流群：<a href=\"http://shang.qq.com/wpa/qunwpa?idkey=7f176ad0cd979c3a7e6ceeab0207a5bfc39ddcf0ad8b3552696e09f04867b245\">114196246</a>\n\n"
			+ "<a href=\"http://wx.wsq.qq.com/170814115\">我的社区>有问必答</a>\t\n\n"
			+" <a href=\"http://mp.weixin.qq.com/s?__biz=MzA4MDA2OTA0Mg==&mid=208184833&idx=1&sn=d9e615e45902c3c72db6c24b65c4af3e#rd\">一键关注</a>";
	
	/**
	 * 如果要支持多公众账号，只需要在此返回各个公众号对应的  ApiConfig 对象即可
	 * 可以通过在请求 url 中挂参数来动态从数据库中获取 ApiConfig 属性值
	 */
	public ApiConfig getApiConfig() {
//		String configId = getPara("configId");
//		ApiConfigModel am = ApiConfigModel.am.findConfigByConfigId(configId);
		
//		ApiConfig ac = new ApiConfig();
//		
//		// 配置微信 API 相关常量
//		ac.setToken(am.getStr("token"));
//		ac.setAppId(am.getStr("appId"));
//		ac.setAppSecret(am.getStr("appSecret"));
//		ac.setEncryptMessage(am.getBoolean("encryptMessage"));
//		ac.setEncodingAesKey(am.getStr("encodingAesKey"));
		
		ApiConfig ac = new ApiConfig();
		System.out.println("xxxxxxxxxxxxxx");
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

	/**
	 * 实现父类抽方法，处理文本消息
	 * 本例子中根据消息中的不同文本内容分别做出了不同的响应，同时也是为了测试 jfinal weixin sdk的基本功能：
	 *     本方法仅测试了 OutTextMsg、OutNewsMsg、OutMusicMsg 三种类型的OutMsg，
	 *     其它类型的消息会在随后的方法中进行测试
	 */
	protected void processInTextMsg(InTextMsg inTextMsg) {
		String msgContent = inTextMsg.getContent().trim();
		// 帮助提示
		if ("help".equalsIgnoreCase(msgContent) || "帮助".equals(msgContent) || "?".equalsIgnoreCase(msgContent) || "？".equalsIgnoreCase(msgContent)) {
			OutTextMsg outMsg = new OutTextMsg(inTextMsg);
			outMsg.setContent(helpStr);
			render(outMsg);
		}
		// 图文消息测试
		else if ("news".equalsIgnoreCase(msgContent) || "新闻".equalsIgnoreCase(msgContent)) {
			OutNewsMsg outMsg = new OutNewsMsg(inTextMsg);
			outMsg.addNews("JFinal 2.0 发布,JAVA 极速 WEB+ORM 框架", "本星球第一个极速开发框架", "https://mmbiz.qlogo.cn/mmbiz/KJoUl0sqZFS0fRW68poHoU3v9ulTWV8MgKIduxmzHiamkb3yHia8pCicWVMCaFRuGGMnVOPrrj2qM13u9oTahfQ9A/0?wx_fmt=png", "http://mp.weixin.qq.com/s?__biz=MzA4NjM4Mjk2Mw==&mid=211063163&idx=1&sn=87d54e2992237a3f791f08b5cdab7990#rd");
			outMsg.addNews("JFinal 1.8 发布,JAVA 极速 WEB+ORM 框架", "现在就加入 JFinal 极速开发世界，节省更多时间去跟女友游山玩水 ^_^", "http://mmbiz.qpic.cn/mmbiz/zz3Q6WSrzq1ibBkhSA1BibMuMxLuHIvUfiaGsK7CC4kIzeh178IYSHbYQ5eg9tVxgEcbegAu22Qhwgl5IhZFWWXUw/0", "http://mp.weixin.qq.com/s?__biz=MjM5ODAwOTU3Mg==&mid=200313981&idx=1&sn=3bc5547ba4beae12a3e8762ababc8175#rd");
			outMsg.addNews("JFinal 1.6 发布,JAVA 极速 WEB+ORM 框架", "JFinal 1.6 主要升级了 ActiveRecord 插件，本次升级全面支持多数源、多方言、多缓", "http://mmbiz.qpic.cn/mmbiz/zz3Q6WSrzq0fcR8VmNCgugHXv7gVlxI6w95RBlKLdKUTjhOZIHGSWsGvjvHqnBnjIWHsicfcXmXlwOWE6sb39kA/0", "http://mp.weixin.qq.com/s?__biz=MjM5ODAwOTU3Mg==&mid=200121522&idx=1&sn=ee24f352e299b2859673b26ffa4a81f6#rd");
			render(outMsg);
		}
		// 音乐消息测试
		else if ("music".equalsIgnoreCase(msgContent) || "音乐".equals(msgContent)) {
			OutMusicMsg outMsg = new OutMusicMsg(inTextMsg);
			outMsg.setTitle("When The Stars Go Blue-Venke Knutson");
			outMsg.setDescription("建议在 WIFI 环境下流畅欣赏此音乐");
			outMsg.setMusicUrl("http://www.jfinal.com/When_The_Stars_Go_Blue-Venke_Knutson.mp3");
			outMsg.setHqMusicUrl("http://www.jfinal.com/When_The_Stars_Go_Blue-Venke_Knutson.mp3");
			outMsg.setFuncFlag(true);
			render(outMsg);
		}
		else if ("美女".equalsIgnoreCase(msgContent)) {
			OutNewsMsg outMsg = new OutNewsMsg(inTextMsg);
			outMsg.addNews(
					"宝贝更新喽",
					"宝贝更新喽，我们只看美女 ^_^",
					"https://mmbiz.qlogo.cn/mmbiz/NbxiacLlcia9evB6d9y6XQdOO3rPcEvdiaXEE6PxOhEVx5zV1ZPkMwiaVWR7Inlj8SU9S3R3Tw54wRdlCbH81V4Xfw/0?wx_fmt=jpeg",
					"http://mp.weixin.qq.com/s?__biz=MzA4MDA2OTA0Mg==&mid=207820985&idx=1&sn=4ef9361e68495fc3ba1d2f7f2bca0511#rd");

			render(outMsg);
		}
		else if ("视频教程".equalsIgnoreCase(msgContent) || "视频".equalsIgnoreCase(msgContent)) {
			renderOutTextMsg("\thttp://pan.baidu.com/s/1nt2zAT7  \t密码:824r");
		}
		else if ("模板消息".equalsIgnoreCase(msgContent)) {
			
			DataItem dataItem=new DataItem();
			dataItem.setFirst(new TempItem("您好,Javen,欢迎使用模版消息!!", "#743A3A"));
			dataItem.setProduct(new TempItem("微信公众平台测试", "#FF0000"));
			dataItem.setPrice(new TempItem("39.8元", "#c4c400"));
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日  HH时mm分ss秒");
			String time=sdf.format(new Date());
			dataItem.setTime(new TempItem(time, "#0000FF"));
			dataItem.setRemark(new TempItem("您的订单已提交，我们将尽快发货，祝生活愉快! 点击可以查看详细信息", "#008000"));
			
			String jsonStr=TempToJson.getTempJson(inTextMsg.getFromUserName(), "tz1w-1fSN_Rzgj9_PjYuLU1EujvjPbtr1LL-_NtO6IM",
					"#743A3A", "http://img2.3lian.com/2014/f5/158/d/86.jpg", dataItem);
			System.out.println(inTextMsg.getToUserName()+" "+inTextMsg.getFromUserName());
			 ApiResult result=TemplateMsgApi.send(jsonStr);
			 System.out.println(result.getJson());
			 renderNull();
			
		}
		else if ("微信服务器IP".equalsIgnoreCase(msgContent)) {
			ApiResult apiResult=CallbackIpApi.getCallbackIp();
			renderOutTextMsg("微信服务IP"+apiResult.getJson());
		}
		else if ("网页授权".equalsIgnoreCase(msgContent)) {
			try {
				String appId=getApiConfig().getAppId();
				String redirect_uri=URLEncoder.encode("http://javen.ngrok.natapp.cn/oauth2", "utf-8");
				String url=SnsAccessTokenApi.getAuthorizeURL(appId, redirect_uri, false);
				
				//<a href=\"http://wx.wsq.qq.com/170814115\">我的社区>有问必答</a>
				String urlStr="<a href=\""+url+"\">点击我授权</a>";
				
				System.out.println("url "+url);
				renderOutTextMsg("授权地址"+urlStr);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if ("授权".equalsIgnoreCase(msgContent)) {
			String url="http://javen.tunnel.mobi/my_weixin/oauth2/oauth";
			String urlStr="<a href=\""+url+"\">点击我授权</a>";
			renderOutTextMsg("授权地址"+urlStr);
		}
		else if ("分享".equalsIgnoreCase(msgContent)) {
			String url="<a href=\"http://javen.ngrok.natapp.cn/share\">分享吧</a>";
			renderOutTextMsg(url);
		}else if (msgContent.equals("8") || "开发者模式".equalsIgnoreCase(msgContent)) {
			String url="url:http://203.88.160.193/my_weixin/msg"
					+"\n token:Javen \n 使用兼容模式";
			renderOutTextMsg(url);
		}else if (msgContent.equals("9") || "QQ咨询".equalsIgnoreCase(msgContent)) {
			String url="http://wpa.qq.com/msgrd?v=3&uin=572839485&site=qq&menu=yes";
			String urlStr="<a href=\""+url+"\">点击咨询</a>";
			renderOutTextMsg("QQ在线咨询"+urlStr);
		}else if (msgContent.equals("10") || "密码".contains(msgContent)) {
			String content="ngrok下载链接:http://pan.baidu.com/s/1dD99kGD 密码:jeyj";
			renderOutTextMsg(content);
		}else if(msgContent.equals("11") || "jssdk".contains(msgContent)){
			String url="http://javen.ngrok.natapp.cn/jssdk";
			String urlStr="<a href=\""+url+"\">JSSDKDEMO</a>";
			renderOutTextMsg(urlStr);
		}
		
		else if ("模板消息".equalsIgnoreCase(msgContent)) {
			
			DataItem dataItem=new DataItem();
			dataItem.setFirst(new TempItem("您好,Javen,欢迎使用模版消息!!", "#743A3A"));
			dataItem.setProduct(new TempItem("微信公众平台测试", "#FF0000"));
			dataItem.setPrice(new TempItem("39.8元", "#c4c400"));
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日  HH时mm分ss秒");
			String time=sdf.format(new Date());
			dataItem.setTime(new TempItem(time, "#0000FF"));
			dataItem.setRemark(new TempItem("您的订单已提交，我们将尽快发货，祝生活愉快! 点击可以查看详细信息", "#008000"));
			
			String jsonStr=TempToJson.getTempJson(inTextMsg.getFromUserName(), "tz1w-1fSN_Rzgj9_PjYuLU1EujvjPbtr1LL-_NtO6IM",
					"#743A3A", "http://img2.3lian.com/2014/f5/158/d/86.jpg", dataItem);
			System.out.println(inTextMsg.getToUserName()+" "+inTextMsg.getFromUserName());
			 ApiResult result=TemplateMsgApi.send(jsonStr);
			 System.out.println(result.getJson());
			 renderNull();
			
		}
		// 其它文本消息直接返回原值 + 帮助提示
		else {
			renderOutTextMsg("\t文本消息已成功接收，内容为： " + inTextMsg.getContent() + "\n\n" + helpStr);
		}
	}


	@Override
	protected void processInVoiceMsg(InVoiceMsg inVoiceMsg)
	{
		//转发给多客服PC客户端
		OutCustomMsg outCustomMsg = new OutCustomMsg(inVoiceMsg);
		render(outCustomMsg);
	}

	@Override
	protected void processInVideoMsg(InVideoMsg inVideoMsg)
	{
		//转发给多客服PC客户端
		OutCustomMsg outCustomMsg = new OutCustomMsg(inVideoMsg);
		render(outCustomMsg);
	}

	@Override
	protected void processInShortVideoMsg(InShortVideoMsg inShortVideoMsg)
	{
		//转发给多客服PC客户端
		OutCustomMsg outCustomMsg = new OutCustomMsg(inShortVideoMsg);
		render(outCustomMsg);
	}

	@Override
	protected void processInLocationMsg(InLocationMsg inLocationMsg)
	{
		//转发给多客服PC客户端
//		OutCustomMsg outCustomMsg = new OutCustomMsg(inLocationMsg);
//		render(outCustomMsg);
		String Location_X = inLocationMsg.getLocation_X();
		String Location_Y = inLocationMsg.getLocation_Y();
		System.out.println("Location_X:" + Location_X + " Location_Y:"
				+ Location_Y);
		location=Location_Y+","+Location_X;
		
			
	}

	@Override
	protected void processInLinkMsg(InLinkMsg inLinkMsg)
	{
		//转发给多客服PC客户端
		OutCustomMsg outCustomMsg = new OutCustomMsg(inLinkMsg);
		render(outCustomMsg);
	}

	@Override
	protected void processInCustomEvent(InCustomEvent inCustomEvent)
	{
		logger.debug("测试方法：processInCustomEvent()");
		renderNull();
	}

	protected void processInImageMsg(InImageMsg inImageMsg)
	{
		//转发给多客服PC客户端
		OutCustomMsg outCustomMsg = new OutCustomMsg(inImageMsg);
		render(outCustomMsg);
		
	}

	/**
	 * 实现父类抽方法，处理关注/取消关注消息
	 */
	protected void processInFollowEvent(InFollowEvent inFollowEvent)
	{
		if (InFollowEvent.EVENT_INFOLLOW_SUBSCRIBE.equals(inFollowEvent.getEvent()))
		{
			logger.debug("关注：" + inFollowEvent.getFromUserName());
			logger.debug("xxxxxxxx：" + inFollowEvent.getToUserName());
			System.out.println("关注：" + inFollowEvent.getFromUserName());
			System.out.println("xxxxxxxx：" + inFollowEvent.getToUserName());
			OutTextMsg outMsg = new OutTextMsg(inFollowEvent);
			outMsg.setContent("这是Jfinal-weixin测试服务</br>\r\n感谢您的关注");
			render(outMsg);
//			String fromUserName=inFollowEvent.getFromUserName();//openid
//			String toUserName=inFollowEvent.getToUserName();//微信Id
//			ApiConfigModel apiConfigModel = ApiConfigModel.dao.findConfigByWechatId(toUserName);
//			if (apiConfigModel!=null) {
//				SubscribeModel subscribeModel = SubscribeModel.dao.findConfigByConfigId(apiConfigModel.getInt("id"));
//				
//				WxUsersModel wxUsersModel=new WxUsersModel();
//				wxUsersModel.set("configId", apiConfigModel.getInt("id"));
//				wxUsersModel.set("openId", fromUserName);
//				wxUsersModel.set("subscribeTime", new Date());
//				getModel(wxUsersModel.getClass()).save();
//				//获取关注的内容
//				String str=null;
//				int type=subscribeModel.getInt("type");
//				if (type==1) {
//					//文本
//					str = subscribeModel.getStr("text");
//				}
//				
//				OutTextMsg outMsg = new OutTextMsg(inFollowEvent);
//				outMsg.setContent(str);
//				render(outMsg);
//			}
			
			
			
		}
		// 如果为取消关注事件，将无法接收到传回的信息
		if (InFollowEvent.EVENT_INFOLLOW_UNSUBSCRIBE.equals(inFollowEvent.getEvent()))
		{
			logger.debug("取消关注：" + inFollowEvent.getFromUserName());
//			String fromUserName=inFollowEvent.getFromUserName();//openid
//			System.out.println("openid 取消了>>"+fromUserName);
//			String toUserName=inFollowEvent.getToUserName();//微信Id
//			ApiConfigModel apiConfigModel = ApiConfigModel.dao.findConfigByWechatId(toUserName);
//			if (apiConfigModel!=null) {
//				WxUsersModel wxUsersModel=WxUsersModel.dao.getUserByConfigId(apiConfigModel.getInt("id"));
//				if (wxUsersModel!=null) {
//					wxUsersModel.set("unsubscribeTime", new Date());
//					wxUsersModel.save();
//				}
//			}	
			
			
			
		}
	}

	@Override
	protected void processInQrCodeEvent(InQrCodeEvent inQrCodeEvent)
	{
		if (InQrCodeEvent.EVENT_INQRCODE_SUBSCRIBE.equals(inQrCodeEvent.getEvent()))
		{
			logger.debug("扫码未关注：" + inQrCodeEvent.getFromUserName());
			OutTextMsg outMsg = new OutTextMsg(inQrCodeEvent);
			outMsg.setContent("感谢您的关注，二维码内容：" + inQrCodeEvent.getEventKey());
			render(outMsg);
		}
		if (InQrCodeEvent.EVENT_INQRCODE_SCAN.equals(inQrCodeEvent.getEvent()))
		{
			logger.debug("扫码已关注：" + inQrCodeEvent.getFromUserName());
		}
	}

	@Override
	protected void processInLocationEvent(InLocationEvent inLocationEvent)
	{
		logger.debug("发送地理位置事件：" + inLocationEvent.getFromUserName());
		OutTextMsg outMsg = new OutTextMsg(inLocationEvent);
		outMsg.setContent("地理位置是：" + inLocationEvent.getLatitude());
		render(outMsg);
		
		
	}

	@Override
	protected void processInMassEvent(InMassEvent inMassEvent)
	{
		logger.debug("测试方法：processInMassEvent()");
		renderNull();
	}

	/**
	 * 实现父类抽方法，处理自定义菜单事件
	 */
	protected void processInMenuEvent(InMenuEvent inMenuEvent)
	{
		logger.debug("菜单事件：" + inMenuEvent.getFromUserName());
		OutTextMsg outMsg = new OutTextMsg(inMenuEvent);
		outMsg.setContent("菜单事件内容是：" + inMenuEvent.getEventKey());
		render(outMsg);
	}

	@Override
	protected void processInSpeechRecognitionResults(InSpeechRecognitionResults inSpeechRecognitionResults)
	{
		logger.debug("语音识别事件：" + inSpeechRecognitionResults.getFromUserName());
		OutTextMsg outMsg = new OutTextMsg(inSpeechRecognitionResults);
		outMsg.setContent("语音识别内容是：" + inSpeechRecognitionResults.getRecognition());
		render(outMsg);
	}
	//处理接收到的模板消息是否送达成功通知事件
	@Override
	protected void processInTemplateMsgEvent(InTemplateMsgEvent inTemplateMsgEvent)
	{
//		logger.debug("测试方法：processInTemplateMsgEvent()");
//		renderNull();
		
		String status = inTemplateMsgEvent.getStatus();
		OutTextMsg outMsg = new OutTextMsg(inTemplateMsgEvent);
		outMsg.setContent("模板消息是否接收成功：" + status);
		render(outMsg);
	}

	@Override
	protected void processInShakearoundUserShakeEvent(InShakearoundUserShakeEvent inShakearoundUserShakeEvent) {
		logger.debug("摇一摇周边设备信息通知事件：" + inShakearoundUserShakeEvent.getFromUserName());
		OutTextMsg outMsg = new OutTextMsg(inShakearoundUserShakeEvent);
		outMsg.setContent("摇一摇周边设备信息通知事件UUID：" + inShakearoundUserShakeEvent.getUuid());
		render(outMsg);
	}

	@Override
	protected void processInVerifySuccessEvent(InVerifySuccessEvent inVerifySuccessEvent) {
		logger.debug("资质认证成功通知事件：" + inVerifySuccessEvent.getFromUserName());
		OutTextMsg outMsg = new OutTextMsg(inVerifySuccessEvent);
		outMsg.setContent("资质认证成功通知事件：" + inVerifySuccessEvent.getExpiredTime());
		render(outMsg);
	}

	@Override
	protected void processInVerifyFailEvent(InVerifyFailEvent inVerifyFailEvent){
		logger.debug("资质认证失败通知事件：" + inVerifyFailEvent.getFromUserName());
		OutTextMsg outMsg = new OutTextMsg(inVerifyFailEvent);
		outMsg.setContent("资质认证失败通知事件：" + inVerifyFailEvent.getFailReason());
		render(outMsg);
	}
	 

//	/**
//	 * 实现父类抽方法，处理文本消息
//	 * 本例子中根据消息中的不同文本内容分别做出了不同的响应，同时也是为了测试 jfinal weixin sdk的基本功能：
//	 *     本方法仅测试了 OutTextMsg、OutNewsMsg、OutMusicMsg 三种类型的OutMsg，
//	 *     其它类型的消息会在随后的方法中进行测试
//	 */
//	protected void processInTextMsg(InTextMsg inTextMsg) {
//		String msgContent = inTextMsg.getContent().trim();
//		// 帮助提示
//		if ("help".equalsIgnoreCase(msgContent) || "帮助".equals(msgContent)) {
//			OutTextMsg outMsg = new OutTextMsg(inTextMsg);
//			outMsg.setContent(helpStr);
//			render(outMsg);
//		}
//		// 图文消息测试
//		else if ("news".equalsIgnoreCase(msgContent) || "新闻".equalsIgnoreCase(msgContent)) {
//			OutNewsMsg outMsg = new OutNewsMsg(inTextMsg);
//			outMsg.addNews("JFinal 2.0 发布,JAVA 极速 WEB+ORM 框架", "本星球第一个极速开发框架", "https://mmbiz.qlogo.cn/mmbiz/KJoUl0sqZFS0fRW68poHoU3v9ulTWV8MgKIduxmzHiamkb3yHia8pCicWVMCaFRuGGMnVOPrrj2qM13u9oTahfQ9A/0?wx_fmt=png", "http://mp.weixin.qq.com/s?__biz=MzA4NjM4Mjk2Mw==&mid=211063163&idx=1&sn=87d54e2992237a3f791f08b5cdab7990#rd");
//			outMsg.addNews("JFinal 1.8 发布,JAVA 极速 WEB+ORM 框架", "现在就加入 JFinal 极速开发世界，节省更多时间去跟女友游山玩水 ^_^", "http://mmbiz.qpic.cn/mmbiz/zz3Q6WSrzq1ibBkhSA1BibMuMxLuHIvUfiaGsK7CC4kIzeh178IYSHbYQ5eg9tVxgEcbegAu22Qhwgl5IhZFWWXUw/0", "http://mp.weixin.qq.com/s?__biz=MjM5ODAwOTU3Mg==&mid=200313981&idx=1&sn=3bc5547ba4beae12a3e8762ababc8175#rd");
//			outMsg.addNews("JFinal 1.6 发布,JAVA 极速 WEB+ORM 框架", "JFinal 1.6 主要升级了 ActiveRecord 插件，本次升级全面支持多数源、多方言、多缓", "http://mmbiz.qpic.cn/mmbiz/zz3Q6WSrzq0fcR8VmNCgugHXv7gVlxI6w95RBlKLdKUTjhOZIHGSWsGvjvHqnBnjIWHsicfcXmXlwOWE6sb39kA/0", "http://mp.weixin.qq.com/s?__biz=MjM5ODAwOTU3Mg==&mid=200121522&idx=1&sn=ee24f352e299b2859673b26ffa4a81f6#rd");
//			render(outMsg);
//		}
//		// 音乐消息测试
//		else if ("music".equalsIgnoreCase(msgContent) || "音乐".equals(msgContent)) {
//			OutMusicMsg outMsg = new OutMusicMsg(inTextMsg);
//			outMsg.setTitle("When The Stars Go Blue-Venke Knutson");
//			outMsg.setDescription("建议在 WIFI 环境下流畅欣赏此音乐");
//			outMsg.setMusicUrl("http://www.jfinal.com/When_The_Stars_Go_Blue-Venke_Knutson.mp3");
//			outMsg.setHqMusicUrl("http://www.jfinal.com/When_The_Stars_Go_Blue-Venke_Knutson.mp3");
//			outMsg.setFuncFlag(true);
//			render(outMsg);
//		}
//		else if ("美女".equalsIgnoreCase(msgContent)) {
//			OutNewsMsg outMsg = new OutNewsMsg(inTextMsg);
//			outMsg.addNews(
//					"JFinal 宝贝更新喽",
//					"jfinal 宝贝更新喽，我们只看美女 ^_^",
//					"https://mmbiz.qlogo.cn/mmbiz/KJoUl0sqZFRHa3VrmibqAXRfYPNdiamFnpPTOvXoxsFlRoOHbVibGhmHOEUQiboD3qXWszKuzWpibFxsVW1RmNB9hPw/0?wx_fmt=jpeg",
//					"http://mp.weixin.qq.com/s?__biz=MzA4NjM4Mjk2Mw==&mid=211356950&idx=1&sn=6315a1a2848aa8cb0694bf1f4accfb07#rd");
//			// outMsg.addNews("秀色可餐", "JFinal Weixin 极速开发就是这么爽，有木有 ^_^", "http://mmbiz.qpic.cn/mmbiz/zz3Q6WSrzq2GJLC60ECD7rE7n1cvKWRNFvOyib4KGdic3N5APUWf4ia3LLPxJrtyIYRx93aPNkDtib3ADvdaBXmZJg/0", "http://mp.weixin.qq.com/s?__biz=MjM5ODAwOTU3Mg==&mid=200987822&idx=1&sn=7eb2918275fb0fa7b520768854fb7b80#rd");
//
//			render(outMsg);
//		}
//		else if ("视频教程".equalsIgnoreCase(msgContent) || "视频".equalsIgnoreCase(msgContent)) {
//			renderOutTextMsg("\thttp://pan.baidu.com/s/1nt2zAT7  \t密码:824r");
//		}
//		// 其它文本消息直接返回原值 + 帮助提示
//		else {
//			renderOutTextMsg("\t文本消息已成功接收，内容为： " + inTextMsg.getContent() + "\n\n" + helpStr);
//		}
//	}
//
//	/**
//	 * 实现父类抽方法，处理图片消息
//	 */
//	protected void processInImageMsg(InImageMsg inImageMsg) {
//		OutImageMsg outMsg = new OutImageMsg(inImageMsg);
//		// 将刚发过来的图片再发回去
//		outMsg.setMediaId(inImageMsg.getMediaId());
//		render(outMsg);
//	}
//
//	/**
//	 * 实现父类抽方法，处理语音消息
//	 */
//	protected void processInVoiceMsg(InVoiceMsg inVoiceMsg) {
//		OutVoiceMsg outMsg = new OutVoiceMsg(inVoiceMsg);
//		// 将刚发过来的语音再发回去
//		outMsg.setMediaId(inVoiceMsg.getMediaId());
//		render(outMsg);
//	}
//
//	/**
//	 * 实现父类抽方法，处理视频消息
//	 */
//	protected void processInVideoMsg(InVideoMsg inVideoMsg) {
//		/* 腾讯 api 有 bug，无法回复视频消息，暂时回复文本消息代码测试
//		OutVideoMsg outMsg = new OutVideoMsg(inVideoMsg);
//		outMsg.setTitle("OutVideoMsg 发送");
//		outMsg.setDescription("刚刚发来的视频再发回去");
//		// 将刚发过来的视频再发回去，经测试证明是腾讯官方的 api 有 bug，待 api bug 却除后再试
//		outMsg.setMediaId(inVideoMsg.getMediaId());
//		render(outMsg);
//		*/
//		OutTextMsg outMsg = new OutTextMsg(inVideoMsg);
//		outMsg.setContent("\t视频消息已成功接收，该视频的 mediaId 为: " + inVideoMsg.getMediaId());
//		render(outMsg);
//	}
//
//	@Override
//	protected void processInShortVideoMsg(InShortVideoMsg inShortVideoMsg)
//	{
//		OutTextMsg outMsg = new OutTextMsg(inShortVideoMsg);
//		outMsg.setContent("\t视频消息已成功接收，该视频的 mediaId 为: " + inShortVideoMsg.getMediaId());
//		render(outMsg);
//	}
//
//	/**
//	 * 实现父类抽方法，处理地址位置消息
//	 */
//	protected void processInLocationMsg(InLocationMsg inLocationMsg) {
//		OutTextMsg outMsg = new OutTextMsg(inLocationMsg);
//		outMsg.setContent("已收到地理位置消息:" +
//							"\nlocation_X = " + inLocationMsg.getLocation_X() +
//							"\nlocation_Y = " + inLocationMsg.getLocation_Y() +
//							"\nscale = " + inLocationMsg.getScale() +
//							"\nlabel = " + inLocationMsg.getLabel());
//		render(outMsg);
//	}
//
//	/**
//	 * 实现父类抽方法，处理链接消息
//	 * 特别注意：测试时需要发送我的收藏中的曾经收藏过的图文消息，直接发送链接地址会当做文本消息来发送
//	 */
//	protected void processInLinkMsg(InLinkMsg inLinkMsg) {
//		OutNewsMsg outMsg = new OutNewsMsg(inLinkMsg);
//		outMsg.addNews("链接消息已成功接收", "链接使用图文消息的方式发回给你，还可以使用文本方式发回。点击图文消息可跳转到链接地址页面，是不是很好玩 :)" , "http://mmbiz.qpic.cn/mmbiz/zz3Q6WSrzq1ibBkhSA1BibMuMxLuHIvUfiaGsK7CC4kIzeh178IYSHbYQ5eg9tVxgEcbegAu22Qhwgl5IhZFWWXUw/0", inLinkMsg.getUrl());
//		render(outMsg);
//	}
//
//	@Override
//	protected void processInCustomEvent(InCustomEvent inCustomEvent)
//	{
//		System.out.println("processInCustomEvent() 方法测试成功");
//	}
//
//	/**
//	 * 实现父类抽方法，处理关注/取消关注消息
//	 */
//	protected void processInFollowEvent(InFollowEvent inFollowEvent) {
//		OutTextMsg outMsg = new OutTextMsg(inFollowEvent);
//		outMsg.setContent("感谢关注 JFinal Weixin 极速开发服务号，为您节约更多时间，去陪恋人、家人和朋友 :) \n\n\n " + helpStr);
//		// 如果为取消关注事件，将无法接收到传回的信息
//		render(outMsg);
//	}
//
//	/**
//	 * 实现父类抽方法，处理扫描带参数二维码事件
//	 */
//	protected void processInQrCodeEvent(InQrCodeEvent inQrCodeEvent) {
//		OutTextMsg outMsg = new OutTextMsg(inQrCodeEvent);
//		outMsg.setContent("processInQrCodeEvent() 方法测试成功");
//		render(outMsg);
//	}
//
//	/**
//	 * 实现父类抽方法，处理上报地理位置事件
//	 */
//	protected void processInLocationEvent(InLocationEvent inLocationEvent) {
//		OutTextMsg outMsg = new OutTextMsg(inLocationEvent);
//		outMsg.setContent("processInLocationEvent() 方法测试成功");
//		render(outMsg);
//	}
//
//	@Override
//	protected void processInMassEvent(InMassEvent inMassEvent)
//	{
//		System.out.println("processInMassEvent() 方法测试成功");
//	}
//
//	/**
//	 * 实现父类抽方法，处理自定义菜单事件
//	 */
//	protected void processInMenuEvent(InMenuEvent inMenuEvent) {
//		renderOutTextMsg("processInMenuEvent() 方法测试成功");
//	}
//
//	/**
//	 * 实现父类抽方法，处理接收语音识别结果
//	 */
//	protected void processInSpeechRecognitionResults(InSpeechRecognitionResults inSpeechRecognitionResults) {
//		renderOutTextMsg("语音识别结果： " + inSpeechRecognitionResults.getRecognition());
//	}
//
//	// 处理接收到的模板消息是否送达成功通知事件
//	protected void processInTemplateMsgEvent(InTemplateMsgEvent inTemplateMsgEvent) {
//		String status = inTemplateMsgEvent.getStatus();
//		renderOutTextMsg("模板消息是否接收成功：" + status);
//	}
}






