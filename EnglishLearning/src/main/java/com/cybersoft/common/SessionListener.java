package com.cybersoft.common;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {
	

	public void sessionCreated(HttpSessionEvent event) {
		System.out.println("session created");
        event.getSession().setMaxInactiveInterval(500);
		
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		System.out.println("session destroyed");
		
	}

}
