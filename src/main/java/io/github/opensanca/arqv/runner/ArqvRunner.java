package io.github.opensanca.arqv.runner;

import io.github.opensanca.arqv.enums.ArqvRules;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

import java.lang.annotation.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Using <code>ArqvRunner</code> as a runner allows you to run a lot of tests to validate your architecture.
 * static {@link junit.framework.Test} <code>suite()</code> method. To use it, annotate a class
 * with <code>@RunWith(ArqvRunner.class)</code> and <code>@EnableTests({TestClass1.class, ...})</code>.
 * When you run this class, it will run all the tests in all the seletected classes.
 *
 * @since 1.0
 */
public class ArqvRunner extends ParentRunner<Runner> {

    private static final ArqvRules[] DEFAULT_TESTS = new ArqvRules[]{ArqvRules.SPRING_REST_RULE};

    /**
     * The <code>EnableTests</code> annotation specifies the classes to be run when a class
     * annotated with <code>@RunWith(ArqvRunner.class)</code> is run.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Inherited
    public @interface EnableTests {
        /**
         * @return the classes to be run
         */
        ArqvRules[] value();
    }

    /**
     * The <code>DisableTests</code> annotation specifies the classes to be not run when a class
     * annotated with <code>@RunWith(ArqvRunner.class)</code> is run.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Inherited
    @Documented
    public @interface DisableTests {
        /**
         * @return the classes to be run
         */
        ArqvRules[] value();
    }

    private static Class<?>[] getAnnotatedClasses(final Class<?> clazz) throws InitializationError {
        Optional<EnableTests> enableClasses = Optional.ofNullable(clazz.getAnnotation(EnableTests.class));
        Optional<DisableTests> disableClasses = Optional.ofNullable(clazz.getAnnotation(DisableTests.class));

        if (enableClasses.isPresent() && disableClasses.isPresent()) {
            throw new UnsupportedOperationException("Choice only one option: EnableTests or DisableTests");
        } else if (enableClasses.isPresent()) {
            return Stream.of(enableClasses.get().value()).map(ArqvRules::getValue).toArray(Class[]::new);
        } else if (disableClasses.isPresent()) {
            return Stream.of(DEFAULT_TESTS)
                    .filter(defaultClazz -> !Arrays.asList(disableClasses.get().value()).contains(defaultClazz)).map(ArqvRules::getValue).toArray(Class[]::new);
        } else {
            return Stream.of(DEFAULT_TESTS).map(ArqvRules::getValue).toArray(Class[]::new);
        }
    }

    private final List<Runner> runners;

    /**
     * Called reflectively on classes annotated with <code>@RunWith(Suite.class)</code>
     *
     * @param clazz   the root class
     * @param builder builds runners for classes in the suite
     */
    public ArqvRunner(final Class<?> clazz, final RunnerBuilder builder) throws InitializationError {
        this(builder, clazz, getAnnotatedClasses(clazz));
    }

    /**
     * Called by this class and subclasses once the classes making up the suite have been determined
     *
     * @param builder builds runners for classes in the suite
     * @param klzz    the root of the suite
     * @param classes the classes in the suite
     */
    protected ArqvRunner(final RunnerBuilder builder, final Class<?> klzz, final Class<?>[] classes) throws InitializationError {
        this(klzz, builder.runners(klzz, classes));
    }

    /**
     * Called by this class and subclasses once the runners making up the suite have been determined
     *
     * @param clalzz  root of the suite
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
