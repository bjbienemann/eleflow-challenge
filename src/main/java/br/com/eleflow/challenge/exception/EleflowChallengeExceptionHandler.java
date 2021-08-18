package br.com.eleflow.challenge.exception;

import java.util.Optional;

import org.springframework.boot.autoconfigure.web.WebProperties.Resources;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
@Order(-2)
public class EleflowChallengeExceptionHandler extends AbstractErrorWebExceptionHandler {


	public EleflowChallengeExceptionHandler(ErrorAttributes errorAttributes,
			Resources resources, ApplicationContext applicationContext, ServerCodecConfigurer codecConfigurer) {
		
		super(errorAttributes, resources, applicationContext);
		
		this.setMessageWriters(codecConfigurer.getWriters());
	}

	@Override
	protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
		return RouterFunctions.route(RequestPredicates.all(), this::handlerFunction);
	}
	
	
	private Mono<ServerResponse> handlerFunction(ServerRequest request) {
		var errorAttributes = getErrorAttributes(request, ErrorAttributeOptions.defaults());
		var status = (int) Optional.ofNullable(errorAttributes.get("status")).orElse(500);
		
		return ServerResponse.status(status)
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(errorAttributes));
	}
}
