package io.github.opensanca.arqv.utils;

import static com.tngtech.archunit.core.domain.Formatters.formatLocation;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAPackage;
import static com.tngtech.archunit.core.domain.JavaModifier.PUBLIC;
import static com.tngtech.archunit.core.domain.properties.HasModifiers.Predicates.modifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.base.Optional;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaMember;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.domain.properties.HasAnnotations;
import com.tngtech.archunit.core.domain.properties.HasModifiers;
import com.tngtech.archunit.lang.AbstractClassesTransformer;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ClassesTransformer;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import io.github.opensanca.arqv.enums.StatusCode;
import com.tngtech.archunit.core.domain.properties.HasOwner.Functions.Get;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class ArqvUtils {

    public static ClassesTransformer<JavaMethod> methods() {
        return new AbstractClassesTransformer<JavaMethod>("methods") {

            @Override
            public Iterable<JavaMethod> doTransform(final JavaClasses javaClasses) {
                List<JavaMethod> methods = new ArrayList<>();
                for (JavaClass javaClass : javaClasses) {
                    methods.addAll(javaClass.getMethods());
                }
                return methods;
            }
        };
    }

    public static DescribedPredicate<? super JavaMember> areDefinedInAPackage(final String packageIdentifier) {
        return Get.<JavaClass>owner().is(resideInAPackage(packageIdentifier));
    }

    public static DescribedPredicate<HasModifiers> arePublic() {
        return modifier(PUBLIC).as("are public");
    }

    public static ArchCondition<JavaMethod> returnStatusCode(final StatusCode statusCode) {
        return new ArchCondition<JavaMethod>("return type " + statusCode) {

            @Override
            public void check(final JavaMethod method, final ConditionEvents events) {
                boolean typeMatches = method.getMethodCallsFromSelf().stream()
                        .anyMatch(javaMethodCall -> javaMethodCall.getTarget().getName().equals(statusCode.getValue()));
                String message = String.format("%s returns %s in %s",
                        method.getFullName(), method.getReturnType().getName(),
                        formatLocation(method.getOwner(), 0));
                events.add(new SimpleConditionEvent(method, typeMatches, message));
            }
        };
    }

    public static ArchCondition<JavaMethod> returnType(final Class<?> type) {
        return new ArchCondition<JavaMethod>("return type " + type.getName()) {
            @Override
            public void check(final JavaMethod method, final ConditionEvents events) {
                boolean typeMatches = method.getReturnType().isAssignableTo(type);
                String message = String.format("%s returns %s in %s",
                        method.getFullName(), method.getReturnType().getName(),
                        formatLocation(method.getOwner(), 0));
                events.add(new SimpleConditionEvent(method, typeMatches, message));
            }
        };
    }

    public static DescribedPredicate<HasAnnotations> annotatedWithRequestMappingWithHttpMethod(RequestMethod requestMethod) {
        return new DescribedPredicate<HasAnnotations>("annotated with RequestMapping with HTTP Method: " + requestMethod) {
            @Override
            public boolean apply(HasAnnotations input) {
                Optional<RequestMapping> annotation = input.tryGetAnnotationOfType(RequestMapping.class);
                return annotation.isPresent() && Arrays.asList(annotation.get().method()).stream().anyMatch(requestMethod1 -> requestMethod1.equals(requestMethod));
            }
        };
    }

}
