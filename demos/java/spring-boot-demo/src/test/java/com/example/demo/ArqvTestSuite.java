package com.example.demo;

import io.github.opensanca.arqv.enums.ArqvRules;
import io.github.opensanca.arqv.runner.ArqvRunner;
import org.junit.runner.RunWith;

@RunWith(ArqvRunner.class)
@ArqvRunner.RunTests(
    excludingRules = {
        ArqvRules.ALL_GET_MAPPING_SHOULD_RETURN_200,
        ArqvRules.ALL_PUBLIC_METHODS_IN_THE_RESOURCE_LAYER_SHOULD_RETURN_RESPONSE_ENTITY_OBJECT
    }
)
public class ArqvTestSuite {

}
