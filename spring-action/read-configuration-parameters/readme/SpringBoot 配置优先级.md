# SpringBoot  配置优先级

1. 命令行参数。
2. 从 `java:comp/env` 得到的 `JNDI` 属性。
3. 通过 `System.getProperties()` 获取的 Java 系统参数。
4. 操作系统环境变量。
5. `RandomValuePropertySource` 配置的 `random.*` 属性值。如 `{random.long}`、`{random.uuid}` 、`{random.int}`、`{random.value}`等。
6. JAR 包外部的 `application-{profile}.properties` 或 `application.yml` （带 spring.profile）配置文件。
7. JAR 包内部的 `application-{profile}.properties` 或 `application.yml` （带 spring.profile）配置文件。
8. JAR 包外部的 `application.properties` 或 `application.yml`（不带 spring.profile）配置文件。
9. JAR 包内部的 `application.properties`或 `application.yml`（不带 spring.profile）配置文件。
（从 JAR 包外向 JAR 包内进行寻找，优先加载带 `{profile}` 的文件，再加载不带 `{profile}`的文件）
10. `@Configuration` 注解类上的 `@PropertySource`。
11. 通过 `SpringApplication.setDefaultProperties` 指定默认属性。

**注意：**`application.properties` 文件的优先级高于 `application.yml` 文件的优先级。

[Spring Boot 资源配置文件读取](https://juejin.im/post/5e92a642518825737f1a6dac)