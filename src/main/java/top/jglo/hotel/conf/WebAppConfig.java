package top.jglo.hotel.conf;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import top.jglo.hotel.coreinterceptor.CoreInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Gkirito
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {
    //实现拦截器 要拦截的路径以及不拦截的路径
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //注册自定义拦截器，添加拦截路径和排除拦截路径
//        registry.addInterceptor( getCoreInterceptor()).addPathPatterns("/**").excludePathPatterns("/loginPage","/login");
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getCoreInterceptor()).addPathPatterns("/**");
        //.excludePathPatterns("/wxapi/**", "/swagger-ui.html", "/swagger-ui", "/v2/api-docs", "/swagger-resources/**", "/webjars/**")
    }

    @Bean
    public CoreInterceptor getCoreInterceptor() {
        return new CoreInterceptor();
    }
}
