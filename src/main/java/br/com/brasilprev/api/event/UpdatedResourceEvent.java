package br.com.brasilprev.api.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

@Getter
public class UpdatedResourceEvent extends ApplicationEvent {

    private static final long serialVersionUID = -9090869656257543071L;

    private HttpServletResponse response;
    private Long code;

    public UpdatedResourceEvent(Object source, HttpServletResponse response, Long code) {
        super(source);
        this.response = response;
        this.code = code;
    }
}
