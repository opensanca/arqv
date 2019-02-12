package io.github.opensanca.arqv.enums;

import io.github.opensanca.arqv.rules.rest.spring.AllDeleteMappingShouldReturn204Rule;
import io.github.opensanca.arqv.rules.rest.spring.AllGetMappingShouldReturn200Rule;
import io.github.opensanca.arqv.rules.rest.spring.AllPatchMappingShouldReturn200Rule;
import io.github.opensanca.arqv.rules.rest.spring.AllPostMappingShouldReturn201Rule;
import io.github.opensanca.arqv.rules.rest.spring.AllPublicMethodsInTheResourceLayerShouldReturnResponseEntityObjectRule;
import io.github.opensanca.arqv.rules.rest.spring.AllPutMappingShouldReturn200Rule;
import io.github.opensanca.arqv.rules.rest.spring.OnlyResourcesMayUseRestControllerAnnotationRule;
import io.github.opensanca.arqv.rules.rest.spring.ResourcesShouldBeInSpecificPackageRule;

public enum ArqvRules {
    ALL_DELETE_MAPPING_SHOULD_RETURN_204(AllDeleteMappingShouldReturn204Rule.class),
    ALL_GET_MAPPING_SHOULD_RETURN_200(AllGetMappingShouldReturn200Rule.class),
    ALL_PATCH_MAPPING_SHOULD_RETURN_200(AllPatchMappingShouldReturn200Rule.class),
    ALL_POST_MAPPING_SHOULD_RETURN_201(AllPostMappingShouldReturn201Rule.class),
    ALL_PUT_MAPPING_SHOULD_RETURN_200(AllPutMappingShouldReturn200Rule.class),
    ALL_PUBLIC_METHODS_IN_THE_RESOURCE_LAYER_SHOULD_RETURN_RESPONSE_ENTITY_OBJECT(AllPublicMethodsInTheResourceLayerShouldReturnResponseEntityObjectRule.class),
    ONLY_RESOURCES_MAY_USE_REST_CONTROLLER_ANNOTATION(OnlyResourcesMayUseRestControllerAnnotationRule.class),
    RESOURCES_SHOULD_BE_IN_SPECIFIC_PACKAGE(ResourcesShouldBeInSpecificPackageRule.class);

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
