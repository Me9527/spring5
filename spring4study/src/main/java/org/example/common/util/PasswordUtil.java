package org.example.common.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {

	public PasswordUtil() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String arg[]){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String password = "123456";
		System.out.println(encoder.encode("123456".subSequence(0, password.length())) );
	}
}
