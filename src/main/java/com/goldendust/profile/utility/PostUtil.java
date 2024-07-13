package com.goldendust.profile.utility;

public class PostUtil {
	
	public static String enableEnter(String text) {
		return text.replace("\n", "<br>");
	}
	
	public static String transToView(String text) {
		return text.replace("<br>", "\n");
	}

}
