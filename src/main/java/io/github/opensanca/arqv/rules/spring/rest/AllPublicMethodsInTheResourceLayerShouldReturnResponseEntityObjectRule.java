package io.github.opensanca.arqv.rules.spring.rest;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.all;
import static io.github.opensanca.arqv.utils.ArqvUtils.areDefinedInAPackage;
import static io.github.opensanca.arqv.utils.ArqvUtils.arePublic;
import static io.github.opensanca.arqv.utils.ArqvUtils.methods;
import static io.github.opensanca.arqv.utils.ArqvUtils.returnType;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;
import org.springframework.http.ResponseEntity;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "")
public class AllPublicMethodsInTheResourceLayerShouldReturnResponseEntityObjectRule {

    private final static String PATH_PACKAGE_RESOURCE = "..resources..";

    @ArchTest
    public static ArchRule ALL_PUBLIC_METHODS_IN_THE_RESOURCE_LAYER_SHOULD_RETURN_RESPONSE_ENTITY_OBJECT =
            all(methods())
                    .that(areDefinedInAPackage(PATH_PACKAGE_RESOURCE))
                    .and(arePublic())
                    .should(returnType(ResponseEntity.class))
                    .because("Use ResponseEntity to return any thing in endpoints.");

}
