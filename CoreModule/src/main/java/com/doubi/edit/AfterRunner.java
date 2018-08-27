package com.doubi.edit;

import org.lianglianglee.oss.OssConstConfig;
import com.doubi.edit.config.OssCoreConfig;
import org.lianglianglee.oss.entity.OssConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Auther :lianglianglee
 * @Description :
 * @Date: 2018年8月27日
 * @Modify:
 */
@Component
public class AfterRunner implements CommandLineRunner {

    @Autowired
    private OssCoreConfig ossCoreConfig;
    private static Logger LOGGER = Logger.getLogger(AfterRunner.class);

    /**
     * 会在服务启动完成后立即执行.
     */
    @Override
    public void run(String... args) {
        OssConfig config = new OssConfig();
        config.setAccessKeyId(ossCoreConfig.getAccessKeyId());
        config.setAccessKeySecret(ossCoreConfig.getAccessKeySecret());
        config.setBucketName(ossCoreConfig.getBucketName());
        config.setEndpoint(ossCoreConfig.getEndpoint());
        OssConstConfig.setConfig(config);
        //刷新其他模块的缓存
        LOGGER.info("系统配置初始化");
    }
}