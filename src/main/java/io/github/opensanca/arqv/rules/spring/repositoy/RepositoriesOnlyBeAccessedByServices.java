package io.github.opensanca.arqv.rules.spring.repositoy;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Service;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(
        packages = "",
        importOptions = ImportOption.DontIncludeTests.class
)
public class RepositoriesOnlyBeAccessedByServices {

    @ArchTest
    public static ArchRule REPOSITORIES_ONLY_BE_ACCESSED_BY_SERVICES =
        classes()
            .that()
                .haveNameMatching(".*Repository")
            .should()
                .onlyBeAccessed()
                .byClassesThat()
                .areAnnotatedWith(Service.class)
            .as("Repositories sohuld be access by @Service");

}
