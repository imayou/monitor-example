package com.ayou.sentinel.dsentinel;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations = {"zipkin.xml"})
public class ZipkinConfig {
	/*
	 * @Bean public OkHttpSenderFactoryBean sender() { OkHttpSenderFactoryBean
	 * senderBean = new OkHttpSenderFactoryBean();
	 * senderBean.setEndpoint("http://localhost:9411/api/v2/spans"); return
	 * senderBean; }
	 * 
	 * @Bean public TracingFactoryBean tracing() { TracingFactoryBean tracingBean =
	 * new TracingFactoryBean(); tracingBean.setLocalServiceName("provider");
	 * AsyncReporterFactoryBean asyncReporterFactoryBean = new
	 * AsyncReporterFactoryBean(); asyncReporterFactoryBean.setSender(new
	 * OkHttpSender(null)); tracingBean.setSpanReporter(spanReporter); }
	 */
}
