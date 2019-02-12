package io.github.opensanca.arqv.rules.rest.spring;

import static com.tngtech.archunit.core.domain.properties.CanBeAnnotated.Predicates.annotatedWith;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.all;
import static io.github.opensanca.arqv.utils.ArqvUtils.methods;
import static io.github.opensanca.arqv.utils.ArqvUtils.areDefinedInAPackage;
import static io.github.opensanca.arqv.utils.ArqvUtils.arePublic;
import static io.github.opensanca.arqv.utils.ArqvUtils.returnStatusCode;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import io.github.opensanca.arqv.enums.StatusCode;
import org.junit.runner.RunWith;
import org.springframework.web.bind.annotation.GetMapping;

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
                    .should(returnStatusCode(StatusCode.OK));

}
