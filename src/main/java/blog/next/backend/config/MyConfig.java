package blog.next.backend.config;

import blog.next.backend.util.YamlAndPropertySourceFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


/**
 * 导入配置文件的另外一种方式,用于加载三方包的配置
 * @EnableConfigurationProperties(Car.class)
 */
//申明这是一个配置类
//配置文件的属性前缀
@Configuration
//PropertySource不支持yaml或者yml文件配置,想要支持yaml，factory传个yaml的解析器进去
//@PropertySource(value = {"classpath:config/myConfig.properties"}, encoding = "utf-8")
//yaml或者yml配置支持
@PropertySource(value = {"classpath:config/myConfig.yml"}, encoding = "utf-8", factory = YamlAndPropertySourceFactory.class)
@ConfigurationProperties(prefix = "myconfig", ignoreUnknownFields = false)
@Data
public class MyConfig {

    private String name;

    private int age;

    private String detail;
}
