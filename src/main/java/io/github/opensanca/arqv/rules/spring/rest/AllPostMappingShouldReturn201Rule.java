package io.github.opensanca.arqv.rules.spring.rest;

import static com.tngtech.archunit.core.domain.properties.CanBeAnnotated.Predicates.annotatedWith;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.all;
import static io.github.opensanca.arqv.utils.ArqvUtils.*;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import io.github.opensanca.arqv.enums.StatusCode;
import org.junit.runner.RunWith;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "")
public class AllPostMappingShouldReturn201Rule {

    private final static String PATH_PACKAGE_RESOURCE = "..resources..";

    @ArchTest
    public static ArchRule ALL_POST_MAPPING_SHOULD_RETURN_201 =
            all(methods())
                    .that(areDefinedInAPackage(PATH_PACKAGE_RESOURCE))
                    .and(arePublic())
                    .and(annotatedWith(PostMapping.class))
                    .or(annotatedWithRequestMappingWithHttpMethod(RequestMethod.POST))
                    .should(returnStatusCode(StatusCode.CREATED));

}
