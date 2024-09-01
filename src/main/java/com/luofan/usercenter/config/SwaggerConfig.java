package com.luofan.usercenter.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.WebFluxRequestHandlerProvider;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义swagger接口文档配置
 */
@Configuration // 标明是配置类
@EnableSwagger2WebMvc
public class SwaggerConfig {
    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)  // DocumentationType.SWAGGER_2 固定的，代表swagger2
                //.groupName("分布式任务系统") // 如果配置多个文档的时候，那么需要配置groupName来分组标识
                .apiInfo(apiInfo()) // 用于生成API信息
                .select() // select()函数返回一个ApiSelectorBuilder实例,用来控制接口被swagger做成文档
                // 扫描指定包下的接口，最为常用
                .apis(RequestHandlerSelectors.basePackage("com.luofan.usercenter.controller"))
                //.withClassAnnotation(RestController.class) // 扫描带有指定注解的类下所有接口
                //.withMethodAnnotation(PostMapping.class) // 扫描带有指定注解的方法接口
                //.apis(RequestHandlerSelectors.any()) // 扫描所有

                // 选择所有的API,如果你想只为部分API生成文档，可以配置这里
                .paths(PathSelectors.any()
                        //.any() // 满足条件的路径，该断言总为true
                        //.none() // 不满足条件的路径，该断言总为false（可用于生成环境屏蔽 swagger）
                        //.ant("/user/**") // 满足字符串表达式路径
                        //.regex("") // 符合正则的路径
                )
                .build();
    }

    /**
     * 用于定义API主界面的信息，比如可以声明所有的API的总标题、描述、版本
     *
     * @return
     */
    private ApiInfo apiInfo() {

        Contact contact = new Contact(
                "骆凡", // 作者姓名
                "https://github.com/luofanlf", // 作者网址
                "2731305688@qq.com.com"); // 作者邮箱

        return new ApiInfoBuilder()
                .title("伙伴匹配项目API") //  可以用来自定义API的主标题
                .description("伙伴匹配项目SwaggerAPI管理") // 可以用来描述整体的API
                .termsOfServiceUrl("https://www.github.com/luofanlf") // 用于定义服务的域名（跳转链接）
                .version("1.0") // 可以用来定义版本
                .license("Swagger-的使用教程")
                .licenseUrl("https://blog.csdn.net")
                .contact(contact)
                .build(); //
    }
}
//    @Bean
//    public static BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
//        return new BeanPostProcessor() {
//
//            @Override
//            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//                if (bean instanceof WebMvcRequestHandlerProvider || bean instanceof WebFluxRequestHandlerProvider) {
//                    customizeSpringfoxHandlerMappings(getHandlerMappings(bean));
//                }
//                return bean;
//            }
//
//            private <T extends RequestMappingInfoHandlerMapping> void customizeSpringfoxHandlerMappings(List<T> mappings) {
//                List<T> copy = mappings.stream()
//                        .filter(mapping -> mapping.getPatternParser() == null)
//                        .collect(Collectors.toList());
//                mappings.clear();
//                mappings.addAll(copy);
//            }
//
//            @SuppressWarnings("unchecked")
//            private List<RequestMappingInfoHandlerMapping> getHandlerMappings(Object bean) {
//                try {
//                    Field field = ReflectionUtils.findField(bean.getClass(), "handlerMappings");
//                    field.setAccessible(true);
//                    return (List<RequestMappingInfoHandlerMapping>) field.get(bean);
//                } catch (IllegalArgumentException | IllegalAccessException e) {
//                    throw new IllegalStateException(e);
//                }
//            }
//        };
//    }
//}
