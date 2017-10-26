package com.dqc.guava.learn.eventbus.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dqc.guava.learn.eventbus.events.Apple;
import com.dqc.guava.learn.eventbus.events.Fruit;
import com.google.common.eventbus.Subscribe;
/**
 * 
 * @Description 
 * @author wssjdi@gmail.com
 * @date 2017年5月12日 下午10:02:44
 */
public class FruitListenerTester {

	private final static Logger LOGGER = LoggerFactory.getLogger(FruitListenerTester.class);
	
	@Subscribe
	public void eat(Fruit event){
		if(LOGGER.isInfoEnabled()){
			LOGGER.info("Fruit eat a [{}] and !",event.getName());
		}
	}
	
	@Subscribe
	public void eat(Apple event){
		if(LOGGER.isInfoEnabled()){
			LOGGER.info("Apple eat a [{}] and !",event.getName());
		}
	}

}
