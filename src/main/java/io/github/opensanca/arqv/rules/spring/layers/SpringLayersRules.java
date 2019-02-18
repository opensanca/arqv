package io.github.opensanca.arqv.rules.spring.layers;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;
import org.junit.runner.RunWith;


@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "")
public class SpringLayersRules {

    @ArchTest
    public static ArchRule SPRING_LAYERS_RULES = Architectures.layeredArchitecture()
            .layer("Resource").definedBy("..resources..")
            .layer("Service").definedBy("..services..")
            .layer("Repository").definedBy("..repositories..")
            .whereLayer("Resource").mayNotBeAccessedByAnyLayer()
            .whereLayer("Service").mayOnlyBeAccessedByLayers("Resource")
            .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service");

}
