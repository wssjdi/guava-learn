package com.dqc.guava.learn.eventbus;

import com.dqc.guava.learn.eventbus.events.Apple;
import com.dqc.guava.learn.eventbus.events.Fruit;
import com.dqc.guava.learn.eventbus.listeners.FruitListenerTester;
import com.google.common.eventbus.EventBus;

public class FruitEventBusTester {

	public static void main(String[] args) {

		final EventBus bus = new EventBus();
		bus.register(new FruitListenerTester());
		System.out.println("eat a Fruit !");
		bus.post(new Fruit("some fruit"));
		System.out.println("eat an Apple!");
		bus.post(new Apple("Apple"));

	}
}
