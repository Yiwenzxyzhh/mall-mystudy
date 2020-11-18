package com.yiwen.mall.component.annotation;

import java.lang.annotation.*;

/**
 * @author ywxie
 * @date 2020/11/16 15:44
 * @describe 自定义注解，有该注解的缓存方法会抛出异常
 * 不过并不是所有的方法都需要处理异常的，比如我们的验证码存储，
 * 如果我们的Redis宕机了，我们的验证码存储接口需要的是报错，而不是返回执行成功。
 * 对于上面这种需求我们可以通过自定义注解来完成，首先我们自定义一个CacheException注解，如果方法上面有这个注解，发生异常则直接抛出。
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheException {
}
