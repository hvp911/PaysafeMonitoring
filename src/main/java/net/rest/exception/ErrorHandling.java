package net.rest.exception;

import com.google.inject.AbstractModule;
import net.rest.exception.ApiExceptionMapper;

/**
 * ErrorHandlingModule
 */
public class ErrorHandling extends AbstractModule {
	@Override
	protected void configure() {
		bind(ApiExceptionMapper.class).toInstance(new ApiExceptionMapper());
		bind(InvalidRequestExceptionMapper.class).toInstance(new InvalidRequestExceptionMapper());
	}
}
