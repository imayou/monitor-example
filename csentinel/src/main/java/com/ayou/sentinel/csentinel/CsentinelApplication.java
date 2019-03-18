package com.ayou.sentinel.csentinel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.dubbo.config.annotation.Reference;
import com.ayou.sentinel.HelloService;

@SpringBootApplication
public class CsentinelApplication {

	@Reference(version = "1.0.0")
	private HelloService demoService;

	public static void main(String[] args) {
		FlowRule sayHello = new FlowRule("sayHello");
		// set limit qps to 100
		sayHello.setCount(100);
		sayHello.setGrade(RuleConstant.FLOW_GRADE_QPS);
		sayHello.setLimitApp("default");

		FlowRule sayAyou = new FlowRule("sayAyou");
		// set limit qps to 100
		sayAyou.setCount(100);
		sayAyou.setGrade(RuleConstant.FLOW_GRADE_QPS);
		sayAyou.setLimitApp("default");

		FlowRule smaile = new FlowRule("smaile");
		// set limit qps to 100
		smaile.setCount(100);
		smaile.setGrade(RuleConstant.FLOW_GRADE_QPS);
		smaile.setLimitApp("default");

		List<FlowRule> rules = new ArrayList<>();
		rules.add(sayHello);
		rules.add(sayAyou);
		rules.add(smaile);
		FlowRuleManager.loadRules(rules);

		SpringApplication.run(CsentinelApplication.class, args).close();
	}

	// 原函数
	@SentinelResource(value = "client", blockHandler = "exceptionHandler", fallback = "helloFallback")
	@PostConstruct
	public void init() {
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(20);
		Semaphore semaphore = new Semaphore(0,true);
		try {
			for (int i = 0; i < 1000; i++) {
				final int index = i;
				fixedThreadPool.execute(()->{
					try {
						int random=(int)(Math.random()*3+1);
						TimeUnit.SECONDS.sleep(random);
						String sayHello = demoService.sayHello("world");
						System.out.println(index+"---"+sayHello);
					} catch (Exception e) {
						e.printStackTrace();
					}finally {
						semaphore.release();
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				semaphore.acquire(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			fixedThreadPool.shutdown();
		}
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
