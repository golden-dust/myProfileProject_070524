package com.goldendust.profile.utility;

import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionUtil {
	
	public static String getSid(HttpServletRequest request) {
		Optional<HttpSession> sessionOptional = Optional.ofNullable(request.getSession(false));
		if (sessionOptional.isPresent()) {
			HttpSession session = sessionOptional.get();
			Optional<String> sidOptional = Optional.ofNullable((String) session.getAttribute("sid"));
			if (sidOptional.isPresent()) {
				return sidOptional.get();
			} 
		}
		
		return null;
	}

}
