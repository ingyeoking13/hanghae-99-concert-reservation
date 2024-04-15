package reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import reservation.Controller.util.TicketValidatorInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private TicketValidatorInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                .addPathPatterns("/concerts/*");
    }
}