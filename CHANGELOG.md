# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).


## [Unreleased]

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


[Unreleased]: https://github.com/complate/complate-spring/compare/e8d3123877be40c6b4c4a6c6a23acffd4cf8965a...HEAD

[mvitz]: https://github.com/mvitz
