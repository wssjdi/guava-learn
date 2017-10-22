package com.dqc.guava.learn.eventbus;

import com.dqc.guava.learn.eventbus.listeners.ListenerTester;
import com.google.common.eventbus.EventBus;

/**
 * @Description 
 * @author wssjdi@gmail.com
 * @date 2017年5月12日 下午8:58:51
 */
public class EventBusTester {

	public static void main(String[] args) {
		
		final EventBus bus = new EventBus();
		bus.register(new ListenerTester());
		System.out.println("post a simple event !");
		bus.post("This is a simple message !");
	}
	
	
	
}
