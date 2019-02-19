package io.github.opensanca.arqv.rules.spring.repositoy;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import io.github.opensanca.arqv.constants.Constants;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Repository;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(
        packages = "",
        importOptions = ImportOption.DontIncludeTests.class
)
public class OnlyRepositoriesMayUseRepositoryAnnotationRule {

    @ArchTest
    public static ArchRule ONLY_REPOSITORIES_MAY_USE_REPOSITORY_ANNOTATION =
        noClasses()
            .that()
                .resideOutsideOfPackage(Constants.REPOSITORY)
            .should()
                .accessClassesThat()
                .areAssignableTo(Repository.class)
            .as("Only Repositories may use " + Repository.class.getSimpleName());

}
