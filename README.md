# complate-spring
*- Server-Side Rendering of JSX based views in Spring MVC*

[![license](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0) [![Build Status](https://travis-ci.org/complate/complate-spring.svg?branch=master)](https://travis-ci.org/complate/complate-spring) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=org.complate%3Acomplate-spring-mvc&metric=alert_status)](https://sonarcloud.io/dashboard?id=org.complate%3Acomplate-spring-mvc) [![codecov](https://codecov.io/gh/complate/complate-spring/branch/master/graph/badge.svg)](https://codecov.io/gh/complate/complate-spring)

[complate](https://complate.org) adapter that can be used in
[Spring MVC](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html)
for Server-Side rendering JSX based views.


## Quick Start

Because there is no stable version in maven central right now you need to
configure Sonatype's OSS Nexus as snapshot repository.

```xml
<repository>
  <id>ossrh</id>
  <name>Sonatype OSS Snapshot Repository</name>
  <url>https://oss.sonatype.org/content/repositories/snapshots</url>
</repository>
```

Afterwards the JAR is available through Maven:

```xml
<dependency>
  <groupId>org.complate</groupId>
  <artifactId>complate-spring-mvc</artifactId>
  <version>0.6.0-SNAPSHOT</version>
</dependency>
```

To configure the relevant parts within your Spring Boot application you need to
add a configuration class like the one shown below.

```java
@Configuration
public class ComplateConfiguration {

    @Bean
    public ComplateSource complateSource(
            @Value("classpath:/templates/complate/bundle.js") Resource resource) {
        /*
         * Note that you have to make sure that the `/templates/complate/bundle.js`
         * file exists within your classpath on runtime and that this file exports
         * a function named `render` by default that has a matching signature:
         *
         * ```
         * export default function render(view, params, stream) {
         *     ...
         * }
         * ```
         *
         * Within this function you can use a complate Renderer to render JSX
         * based views. Because you then need to transpile your JavaScript code
         * you can use faucet or any other bundler that has support for
         * transpiling *.jsx files.
         */
        return new ResourceComplateSource(resource);
    }

    @Bean
    public ComplateRenderer complateRenderer(ComplateSource source) {
        /*
         * Note that it's possible to add global bindings that can be accessed
         * from your JSX views by providing a `Map<String, Object>` as second
         * argument to `NashornComplateRenderer`.
         *
         * Because `NashornComplateRenderer` only evaluates the given `ComplateSource`
         * on instantiation changes made to this source afterwards will not be
         * picked up. If you want to re-evaluate the `ComplateSource` on every call
         * to `render` you can wrap the `NashornComplateRenderer` within an
         * `ComplateReEvaluatingRenderer`.
         *
         * If you encounter problems that may be related with the multi threaded
         * nature of a Spring web application you can wrap the `NashornComplateRenderer`
         * (or `ComplateReEvaluatingRenderer`) within an `ComplateThreadLocalRenderer`.
         * This will create an instance that is exclusively used within a thread.
         */
        return new NashornComplateRenderer(source);
    }

    @Bean
    public ComplateViewResolver complateViewResolver(ComplateRenderer renderer) {
        return new ComplateViewResolver(renderer);
    }
}
```


## License

complate-spring is Open Source software released under the
[Apache 2.0 license](http://www.apache.org/licenses/LICENSE-2.0.html).
