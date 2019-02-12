package io.github.opensanca.arqv.enums;

import java.util.stream.Stream;

public enum ArqvGroupRules {

    SPRING_REST_GROUPS_RULES(new Class<?>[] {
            ArqvRules.RESOURCES_SHOULD_BE_IN_SPECIFIC_PACKAGE.getValue(),
            ArqvRules.ONLY_RESOURCES_MAY_USE_REST_CONTROLLER_ANNOTATION.getValue(),
            ArqvRules.ALL_DELETE_MAPPING_SHOULD_RETURN_204.getValue(),
            ArqvRules.ALL_GET_MAPPING_SHOULD_RETURN_200.getValue(),
            ArqvRules.ALL_PATCH_MAPPING_SHOULD_RETURN_200.getValue(),
            ArqvRules.ALL_POST_MAPPING_SHOULD_RETURN_201.getValue(),
            ArqvRules.ALL_PUT_MAPPING_SHOULD_RETURN_200.getValue(),
            ArqvRules.ALL_PUBLIC_METHODS_IN_THE_RESOURCE_LAYER_SHOULD_RETURN_RESPONSE_ENTITY_OBJECT.getValue()
    });

    private Class<?>[] value;

    ArqvGroupRules(final Class<?>[] value) {
        this.value = value;
    }

    public Class<?>[] getValue() {
        return value;
    }

    public ArqvRules[] getArqvRules() {
        return Stream.of(value).map(ArqvRules::getByClass).toArray(ArqvRules[]::new);
    }
}
