package com.transing.mcss4dpm.job.DealClass;

import com.alibaba.fastjson.JSON;
import com.transing.mcss4dpm.JobEvent.Bo.ScriptTask;
import com.transing.mcss4dpm.biz.service.impl.api.ShellProcess;
import com.transing.mcss4dpm.biz.service.impl.api.bo.AndroidDriverStatus;
import com.transing.mcss4dpm.biz.service.impl.api.bo.AppiumDriverManager;
import com.transing.mcss4dpm.biz.service.impl.api.bo.DevicesInfoBo;
import com.transing.mcss4dpm.biz.service.impl.api.impl.DriverManagerImpl;
import com.transing.mcss4dpm.biz.service.impl.api.impl.LinuxCommandImpl;

import java.util.List;
import java.util.Scanner;

/**
 * ${设备添加}
 *
 * @author haolen
 * @version 1.0 2018/7/27
 */
public class DevicesInfo extends DriverManagerImpl implements LinuxCommandImpl {
    private static DevicesInfo inst = null;

    public static DevicesInfo getInstance() {
        if (inst == null) {
            inst = new DevicesInfo();
        }
        return inst;
    }

    public void execue(ScriptTask task) {

        String dataTypeId = task.getDataTypeId();
        String param = task.getParam();
        DevicesInfoBo devicesInfoBo = JSON.parseObject(param, DevicesInfoBo.class);
        System.out.println("获得kafka里面的参数为========="+JSON.toJSONString(devicesInfoBo));
        // 获得原本的集合里面的数据个数
        int size =  driverManagerList.size();
        System.out.println("集合里面的数据为=========="+JSON.toJSONString(driverManagerList)+"=======一个有====="+size);
        AppiumDriverManager appiumDriverManager = new AppiumDriverManager();
        appiumDriverManager.setDeviceInfo(devicesInfoBo);
        int port = 4726;
        int bp = 4724;
        int cp = 9515;
        appiumDriverManager.setBp(bp - size);
        appiumDriverManager.setCp(cp + size);
        appiumDriverManager.setPort(port + size);
        appiumDriverManager.setStatus("0");
        driverManagerList.add(appiumDriverManager);
        System.out.println("经过一些列的处理之后集合里面的数据为=========="+JSON.toJSONString(driverManagerList));

    }



    @Override
    public String startAppiumService(int port, int bp, int cp, String devicesName, String timeout) {
        return null;
    }

    @Override
    public boolean stopAppiumService(String pid, String deviceName) {
        return false;
    }

    @Override
    public boolean releaseAppiumDriver(AppiumDriverManager driverManager) {
        return false;
    }

    @Override
    public boolean releaseAppiumDriver(AppiumDriverManager driverManager, String status) {
        return false;
    }

    @Override
    public List<DevicesInfoBo> getConnectDevices() {
        return null;
    }

    @Override
    public AppiumDriverManager getDriverByStatus(String status) {
        return null;
    }

    @Override
    public AppiumDriverManager getDriverByDevicesId(long id) {
        return null;
    }

    @Override
    public AppiumDriverManager getDriverByStatusAndDevicesId(String status, long id) {
        return null;
    }

    @Override
    public AppiumDriverManager getDriverByStatusAndDataTypeId(String status, String dataTypeId) {
        return null;
    }

    @Override
    public AppiumDriverManager getDriverByStatusAndDataTypeId(String status) {
        return null;
    }

    @Override
    public AndroidDriverStatus getDriverById(String servicePid) {
        return null;
    }

    @Override
    public AndroidDriverStatus getDriverByName(String deviceName) {
        return null;
    }

    @Override
    public AndroidDriverStatus getDriverByBindApp(String bindApp) {
        return null;
    }

    @Override
    public void setAndroidDriverStatusStatus(AndroidDriverStatus androidDriverStatus, String status) {

    }

    @Override
    public ShellProcess executeShell(String shell, boolean isWait) {
        return null;
    }

    @Override
    public Scanner getShellResultContent(Process process) {
        return null;
    }
}
