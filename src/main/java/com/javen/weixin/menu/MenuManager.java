package com.javen.weixin.menu;


import com.jfinal.kit.JsonKit;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.MenuApi;

/**
 * @author Javen
 * @Email javenlife@126.com
 * 菜单管理器类 
 */
public class MenuManager  {
	 public static void main(String[] args) { 
		 
		   // 将菜单对象转换成json字符串
		   //有问题：主菜单项多了一个type
		   String jsonMenu = JsonKit.toJson(getTestMenu()).toString();
		   System.out.println(jsonMenu);
		   ApiConfig ac = new ApiConfig();
			
			// 配置微信 API 相关常量
			ac.setAppId("wx614c453e0d1dcd12");
			ac.setAppSecret("19a02e4927d346484fc70327970457f9");
			//服务号
//			ac.setAppId("wx481c0354ac4a8b2b");
//			ac.setAppSecret("02e8cbd8ac3646f275e7196cd7519463");
//			ac.setAppId(PropKit.get("appId"));
//			ac.setAppSecret(PropKit.get("appSecret"));
		    ApiConfigKit.setThreadLocalApiConfig(ac);
		   
		   //创建菜单
	       MenuApi menuApi=new MenuApi();
	       ApiResult apiResult=menuApi.createMenu(jsonMenu);
	       
	       
	       System.out.println(apiResult.getJson());
	 }  
	 
	 
	 /** 
	     * 组装菜单数据 
	     *  
	     * @return 
	     */  
	    private static Menu getMenu() {  
	    	ViewButton btn11 = new ViewButton();  
	        btn11.setName("健康教育馆预约");  
	        btn11.setType("view");  
	        btn11.setUrl("http://javen.tunnel.mobi/whsf/msg/healthOrder");  
	  
	        ViewButton btn12 = new ViewButton();  
	        btn12.setName("康复预约");  
	        btn12.setType("view");  
	        btn12.setUrl("http://javen.tunnel.mobi/whsf/msg/recoveryOrder");  
	  
	        ViewButton btn13 = new ViewButton();  
	        btn13.setName("康复排队信息");  
	        btn13.setType("view");  
	        btn13.setUrl("http://javen.tunnel.mobi/whsf/msg/queueUp");  
	  
	        ViewButton btn21 = new ViewButton();  
	        btn21.setName("中心简介");  
	        btn21.setType("view");  
	        btn21.setUrl("http://javen.tunnel.mobi/whsf/msg/introduce");  
	  
	       /* ViewButton btn22 = new ViewButton();  
	        btn22.setName("APP下载");  
	        btn22.setType("view");  
	        btn22.setUrl("http://javen.tunnel.mobi/whsf/msg/appDownload");  
	  */
	        ViewButton btn22 = new ViewButton();  
	        btn22.setName("健康资讯");  
	        btn22.setType("view");  
	        btn22.setUrl("http://javen.tunnel.mobi/whsf/msg/healthInformation");  
	  
	        ViewButton btn23 = new ViewButton();  
	        btn23.setName("健康新闻");  
	        btn23.setType("view");  
	        btn23.setUrl("http://javen.tunnel.mobi/whsf/msg/healthNew");  
	  
	        ViewButton btn24 = new ViewButton();  
	        btn24.setName("健康栏目");  
	        btn24.setType("view");  
	        btn24.setUrl("http://javen.tunnel.mobi/whsf/msg/healthTv");  
	        
	        ViewButton btn25 = new ViewButton();  
	        btn25.setName("微社区");  
	        btn25.setType("view");  
	        btn25.setUrl("http://javen.tunnel.mobi/whsf/msg/wsq");  
	  
	  
	        ViewButton btn31 = new ViewButton();  
	        btn31.setName("注册绑定");  
	        btn31.setType("view");  
	        btn31.setUrl("http://javen.tunnel.mobi/whsf/msg/register");  
	        
	        ViewButton btn32 = new ViewButton();  
	        btn32.setName("档案查询");  
	        btn32.setType("view");  
	        btn32.setUrl("http://javen.tunnel.mobi/whsf/msg/recordQuery");  
	  
	        ViewButton btn33 = new ViewButton();  
	        btn33.setName("二维码");  
	        btn33.setType("view");  
	        btn33.setUrl("http://javen.tunnel.mobi/whsf/msg/code");  
//	        
//	        ViewButton btn34 = new ViewButton();  
//	        btn34.setName("积分查询");  
//	        btn34.setType("view");  
//	        btn34.setUrl("http://javen.tunnel.mobi/whsf/msg/integralQuery");  
	        
	        ViewButton btn35 = new ViewButton();  
	        btn35.setName("地址导航");  
	        btn35.setType("view");  
	        btn35.setUrl("http://javen.tunnel.mobi/whsf/msg/Map");  
	  
	        
	        ComButton mainBtn1 = new ComButton();  
	        mainBtn1.setName("业务功能");  
	        mainBtn1.setSub_button(new Button[] { btn11, btn12});  
	  
	        ComButton mainBtn2 = new ComButton();  
	        mainBtn2.setName("中心服务");  
	        mainBtn2.setSub_button(new Button[] { btn21, btn22, btn23, btn24 });  
	  
	        ComButton mainBtn3 = new ComButton();  
	        mainBtn3.setName("个人中心");  
	        mainBtn3.setSub_button(new Button[] { btn31, btn32, btn33, btn35 });  
	  
	        /** 
	         * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br> 
	         *  
	         * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br> 
	         * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br> 
	         * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 }); 
	         */  
	        Menu menu = new Menu();  
	        menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });  
	  
	        return menu;  
	    }
	    
	    /** 
	     * 组装菜单数据 
	     *  
	     * @return 
	     */  
	    private static Menu getTestMenu() {  
	    	ViewButton btn11 = new ViewButton();  
	        btn11.setName("健康教育馆预约");  
	        btn11.setType("view");  
	        btn11.setUrl("http://whsf.tunnel.mobi/whsf/msg/healthOrder");  
	  
	        ViewButton btn12 = new ViewButton();  
	        btn12.setName("咨询");  
	        btn12.setType("view");  
	        //http://tencent://message/?uin=572839485&Site=在线咨询&Menu=yes
	        //http://wpa.qq.com/msgrd?v=3&uin=572839485&site=qq&menu=yes
	        btn12.setUrl("http://wpa.qq.com/msgrd?v=3&uin=572839485&site=qq&Site=售后咨询&menu=yes");  
	  
	        ViewButton btn13 = new ViewButton();  
	        btn13.setName("康复排队信息");  
	        btn13.setType("view");  
	        btn13.setUrl("http://whsf.tunnel.mobi/whsf/msg/queueUp");  
	  
	        ViewButton btn21 = new ViewButton();  
	        btn21.setName("中心简介");  
	        btn21.setType("view");  
	        btn21.setUrl("http://whsf.tunnel.mobi/whsf/msg/introduce");  
	  
	       /* ViewButton btn22 = new ViewButton();  
	        btn22.setName("APP下载");  
	        btn22.setType("view");  
	        btn22.setUrl("http://whsf.tunnel.mobi/whsf/msg/appDownload");  
	  */
	        ViewButton btn22 = new ViewButton();  
	        btn22.setName("健康资讯");  
	        btn22.setType("view");  
	        btn22.setUrl("http://whsf.tunnel.mobi/whsf/msg/healthInformation");  
	  
	        ViewButton btn23 = new ViewButton();  
	        btn23.setName("健康新闻");  
	        btn23.setType("view");  
	        btn23.setUrl("http://whsf.tunnel.mobi/whsf/msg/healthNew");  
	  
	        ViewButton btn24 = new ViewButton();  
	        btn24.setName("健康栏目");  
	        btn24.setType("view");  
	        btn24.setUrl("http://whsf.tunnel.mobi/whsf/msg/healthTv");  
	        
	        ViewButton btn25 = new ViewButton();  
	        btn25.setName("微社区");  
	        btn25.setType("view");  
	        btn25.setUrl("http://whsf.tunnel.mobi/whsf/msg/wsq");  
	  
	  
	        ViewButton btn31 = new ViewButton();  
	        btn31.setName("注册绑定");  
	        btn31.setType("view");  
	        btn31.setUrl("http://whsf.tunnel.mobi/whsf/msg/register");  
	        
	        ViewButton btn32 = new ViewButton();  
	        btn32.setName("档案查询");  
	        btn32.setType("view");  
	        btn32.setUrl("http://whsf.tunnel.mobi/whsf/msg/recordQuery");  
	  
	        ViewButton btn33 = new ViewButton();  
	        btn33.setName("二维码");  
	        btn33.setType("view");  
	        btn33.setUrl("http://whsf.tunnel.mobi/whsf/msg/code");  
//	        
//	        ViewButton btn34 = new ViewButton();  
//	        btn34.setName("积分查询");  
//	        btn34.setType("view");  
//	        btn34.setUrl("http://whsf.tunnel.mobi/whsf/msg/integralQuery");  
	        
	        ViewButton btn35 = new ViewButton();  
	        btn35.setName("地址导航");  
	        btn35.setType("view");  
	        btn35.setUrl("http://whsf.tunnel.mobi/whsf/msg/Map");  
	  
	        
	        ComButton mainBtn1 = new ComButton();  
	        mainBtn1.setName("业务功能");  
	        mainBtn1.setSub_button(new Button[] { btn11, btn12});  
	  
	        ComButton mainBtn2 = new ComButton();  
	        mainBtn2.setName("中心服务");  
	        mainBtn2.setSub_button(new Button[] { btn21, btn22, btn23, btn24 });  
	  
	        ComButton mainBtn3 = new ComButton();  
	        mainBtn3.setName("个人中心");  
	        mainBtn3.setSub_button(new Button[] { btn31, btn32, btn33, btn35 });  
	  
	        /** 
	         * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br> 
	         *  
	         * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br> 
	         * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br> 
	         * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 }); 
	         */  
	        Menu menu = new Menu();  
	        menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });  
	  
	        return menu;  
	    }
	    /** 
	     * 组装菜单数据 
	     *  
	     * @return 
	     */  
	    private static Menu getwhsfMenu() {  
	    	ViewButton btn11 = new ViewButton();  
	        btn11.setName("健康教育馆预约");  
	        btn11.setType("view");  
	        btn11.setUrl("http://whsf.tunnel.mobi/whsf/msg/healthOrder");  
	  
	        ViewButton btn12 = new ViewButton();  
	        btn12.setName("康复预约");  
	        btn12.setType("view");  
	        btn12.setUrl("http://whsf.tunnel.mobi/whsf/msg/recoveryOrder");  
	  
	        ViewButton btn13 = new ViewButton();  
	        btn13.setName("康复排队信息");  
	        btn13.setType("view");  
	        btn13.setUrl("http://whsf.tunnel.mobi/whsf/msg/queueUp");  
	  
	        ViewButton btn21 = new ViewButton();  
	        btn21.setName("中心简介");  
	        btn21.setType("view");  
	        btn21.setUrl("http://whsf.tunnel.mobi/whsf/msg/introduce");  
	  
	       /* ViewButton btn22 = new ViewButton();  
	        btn22.setName("APP下载");  
	        btn22.setType("view");  
	        btn22.setUrl("http://whsf.tunnel.mobi/whsf/msg/appDownload");  
	  */
	        ViewButton btn22 = new ViewButton();  
	        btn22.setName("健康资讯");  
	        btn22.setType("view");  
	        btn22.setUrl("http://whsf.tunnel.mobi/whsf/msg/healthInformation");  
	  
	        ViewButton btn23 = new ViewButton();  
	        btn23.setName("健康新闻");  
	        btn23.setType("view");  
	        btn23.setUrl("http://whsf.tunnel.mobi/whsf/msg/healthNew");  
	  
	        ViewButton btn24 = new ViewButton();  
	        btn24.setName("健康栏目");  
	        btn24.setType("view");  
	        btn24.setUrl("http://whsf.tunnel.mobi/whsf/msg/healthTv");  
	        
	        ViewButton btn25 = new ViewButton();  
	        btn25.setName("微社区");  
	        btn25.setType("view");  
	        btn25.setUrl("http://whsf.tunnel.mobi/whsf/msg/wsq");  
	  
	  
	        ViewButton btn31 = new ViewButton();  
	        btn31.setName("注册绑定");  
	        btn31.setType("view");  
	        btn31.setUrl("http://whsf.tunnel.mobi/whsf/msg/register");  
	        
	        ViewButton btn32 = new ViewButton();  
	        btn32.setName("档案查询");  
	        btn32.setType("view");  
	        btn32.setUrl("http://whsf.tunnel.mobi/whsf/msg/recordQuery");  
	  
	        ViewButton btn33 = new ViewButton();  
	        btn33.setName("二维码");  
	        btn33.setType("view");  
	        btn33.setUrl("http://whsf.tunnel.mobi/whsf/msg/code");  
//	        
//	        ViewButton btn34 = new ViewButton();  
//	        btn34.setName("积分查询");  
//	        btn34.setType("view");  
//	        btn34.setUrl("http://whsf.tunnel.mobi/whsf/msg/integralQuery");  
	        
	        ViewButton btn35 = new ViewButton();  
	        btn35.setName("地址导航");  
	        btn35.setType("view");  
	        btn35.setUrl("http://whsf.tunnel.mobi/whsf/msg/Map");  
	  
	        
	        ComButton mainBtn1 = new ComButton();  
	        mainBtn1.setName("业务功能");  
	        mainBtn1.setSub_button(new Button[] { btn11, btn12});  
	  
	        ComButton mainBtn2 = new ComButton();  
	        mainBtn2.setName("中心服务");  
	        mainBtn2.setSub_button(new Button[] { btn21, btn22, btn23, btn24 });  
	  
	        ComButton mainBtn3 = new ComButton();  
	        mainBtn3.setName("个人中心");  
	        mainBtn3.setSub_button(new Button[] { btn31, btn32, btn33, btn35 });  
	  
	        /** 
	         * 这是公众号javenlife目前的菜单结构，每个一级菜单都有二级菜单项<br> 
	         *  
	         * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br> 
	         * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br> 
	         * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 }); 
	         */  
	        Menu menu = new Menu();  
	        menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });  
	  
	        return menu;  
	    }
}
