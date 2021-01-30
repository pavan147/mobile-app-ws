package com.mobiledeveloperblog.app.ws.shared;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class Utils {

	private final Random random = new SecureRandom();
	private final String alphabets = "poiutrewqasdfghjklmnbvcxz123654789!@#$%^&*()_+:[] {}?/>.|+=_-<,";

	public String generateUserId(int length) {
		return generateRandomString(length);
	}

	private String generateRandomString(int length) {

		StringBuilder returnValue = new StringBuilder(length);

		for (int i = 0; i < length; i++) {

			returnValue.append(alphabets.charAt(random.nextInt(alphabets.length())));
		}
		return new String(returnValue);
	}

}
