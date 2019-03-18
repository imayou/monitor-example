package com.ayou.sentinel;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations = {"zipkin.xml"})
public class ZipkinConfig {
	
}
