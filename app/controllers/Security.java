package controllers;

import models.User;

public class Security extends Secure {
	public static boolean authenticate(String username, String pw) {
		return User.connect(username) != null;
	}
}
