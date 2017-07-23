package com.test;

import org.junit.Test;

public class TestDemo {
	@Test
	public void test1(){
		int c=2;
		System.out.println(c);
		int k=c++;
		System.out.println(k);
		System.out.println(c);
	}
}
