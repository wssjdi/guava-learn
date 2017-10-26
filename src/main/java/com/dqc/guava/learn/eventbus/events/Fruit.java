package com.dqc.guava.learn.eventbus.events;

public class Fruit {

	private final String name ;

	public Fruit(String name) {
		this.name = name ;
	}
	
	public String getName() {
		return name;
	}
}
