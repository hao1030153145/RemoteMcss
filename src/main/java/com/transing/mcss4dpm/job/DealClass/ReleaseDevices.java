package com.transing.mcss4dpm.job.DealClass;

import com.jeeframework.logicframework.integration.sao.zookeeper.BaseSaoZookeeper;
import com.jeeframework.webframework.exception.WebException;
import com.transing.mcss4dpm.JobEvent.Bo.ScriptTask;
import com.transing.mcss4dpm.biz.service.impl.api.DriverManager2;
import com.transing.mcss4dpm.biz.service.impl.api.bo.AppiumDriverManager;
import com.transing.mcss4dpm.web.exception.MySystemCode;

/**
 * ${释放设备}
 *
 * @author weiqiliu
 * @version 1.0 2018/2/28
 */
public class ReleaseDevices {

    private static ReleaseDevices inst = null;

    public static ReleaseDevices getInstance() {
        if (inst == null) {
            inst = new ReleaseDevices();
        }
        return inst;
    }

    public void execue(ScriptTask task) {
        BaseSaoZookeeper baseSaoZookeeper = task.getBaseSaoZookeeper();
        String deviceId = task.getDeviceId();
        String serverName = task.getServerName();
        DriverManager2 driverManager2 = DriverManager2.getInstance();
        AppiumDriverManager driverManager = driverManager2.getDriverByDevicesId(Long.parseLong(deviceId));
        driverManager2.releaseAppiumDriver(driverManager);
        String baseUsedPath = "/mcss_device_used";
        String path = baseUsedPath + "/" + serverName + "_" + deviceId;
        for (int i = 0; i < 3; i++) {
            try {
                if (baseSaoZookeeper.isExisted(path)) {
                    baseSaoZookeeper.delete(path, true);
                }
                break;
            } catch (Exception e) {
                System.out.println("zk删除设备失败    >>>>>>>>>>   " + path);
                throw new WebException(MySystemCode.GET_DELETE_DEVICE_NULL);
            }
        }
    }
}
