package com.imooc.maven02.util;

import com.imooc.maven01.model.HelloWorld;

public class Speak{
	public String sayHi(){
		return new HelloWorld().sayHello();
	}
}