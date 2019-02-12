package io.github.opensanca.arqv.runner;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import io.github.opensanca.arqv.enums.ArqvGroupRules;
import io.github.opensanca.arqv.enums.ArqvRules;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

/**
 * Using <code>ArqvRunner</code> as a runner allows you to run a lot of tests to validate your architecture.
 * static {@link junit.framework.Test} <code>suite()</code> method. To use it, annotate a class
 * with <code>@RunWith(ArqvRunner.class)</code> and <code>@EnableTests({TestClass1.class, ...})</code>.
 * When you run this class, it will run all the tests in all the seletected classes.
 *
 * @since 1.0
 */
public class ArqvRunner extends ParentRunner<Runner> {

    private static final ArqvRules[] DEFAULT_TESTS = ArqvGroupRules.SPRING_REST_GROUPS_RULES.getArqvRules();

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Inherited
    public @interface RunTests {

        /**
         * @return the classes to be run
         */
        ArqvRules[] includingRules() default {};

        ArqvRules[] excludingRules() default {};

        ArqvGroupRules[] includingGroupRules() default {};

        ArqvGroupRules[] excludingGroupRules() default {};
    }

    private static Class<?>[] getAnnotatedClasses(final Class<?> clazz) throws InitializationError {
        Optional<RunTests> runTests = Optional.ofNullable(clazz.getAnnotation(RunTests.class));
        if (runTests.isPresent()) {
            final RunTests runTestsAnnotation = runTests.get();

            final ArqvRules[] includingRules = getArqvIncludingRules(runTestsAnnotation);

            final ArqvRules[] excludingRules = convertArqvGroupRulesArrayToArqvRulesArrayAndAddRules(runTestsAnnotation.excludingGroupRules(),
                    runTestsAnnotation.excludingRules());

            return Stream.of(includingRules)
                    .filter(defaultClazz -> !Arrays.asList(excludingRules)
                            .contains(defaultClazz)).map(ArqvRules::getValue).toArray(Class[]::new);
        } else {
            return Stream.of(DEFAULT_TESTS).map(ArqvRules::getValue).toArray(Class[]::new);
        }

    }

    private static ArqvRules[] convertArqvGroupRulesArrayToArqvRulesArrayAndAddRules(final ArqvGroupRules[] arqvGroupRules, final ArqvRules[] arqvRules) {
        final ArqvRules[] rules = Stream.of(arqvGroupRules)
                .map(ArqvGroupRules::getArqvRules)
                .reduce(ArrayUtils::addAll)
                .orElse(new ArqvRules[] {});
        return ArrayUtils.addAll(rules, arqvRules);
    }

    private static ArqvRules[] getArqvIncludingRules(final RunTests runTestsAnnotation) {
        ArqvRules[] includingRules = convertArqvGroupRulesArrayToArqvRulesArrayAndAddRules(runTestsAnnotation.includingGroupRules(),
                runTestsAnnotation.includingRules());
        if (includingRules.length == 0) {
            includingRules = DEFAULT_TESTS;
        }
        return includingRules;
    }

    private final List<Runner> runners;

    /**
     * Called reflectively on classes annotated with <code>@RunWith(Suite.class)</code>
     *
     * @param clazz the root class
     * @param builder builds runners for classes in the suite
     */
    public ArqvRunner(final Class<?> clazz, final RunnerBuilder builder) throws InitializationError {
        this(builder, clazz, getAnnotatedClasses(clazz));
    }

    /**
     * Called by this class and subclasses once the classes making up the suite have been determined
     *
     * @param builder builds runners for classes in the suite
     * @param klzz the root of the suite
     * @param classes the classes in the suite
     */
    protected ArqvRunner(final RunnerBuilder builder, final Class<?> klzz, final Class<?>[] classes) throws InitializationError {
        this(klzz, builder.runners(klzz, classes));
    }

    /**
     * Called by this class and subclasses once the runners making up the suite have been determined
     *
     * @param clalzz root of the suite
     * @param runners for each class in the suite, a {@link Runner}
     */
    protected ArqvRunner(final Class<?> clalzz, final List<Runner> runners) throws InitializationError {
        super(clalzz);
        this.runners = Collections.unmodifiableList(runners);
    }

    @Override
    protected List<Runner> getChildren() {
        return runners;
    }

    @Override
    protected Description describeChild(final Runner child) {
        return child.getDescription();
    }

    @Override
    protected void runChild(final Runner runner, final RunNotifier notifier) {
        runner.run(notifier);
    }
}
