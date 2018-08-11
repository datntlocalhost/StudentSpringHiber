package com.runsystem.datnt;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("aop-context.xml");
		
		Test test = context.getBean("test", Test.class);
		
		test.display("asd");
	}

}
