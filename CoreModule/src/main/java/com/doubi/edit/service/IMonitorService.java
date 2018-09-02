package com.doubi.edit.service;

import com.doubi.edit.dto.MonitorInfoBean;

/**
 * @ClessName IMonitorService
 * @Author liangliang
 * @Date 2018/8/31 16:10
 * @Version 1.0
 */
public interface IMonitorService {

  MonitorInfoBean getMonitorInfoBean() throws Exception;
}
