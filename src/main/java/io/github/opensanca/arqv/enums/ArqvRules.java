package io.github.opensanca.arqv.enums;

import io.github.opensanca.arqv.rules.rest.spring.SpringResourceRules;

public enum ArqvRules {
    SPRING_REST_RULE(SpringResourceRules.class);
    private Class<?> value;

    ArqvRules(final Class<?> value) {
        this.value = value;
    }

    public Class<?> getValue() {
        return value;
    }
}
