package io.github.opensanca.arqv.enums;

import io.github.opensanca.arqv.rules.spring.layers.SpringLayersRules;
import io.github.opensanca.arqv.rules.spring.repositoy.RepositoriesOnlyBeAccessedByServices;
import io.github.opensanca.arqv.rules.spring.repositoy.RepositoriesShouldBeInSpecificPackageRule;
import io.github.opensanca.arqv.rules.spring.rest.AllDeleteMappingShouldReturn204Rule;
import io.github.opensanca.arqv.rules.spring.rest.AllGetMappingShouldReturn200Rule;
import io.github.opensanca.arqv.rules.spring.rest.AllPatchMappingShouldReturn200Rule;
import io.github.opensanca.arqv.rules.spring.rest.AllPostMappingShouldReturn201Rule;
import io.github.opensanca.arqv.rules.spring.rest.AllPublicMethodsInTheResourceLayerShouldReturnResponseEntityObjectRule;
import io.github.opensanca.arqv.rules.spring.rest.AllPutMappingShouldReturn200Rule;
import io.github.opensanca.arqv.rules.spring.rest.OnlyResourcesMayUseRestControllerAnnotationRule;
import io.github.opensanca.arqv.rules.spring.rest.ResourcesShouldBeInSpecificPackageRule;

public enum ArqvRules {

    SPRING_LAYERS_RULES(SpringLayersRules.class),
    ALL_DELETE_MAPPING_SHOULD_RETURN_204(AllDeleteMappingShouldReturn204Rule.class),
    ALL_GET_MAPPING_SHOULD_RETURN_200(AllGetMappingShouldReturn200Rule.class),
    ALL_PATCH_MAPPING_SHOULD_RETURN_200(AllPatchMappingShouldReturn200Rule.class),
    ALL_POST_MAPPING_SHOULD_RETURN_201(AllPostMappingShouldReturn201Rule.class),
    ALL_PUT_MAPPING_SHOULD_RETURN_200(AllPutMappingShouldReturn200Rule.class),
    ALL_PUBLIC_METHODS_IN_THE_RESOURCE_LAYER_SHOULD_RETURN_RESPONSE_ENTITY_OBJECT(AllPublicMethodsInTheResourceLayerShouldReturnResponseEntityObjectRule.class),
    ONLY_RESOURCES_MAY_USE_REST_CONTROLLER_ANNOTATION(OnlyResourcesMayUseRestControllerAnnotationRule.class),
    RESOURCES_SHOULD_BE_IN_SPECIFIC_PACKAGE(ResourcesShouldBeInSpecificPackageRule.class),
    REPOSITORIES_SHOULD_STAY_ON_REPOSITORY_PACKAGE(RepositoriesShouldBeInSpecificPackageRule.class),
    REPOSITORIES_ONLY_BE_ACCESSED_BY_SERVICES(RepositoriesOnlyBeAccessedByServices.class);

    private Class<?> value;

    ArqvRules(final Class<?> value) {
        this.value = value;
    }

    public Class<?> getValue() {
        return value;
    }

    public static ArqvRules getByClass(final Class<?> clazz) {
        if (clazz != null) {
            for (ArqvRules klazz : values()) {
                if (klazz.getValue().equals(clazz)) {
                    return klazz;
                }
            }
        }
        return null;
    }
}
