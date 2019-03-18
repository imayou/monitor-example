package com.ayou.sentinel.dsentinel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DsentinelApplication {

	public static void main(String[] args) {
		// start embedded zookeeper server
		// new EmbeddedZooKeeper(2181, false).start();
		
//		FlowRule sayHello = new FlowRule("sayHello");
//		// set limit qps to 100
//		sayHello.setCount(100);
//		sayHello.setGrade(RuleConstant.FLOW_GRADE_QPS);
//		sayHello.setLimitApp("default");
//
//		FlowRule sayAyou = new FlowRule("sayAyou");
//		// set limit qps to 100
//		sayAyou.setCount(100);
//		sayAyou.setGrade(RuleConstant.FLOW_GRADE_QPS);
//		sayAyou.setLimitApp("default");
//
//		FlowRule smaile = new FlowRule("smaile");
//		// set limit qps to 100
//		smaile.setCount(100);
//		smaile.setGrade(RuleConstant.FLOW_GRADE_QPS);
//		smaile.setLimitApp("default");
//
//		List<FlowRule> rules = new ArrayList<>();
//		//rules.add(sayHello);
//		//rules.add(sayAyou);
//		rules.add(smaile);
//		FlowRuleManager.loadRules(rules);
		
		SpringApplication.run(DsentinelApplication.class, args);
	}

}
