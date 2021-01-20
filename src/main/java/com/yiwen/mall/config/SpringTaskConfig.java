package com.yiwen.mall.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author ywxie
 * @date 2020/10/20 13:54
 * @describe 定时任务配置
 * SpringTask是Spring自主研发的轻量级定时任务工具，相比于Quartz更加简单方便，且不需要引入其他依赖即可使用。
 */
@Configuration
@EnableScheduling
public class SpringTaskConfig {
}
