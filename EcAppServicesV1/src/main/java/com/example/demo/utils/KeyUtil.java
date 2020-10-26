package com.example.demo.utils;

import java.util.Random;

public class KeyUtil {
	/*
	 * generate a primary key
	 * format: time + random number
	 * @return
	 * */
	public static synchronized String genUniqueKey() {
		Random random = new Random();
		Integer a = random.nextInt(900000) + 100000;
		return System.currentTimeMillis() + String.valueOf(a);
	}
}
