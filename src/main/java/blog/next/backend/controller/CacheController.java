package blog.next.backend.controller;

import blog.next.backend.entity.user.Persion;
import blog.next.backend.entity.user.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
//@CacheConfig
public class CacheController {


    //操作k-v都是字符串的
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //操作k-v都是对象的
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisTemplate redisJsonTemplate;

    /**
     * Cacheable注解
     * 将方法的运行接口进行缓存；以后在要相同的参数，直接从缓存中获取，不调用方法
     * 几个属性
     * cacheNames/value: 指定缓存组件的名字
     * key： 缓存数据使用的key： 可以用他来指定，默认是使用方法参数的值
     * 编写SpEl表达式： #id；参数id的值  @root.args[0];第一个参数的值
     * keyGenerator: key的生成器；可以自己指定key的生成器的组件id
     * key/keyGenerator 二选一
     * cacheManager: 指定缓存管理器 内存、redis、mongodb
     * condition: 指定符合条件的情况下缓存
     * unless: 否定缓存；当unless指定的条件为true，方法的返回值就不会被缓存;
     *
     * @param age 年龄
     * @return 返回对象
     */
    @GetMapping("/getPersion/{age}")
    @Cacheable(cacheNames = "persion", keyGenerator = "myKeyGenerator", unless = "#result == null")
    public Persion getPersion(@PathVariable("age") Integer age) {
        System.out.println("非缓存，查询数据库");
        Persion persion = new Persion();
        persion.setAge(age);
        return persion;
    }

    /**
     * 存在@CachePut注解的方法一定会被执行；
     * 注解@Cacheing中，里面既包含了@Cahceble又包含了@CachePut注解，那么此方法每次调用都会被执行
     *
     * @param persion
     * @return
     */
    @CachePut(value = "emp", key = "#result.id")
    public Persion updatePersion(Persion persion) {
        return persion;
    }


    @GetMapping("/cache/redis")
    public Persion redisTest() {
        Persion persion = new Persion();
        persion.setAge(13);
        persion.setBirth(new Date());
        persion.setUserName("liangyu");
        Pet pet = new Pet();
        pet.setAge(3);
        pet.setName("阿毛");
        persion.setPet(pet);
        redisTemplate.opsForValue().set("pet-01", persion);
        redisJsonTemplate.opsForValue().set("pet-02", persion);
        return persion;
    }


    //缓存
    @GetMapping("/cache/redisJson/{age}")
    @Cacheable(cacheNames = "redisJson", unless = "#result == null")
    public Persion redisJson(@PathVariable("age") Integer age) {
        System.out.println("方法体");
        Persion persion = new Persion();
        persion.setAge(age);
        persion.setBirth(new Date());
        persion.setUserName("liangyu");
        Pet pet = new Pet();
        pet.setAge(3);
        pet.setName("阿毛");
        persion.setPet(pet);
        return persion;
    }


    @GetMapping("/cache/lyCacheManage1/{age}")
    @Cacheable(cacheManager = "lyCacheManager",cacheNames = "lyCacheManage1", unless = "#result == null")
    public Persion lyCacheManage1(@PathVariable("age") Integer age) {
        System.out.println("lyCacheManage1方法体");
        Persion persion = new Persion();
        persion.setAge(age);
        persion.setBirth(new Date());
        persion.setUserName("liangyu");
        Pet pet = new Pet();
        pet.setAge(3);
        pet.setName("阿毛");
        persion.setPet(pet);
        return persion;
    }

}
