package io.github.opensanca.arqv.rules.spring.repositoy;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Service;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;


@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "")
public class RepositoriesOnlyBeAccessedByServices {

    @ArchTest
    public static ArchRule REPOSITORIES_ONLY_BE_ACCESSED_BY_SERVICES = classes().that().haveNameMatching(".*Repository")
            .should().onlyBeAccessed().byClassesThat().areAnnotatedWith(Service.class);

}
