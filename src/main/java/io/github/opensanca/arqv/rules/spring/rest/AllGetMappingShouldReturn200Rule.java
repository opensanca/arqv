package io.github.opensanca.arqv.rules.spring.rest;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import io.github.opensanca.arqv.enums.StatusCode;
import org.junit.runner.RunWith;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.tngtech.archunit.core.domain.properties.CanBeAnnotated.Predicates.annotatedWith;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.all;
import static io.github.opensanca.arqv.utils.ArqvUtils.*;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "")
public class AllGetMappingShouldReturn200Rule {

    private final static String PATH_PACKAGE_RESOURCE = "..resources..";

    @ArchTest
    public static ArchRule ALL_GET_MAPPING_SHOULD_RETURN_200 =
            all(methods())
                    .that(areDefinedInAPackage(PATH_PACKAGE_RESOURCE))
                    .and(arePublic())
                    .and(annotatedWith(GetMapping.class))
                    .or(annotatedWithRequestMappingWithHttpMethod(RequestMethod.GET))
                    .should(returnStatusCode(StatusCode.OK));

}
