package io.github.opensanca.arqv.rules.rest.spring;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "")
public class ResourcesShouldBeInSpecificPackageRule {

    private final static String PATH_PACKAGE_RESOURCE = "..resources..";

    @ArchTest
    static final ArchRule RESOURCES_SHOULD_BE_IN_SPECIFIC_PACKAGE =
            classes().that()
                    .haveNameMatching(".*Resource")
                    .should()
                    .resideInAPackage(PATH_PACKAGE_RESOURCE)
                    .as("Resources should reside in a package '..resources..'");

}
