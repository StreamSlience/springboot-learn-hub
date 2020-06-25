package com.streamslience.springaction.readconfigurationparameters;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 使用{@code @Value}读取配置文件参数
 * <p>
 * <br><strong>关于@{code @Value}设置默认值问题</strong>
 * <br>springboot中可以使用两种形式设置默认值：
 * <br>1.使用{@code @Value}进行设定
 * {@code @Value("${book.press:北京联合出版有限责任公司}")}
 * <br>2.直接对变量进行赋值。
 * {@code  @Value("${book.press}")
 * private String press = "北京联合出版有限责任公司";}
 * <p>
 *
 * @author StreamSlience
 * @date 2020-06-25 14:59
 */
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
