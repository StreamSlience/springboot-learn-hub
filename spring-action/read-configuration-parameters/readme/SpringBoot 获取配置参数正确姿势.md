# Spring Boot 获取配置参数正确姿势

**提供以下几种读取配置文件参数的方式：**

- 通过 `Environment` 对象
- 通过 `@PropertySource` 和 `@Value`
- 通过 `@ConfigurationProperties`读取

## 配置文件案例模板

```yaml
book:
  name:
    zh: 白鲸
    en: Moby Dick
  author:
    name: 赫尔曼·梅尔维尔
    nationality: 美
    born: 1819
    died: 1891
  press:

library:
  name: 杭州市图书馆
  info:
    address: 中国浙江省杭州市江干区新城商圈解放东路58号

  books:
    - name: 天才基本法
      description: 二十二岁的林朝夕在父亲确诊阿尔茨海默病这天，得知自己暗恋多年的校园男神裴之即将出国深造的消息——对方考取的学校，恰是父亲当年为她放弃的那所。
    - name: 时间的秩序
      description: 为什么我们记得过去，而非未来？时间“流逝”意味着什么？是我们存在于时间之内，还是时间存在于我们之中？卡洛·罗韦利用诗意的文字，邀请我们思考这一亘古难题——时间的本质。
    - name: 了不起的我
      description: 如何养成一个新习惯？如何让心智变得更成熟？如何拥有高质量的关系？ 如何走出人生的艰难时刻？

  area:
    - A区
    - B区
    - C区
    - D区

  borrow:
    A1: A1
    A2: A2
    A3: A3

  magazine:
    time:
      content: 时事政治
      price: 10
    wheels:
      content: 汽车
      price: 20

car:
  name: 宝马
  weight: ${random.long(100)}
  price: ${random.long}
  id: ${random.uuid}
  rank:
    - 尊享版
    - 豪华版
    - 旗舰版
```

