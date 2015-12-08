package com.jfinal.weixin.demo;

import com.jfinal.core.Controller;

public class IndexController extends Controller{
	public void index() {
		setAttr("test", "这里是测试...");
		render("/index.jsp");
	}
	
	public void test() {
		setAttr("Testad", "这里是测试...");
		render("index.jsp");
	}
	
	
}
