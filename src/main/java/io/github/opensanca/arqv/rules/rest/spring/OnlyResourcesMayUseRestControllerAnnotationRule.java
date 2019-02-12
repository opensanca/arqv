package io.github.opensanca.arqv.rules.rest.spring;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;
import org.springframework.web.bind.annotation.RestController;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "")
public class OnlyResourcesMayUseRestControllerAnnotationRule {

    private final static String PATH_PACKAGE_RESOURCE = "..resources..";

    @ArchTest
    public static final ArchRule ONLY_RESOURCES_MAY_USE_REST_CONTROLLER_ANNOTATION =
            noClasses()
                    .that()
                    .resideOutsideOfPackage(PATH_PACKAGE_RESOURCE)
                    .should()
                    .accessClassesThat()
                    .areAssignableTo(RestController.class)
                    .as("Only Resources may use the " + RestController.class.getSimpleName());

}
