package com.fintonic.configuration;

import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class ScenarioContext extends ConcurrentHashMap<String, Object> {
}
