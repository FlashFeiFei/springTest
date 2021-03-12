package blog.next.backend.config;

import blog.next.backend.util.YamlAndPropertySourceFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//申明这是一个配置类
//配置文件的属性前缀
@Configuration
//PropertySource不支持yaml或者yml文件配置,想要支持yaml，factory传个yaml的解析器进去
//@PropertySource(value = {"classpath:config/myConfig.properties"}, encoding = "utf-8")
//yaml或者yml配置支持
@PropertySource(value = {"classpath:config/myConfig.yml"}, encoding = "utf-8", factory = YamlAndPropertySourceFactory.class)
@ConfigurationProperties(prefix = "myconfig", ignoreUnknownFields = false)
public class MyConfig {

    private String name;

    private int age;

    private String detail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
