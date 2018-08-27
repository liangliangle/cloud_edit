package com.lianglianglee.edit;

import com.kcss.oss.OssConstConfig;
import com.lianglianglee.edit.config.OssCoreConfig;
import com.lianglianglee.oss.entity.OssConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 下午5:06 2018/5/28 2018
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