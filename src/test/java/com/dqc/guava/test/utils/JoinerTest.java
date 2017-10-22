package com.dqc.guava.test.utils;

import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Files;

/**
 * 
 * @Description 
 * @author wssjdi@gmail.com
 * @date 2017年05月12日 下午7:19:54
 */
public class JoinerTest {
	
	private final List<String> stringList = Arrays.asList("Google","Guava","Java","Scala","KafKa");

	private final List<String> stringListWithNull = Arrays.asList("Google","Guava","Java","Scala","KafKa",null);
	
	@Test
	public void testJoinOnJoin(){
		String result = Joiner.on("#").join(stringList);
		Assert.assertEquals(result,"Google#Guava#Java#Scala#KafKa");
	}

	
	@Test
	public void testJoinOnJoinWithoutNull(){
		String result = Joiner.on("#").skipNulls().join(stringListWithNull);
		Assert.assertEquals(result,"Google#Guava#Java#Scala#KafKa");
	}

	@Test
	public void testJoinOnJoinWithNullToDefault(){
		String result = Joiner.on("#").useForNull("DEFAULT").join(stringListWithNull);
		Assert.assertEquals(result,"Google#Guava#Java#Scala#KafKa#DEFAULT");
	}
	

	@Test
	public void testJoinOnAppend(){
		final StringBuilder sb = new StringBuilder();
		StringBuilder nsb = Joiner.on("#").useForNull("DEFAULT").appendTo(sb, stringListWithNull);
		Assert.assertEquals(nsb.toString(),"Google#Guava#Java#Scala#KafKa#DEFAULT");
		Assert.assertSame(sb, nsb);
	}
	
	private final String myFileName = "E:\\learnspace\\guava-joiner-test-file.log";
	

//	@Test
//	public void testJoinOnFile(){
//		try {
//			File file = new File(myFileName);
//			FileWriter fw = new FileWriter(file);
//
//			Joiner.on("#").useForNull("DEFAULT").appendTo(fw, stringListWithNull);
//			fw.flush();
//			Assert.assertTrue(Files.isFile().test(file));
//		} catch (Exception e) {
//			Assert.fail("Joiner append to file failed !");		
//		}
//	}
	
	private final Map<String, String> stringMap = ImmutableMap.of("Java", "Hello", "Scale", "World");
			


	@Test
	public void testJoinOnMap(){
		String result = Joiner.on("#").withKeyValueSeparator("=").join(stringMap);
		Assert.assertEquals(result,"Java=Hello#Scale=World");
	}


	@Test
	public void testJoinOnMapAppendFile(){
		try {
			File file = new File(myFileName);
			FileWriter fw = new FileWriter(file);
			Joiner.on("#").withKeyValueSeparator("=").appendTo(fw, stringMap);
			fw.flush();
			Assert.assertTrue(Files.isFile().test(file));
		} catch (Exception e) {
			Assert.fail("Joiner append to file failed !");		
		}
	}
	
	
	
	
	
	
	
	@Test
	public void testJoinOnStream(){
		String result = stringListWithNull.stream().filter(item->item!=null && !item.isEmpty()).collect(Collectors.joining("#"));
		Assert.assertEquals(result,"Google#Guava#Java#Scala#KafKa");
	}
	


	@Test
	public void testJoinOnStreamNullToDefault(){
		String result = stringListWithNull.stream()
				.map(item->item==null || item.isEmpty()?"DEFAULT":item)
				.collect(Collectors.joining("#"));
		Assert.assertEquals(result,"Google#Guava#Java#Scala#KafKa#DEFAULT");
	}
	

	@Test
	public void testJoinOnStreamNullToDefaultByAMethod(){
		String result = stringListWithNull.stream()
				.map(this::getMyValue)
				.collect(Collectors.joining("#"));
		Assert.assertEquals(result,"Google#Guava#Java#Scala#KafKa#DEFAULT");
	}
	
	private String getMyValue(final String value){
		return value == null || value.isEmpty() ? "DEFAULT" : value;
	}
	
	
}
