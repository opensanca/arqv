package io.github.opensanca.arqv.utils;

import io.github.opensanca.arqv.enums.ArqvGroupRules;
import io.github.opensanca.arqv.enums.ArqvRules;
import org.apache.commons.lang3.ArrayUtils;

public class Defaults {

    public static ArqvRules[] tests() {
        return ArrayUtils.addAll(
                ArqvGroupRules.SPRING_REST_GROUPS_RULES.getArqvRules(),
                ArqvGroupRules.SPRING_REPOSITORY_GROUP_RULES.getArqvRules()
        );
    }

}
