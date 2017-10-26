package com.dqc.guava.learn.eventbus;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.dqc.guava.learn.eventbus.listeners.ListenerTester;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

/**
 * @Description 
 * @author wssjdi@gmail.com
 * @date 2017年5月12日 下午8:58:51
 */
public class EventBusTester {

	public static void main(String[] args) {
//		Executor executor = Executors.newFixedThreadPool(10);
//		final AsyncEventBus aBus = new AsyncEventBus(executor);
		final EventBus bus = new EventBus();
		bus.register(new ListenerTester());
		System.out.println("post a simple String event !");
		bus.post("This is a simple message !");
		System.out.println("post a simple Integer event !");
		bus.post(3);
	}
	
	
	
}
