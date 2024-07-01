package com.back.reservoirmanagement.config;

import com.back.reservoirmanagement.common.json.JacksonObjectMapper;
import com.back.reservoirmanagement.interceptor.JwtTokenAdminInterceptor;
import com.back.reservoirmanagement.interceptor.JwtTokenUserInterceptor;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Configuration
@EnableSwagger2
@EnableKnife4j
@Slf4j
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;

    @Autowired
    private JwtTokenUserInterceptor jwtTokenUserInterceptor;
    /**
     * 注册自定义拦截器
     *
     * @param registry
     */
    protected void addInterceptors(InterceptorRegistry registry) {
        log.info("开始注册自定义拦截器...");

        // 管理端
        registry.addInterceptor(jwtTokenAdminInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/user/login");

        //用户端
        registry.addInterceptor(jwtTokenUserInterceptor)
                .addPathPatterns("/app/**")
                .excludePathPatterns("/app/user/login");
//                .excludePathPatterns("/app/**");
    }

    /**
     * 扩展mvc框架的消息转换器
     * @param converters
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //创建消息转换器对象
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        //设置对象转换器，底层使用Jackson将Java对象转为json
        messageConverter.setObjectMapper(new JacksonObjectMapper());
        //将上面的消息转换器对象追加到mvc框架的转换器集合中
        converters.add(0,messageConverter);
    }

    /**
     * 配置静态资源映射
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

//    @Bean
//    public Docket createRestApi() {
//        // 文档类型
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.back.reservoirmanagement.controller"))
//                .paths(PathSelectors.any())
//                .build();
//    }
    /**
     * 通过knife4j生成管理端接口文档
     * @return
     */
    @Bean
    public Docket docketAdmin() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("水库项目接口文档")
                .version("2.0")
                .description("水库项目接口文档")
                .build();

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("管理端接口")
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.back.reservoirmanagement.controller.admin"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    /**
     * 通过knife4j生成用户端接口文档
     * @return
     */
    @Bean
    public Docket docketUser() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("水库项目接口文档")
                .version("2.0")
                .description("水库项目接口文档")
                .build();

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("用户端接口")
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.back.reservoirmanagement.controller.app"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    @Bean
    public Docket dockerCommon() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("通用接口")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.back.reservoirmanagement.controller.common"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("水库项目")
                .version("2.0")
                .description("水库项目接口文档")
                .build();
    }
}