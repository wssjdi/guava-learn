package com.dqc.guava.test.utils;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.base.Splitter;

/**
 * 
 * @Description 
 * @author wssjdi@gmail.com
 * @date 2017年5月12日 下午8:23:27
 */
public class SplitterTest {
	
	@Test
	public void testSplitOnSpliter(){
		List<String> result = Splitter.on("|").splitToList("Hello|World");
		Assert.assertEquals(result.size(), 2);
		Assert.assertEquals(result.get(0), "Hello");
		Assert.assertEquals(result.get(1), "World");
	}

	@Test
	public void testSplitOnOmitEmpty(){
		List<String> result = Splitter.on("|").omitEmptyStrings().splitToList("Hello|World|||");
		Assert.assertEquals(result.size(), 2);
		Assert.assertEquals(result.get(0), "Hello");
		Assert.assertEquals(result.get(1), "World");
	}
	

	@Test
	public void testSplitOnOmitEmptyWithTrim(){
		List<String> result = Splitter.on("|").omitEmptyStrings().trimResults().splitToList("Hello | World|China very good||");
		Assert.assertEquals(result.size(), 3);
		Assert.assertEquals(result.get(0), "Hello");
		Assert.assertEquals(result.get(1), "World");
	}



	@Test
	public void testSplitOnFixedlength(){
		List<String> result = Splitter.fixedLength(4).splitToList("1234567890");
		Assert.assertEquals(result.size(), 3);
		Assert.assertEquals(result.get(0), "1234");
		Assert.assertEquals(result.get(1), "5678");
		Assert.assertEquals(result.get(2), "90");
	}


	@Test
	public void testSplitOnOmitEmptyWithLimit(){
//		Splitter.onPattern("\\|");
//		Splitter.on(Pattern.compile("\\|"));
		List<String> result = Splitter.on("|").omitEmptyStrings().trimResults().limit(3).splitToList("Hello | World| China |Very|good||");
		Assert.assertEquals(result.size(), 3);
		Assert.assertEquals(result.get(0), "Hello");
		Assert.assertEquals(result.get(1), "World");
		Assert.assertEquals(result.get(2), "China |Very|good||");
	}
	
	@Test
	public void testSplitOnMap(){
		Map<String,String> result = Splitter.on("|").omitEmptyStrings().trimResults().withKeyValueSeparator("=").split("a=A | b=B| c=C |d=D|e=E||");
		Assert.assertEquals(result.size(), 5);
		Assert.assertEquals(result.get("a"), "A");
		Assert.assertEquals(result.get("b"), "B");
		Assert.assertEquals(result.get("c"), "C");
	}
	
}
