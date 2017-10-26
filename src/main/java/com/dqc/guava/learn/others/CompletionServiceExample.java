package com.dqc.guava.learn.others;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @Description 并发编程的一个小陷阱
 * @author wssjdi@gmail.com
 * @date 2017年10月26日 下午10:47:41
 */
public class CompletionServiceExample {

	private final static Logger LOGGER = LoggerFactory.getLogger(CompletionServiceExample.class);
	
	public static void main(String[] args) {
		
//		final ExecutorService service  = Executors.newFixedThreadPool(5);
//		List<Runnable> tasks = IntStream.range(0, 5).boxed()
//				.map(CompletionServiceExample::toTask).collect(Collectors.toList());
//		List<Future<?>> futureList = Lists.newArrayList();
//		tasks.forEach(r-> futureList.add(service.submit(r)));
//		for(Future<?> f :  futureList){
//			try {
//				f.get();
//				LOGGER.info("======");
//			} catch (InterruptedException | ExecutionException e) {
//				e.printStackTrace();
//			}
//		}

//		final ExecutorService service  = Executors.newSingleThreadExecutor();
//		final CompletionService<Object> completionService = new ExecutorCompletionService<Object>(service);
//		tasks.forEach(r-> completionService.submit(Executors.callable(r)));
//		Future<?> f ;
//		try {			
//			TimeUnit.SECONDS.sleep(12);
//			List<Runnable> runnables = service.shutdownNow();
//			LOGGER.info(String.valueOf(runnables));
//			while((f = completionService.take())!=null){
//				LOGGER.info(String.valueOf(f.get()));
//			}
//		} catch (InterruptedException | ExecutionException e) {
//			e.printStackTrace();
//		}
		

		final ExecutorService service  = Executors.newSingleThreadExecutor();
		final CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(service);
		List<Callable<Integer>> tasks = IntStream.range(0, 5).boxed()
				.map(MyTask::new).collect(Collectors.toList());		

		tasks.forEach(r-> completionService.submit(r));
		try {
			TimeUnit.SECONDS.sleep(12);
			service.shutdownNow();
			tasks.stream().filter(t -> !((MyTask)t).isSuccess()).forEach(t->LOGGER.info(String.valueOf(t)));;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	
	}	

	private static Runnable toTask(int i){
		return ()->{
			try {
				LOGGER.error("the task {} will be started", i);
				TimeUnit.SECONDS.sleep(i*5+5);
				LOGGER.error("the task {} done", i);
			} catch (InterruptedException e) {
				LOGGER.error("the task {} be interrupted", i);
				e.printStackTrace();
			}
			
		};
	}

	private static class MyTask implements Callable<Integer>{

		private boolean success = false;
		
		private Integer value;
		
		public MyTask(Integer value) {
			this.value = value;
		}
		
		@Override
		public Integer call() throws Exception {
			try {
				LOGGER.info("the task {} will be started", value);
				TimeUnit.SECONDS.sleep(value*5+5);
				LOGGER.info("the task {} done", value);
				this.success = true;
			} catch (InterruptedException e) {
				LOGGER.error("the task {} be interrupted", value);
				e.printStackTrace();
			}
			return value;
		}

		public boolean isSuccess() {
			return success;
		}

		public Integer getValue() {
			return value;
		}
		
	}
	
	
	
	
	
}
