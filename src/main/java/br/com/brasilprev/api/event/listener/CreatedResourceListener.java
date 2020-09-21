package br.com.brasilprev.api.event.listener;

import br.com.brasilprev.api.event.CreatedResourceEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Component
public class CreatedResourceListener implements ApplicationListener<CreatedResourceEvent>{

	@Override
	public void onApplicationEvent(CreatedResourceEvent event) {
		HttpServletResponse response = event.getResponse();
		Long code = event.getCode();
		addHeaderLocation(response, code);
	}

	private void addHeaderLocation(HttpServletResponse response, Long code) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(code).toUri();
			response.setHeader("Location", uri.toASCIIString());
	}

}
