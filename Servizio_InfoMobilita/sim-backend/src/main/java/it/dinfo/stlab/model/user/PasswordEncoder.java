package it.dinfo.stlab.model.user;

import java.security.MessageDigest;
import org.apache.commons.codec.binary.Base64;

public class PasswordEncoder {

	public static String encodePassword(String plainPassword) {
		if (plainPassword == null) {
			throw new IllegalArgumentException("password cannot be null");
		}

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainPassword.getBytes("UTF-8"));
			return new String(Base64.encodeBase64(md.digest()));
		} catch (Exception e) {
			throw new RuntimeException("algorithm not found");
		}

	}
}
