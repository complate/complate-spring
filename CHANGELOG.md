# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).


## [Unreleased]


## [1.0.0] - 2023-03-01

This release requires Spring Boot 3.0.x as baseline, which results into the
following breaking changes:

- This library is now build with JDK 17 which means the application needs to be
  build with at least JDK 17, too.
- This library now dependens on the Servlet API within the jakarta namespace
  which requires the application to depent on this, too.

Additionally the `complate-core` is updated to version `1.0.0` which was split
into separate modules for supported engines. This means you need to declare an
explicit dependency on the engine (currently Graal or Nashorn) that you want to
use.

### 🔨 Dependency Upgrades
- Upgrade `complate-core` to 1.0.0
- Upgrade JDK baseline to 17 (latest baseline of Spring Boot 3.0.x)
- Upgrade Spring Framework to 6.0.x (latest baseline of Spring Boot 3.0.x)
- Upgrade Servlet API to jakarta namespace and version 6 (latest baseline of Spring Boot 3.0.x)

### ❤️ Contributors

We'd like to thank all the contributors who worked on this release!

- [@cj-innoq][cj-innoq]
- [@mvitz][mvitz]


## [0.7.0] - 2020-07-07

This release updates complate-core which contains a new renderer that uses the
Graal JavaScript engine.

### 📣 Notes
- See [`complate-core`s CHANGELOG](https://github.com/complate/complate-java/blob/main/CHANGELOG.md)
  for the new Graal based renderer.

### ❤️ Contributors

We'd like to thank all the contributors who worked on this release!

- [@mvitz][mvitz]


## [0.6.0] - 2020-01-06

This release is the successor of the last
[complate-spring-mvc](https://github.com/complate/complate-spring-mvc) release.

It contains a few breaking changes that you will need to change when upgrading:

1. The `groupId` changed from `com.github.complate` to `org.complate`.
2. The parameter order of the JavaScript render method changed to
   `stream, view, params`.
3. The `ViewResolver` changed from `com.github.complate.ComplateViewResolver` to
   `org.complate.spring.mvc.ComplateViewResolver`.
4. The `com.github.complate.ScriptingEngine` changed to
   `org.complate.core.ComplateRenderer`.
5. The `com.github.complate.NashornScriptingBridge` changed to
   `org.complate.nashorn.NashornComplateRenderer`.
6. Instead of using Spring's `org.springframework.core.io.Resource` directly you
   need to use `org.complate.spring.source.ResourceComplateSource`.

If you rely on the previous behaviour that your JavaScript file is read on every
request you further need to wrap `org.complate.nashorn.NashornComplateRenderer`
within a `org.complate.core.renderer.ComplateReEvaluatingRenderer`. This may be
slower than before (because the `ScriptingEngine` is then recreated on every
request) but for production usage without wrapping the performance should be
increased.

### 📣 Notes
- `groupId` changed to `org.complate` ([e8d3123](https://github.com/complate/complate-spring/commit/e8d3123877be40c6b4c4a6c6a23acffd4cf8965a)).
- Multiple breaking changes as described above. Most introduced within the
  `complate-core` dependency.

### ⭐️ New Features
- JavaScript file can be read only once which enhances performance.

### 📔 Documentation
- A minimal configuration is shown in the readme.

### 🔨 Dependency Upgrades
- Upgrade to Spring Framework 5.1.2 (baseline of Spring Boot 2.1.0.RELEASE)
- Upgrade to Servlet 4.0.1 (baseline of Spring Boot 2.1.0.RELEASE)

### ❤️ Contributors

We'd like to thank all the contributors who worked on this release!

- [@mvitz][mvitz]


[Unreleased]: https://github.com/complate/complate-spring/compare/v1.0.0...HEAD
[1.0.0]: https://github.com/complate/complate-spring/compare/v0.7.0...v1.0.0
[0.7.0]: https://github.com/complate/complate-spring/compare/v0.6.0...v0.7.0
[0.6.0]: https://github.com/complate/complate-spring/compare/e8d3123877be40c6b4c4a6c6a23acffd4cf8965a...v0.6.0

[cj-innoq]: https://github.com/cj-innoq
[mvitz]: https://github.com/mvitz
