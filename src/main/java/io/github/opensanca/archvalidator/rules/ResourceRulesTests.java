package io.github.opensanca.archvalidator.rules;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import io.github.opensanca.archvalidator.IgnoreTests;
import org.junit.runner.RunWith;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(
        packages = "com.example.demo",
        importOptions = IgnoreTests.class
)
public class ResourceRulesTests {
    
    public static final String RESOURCE_PACKAGE = "..resource..";

    @ArchTest
    static final ArchRule TESTE_1 =
        classes().that()
            .haveSimpleNameContaining("Resource")
        .should()
            .resideInAPackage(RESOURCE_PACKAGE);
}
