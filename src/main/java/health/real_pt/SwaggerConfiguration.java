package health.real_pt;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@Profile({"local","dev"})
@Configuration
@EnableSwagger2
public class SwaggerConfiguration extends WebMvcConfigurationSupport {

    @Bean
    public Docket apiV1() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/v1/api/**"))
                .build()
                .apiInfo(apiInfo())
                .enable(true);
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("API 타이틀")
                .description("API 상세소개 및 사용법 등..")
                .version("1.0")
                .build();
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

//
//    @Bean
//    public Docket petApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.ant("/api/**"))
//                .build()
//                .pathMapping("/")
//                .directModelSubstitute(LocalDate.class, String.class)
//                .genericModelSubstitutes(ResponseEntity.class)
//                .alternateTypeRules(
//                        newRule(typeResolver.resolve(DeferredResult.class,
//                                        typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
//                                typeResolver.resolve(WildcardType.class)))
//                .useDefaultResponseMessages(false)
//                .globalResponses(HttpMethod.GET,
//                        singletonList(new ResponseBuilder()
//                                .code("500")
//                                .description("500 message")
//                                .representation(MediaType.TEXT_XML)
//                                .apply(r ->
//                                        r.model(m ->
//                                                m.referenceModel(ref ->
//                                                        ref.key(k ->
//                                                                k.qualifiedModelName(q ->
//                                                                        q.namespace("some:namespace")
//                                                                                .name("ERROR"))))))
//                                .build()))
//                .securitySchemes(singletonList(apiKey()))
//                .securityContexts(singletonList(securityContext()))
//                .enableUrlTemplating(true)
//                .globalRequestParameters(
//                        singletonList(new springfox.documentation.builders.RequestParameterBuilder()
//                                .name("someGlobalParameter")
//                                .description("Description of someGlobalParameter")
//                                .in(ParameterType.QUERY)
//                                .required(true)
//                                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
//                                .build()))
//                .tags(new Tag("Pet Service", "All apis relating to pets"))
//                .additionalModels(typeResolver.resolve(Addition.class));
//    }
//
//    @Autowired
//    private TypeResolver typeResolver;
//
//    private ApiKey apiKey() {
//        return new ApiKey("mykey", "api_key", "header");
//    }
//
//    private SecurityContext securityContext() {
//        return SecurityContext.builder()
//                .securityReferences(defaultAuth())
//                .forPaths(PathSelectors.regex("/anyPath.*"))
//                .build();
//    }
//
//    List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope
//                = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return singletonList(
//                new SecurityReference("mykey", authorizationScopes));
//    }
//
//    @Bean
//    SecurityConfiguration security() {
//        return SecurityConfigurationBuilder.builder()
//                .clientId("test-app-client-id")
//                .clientSecret("test-app-client-secret")
//                .realm("test-app-realm")
//                .appName("test-app")
//                .scopeSeparator(",")
//                .additionalQueryStringParams(null)
//                .useBasicAuthenticationWithAccessCodeGrant(false)
//                .enableCsrfSupport(false)
//                .build();
//    }
//
//    @Bean
//    UiConfiguration uiConfig() {
//        return UiConfigurationBuilder.builder()
//                .deepLinking(true)
//                .displayOperationId(false)
//                .defaultModelsExpandDepth(1)
//                .defaultModelExpandDepth(1)
//                .defaultModelRendering(ModelRendering.EXAMPLE)
//                .displayRequestDuration(false)
//                .docExpansion(DocExpansion.NONE)
//                .filter(false)
//                .maxDisplayedTags(null)
//                .operationsSorter(OperationsSorter.ALPHA)
//                .showExtensions(false)
//                .showCommonExtensions(false)
//                .tagsSorter(TagsSorter.ALPHA)
//                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
//                .validatorUrl(null)
//                .build();
//    }

}