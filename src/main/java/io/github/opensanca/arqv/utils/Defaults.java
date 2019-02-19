package io.github.opensanca.arqv.utils;

import java.util.stream.Stream;

import io.github.opensanca.arqv.enums.ArqvGroupRules;
import io.github.opensanca.arqv.enums.ArqvRules;

public class Defaults {

    public static ArqvRules[] rules() {
        return Stream.of(
                ArqvGroupRules.SPRING_REST_GROUPS_RULES.getArqvRules(),
                ArqvGroupRules.SPRING_REPOSITORY_GROUP_RULES.getArqvRules(),
                ArqvGroupRules.SPRING_LAYERS_GROUP_RULES.getArqvRules()
        ).flatMap(Stream::of).toArray(ArqvRules[]::new);
    }
}
