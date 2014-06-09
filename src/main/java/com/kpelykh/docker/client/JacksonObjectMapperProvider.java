package com.kpelykh.docker.client;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

@Provider
public class JacksonObjectMapperProvider implements ContextResolver<ObjectMapper> {

	private ObjectMapper mapper = new ObjectMapper();

	public JacksonObjectMapperProvider() {
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	@Override
	public ObjectMapper getContext(Class<?> type) {
		return mapper;
	}

}
