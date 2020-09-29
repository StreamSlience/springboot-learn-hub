# Spring Boot 配置文件数据类型语法

`SpringBoot`支持两种配置文件格式：`.propertie` 和 `.yml`

## `.properties`文件数据结构示例

```properties
#普通属性
plane.name=波音747

#random.* 自动注入随机值
plane.weight=${random.long(100)}
plane.price=${random.long}
plane.id=${random.uuid}

#集合/数组
plane.rank[0]=尊享版
plane.rank[1]=豪华版
plane.rank[2]=旗舰版
#等价于
#plane.rank=尊享版,豪华版,旗舰版

#引用类型
plane.driver.name=小沈
plane.driver.age=25
plane.driver.weight=120
#等价于
#plane.driver[name]=小沈
#plane.driver[age]=25
#plane.driver[weight]=120

#负责数据类型集合/数组(元素是引用类型)
plane.passenger[0][name]=小朋友
plane.passenger[0][age]=3
plane.passenger[0][weight]=30
plane.passenger[1][name]=大朋友
plane.passenger[1][age]=20
plane.passenger[1][weight]=160
#等价于
#plane.passenger[0].name=小朋友
#plane.passenger[0].age=3
#plane.passenger[0].weight=30
#plane.passenger[1].name=大朋友
#plane.passenger[1].age=20
#plane.passenger[1].weight=160

#简单Map
plane.food[汉堡]=20
plane.food[盖浇饭]=50
#等价于
#plane.food.汉堡=20
#plane.food.盖浇饭=50

#复杂Map
plane.staff[乘务长].name=小美
plane.staff[乘务长].age=27
plane.staff[乘务长].weight=99
#等价于
#plane.staff.乘务长.name=小美
#plane.staff.乘务长.age=27
#plane.staff.乘务长.weight=99
#等价于
#plane.staff.乘务长.[name]=小美
#plane.staff.乘务长.[age]=27
#plane.staff.乘务长.[weight]=99
#等价于
#plane.staff[乘务长][name]=小美
#plane.staff[乘务长][age]=27
#plane.staff[乘务长][weight]=99
```

```java
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
static class Plane extends Car {

		private String name;

    private String weight;

    private Long price;

    private String id;

    private List<String> rank;

    private Map<String,Integer> food;

    private Person driver;

    private Person[] passenger;

    private Map<String, Person> staff;

    @Data
    static class Person {
        private String name;
        private Integer age;
        private Integer weight;
    }
}
```

## `.yml`文件数据结构示例

```yaml
library:
  name: 杭州市图书馆

#引用类型
  info:
    address: 中国浙江省杭州市江干区新城商圈解放东路58号
#等价于
#  info: {
#    address: 中国浙江省杭州市江干区新城商圈解放东路58号
#  }

#复杂集合/数组(元素类型为引用类型)
  books:
    - name: 天才基本法
      description: 二十二岁的林朝夕在父亲确诊阿尔茨海默病这天，得知自己暗恋多年的校园男神裴之即将出国深造的消息——对方考取的学校，恰是父亲当年为她放弃的那所。
    - name: 时间的秩序
      description: 为什么我们记得过去，而非未来？时间“流逝”意味着什么？是我们存在于时间之内，还是时间存在于我们之中？卡洛·罗韦利用诗意的文字，邀请我们思考这一亘古难题——时间的本质。
    - name: 了不起的我
      description: 如何养成一个新习惯？如何让心智变得更成熟？如何拥有高质量的关系？ 如何走出人生的艰难时刻？
#等价于
#  books:
#    - {
#      name: 天才基本法,
#      description: 二十二岁的林朝夕在父亲确诊阿尔茨海默病这天，得知自己暗恋多年的校园男神裴之即将出国深造的消息——对方考取的学校，恰是父亲当年为她放弃的那所。
#    }
#    - {
#      name: 时间的秩序,
#      description: 为什么我们记得过去，而非未来？时间“流逝”意味着什么？是我们存在于时间之内，还是时间存在于我们之中？卡洛·罗韦利用诗意的文字，邀请我们思考这一亘古难题——时间的本质。
#    }
#    - {
#      name: 了不起的我,
#      description: 如何养成一个新习惯？如何让心智变得更成熟？如何拥有高质量的关系？ 如何走出人生的艰难时刻？
#    }

#简单集合/数组(元素类型为基本数据类型(包装类)或String)
  area:
    - A区
    - B区
#等价于
#  area: A区,B区

#简单Map(键值都为基本数据类型或string)
  borrow:
    A1: A1
    A2: A2
    A3: A3
#等价于
#  borrow[A1]: A1
#  borrow[A2]: A2
#  borrow[A3]: A3

#复杂Map(键为基本数据类型或String,值为引用类型)
  magazine:
    time:
      content: 时事政治
      price: 10
    wheels:
      content: 汽车
      price: 20
#等价于
#  magazine[time]:
#    content: 时事政治
#    price: 10
#  magazine[wheels]:
#    content: 汽车
#    price: 20
#等价于
#  magazine:
#    time: {
#      content: 时事政治,
#      price: 10
#    }
#    wheels: {
#      content: 汽车,
#      price: 20
#    }
```

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



[@ConfigurationProperties绑定配置信息至Array、List、Map、Bean_JustryDeng-CSDN博客_configurationproperties map](https://blog.csdn.net/justry_deng/article/details/90758250?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase)