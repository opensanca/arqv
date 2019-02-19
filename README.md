# Arqv - Archtecture Validation

## What is this?

Have you already defined a software architecture pattern with your team, but over time that pattern has been lost?
#### So this library is for you!

___

### Spring Rest Validation

Currently, microservices is the architecture most used by companies.
Because this, Spring have been the most famous framework to create microservice, and to communicate with these microservices, the most famous protocol is REST.


But using REST is not as simple as it sounds.
REST has a good practice guide (return status code, http methods for each operation and etc), and following these patterns to create a RESTful API is very difficult if you don't have a validation of your code!
And is exactly here that Arqv helps you!

**Arqv have a group of rules to validate your code and help you to follow a REST good practice to create a RESTful API. And the best thing is that Arqv is totally flexible and allows you to choice what rules you want to follow.**

___
In this example below, we are validate the default rules for Spring Resources but excluding the rule for Patch method return 200.

Example code:

```java
@RunWith(ArqvRunner.class)
@ArqvRunner.RunTests(includingGroupRules = ArqvGroupRules.SPRING_REST_GROUPS_RULES,
excludingRules = ArqvRules.ALL_PATCH_MAPPING_SHOULD_RETURN_200)
public class SpringRestTest {
    
}
```

In this example bellow, we are validate the default rules for Spring Resources but excluding the rule for Patch method return 200.