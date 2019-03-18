package com.ayou.sentinel;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.dubbo.config.annotation.Service;

@Service(version = "1.0.0", filter="tracing")
public class HelloServiceImpl implements HelloService {

	// 原函数
	//@SentinelResource(value = "sayHello", blockHandler = "exceptionHandler", fallback = "helloFallback")
	@Override
	public String sayHello(String name) {
		// throw new RuntimeException();
		int random=(int)(Math.random()*3+1);
		try {
			TimeUnit.SECONDS.sleep(random);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "Hello, " + name + ", " + new Date();
	}

	// 原函数
	//@SentinelResource(value = "sayAyou", blockHandler = "exceptionHandler", fallback = "helloFallback")
	@Override
	public String sayAyou(String name) {
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "sayAyou";
	}

	// 原函数
	//@SentinelResource(value = "smaile", blockHandler = "exceptionHandler", fallback = "helloFallback")
	@Override
	public String smaile(String name) {
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "smaile";
	}

	// Fallback 函数，函数签名与原函数一致.
	public String helloFallback(long s) {
		return String.format("Halooooo %d", s);
	}

	// Block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致.
	public String exceptionHandler(long s, BlockException ex) {
		// Do some log here.
		ex.printStackTrace();
		return "Oops, error occurred at " + s;
	}
}