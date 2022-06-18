package com.anhun.idea_demo.config;

import org.springframework.context.ApplicationContext;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

/**
 * 这里是自定义模板解析器的配置类
 */
//@Configuration
public class ThymeleafConfig {
    //    创建模板解析器的Bean
//    @Primary
//    @Bean
    public SpringResourceTemplateResolver springResourceTemplateResolver(ApplicationContext ctx) {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("classpath:/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setApplicationContext(ctx);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(true);
        return templateResolver;
    }

    //    创建模板引擎的Bean
//    @Primary
//    @Bean
    public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }
}