关于`.propertie`和`.yml`文件的数据语法可以看另一篇笔记：`[SpringBoot` 配置文件数据类型语法](https://www.notion.so/SpringBoot-aeda2d2624b74536884c016c429915fa)

---



## 通过 `Environment` 对象

该方法不过多解释，直接上案例代码：

```java
@Configuration
public class WithEnvironment {

    private final Environment environment;

    @Autowired
    public WithEnvironment(Environment environment) {
        this.environment = environment;
    }

    public Properties bookProperties() {
        Properties properties = new Properties();
        properties.put("book.name.en", environment.getRequiredProperty("book.name.en"));
        properties.put("book.name.zh", environment.getRequiredProperty("book.name.zh"));
        properties.put("book.author.name", environment.getRequiredProperty("book.author.name"));
        properties.put("book.author.nationality", environment.getRequiredProperty("book.author.nationality"));
        properties.put("book.author.born", environment.getRequiredProperty("book.author.born"));
        properties.put("book.author.died", environment.getRequiredProperty("book.author.died"));
        //如果配置文件中不存在对应的路径将抛出异常
        if (StringUtils.isNotEmpty(environment.getRequiredProperty("book.press"))) {
            properties.put("book.press", environment.getRequiredProperty("book.press"));
        }
        return properties;
    }
}
```

---



## 通过 `@PropertySource` 和 `@Value`

```java
@Data
@Configuration
public class WithValue {

    @Value("${book.name.zh}")
    private String nameZh;

    @Value("${book.name.en}")
    private String nameEn;

    @Value("${book.author.name}")
    private String authorName;

    @Value("${book.author.nationality}")
    private String authorNationality;

    @Value("${book.author.born}")
    private Long authorBorn;

    @Value("${book.author.died}")
    private Long authorDied;

    /**
     * 语法为 ${路径:默认值}
     * 注意:只有当配置文件中对应路径不存在时才设置默认值
     * 比方说如果配置文件存在如下路径，则认为有值，值为空
     * book:
     * press:
     */
    @Value("${book.press:北京联合出版有限责任公司}")
    private String press;

}
```

### `@Value`使用说明

**特点说明：**

1. 只能单一指定路径配置注入值。
2. 不支持数组、集合等复杂的数据类型。
3. 不支持数据校验(`javax.validation.constraints`下的参数校验注解)。
4. ~~对属性名匹配有严格的要求。~~
5. 支持`[SpEL]`表达式。https://www.notion.so/SpringBoot-6ac44fa15abf4258a276600743f0accf#66c1f36490014d59b329d4fbde981eb4

**默认值设置的两种方式：**

1. **使用`@Value`**

    ```java
    @Value("${book.press:北京联合出版有限责任公司}")
    private String press;
    ```

2. **直接对变量赋值**

    ```java
    @Value("${book.press}")
    private String press = "北京联合出版有限责任公司";
    ```

### `@PropertySource`使用说明

 		该注解用来指定读取的配置文件。

 		没有使用 `@PropertySource` 注解指定加载的文件时，默认使用 `application.xxx`文件中与实体对象的属性相同的配置项。`@PropertySources` 注解优先级比较低，即使指定了加载的文件，但出现与 `application.xxx` 文件相同的配置项时会被其覆盖。

**注意：**该注解目前只能对`.properties`文件进行路径指定，对`.yml`是无效的！！！

---



## 通过 `@ConfigurationProperties`读取

`@ConfigurationProperties`配合其他注解可以实现多种形式的配置读取，并生成bean对象。

### 方式一：@ConfigurationProperties和@Configuration/@Component同时作用于类级别

```java
@Data
@Configuration
@ConfigurationProperties(prefix = "library")
public class WithConfigurationProperties {

    /**
     * 基本类型、String类型 变量
     */
    private String name;

    /**
     * 引用变量
     */
    private Info info;

    /**
     * 元素为基本类型或String类型的集合
     */
    private List<String> area;

    /**
     * 元素为引用类型的集合
     */
    private List<Book> books;

    /**
     * 基本类型或String的Map
     */
    private Map<String,String> borrow;

    /**
     * 引用类型的Map
     */
    private Map<String,Magazine> magazine;

    @Data
    static class Info{
        private String address;
    }

    @Data
    static class Book {
        private String name;
        private String description;
    }

    @Data
    static class Magazine{
        private String content;
        private Integer price;
    }

}
```

### `@ConfigurationProperties`使用说明

**特点说明：**

1. 可以从配置文件中批量注入属性。
2. 支持获取复杂的数据类型(包括引用类型、基本数据类型、String、List、map、数组)。
3. 支持`JSR303`数据校验(`javax.validation.constraints`下的参数校验注解)。
4. 对属性名匹配的要求较低，比如`user-name`，`user_name`，`userName`，`USER_NAME`都可以取值。
5. 不支持`[SpEL](http://itmyhome.com/spring/expressions.html)`表达式

**使用注意：**

1. 必须指定注解中的`prefix`元素值来指明一组配置参数的起始点。
2. 配置类应当符合`JavaBean`规范(例如对应变量都应当创建对应的`setter`方法，否则将注入不了参数)。
3. 配置类中变量名称应当与`yml`中最后的标签名相同，否则需要使用`@Value`进行注入。

---

### 方式二：@ConfigurationProperties和@Bean作用于方法级别

```java
@Configuration
@PropertySource(value = "classpath:application.yml")
public class WithBean {

    @Bean
    @ConfigurationProperties("car")
    public Car getCarBean(){
        return new Car();
    }
		
		/**
     * @Bean
     * @ConfigurationProperties("car")
     * ... ...
     */
    @Data
    static class Car{

        private String name;

        private String weight;

        private Long price;

        private String id;

        private List<String> rank;

    }
}
```

​		实际上仅仅是改变了`@ConfigurationProperties`的作用范围，因此使用情况同注解作用于类时完全一样。

​		以上方式的有点在于可以在同一个配置类中配置多个不同组的`Bean`对象，而本文第三种方式明显只能配置一组参数。

### 方式三：@ConfigurationProperties和@EnableConfigurationProperties

```java
@Data
@ConfigurationProperties(prefix = "library")
public class WithConfigurationProperties {
		// ... ...
}
```

```java
@SpringBootApplication
@EnableConfigurationProperties({WithConfigurationProperties.class})
public class ReadConfigurationParametersApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadConfigurationParametersApplication.class, args);
    }

}
```

       `@EnableConfigurationProperties`可以作用于 **启动类** 或者 **应用类**。
    
       `@EnableConfigurationProperties`是用来开启对`@ConfigurationProperties`配置`Bean`的支持。也就是`@EnableConfigurationProperties`告诉`Spring Boot`能支持`@ConfigurationProperties`。如果不指定会看到如下异常:

```java
Caused by: org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type [com.dxz.property.MailProperties] found for dependency: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}
```

并且指定的类上必须被`@ConfigurationProperties`修饰。





[在 SpringBoot 中获取配置参数的最佳姿势](http://ckjava.com/2019/07/25/SpringBoot-yml-properties-config-usage-practice/)

[@ConfigurationProperties获取springboot yml中的复杂数据](https://juejin.im/post/5cf49068e51d45105d63a4b3)

[注解ConfigurationProperties注入yml配置文件中的数据](https://www.cnblogs.com/lgjlife/p/10762893.html)

[注解ConfigurationProperties注入yml配置文件中的数据](https://www.cnblogs.com/lgjlife/p/10762893.html)

[](https://www.fangzhipeng.com/springboot/2017/05/02/sb2-config-file.html)

[8. Spring 表达式语言 (SpEL)](http://itmyhome.com/spring/expressions.html)

[@ConfigurationProperties绑定配置信息至Array、List、Map、Bean_JustryDeng-CSDN博客_configurationproperties map](https://blog.csdn.net/justry_deng/article/details/90758250?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase)