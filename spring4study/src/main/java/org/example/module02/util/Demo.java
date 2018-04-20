package org.example.module02.util;

import java.util.regex.Pattern;

public class Demo {

	public Demo() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] arg) {
		String userName = "^([a-zA-Z0-9])(([\\-.]|[_]+)?([a-zA-Z0-9]+))*(@){1}[a-z0-9]+[.]{1}(([a-z]{2,3})|([a-z]{2,3}[.]{1}[a-z]{2,3}))$" ; //"^(([a-z])+.)+[A-Z]([a-z])+$";
		String password = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa!";

		boolean matches = Pattern.matches(userName, password );

		System.out.println("matches = " + matches);
		
	}
}
