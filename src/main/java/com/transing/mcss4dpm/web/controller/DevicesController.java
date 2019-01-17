package com.transing.mcss4dpm.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jeeframework.logicframework.biz.service.mq.producer.BaseKafkaProducer;
import com.transing.mcss4dpm.JobEvent.Bo.ScriptTask;
import com.transing.mcss4dpm.biz.service.TaskService;
import com.transing.mcss4dpm.biz.service.impl.api.bo.DevicesInfoBo;
import com.transing.mcss4dpm.integration.bo.DevicesInf;
import com.transing.mcss4dpm.web.po.RunningActionPO;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ${脚本规则,脚本录制}
 *
 * @author weiqiliu
 * @version 1.0 2018/1/22
 */
@Controller("devicesController")
@Api(value = "设备维护相关", description = "关于设备信息访问接口", position = 2)
@RequestMapping(path = "/devicesInfo")
public class DevicesController {


    @Resource
    private TaskService taskService;

    @Resource
    private BaseKafkaProducer baseKafkaProducer;


    @RequestMapping(value = "/getDevicesInfo.json", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "获得数据库中设备信息", position = 0)
    public List<DevicesInf> getDevicesInfo(@RequestParam(value = "registServer", required = false) @ApiParam(value = "registServer", required = false) String registServer,
                                           HttpServletRequest req, HttpServletResponse res) {

        System.out.println("=========成功进入远程mcss，/getDevicesInfo.json调用接口成功=======");
        DevicesInf devicesInf = new DevicesInf();
        devicesInf.setRegistServer(registServer);
        return taskService.getDevicesByServerList(devicesInf);
    }


    @RequestMapping(value = "/addDevicesInfo.json", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加设备信息", position = 0)
    public RunningActionPO addDevicesInfo(@RequestParam(value = "id", required = true) @ApiParam(value = "id", required = true) String id,
                                          @RequestParam(value = "devicesName", required = true) @ApiParam(value = "devicesName", required = true) String devicesName,
                                          @RequestParam(value = "mobileType", required = false) @ApiParam(value = "mobileType", required = false) String mobileType,
                                          @RequestParam(value = "serialNumber", required = false) @ApiParam(value = "serialNumber", required = false) String serialNumber,
                                          @RequestParam(value = "registServer", required = true) @ApiParam(value = "registServer", required = true) String registServer,
                                          @RequestParam(value = "height", required = false) @ApiParam(value = "height", required = false) String height,
                                          @RequestParam(value = "width", required = false) @ApiParam(value = "width", required = false) String width,
                                          @RequestParam(value = "funcation", required = true) @ApiParam(value = "function", required = true) String funcation,
                                          HttpServletRequest req, HttpServletResponse res) {

        System.out.println("=========成功进入远程mcss，/addDevicesInfo.json调用接口成功=======");
        String dealClass = "DevicesInfo";
        DevicesInfoBo devicesInfoBo = new DevicesInfoBo();

        devicesInfoBo.setId(Integer.parseInt(id));
        devicesInfoBo.setDevicesName(devicesName);
        devicesInfoBo.setFuncation(funcation);
        devicesInfoBo.setRegistServer(registServer);
        devicesInfoBo.setSerialNumber(serialNumber);
        String param = JSONObject.toJSONString(devicesInfoBo);

        //封装
        ScriptTask scriptTask = new ScriptTask();
        scriptTask.setParam(param);
        scriptTask.setServerName(registServer);
        scriptTask.setDealClass(dealClass);

        String paramTask = JSON.toJSONString(scriptTask);
        //调用kafaka send方法
        //baseKafkaProducer.send(registServer, paramTask);
        //返回参数
        RunningActionPO runningActionPO = new RunningActionPO();
        runningActionPO.setScriptId(1);
        return runningActionPO;
    }


}
