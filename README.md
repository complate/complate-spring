# complate-spring
*- Server-Side Rendering of JSX based views in Spring MVC*

[![license](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0)

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
  <version>0.1.0-SNAPSHOT</version>
</dependency>
```

To configure the relevant parts within your Spring Boot application you need to
add a configuration class like the one shown below.

```java
@Configuration
public class ComplateConfiguration {

    @Bean
    public ComplateSource complateSource(@Value("classpath:/templates/complate/bundle.js") Resource resource) {
        return new ResourceComplateSource(resource);
    }

    @Bean
    public ComplateRenderer complateRenderer(ComplateSource source) {
        return new NashornComplateRenderer(source);
    }

    @Bean
    public ComplateViewResolver complateViewResolver(ComplateRenderer renderer) {
        return new ComplateViewResolver(renderer);
    }
}
```

Note that the `@Value`-Annotation specifies the location of the complate script
file that is used for rendering the views and maybe needs to be adjusted.


## License

complate-spring is Open Source software released under the
[Apache 2.0 license](http://www.apache.org/licenses/LICENSE-2.0.html).
