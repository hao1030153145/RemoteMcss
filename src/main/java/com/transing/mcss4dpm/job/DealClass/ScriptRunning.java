package com.transing.mcss4dpm.job.DealClass;

import com.alibaba.fastjson.JSON;
import com.transing.mcss4dpm.JobEvent.Bo.McssTask;
import com.transing.mcss4dpm.biz.service.impl.api.AppiumAction;
import com.transing.mcss4dpm.biz.service.impl.api.CrawlAction;
import com.transing.mcss4dpm.biz.service.impl.api.DriverManager2;
import com.transing.mcss4dpm.biz.service.impl.api.bo.AppiumDriverManager;
import com.transing.mcss4dpm.biz.service.impl.api.bo.AppiumSettingBo;
import com.transing.mcss4dpm.integration.bo.*;
import com.transing.mcss4dpm.util.CallRemoteServiceUtil;
import com.transing.mcss4dpm.util.DateUtil;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ${description}
 *
 * @author weiqiliu
 * @version 1.0 2018/1/30
 */
public class ScriptRunning {
    private static ScriptRunning inst = null;

    public static ScriptRunning getInstance() {
        if (inst == null) {
            inst = new ScriptRunning();
        }
        return inst;
    }

    public void execue(McssTask task) {
        String datasourceTypeId = task.getDataTypeId();
        String subTaskid = task.getSubTaskId(); //子任务id
        String param = task.getParam();
        String workFlowId = task.getWorkFlowId();
        System.out.println("workFlowId :>>>>>>>>>>>    " + workFlowId);
        System.out.println("datasourceTypeId :>>>>>>>>>>   " + datasourceTypeId);
        System.out.println("subTaskid :>>>>>>>>>>   " + subTaskid);
        System.out.println("param :>>>>>>>>>>   " + param);
        //解析输入参数
        List<SubTaskParam> subTaskParams = JSON.parseArray(param, SubTaskParam.class);
        List<ScriptDetailBO> scriptDetailBOList = new ArrayList<>();
        int startStep = 0;
        int backStep = 0;
        //获取可用设备
        DriverManager2 driverManager = DriverManager2.getInstance();
        //获取抓取规则
        List<CrawlRegulationBO> crawlRegulationBOList = new ArrayList<>();
        CrawlRegulationListBO crawlRegulationListBO = new CrawlRegulationListBO();
        CrawlRegulationItemBO crawlRegulationItemBO = driverManager.getCrawlRegulationByTypeid(datasourceTypeId);
        if (crawlRegulationItemBO != null) {
            crawlRegulationBOList = crawlRegulationItemBO.getCrawlRegulationBOList();
            crawlRegulationListBO = crawlRegulationItemBO.getCrawlRegulationListBO();
        }
        //初始化执行实现类
        AppiumAction appiumAction = new AppiumAction();
        //寻找是否有已经启动,并且在等待
        AppiumDriverManager appiumDriverManager = driverManager.getDriverByStatusAndDataTypeId("3", datasourceTypeId);
        if (appiumDriverManager == null) {
            //没有启动对应应用的设备
            //获取一个空闲设备
            appiumDriverManager = driverManager.getDriverByStatus("0");
            if (appiumDriverManager != null) {
                System.out.println("启动新设备  >>>>>>>>>>   : " + appiumDriverManager.getDeviceInfo().getDevicesName());
                //根据数据源类型id获取启动参数
                AppiumSettingBo appiumSettingBo = driverManager.getApplicationSettingBo(datasourceTypeId);
                if (appiumSettingBo == null) {
                    System.out.println("没有被配置的数据源类型>>>>>>>>>>  ");
                    return;
                }
                //启动services
                String servicePid = driverManager.startAppiumService(appiumDriverManager.getPort(), appiumDriverManager.getBp(), appiumDriverManager.getCp(), appiumDriverManager.getDeviceInfo().getDevicesName(), String.valueOf(appiumSettingBo.getNewCommandTimeout()));
                appiumDriverManager.setServicePid(servicePid);
                System.out.println(appiumDriverManager.getDeviceInfo().getDevicesName() + "的 servicePid 是  >>>>>>>>>>   : " + servicePid);
                appiumDriverManager.setStatus("1");
                //启动client
                DesiredCapabilities desiredCapabilities = driverManager.appiumSetting(appiumSettingBo);
                if (appiumSettingBo.getNewCommandTimeout() != null && appiumSettingBo.getNewCommandTimeout() > 0) {
                    appiumDriverManager.setDelay(appiumSettingBo.getNewCommandTimeout() * 1000);
                } else {
                    appiumDriverManager.setDelay(300 * 1000);
                }
                AndroidDriver androidDriver = driverManager.launchAppium(desiredCapabilities, appiumDriverManager, 10);
                //把该设备调整为启动运行状态
                appiumDriverManager.setAndroidDriver(androidDriver);
                appiumDriverManager.setBindApp(appiumSettingBo.getName());
                appiumDriverManager.setDatasourceTypeId(datasourceTypeId);
                appiumDriverManager.setStatus("2");
                //获取脚本详情
                scriptDetailBOList = driverManager.getScriptBytypeId(datasourceTypeId);
                //找出循环范围
                DivingBO divingBO = findDiving(scriptDetailBOList);
                startStep = 1;
                backStep = divingBO.getBack();
            } else {
                System.out.println("没有空闲设备  >>>>>>>>>>   寻找是否有异常设备,尝试启动异常设备");
                appiumDriverManager = driverManager.getDriverByStatusAndDataTypeId("9");
                if (appiumDriverManager != null) {
                    System.out.println("启动新设备  >>>>>>>>>>   : " + appiumDriverManager.getDeviceInfo().getDevicesName());
                    //根据数据源类型id获取启动参数
                    AppiumSettingBo appiumSettingBo = driverManager.getApplicationSettingBo(datasourceTypeId);
                    //启动services
                    String servicePid = driverManager.startAppiumService(appiumDriverManager.getPort(), appiumDriverManager.getBp(), appiumDriverManager.getCp(), appiumDriverManager.getDeviceInfo().getDevicesName(), String.valueOf(appiumSettingBo.getNewCommandTimeout()));
                    appiumDriverManager.setServicePid(servicePid);
                    System.out.println(appiumDriverManager.getDeviceInfo().getDevicesName() + "的 servicePid 是  >>>>>>>>>>   : " + servicePid);
                    appiumDriverManager.setStatus("1");
                    //启动client
                    DesiredCapabilities desiredCapabilities = driverManager.appiumSetting(appiumSettingBo);
                    if (appiumSettingBo.getNewCommandTimeout() != null && appiumSettingBo.getNewCommandTimeout() > 0) {
                        appiumDriverManager.setDelay(appiumSettingBo.getNewCommandTimeout() * 1000);
                    } else {
                        appiumDriverManager.setDelay(300 * 1000);
                    }
                    AndroidDriver androidDriver = driverManager.launchAppium(desiredCapabilities, appiumDriverManager, 10);
                    //把该设备调整为启动运行状态
                    appiumDriverManager.setAndroidDriver(androidDriver);
                    appiumDriverManager.setBindApp(appiumSettingBo.getName());
                    appiumDriverManager.setDatasourceTypeId(datasourceTypeId);
                    appiumDriverManager.setStatus("2");
                    //获取脚本详情
                    scriptDetailBOList = driverManager.getScriptBytypeId(datasourceTypeId);
                    //找出循环范围
                    DivingBO divingBO = findDiving(scriptDetailBOList);
                    startStep = 1;
                    backStep = divingBO.getBack();
                } else {
                    System.out.println("没有可用设备  等待15 * 60 * 1000秒>>>>>>>>>>   ");
                    appiumAction.sleep(15 * 60 * 1000);
                    return;
                }
            }
        } else {
            //有对应启动的设备
            //获取脚本详情
            System.out.println("启动已有设备  >>>>>>>>>>   ");
            scriptDetailBOList = driverManager.getScriptBytypeId(datasourceTypeId);
            //找出循环范围
            DivingBO divingBO = findDiving(scriptDetailBOList);
            startStep = divingBO.getBack();
            backStep = divingBO.getBack();
        }

        try {
            AndroidDriver androidDriver = appiumDriverManager.getAndroidDriver();
            String pid = appiumDriverManager.getServicePid();
            String devicesName = appiumDriverManager.getDeviceInfo().getDevicesName();
            List<Map<String, Object>> crawlMapList = new ArrayList<>();
            Map<String, Object> crawlMap = new HashMap<>();
            //判断是否有循环,并获取循环操作相关参数
            CircleBO circleBO = getCircleParam(scriptDetailBOList, subTaskParams);
            int circleStep = circleBO.getCircleStep();
            System.out.println("circleStep ============================" + circleStep);
            int circlemaxsize = 100;
            CircleRegulationRootBO circleRegulationArrayBO = circleBO.getCircleRegulationArrayBO();
            String circleCutOffType = circleBO.getCircleCutOffType();
            String circleCutOffValue = circleBO.getCircleCutOffValue();

            //1.开始执行
            for (int i = startStep; i < backStep; i++) {

                System.out.println("这里开始第"+i+"步 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                execueScript(i, scriptDetailBOList.get(i - 1), subTaskParams, driverManager, appiumDriverManager, appiumAction);
                //抓取数据
                try {
                    crawlMapList.addAll(getCrawlData(crawlRegulationBOList, crawlRegulationListBO, i, appiumAction, androidDriver, subTaskParams, crawlMap));
                } catch (Exception e) {
                    System.out.println("抓取第" + i + "异常,跳过继续处理  >>>>>>>>>>   " + e.getMessage());
                }
            }
            //2.开始循环
            int itemSize = 1;
            while (true) {
                System.out.println("while循环中的backStep = >>>>>>>>>>>>>>>>>>>>"+ backStep);
                System.out.println("while循环中的circleStep = >>>>>>>>>>>>>>>>>>>>"+ circleStep);
                for (int i = backStep; i <= circleStep; i++) {
                    System.out.println("for循环中的backStep = >>>>>>>>>>>>>>>>>>>>"+ backStep);
                    System.out.println("for循环中的circleStep = >>>>>>>>>>>>>>>>>>>>"+ circleStep);
                    execueScript(i, scriptDetailBOList.get(i - 1), subTaskParams, driverManager, appiumDriverManager, appiumAction);
                    //抓取数据
                    try {
                        crawlMapList.addAll(getCrawlData(crawlRegulationBOList, crawlRegulationListBO, i, appiumAction, androidDriver, subTaskParams, crawlMap));
                    } catch (Exception e) {
                        System.out.println("抓取第" + i + "异常,跳过继续处理  >>>>>>>>>>   ");
                    }
                }
                String jsonParam = net.sf.json.JSONArray.fromObject(crawlMapList).toString();
                callMcss(jsonParam, workFlowId, subTaskid, datasourceTypeId, subTaskParams);
                crawlMap.clear();
                //TODO 分页截至条件改动 最多循环100次,避免一直死循环
                if (circleCutOffType.equals("1")) {
                    //截止条件为  数值
                    try {
                        circlemaxsize = Integer.parseInt(circleCutOffValue);
                    } catch (Exception e) {
                        circlemaxsize = 100;
                    }
                    if (itemSize < circlemaxsize) {
                        if (circleRegulationArrayBO != null) {
                            for (CircleRegulationBO circleRegulationBO : circleRegulationArrayBO.getCircleRegulationBO()) {
                                String circleActionType = circleRegulationBO.getCircleRegulationType();
                                String circleElementValue = circleRegulationBO.getCircleRegulationValue();
                                driverManager.execuseAction("1", circleElementValue, circleActionType, "", appiumDriverManager, appiumAction);
                            }
                        }
                    } else {
                        break;
                    }
                } else {
                    //截止条件为  非数值
                    if (itemSize > circlemaxsize) {
                        break;
                    }
                    if (!appiumAction.haveElementBySource(appiumDriverManager.getAndroidDriver(), circleCutOffValue)) {
                        if (circleRegulationArrayBO != null) {
                            for (CircleRegulationBO circleRegulationBO : circleRegulationArrayBO.getCircleRegulationBO()) {
                                String circleActionType = circleRegulationBO.getCircleRegulationType();
                                String circleElementValue = circleRegulationBO.getCircleRegulationValue();
                                driverManager.execuseAction("1", circleElementValue, circleActionType, "", appiumDriverManager, appiumAction);
                            }
                        }
                    } else {
                        break;
                    }
                }
                itemSize++;
            }
            startStep = circleStep + 1;
            //3.结束执行
            for (int i = startStep; i <= scriptDetailBOList.size(); i++) {
                execueScript(i, scriptDetailBOList.get(i - 1), subTaskParams, driverManager, appiumDriverManager, appiumAction);
                //抓取数据
                try {
                    crawlMapList.addAll(getCrawlData(crawlRegulationBOList, crawlRegulationListBO, i, appiumAction, androidDriver, subTaskParams, crawlMap));
                } catch (Exception e) {
                    System.out.println("抓取第" + i + "异常,跳过继续处理  >>>>>>>>>>   ");
                }
            }
            //调用mcss存储接口
//            String jsonParam = net.sf.json.JSONArray.fromObject(crawlMapList).toString();
//            callMcss(jsonParam,workFlowId, subTaskid, datasourceTypeId, subTaskParams);
//            crawlMap.clear();
            //设置为运行等待状态
            appiumDriverManager.setStatus("3");
        } catch (Exception e) {
            System.out.println("scriptRuning异常 >>>>>>>" + e);
            driverManager.releaseAppiumDriver(appiumDriverManager, "9");
        }
    }

    private void execueScript(int i, ScriptDetailBO scriptDetailBO, List<SubTaskParam> subTaskParams, DriverManager2 driverManager, AppiumDriverManager appiumDriverManager, AppiumAction appiumAction) {
        System.out.println(appiumDriverManager.getDeviceInfo().getDevicesName() + "  脚本运行step  >>>>>>>>>>   " + i);
        //Element相关
        String elementType = String.valueOf(scriptDetailBO.getElementtype());
        String elementInputParamType = scriptDetailBO.getElementInputParamType();
        String elementValue = scriptDetailBO.getElementvalue();
        String elementinputparam = scriptDetailBO.getElementinputparam();
        String block = String.valueOf(scriptDetailBO.getBlock());
        String blockType = String.valueOf(scriptDetailBO.getBlockType());
        String blockValue = scriptDetailBO.getBlockValue();

        //Action相关
        String actionType = String.valueOf(scriptDetailBO.getActiontype());
        String actionInputParamType = scriptDetailBO.getActionInputParamType();
        String actionValue = scriptDetailBO.getActionvalue();
        String actionInputParam = scriptDetailBO.getActionInputParam();

        if (elementInputParamType != null && elementInputParamType.equals("2")) {
            for (SubTaskParam subTaskParam : subTaskParams) {
                if (subTaskParam.getParamEnName().equals(elementinputparam)) {
                    elementValue = subTaskParam.getSubParam();
                    break;
                }
            }
        }
        if (actionInputParamType != null && actionInputParamType.equals("2")) {
            for (SubTaskParam subTaskParam : subTaskParams) {
                if (subTaskParam.getParamEnName().equals(actionInputParam)) {
                    actionValue = subTaskParam.getSubParam();
                    break;
                }
            }
        }
        //运行脚本
        if (block.equals("1")) {
            System.out.println("滑动阻断运行   >>>>>>>>>> ");
            int maxWhile = 0;
            if (maxWhile < 5) {
                System.out.println("滑动阻断执行第   >>>>>>>>>> " + maxWhile + "次");
                while (true) {
                    if (appiumAction.haveElementBySource(appiumDriverManager.getAndroidDriver(), blockValue)) {
                        System.out.println("滑动阻断执行满足条件  退出 >>>>>>>>>> "+blockValue);
                        break;
                    } else {
                        System.out.println("滑动阻断执行第   >>>>>>>>>> " + maxWhile + "次");
                        driverManager.execuseAction(elementType, elementValue, actionType, actionValue, appiumDriverManager, appiumAction);
                        maxWhile++;
                        appiumAction.sleep(1 * 1000);
                    }
                }
            }
        } else {
            System.out.println("没有滑动阻断运行    >>>>>>>>>>>>>>>");
            driverManager.execuseAction(elementType, elementValue, actionType, actionValue, appiumDriverManager, appiumAction);
        }
    }

    private DivingBO findDiving(List<ScriptDetailBO> scriptDetailBOList) {
        DivingBO divingBO = new DivingBO();
        divingBO.setBack(1);
        //找出循环
        for (int i = 0; i < scriptDetailBOList.size(); i++) {
            if (scriptDetailBOList.get(i).getBack() != 0) {
                divingBO.setBack(scriptDetailBOList.get(i).getBack());
            }
        }
        return divingBO;
    }

    private CircleBO getCircleParam(List<ScriptDetailBO> scriptDetailBOList, List<SubTaskParam> subTaskParams) {
        CircleBO circleBO = new CircleBO();
        int circleStep = scriptDetailBOList.size();
        int circlemaxsize = 0;
        CircleRegulationRootBO circleRegulationArrayBO = null;
        String circleCutOffType = "";
        String circleCutOffValue = "";
        for (ScriptDetailBO scriptDetailBO : scriptDetailBOList) {
            if (scriptDetailBO.getCircle() == 1) {
                circleStep = scriptDetailBO.getCirclestep();
                String circleInputParam = scriptDetailBO.getCircleinputparamId();
                if (circleInputParam == null || circleInputParam.equals("")) {
                    circleCutOffValue = scriptDetailBO.getCircleCutOffValue();
                    circleCutOffType = scriptDetailBO.getCircleCutOffType();
                } else {
                    for (SubTaskParam subTaskParam : subTaskParams) {
                        if (subTaskParam.getParamEnName().equals(circleInputParam)) {
                            try {
                                circleCutOffValue = subTaskParam.getSubParam();
                                circleCutOffType = "1";
                            } catch (Exception e) {
                                circleCutOffValue = scriptDetailBO.getCircleCutOffValue();
                                circleCutOffType = scriptDetailBO.getCircleCutOffType();
                            }
                            break;
                        }
                    }
                }
                String circleRegulation = scriptDetailBO.getCircleRegulation();
                if (circleRegulation != null && !circleRegulation.equals("")) {
                    circleRegulationArrayBO = JSON.parseObject(circleRegulation, CircleRegulationRootBO.class);
                }
                break;
            }
        }
        circleBO.setCircleStep(circleStep);
        circleBO.setCirclemaxsize(circlemaxsize);
        circleBO.setCircleRegulationArrayBO(circleRegulationArrayBO);
        circleBO.setCircleCutOffType(circleCutOffType);
        circleBO.setCircleCutOffValue(circleCutOffValue);
        return circleBO;
    }

    private List<Map<String, Object>> getCrawlData(List<CrawlRegulationBO> crawlRegulationBOList, CrawlRegulationListBO crawlRegulationListBO, int i, AppiumAction appiumAction, AndroidDriver androidDriver, List<SubTaskParam> subTaskParams, Map<String, Object> crawlMap) throws Exception {
        List<Map<String, Object>> crawlMapList = new ArrayList<>();
        String content = appiumAction.getScreenSrc(androidDriver);
        List<String> contentList = new ArrayList<>();
        //执行抓取分页脚本
        if (crawlRegulationListBO != null && crawlRegulationListBO.getStep() == i) {
            //前置处理
            CrawlAction crawlAction = new CrawlAction();
            String beforeprocessorArray = crawlRegulationListBO.getBeforeprocessorArray();
            content = crawlAction.beforeProcessorAction(beforeprocessorArray, content);
            //抓取规则
            String crawlArray = crawlRegulationListBO.getCrawlArray();
            List<String> crawlStringList = crawlAction.listCrawl(crawlArray, content);
            for (String crawlString : crawlStringList) {
                //后置处理
                String afterProcessorArray = crawlRegulationListBO.getAfterprocessorArray();
                crawlString = crawlAction.afterProcessorAction(afterProcessorArray, crawlString);
                contentList.add(crawlString);
            }
        } else {
            contentList.add(content);
        }

        for (String contents : contentList) {
            //执行抓取内容脚本
            List<CrawlRegulationBO> crawlRegulationBOStepList = new ArrayList<>();
            for (CrawlRegulationBO crawlRegulationBO : crawlRegulationBOList) {
                if (crawlRegulationBO.getStep().equals(i + "")) {
                    crawlRegulationBOStepList.add(crawlRegulationBO);
                }
            }
            //如果该步骤有抓取任务,获取页面,执行抓取脚本
            if (crawlRegulationBOStepList.size() > 0) {
                for (CrawlRegulationBO crawlRegulationBO : crawlRegulationBOStepList) {
                    CrawlAction crawlAction = new CrawlAction();
                    String crawlString = crawlAction.crawl(crawlRegulationBO, contents, subTaskParams, androidDriver);
                    Object crawlItem = crawlString;
                    if (crawlRegulationBO.getType().equalsIgnoreCase("int")) {
                        if (crawlString == null) {
                            crawlItem = 0;
                        } else {
                            try {
                                crawlItem = Integer.parseInt(crawlString);
                            } catch (Exception e) {
                                crawlItem = 0;
                            }
                        }
                    } else if (crawlRegulationBO.getType().equalsIgnoreCase("datetime")) {
                        if (crawlString == null) {
                            crawlItem = System.currentTimeMillis();
                        } else {
                            crawlItem = DateUtil.parseDate(crawlString);
                        }
                    }
                    crawlMap.put(crawlRegulationBO.getItem(), crawlItem);
                }
                crawlMapList.add(crawlMap);
            }
        }
        return crawlMapList;
    }

    private void callMcss(String jsonParam,String workFlowId, String subTaskid, String datasourceTypeId, List<SubTaskParam> subTaskParams) {
        //调用mcss存储接口
        String mcssServer = System.getProperty("mcss_url");
        String getDataUrl = "/crawlTask/preserveData.json";
        Map<String, String> dataTypePassMap = new HashMap<String, String>();
        dataTypePassMap.put("taskId", subTaskid);
        dataTypePassMap.put("datasourceTypeId", datasourceTypeId);
        dataTypePassMap.put("jsonParam", jsonParam);
        dataTypePassMap.put("workFlowId", workFlowId);
        dataTypePassMap.put("formerUrl", System.currentTimeMillis() + "");
//        for (SubTaskParam subTaskParam : subTaskParams) {
//            if (subTaskParam.getParamEnName().equalsIgnoreCase("url")) {
//                dataTypePassMap.put("formerUrl", subTaskParam.getSubParam());
//                System.out.println("formerUrl    >>>>>>>>>>   " + subTaskParam.getSubParam());
//                break;
//            }
//        }
        System.out.println("jsonParam    >>>>>>>>>>   " + jsonParam);
        System.out.println("taskId    >>>>>>>>>>   " + subTaskid);
        System.out.println("datasourceTypeId    >>>>>>>>>>   " + datasourceTypeId);
        System.out.println("workFlowId    >>>>>>>>>>   " + workFlowId);
        CallRemoteServiceUtil.callRemoteService(this.getClass().getName(), mcssServer + getDataUrl, "post", dataTypePassMap);
    }

}
