package com.dqc.guava.learn.eventbus.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.eventbus.Subscribe;

/**
 * 
 * @Description 
 * @author wssjdi@gmail.com
 * @date 2017年5月12日 下午8:55:55
 */
public class ListenerTester {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(ListenerTester.class);
	
	@Subscribe
	public void doHandler(final String event){
		if(LOGGER.isInfoEnabled()){
			LOGGER.info("recived a event [{}] and will hanle it !",event);
		}
	}
	
	

}
