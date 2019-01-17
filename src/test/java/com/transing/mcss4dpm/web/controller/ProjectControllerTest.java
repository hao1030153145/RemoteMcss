package com.transing.mcss4dpm.web.controller;

import com.jeeframework.testframework.AbstractSpringBaseControllerTest;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static junit.framework.TestCase.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProjectControllerTest extends AbstractSpringBaseControllerTest {

    @Test
   // @Rollback(value = false)
    public void testDataTypePass() throws Exception {
        String requestURI = "/crawlTask/executeCrawl.json";
        MvcResult mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders.post(requestURI).param("paramType", "0").param("testInLogin", "no").param("typeNo", "dataMcrawl").param("firstDetailId", "11541").param("projectId", "2563").param("flowDetailId", "11542").param("flowId", "0").param("workFlowId", "0").param("travelParams", " {\"flowDetailId\":11542}").param("jsonParam", "{\"count\":0,\"jsonParam\":{\"crawlWay\":\"\",\"datasourceId\":\"461\",\"datasourceName\":\"盖茨拜耳\",\"datasourceTypeId\":\"1223\",\"datasourceTypeName\":\"盖茨拜耳-内容\",\"inputParamArray\":[{\"controlProp\":\"\",\"createTime\":{\"date\":18,\"day\":6,\"hours\":2,\"minutes\":24,\"month\":7,\"seconds\":10,\"time\":1534530250000,\"timezoneOffset\":-480,\"year\":118},\"datasourceId\":461,\"datasourceTypeId\":1223,\"id\":1733,\"isRequired\":\"0\",\"paramCnName\":\"临时链接\",\"paramEnName\":\"tempurl\",\"prompt\":\"临时链接\",\"restrictions\":\"\",\"styleId\":\"5\",\"styleName\":\"输入与上传\",\"styleCode\":\"input-file\",\"paramValue\":\"[{http://mp.weixin.qq.com/s?src=11&timestamp=1534902667&ver=1075&signature=C1LPslhdAbathW*WZX2GWnoOZAW0jBTp28paQZZZHyyzV*ALYxruc13Fd*Iafbiqa5MaK4B9ladJZAJ2JCn4ELBp5FSpuj0IzoE6sZKGRn1zI2HliUHB4tQdPmAUKGzv&new=1,http://mp.weixin.qq.com/s?src=11&timestamp=1534902667&ver=1075&signature=ca*pydBYRuAlCoT6vuYFAl-uhvPKcDkRi7E1X4Jam5bD4Q9o*2NsOPVJNe*7*qc-fClykhh-V5hv493*DfzsywldfiI6cV2xStaa8ypq6hDTjYviB5YCElAO8F7L6UkC&new=1,http://mp.weixin.qq.com/s?src=11&timest2XAZN5CGynEoj4XgDLaSCHhceuIWrBUr0h3eVNS0qOXvd8dHUKTXCXifoA1vh37C9nnCxBUKQz5RYWRK4*SRd3sFWGmz9PbTal1LR7kbMKSp&new=1,http://mp.weixin.qq.com/s?src=11&timestamp=1534902667&ver=1075&signature=uZEs7td1T0ngGyxgKK08zLvEtE8hno7N8vzIyrc8fgByp1Bd6CQQuGo80AAkwmRBDatYMlGdaL3tgCXJ19qpsrl5L4BsncHacKjehm0HbtiIG1w0dJAq7AO5cxSojgWN&new=1,http://mp.weixin.qq.com/s?src=11&timestamp=1534902667&ver=1075&signature=XkkGTy8Jl5GkXHKzjUJMypAR*iVKOVflmnI1*y9t2TORZrXh9yYaiJ08dwdzGh7ueGPEP7qt51ePoToGsdjMj2At2fACXB92eYCoRIq5j01heHPTQTzp0PPcUfTFPdv-&new=1,http://mp.weixin.qq.com/s?src=11&timestamp=1534902667&ver=1075&signature=qWDUH*EFrZLlCv-8wFS9ZF9pElwOi3ixkmwW*jwk*kTXa*pw4W-cDhQA*FusVn3VnTawEFZY-O-aCpoz2mBLD0e62B83oU23Uh8LtIwluRaWoczZ*iPOjgrIZsN4*rNT&new=1,http://mp.weixin.qq.com/s?src=11&timestamp=1534902667&ver=1075&signature=E8vW68U*hmOZMHyeeQJafUB*1JE5B9r7tsrg3WWvIulHVUHVmBDtfylQhGgratt2kCUNr2gHpVdCtCoTmoRSzHw-pOFIKB*eJZUrUwMuK1fwXgkZi3SDcpxVE446ia5a&new=1}]\"}],\"status\":0,\"taskName\":\"流程抓取盖茨拜耳-内容\",\"workFlowTemplateId\":0},\"status\":0,\"taskName\":\"流程抓取盖茨拜耳-内容\",\"workFlowTemplateId\":0}"))
                .andDo(print()).andExpect(status().isOk()).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertTrue(com.jeeframework.util.json.JSONUtils.isJSONValid(response));
        System.out.println();
        assertTrue(JSONObject.fromObject(response).getInt("code") == 0);
    }

    @Test
    @Rollback(value = false)
    public void testupdataCopleteNu() throws Exception {
        String requestURI = "/crawlTask/updataCopleteNu.json";
        MvcResult mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders.get(requestURI).param("testInLogin", "no").param("subTaskId", "3").param("status", "1")).andDo(print()).andExpect(status().isOk()).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertTrue(com.jeeframework.util.json.JSONUtils.isJSONValid(response));
        System.out.println();
        assertTrue(JSONObject.fromObject(response).getInt("code") == 0);
    }

    @Test
    @Rollback(value = false)
    public void testDevicesList() throws Exception {
        String requestURI = "/getDevicesList.json";
        MvcResult mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders.get(requestURI).param("testInLogin", "no").param("registServer", "mcss_1")).andDo(print()).andExpect(status().isOk()).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertTrue(com.jeeframework.util.json.JSONUtils.isJSONValid(response));
        System.out.println();
        assertTrue(JSONObject.fromObject(response).getInt("code") == 0);
    }

    @Test
    @Rollback(value = false)
    public void test() throws Exception {
        String requestURI = "/test.json";
        MvcResult mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders.get(requestURI).param("testInLogin", "no")).andDo(print()).andExpect(status().isOk()).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertTrue(com.jeeframework.util.json.JSONUtils.isJSONValid(response));
        System.out.println();
        assertTrue(JSONObject.fromObject(response).getInt("code") == 0);
    }

    @Test
    @Rollback(value = false)
    public void testThread() throws Exception {
        String requestURI = "/testThread.json";
        MvcResult mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders.get(requestURI).param("testInLogin", "no").param("registServer", "mcss_2")).andDo(print()).andExpect(status().isOk()).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertTrue(com.jeeframework.util.json.JSONUtils.isJSONValid(response));
        System.out.println();
        assertTrue(JSONObject.fromObject(response).getInt("code") == 0);
    }

    @Test
    @Rollback(value = false)
    public void testStopCrawl() throws Exception {
        String requestURI = "/crawlTask/stopCrawl.json";
        MvcResult mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders.get(requestURI).param("testInLogin", "no").param("taskId", "265")).andDo(print()).andExpect(status().isOk()).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertTrue(com.jeeframework.util.json.JSONUtils.isJSONValid(response));
        System.out.println();
        assertTrue(JSONObject.fromObject(response).getInt("code") == 0);
    }

    @Test
    @Rollback(value = false)
    public void testPreserveData() throws Exception {
        String requestURI = "/crawlTask/preserveWeiXinData.json";
        MvcResult mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders.post(requestURI).param("testInLogin", "no").param("jsonParam", "{\"author\":\"医保小组\",\"commentList\":[],\"content\":\"7月29日上午，由北京大学肿瘤医院医保处牵头举办的北京市肿瘤卫生经济及医疗保险联盟成立大会暨首届峰会顺利召开。本次大会邀请到了北京大学肿瘤医院郭军副院长、北京市人力资源和社会保障局徐仁忠副巡视员、北京市医疗保险事务管理中心杜鑫主任、北京卫生经济学会医疗保险专业委员会贾方红主任、北京市公共卫生信息中心统计室郭默宁主任、北京市肿瘤防治办公室王宁主任等多位来自医疗、医保及相关领域的专家和学者莅临指导。来自北京协和医院、中国医学科学院肿瘤医院、北京大学人民医院、北京大学第三医院、北京南郊肿瘤医院等19家兄弟医院的医保处负责人也积极响应会议邀请并热情到会。此外，还有我院医师代表、卫生经济领域的企业代表和商业保险的精英也列席会议。北京大学肿瘤医院郭军副院长北京市人力资源和社会保障局徐仁忠副巡视员北京市医疗保险事务管理中心杜鑫主任北京卫生经济学会医疗保险专业委员会贾方红主任北京市公共卫生信息中心统计室郭默宁主任北京市肿瘤防治办公室王宁主任会议由北京大学肿瘤医院郭军副院长的致辞拉开序幕。郭军副院长指出，恶性肿瘤是当前危害人类健康的主要疾病之一，尽管发达国家肿瘤的发病率已经呈现下降趋势，但我国作为发展中国家，肿瘤的发病率仍逐年攀升，这其中凸显出肿瘤防治工作及建立契合肿瘤病人的医保支付体系的重要性及迫切性。在不断深化医疗质量内涵管理的同时，也需注意到保险的支付方式与之息息相关。郭军副院长对联盟在推动北京地区肿瘤相关医疗保险、卫生经济及防治等领域发挥出价值和贡献给予了殷切期望，并预祝本次峰会圆满成功。在接下来的领导讲话中，北京市人力资源和社会保障局徐仁忠副巡视员对大会和联盟的主题和思维角度给予了高度评价，并希望联盟能够多视角、多方面地提出更多的惠民建议，促使医保和卫生经济发挥更大的作用；北京市医疗保险事务管理中心杜鑫主任表示，长久以来医保一直致力于探讨如何使医保基金效益最大化，在避免基金浪费上做了大量工作的基础上，下一步的工作重点应该是研究如何把节约的医保基金用到真正需要的患者身上，希望联盟是真正搞研究、不功利、有作为的联盟；北京卫生经济学会医疗保险专业委员会贾方红主任认为，医保是世界难题，而肿瘤领域的医保又是重中之重，因为消耗的费用所占的比重大，希望联盟能够认真研究、造福参保人；北京市公共卫生信息中心统计室郭默宁主任表示，北京市公共卫生信息中心作为数据管理部，服务于医疗管理、卫生经济及医疗保险等领域，希望能有机会与联盟展开紧密合作。在与会领导热情洋溢的致辞之后，与会嘉宾全体表决，同意联盟成立。随后，联盟执行主席，医保处冷家骅处长为各位联盟嘉宾及成员介绍了联盟成立的背景、工作设想以及发展规划：汇聚专业精英和跨学科人才，研讨分析北京地区肿瘤防诊治全程中亟待解决的卫生经济和医疗保险领域相关问题，促进医保决策和医疗决策形成决策合力，搭建政府、研究机构、医疗机构、企业等的沟通平台，为上级政府部门提供政策咨询，为相关医疗机构提供解决方案，促进首都医药卫生事业发展，进而最终实现保险政策的惠民价值。北京大学肿瘤医院医保处冷家骅处长接下来，会议进入学术及经验分享环节。该环节由卫生经济和医疗保险两个部分组成。在卫生经济部分中，北京大学肿瘤医院医保处冷家骅处长基于肿瘤外科医生视角，结合临床工作中遇到的真实案例，为大家介绍了《现代肿瘤外科面临的卫生经济学挑战》，指出现行体制下，卫生经济学的推广应用有助于合理高效的分配医疗资源，实现一定偿付能力下的“最佳”医疗。随后，拜耳公司高级药物经济学经理朱旻深入浅出，形象的为大家介绍了《药物经济学在医疗决策中的作用》，详细阐述了药物经济学的定义、作用、目的、方法等。而北京大学肿瘤医院影像科唐磊主任以影像医师视角，介绍了《医学影像如何握住“看不见的手”：关于卫生经济学若干问题的思考》，提出了临床影像日常工作中亟待进行经济学评价的相关问题。最后，北京市肿瘤防治办公室王宁主任站在北京地区肿瘤疾病的流行特征及现状角度，为来宾介绍《基于肿瘤登记工作服务北京市恶性肿瘤综合防治》，阐述了肿瘤登记工作的流程、相关肿瘤发病数据的统计结果。在医疗保险部分中，北京大学人民医院医保处王茹副主任率先介绍了《北京大学人民医院DRGs付费探索与实践》，对人民医院在北京市DRGs预付费试点过程中的相关难题及心得体会进行了分享。北京宣武医院医保处焦卫平主任介绍了《肿瘤防治与医保的几点思考——基层医保管理者的工作体会》，提倡理性对待支付方式在医改中作用，强调多学科多部门合作加强医疗保险在肿瘤防治中重要的资源配置作用。北京大学肿瘤医院医保处刘忆副处长及郑启文介绍《肿瘤DRGs精细化管理与应用》，展示了DRGs用于我院医保管理的工作思路，总结了医务处、病案统计室、医保处、运营办四部门联合进行的DRGs推广活动，并以胃癌手术病例为例把DRGs数据分析思路进行了展示和梳理。最后，泰康健康医疗管理部王艳萍总经理介绍了《商业健康险之肿瘤保险》，从健康险市场整体概况出发，对商业健康险中肿瘤保险以及发展趋势和面临的困境进行了阐述。最后，大会进入圆桌会议环节，与会嘉宾成员共同参与讨论，为联盟今后的发展方向献言献策。参与讨论的人员纷纷表示，联盟今后的发展是有前途、有意义、有内涵的，坚信在市一级机构的关怀和成员单位的共同努力下，联盟必将为医疗保险发展贡献出举足轻重的力量。长按识别或搜索们的微信公众号ID： hiyibaoer\",\"formerUrl\":\"http://mp.weixin.qq.com/s?src=11&timestamp=1512358814&ver=553&signature=xKggyyWkNhLYXNBMUODJW5-I-8Ax7-2wp2jGUgKeNr8FXfzRsClkXW5XfII9saw-lTrPSDiteIo4fs24WFhwsMNpGX3ZFBh5MW9Yw742LxPI-roZyRZKvDJ4M0YJvDvP&new=1\",\"publishTime\":\"2017-12-04\",\"replytimes\":\"1\",\"sougoid\":0,\"title\":\"北京市肿瘤卫生经济及医疗保险联盟成立大会暨首届峰会顺利召开\",\"url\":\"http%3A%2F%2Fmp.weixin.qq.com%2Fs%3F__biz%3DMzU3NTIzNTU0Nw%3D%3D%26mid%3D2247483661%26idx%3D1%26sn%3Da08cf2583ceb3d59b0f8383ee61146b9%26scene%3D0%26ascene%3D1%26devicetype%3Dandroid-23%26version%3D2605033c%26nettype%3DWIFI%26abtest_cookie%3DAwABAAgACgAMAAkAnoYeAEuIHgBgiB4A34geAPyIHgANiR4ASIkeAG%252BJHgDKiR4AAAA%253D%26pass_ticket%3Dv60jsSh%252BtWkEU2VgSOO%252FFzVPrtN2HZZmUp8YBDf8zSY%253D%26wx_header%3D1\",\"viewtimes\":\"3\"}").param("datasourceTypeId", "16").param("taskId", "2")).andDo(print()).andExpect(status().isOk()).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertTrue(com.jeeframework.util.json.JSONUtils.isJSONValid(response));
        System.out.println();
        assertTrue(JSONObject.fromObject(response).getInt("code") == 0);
    }

    @Test
    @Rollback(value = false)
    public void testGetStorageTypeFieldByDatasourceTypeId() throws Exception {
        String requestURI = "/common/getStorageTypeFieldByDatasourceTypeId.json";
        MvcResult mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders.get(requestURI).param("testInLogin", "no").param("datasourceTypeId", "134")).andDo(print()).andExpect(status().isOk()).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertTrue(com.jeeframework.util.json.JSONUtils.isJSONValid(response));
        System.out.println();
        assertTrue(JSONObject.fromObject(response).getInt("code") == 0);
    }

    @Test
    @Rollback(value = false)
    public void addDevicesTest() throws Exception {
        String requestURI = "/common/addDevice.json";
        MvcResult mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders.get(requestURI).param("testInLogin", "no").param("deviceName", "192.168.1.190")).andDo(print()).andExpect(status().isOk()).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertTrue(com.jeeframework.util.json.JSONUtils.isJSONValid(response));
        System.out.println();
        assertTrue(JSONObject.fromObject(response).getInt("code") == 0);
    }

    @Test
    @Rollback(value = false)
    public void removeDevicesTest() throws Exception {
        String requestURI = "/common/removeDevice.json";
        MvcResult mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders.get(requestURI).param("testInLogin", "no").param("deviceName", "192.168.1.190")).andDo(print()).andExpect(status().isOk()).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertTrue(com.jeeframework.util.json.JSONUtils.isJSONValid(response));
        System.out.println();
        assertTrue(JSONObject.fromObject(response).getInt("code") == 0);
    }

    @Test
    @Rollback(value = false)
    public void preserveDataTest() throws Exception {
        String requestURI = "/crawlTask/preserveData.json";
        MvcResult mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders.post(requestURI).param("testInLogin", "no").param("formerUrl", "http://mp.weixin.qq.com/s?src=11&timestamp=1534302836&ver=1061&signature=yHzW6Ier01SGe1y1RMH*6wMHBhrU335WXAvz9O13VzT*am-wwvnLVRUR7UwF3l3dUHqbILv09-Hv0OV37ELocKdeVISvToKEHMJJDEILwo58sUNsdKQMFbmF*MjVL0Ny&new=1").param("taskId", "104736").param("workFlowId", "0").param("jsonParam", "[{\n" +
                        "\t\"uid\": \"ezhanzhan\",\n" +
                        "\t\"datetime\": {\n" +
                        "\t\t\"date\": 29,\n" +
                        "\t\t\"hours\": 0,\n" +
                        "\t\t\"seconds\": 0,\n" +
                        "\t\t\"month\": 4,\n" +
                        "\t\t\"timezoneOffset\": -480,\n" +
                        "\t\t\"year\": 118,\n" +
                        "\t\t\"minutes\": 0,\n" +
                        "\t\t\"time\": 1527523200000,\n" +
                        "\t\t\"day\": 2\n" +
                        "\t},\n" +
                        "\t\"ltimes\": 1,\n" +
                        "\t\"author\": null,\n" +
                        "\t\"tempurl\": \"http://mp.weixin.qq.com/s?src=11&timestamp=1534302836&ver=1061&signature=yHzW6Ier01SGe1y1RMH*6wMHBhrU335WXAvz9O13VzT*am-wwvnLVRUR7UwF3l3dUHqbILv09-Hv0OV37ELocKdeVISvToKEHMJJDEILwo58sUNsdKQMFbmF*MjVL0Ny&new=1\",\n" +
                        "\t\"source\": \"钱站鹅站站\",\n" +
                        "\t\"title\": \"盖茨成立基金会居然另有所图？|Fantalk\",\n" +
                        "\t\"url\": \"http://mp.weixin.qq.com/s?__biz=MzU1MTY2MTU5OQ==&mid=2247484589&idx=2&sn=c463adcbf09fb70f56c326c753279ca9&scene=0&ascene=1&devicetype=android-23&version=26060536&nettype=WIFI&abtest_cookie=AgABAAoACwACACaXHgA/mR4AAAA=&lang=zh_CN&pass_ticket=4xBEj10nyr4zqLN5ipdCJ3b7GjtbSDYqCidFDMv7SSsymdptQyh9k3JQ3l/MpzI+&wx_header=1\",\n" +
                        "\t\"content\": \"本期知识点快读盖茨基金会，是慈善还是避税？☑比尔盖茨基金会在过去几十年中已经累计捐出超过百亿美元的现金和食物。也成功让比尔盖茨从一个成功的企业家变成了一位成功的慈善家。☑比尔盖茨创立基金会的初衷，是因为美国的遗产税与资产增值税很高，两种税率从15%到45%不等。因此，慈善基金会最初的目的其实是用来避税。☑比尔盖茨如果把所有资产拿在手里，即使不考虑遗产的情况，每年仍要上交数额不小的税费。因此把资产放入基金会，按照美国的政策是每年必须要捐出收益的5%，但其他的税就不用再交了。点击这里观看本期Fantalk↓↓↓[建议在wifi环境下观看]拓展阅读资本家的基金会，赚钱慈善两不误本期Fantalk中介绍了比尔盖茨与巴菲特关于慈善的故事。其实作为Fantalk的老朋友，“股神”巴菲特无数次出现在节目中，主持人杨帆作为巴老的忠实拥趸，在不久前更是带领Fantalk节目组专门来到巴菲特股东大会进行专题节目。但是这次巴菲特出现的身份与以往都不太一样，“股神”成为了慈善家。2006年巴菲特将自己85%的资产捐给了五个基金会，其中向盖茨基金会捐出了1000万股伯克希尔公司的股票，价值约合310亿美元。巴菲特的这次捐赠，一方面是因为原本负责管理慈善用款的夫人去世，另一方面也是因为巴菲特对于比尔盖茨的信任以及对盖茨基金会在慈善方面成就的信任。事实上，在盖茨基金会成立至今的几十年时间中，基金会捐助了众多慈善项目，包括落后地区卫生保健、传染病研究、赈灾、少数族裔教育、社区建设等等。而在慈善的背后，基金会的经营其实至关重要，毕竟只花钱不赚钱可是行不通的。盖茨基金会是将资产管理和慈善事业相互独立运营，资产归盖茨基金会会信托管理，由专业的团队进行运营，保证比尔盖茨和巴菲特捐赠的资产获得稳定收益。获得的收益则交给盖茨基金会，来对不同的慈善项目进行管理和运作。不过，在基金会的运营上，盖茨和巴菲特其实也有一点点“私心”，比如巴菲特在做股权捐赠时，就提出了明确的条件：基金会必须把捐赠的资产全部用作慈善，使其能免除赠与税和其他税收。我们不能以小人之心度君子之腹，比尔盖茨创立基金会以及巴菲特捐赠资产的初衷肯定是以慈善为目的，毕竟在美国想要避税绝不是只有做慈善一个方法。现实世界没有超级英雄，不能只做好事不赚钱，通过信托基金的模式，在避税的同时，还能有持续的收益来做慈善，一举多得，岂不乐哉？想了解更多内容请扫码关注微信公众账号“帆talk”\",\n" +
                        "\t\"vtimes\": 523\n" +
                        "}]").param("datasourceTypeId", "1223")).andDo(print()).andExpect(status().isOk()).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertTrue(com.jeeframework.util.json.JSONUtils.isJSONValid(response));
        System.out.println();
        assertTrue(JSONObject.fromObject(response).getInt("code") == 0);
    }

    @Test
    @Rollback(value = false)
    public void preserveWeiXinCommentDataTest() throws Exception {
        String requestURI = "/weixin/preserveWeiXinCommentData.json";
        MvcResult mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders.post(requestURI).param("testInLogin", "no").param("taskId", "2065").param("jsonParam", "[{\"parent\":\"http://mp.weixin.qq.com/s?__biz=MzAxNDYzNzI0OQ==&mid=2655137165&idx=2&sn=308f6bb3bc37be19f966ba669f34b7ea&scene=0&key=d54b4a39ce7d6c1b55c3230b94820699acdd863caa9214f6450e986686993bbf27a954e56da4a92d36a581d6282b8c905935744d224daf9db77d28e96d7ffec83fd5740cac6decec7bc50bae70ec8d33&ascene=1&uin=MjQxNzUyODQwNQ%3D%3D&devicetype=android-23&version=2605033c&nettype=WIFI&abtest_cookie=AwABAAgACgAMAAgAnoYeAGqKHgCbih4ApYoeALmKHgC%2Fih4AxYoeANiKHgAAAA%3D%3D&lang=zh_CN&pass_ticket=XFUEw8GIcA86xbYl95jhKHlgT%2BqbC%2BT7OLJRBboYGPr3w%2BOIWwPHnVyvtGf%2FDPth\",\"datetime\":{\"date\":29,\"hours\":0,\"seconds\":0,\"month\":11,\"timezoneOffset\":-480,\"year\":117,\"minutes\":0,\"time\":1514476800000,\"day\":5},\"ltimes\":26,\"author\":\"三金\",\"crawltime\":null,\"icon\":\"http://wx.qlogo.cn/mmhead/UkpVGib3ATCxPDs4pIaPzLY2mF1k3GeJYplH1aLoH9jA/96\",\"rltimes\":0,\"reply\":\"\",\"content\":\"666我都想嫁给小编了\",\"url\":\"www.baidu.com\"},{\"parent\":\"http://mp.weixin.qq.com/s?__biz=MzAxNDYzNzI0OQ==&mid=2655137165&idx=2&sn=308f6bb3bc37be19f966ba669f34b7ea&scene=0&key=d54b4a39ce7d6c1b55c3230b94820699acdd863caa9214f6450e986686993bbf27a954e56da4a92d36a581d6282b8c905935744d224daf9db77d28e96d7ffec83fd5740cac6decec7bc50bae70ec8d33&ascene=1&uin=MjQxNzUyODQwNQ%3D%3D&devicetype=android-23&version=2605033c&nettype=WIFI&abtest_cookie=AwABAAgACgAMAAgAnoYeAGqKHgCbih4ApYoeALmKHgC%2Fih4AxYoeANiKHgAAAA%3D%3D&lang=zh_CN&pass_ticket=XFUEw8GIcA86xbYl95jhKHlgT%2BqbC%2BT7OLJRBboYGPr3w%2BOIWwPHnVyvtGf%2FDPth\",\"datetime\":{\"date\":29,\"hours\":0,\"seconds\":0,\"month\":11,\"timezoneOffset\":-480,\"year\":117,\"minutes\":0,\"time\":1514476800000,\"day\":5},\"ltimes\":12,\"author\":\"吉川\",\"crawltime\":null,\"icon\":\"http://wx.qlogo.cn/mmhead/Xewa2JUmZ1oibRNBX5Cn3TwMqbkvpq4uMicPLQhmnuBttq7j7fQYgibfA/96\",\"rltimes\":0,\"reply\":\"小小的弧度嘛，其实意思是腕不能高过肘，肘不该高过肩\",\"content\":\"我记得MAX说过 侧平举的由高到低是肩肘腕，今天咋说又在一条线上了?\",\"url\":\"www.baidu.com\"},{\"parent\":\"http://mp.weixin.qq.com/s?__biz=MzAxNDYzNzI0OQ==&mid=2655137165&idx=2&sn=308f6bb3bc37be19f966ba669f34b7ea&scene=0&key=d54b4a39ce7d6c1b55c3230b94820699acdd863caa9214f6450e986686993bbf27a954e56da4a92d36a581d6282b8c905935744d224daf9db77d28e96d7ffec83fd5740cac6decec7bc50bae70ec8d33&ascene=1&uin=MjQxNzUyODQwNQ%3D%3D&devicetype=android-23&version=2605033c&nettype=WIFI&abtest_cookie=AwABAAgACgAMAAgAnoYeAGqKHgCbih4ApYoeALmKHgC%2Fih4AxYoeANiKHgAAAA%3D%3D&lang=zh_CN&pass_ticket=XFUEw8GIcA86xbYl95jhKHlgT%2BqbC%2BT7OLJRBboYGPr3w%2BOIWwPHnVyvtGf%2FDPth\",\"datetime\":{\"date\":29,\"hours\":0,\"seconds\":0,\"month\":11,\"timezoneOffset\":-480,\"year\":117,\"minutes\":0,\"time\":1514476800000,\"day\":5},\"ltimes\":1,\"author\":\"yield\",\"crawltime\":null,\"icon\":\"http://wx.qlogo.cn/mmhead/XYrRG5UShDdGYYW0HDfRKEDicRBfMZibUn54KgRCkTibgmSP8qVOzJAjQ/96\",\"rltimes\":0,\"reply\":\"一般可以是：绳索下压小重量热身+窄距卧推或双杠臂屈伸或反向俯卧撑+仰卧曲柄臂屈伸+单臂哑铃臂屈伸（俯身或坐姿脑后）+直杆下压（正反手）+绳索下压，每个动作4组即可，重量常规选择能重复8~12次的，但你可以使用递增组或递减组调节这个重量\",\"content\":\"max，练三头一般几个动作，还有一个每一组动作的数量和重量应该如何选择\",\"url\":\"www.baidu.com\"},{\"parent\":\"http://mp.weixin.qq.com/s?__biz=MzAxNDYzNzI0OQ==&mid=2655137165&idx=2&sn=308f6bb3bc37be19f966ba669f34b7ea&scene=0&key=d54b4a39ce7d6c1b55c3230b94820699acdd863caa9214f6450e986686993bbf27a954e56da4a92d36a581d6282b8c905935744d224daf9db77d28e96d7ffec83fd5740cac6decec7bc50bae70ec8d33&ascene=1&uin=MjQxNzUyODQwNQ%3D%3D&devicetype=android-23&version=2605033c&nettype=WIFI&abtest_cookie=AwABAAgACgAMAAgAnoYeAGqKHgCbih4ApYoeALmKHgC%2Fih4AxYoeANiKHgAAAA%3D%3D&lang=zh_CN&pass_ticket=XFUEw8GIcA86xbYl95jhKHlgT%2BqbC%2BT7OLJRBboYGPr3w%2BOIWwPHnVyvtGf%2FDPth\",\"datetime\":{\"date\":29,\"hours\":0,\"seconds\":0,\"month\":11,\"timezoneOffset\":-480,\"year\":117,\"minutes\":0,\"time\":1514476800000,\"day\":5},\"ltimes\":1,\"author\":\"Anderson\",\"crawltime\":null,\"icon\":\"http://wx.qlogo.cn/mmhead/PiajxSqBRaEKYKaHayEYFcb0AWyXMiaFWqyNr8CuQgEQwic1sCevU9U4w/96\",\"rltimes\":0,\"reply\":\"硬拉分直腿硬拉、屈腿硬拉，罗马尼亚硬拉其实就是膝盖有小小弯曲的直腿硬拉，类似于四分之一蹲的姿态，它先把二头拉长了，你再硬拉，对股二头刺激很明显。哑铃硬拉，那就是练到股二头嘛，跟杠铃一样。\",\"content\":\"罗马尼亚硬拉和普通硬拉有什么区别。有没有什么可以在只有哑铃的情况下练股二头肌的动作，是股，不是肱。\",\"url\":\"www.baidu.com\"},{\"parent\":\"http://mp.weixin.qq.com/s?__biz=MzAxNDYzNzI0OQ==&mid=2655137165&idx=2&sn=308f6bb3bc37be19f966ba669f34b7ea&scene=0&key=d54b4a39ce7d6c1b55c3230b94820699acdd863caa9214f6450e986686993bbf27a954e56da4a92d36a581d6282b8c905935744d224daf9db77d28e96d7ffec83fd5740cac6decec7bc50bae70ec8d33&ascene=1&uin=MjQxNzUyODQwNQ%3D%3D&devicetype=android-23&version=2605033c&nettype=WIFI&abtest_cookie=AwABAAgACgAMAAgAnoYeAGqKHgCbih4ApYoeALmKHgC%2Fih4AxYoeANiKHgAAAA%3D%3D&lang=zh_CN&pass_ticket=XFUEw8GIcA86xbYl95jhKHlgT%2BqbC%2BT7OLJRBboYGPr3w%2BOIWwPHnVyvtGf%2FDPth\",\"datetime\":{\"date\":29,\"hours\":0,\"seconds\":0,\"month\":11,\"timezoneOffset\":-480,\"year\":117,\"minutes\":0,\"time\":1514476800000,\"day\":5},\"ltimes\":4,\"author\":\"Riggs\",\"crawltime\":null,\"icon\":\"http://wx.qlogo.cn/mmhead/Q3auHgzwzM7AibRoy2Wia3bZ1NmY2gPhMyy2f3R2N5nIBscR95ibJkLpQ/96\",\"rltimes\":0,\"reply\":\"\",\"content\":\"都是些经常被忽略的细节！而往往细节决定成败！做好了，效果差得不是一点半点。\",\"url\":\"www.baidu.com\"},{\"parent\":\"http://mp.weixin.qq.com/s?__biz=MzAxNDYzNzI0OQ==&mid=2655137165&idx=2&sn=308f6bb3bc37be19f966ba669f34b7ea&scene=0&key=d54b4a39ce7d6c1b55c3230b94820699acdd863caa9214f6450e986686993bbf27a954e56da4a92d36a581d6282b8c905935744d224daf9db77d28e96d7ffec83fd5740cac6decec7bc50bae70ec8d33&ascene=1&uin=MjQxNzUyODQwNQ%3D%3D&devicetype=android-23&version=2605033c&nettype=WIFI&abtest_cookie=AwABAAgACgAMAAgAnoYeAGqKHgCbih4ApYoeALmKHgC%2Fih4AxYoeANiKHgAAAA%3D%3D&lang=zh_CN&pass_ticket=XFUEw8GIcA86xbYl95jhKHlgT%2BqbC%2BT7OLJRBboYGPr3w%2BOIWwPHnVyvtGf%2FDPth\",\"datetime\":{\"date\":29,\"hours\":0,\"seconds\":0,\"month\":11,\"timezoneOffset\":-480,\"year\":117,\"minutes\":0,\"time\":1514476800000,\"day\":5},\"ltimes\":1,\"author\":\"yield\",\"crawltime\":null,\"icon\":\"http://wx.qlogo.cn/mmhead/XYrRG5UShDdGYYW0HDfRKEDicRBfMZibUn54KgRCkTibgmSP8qVOzJAjQ/96\",\"rltimes\":0,\"reply\":\"要是当做帮补日的话，不需要做这么多的\",\"content\":\"练胸日进行三头训练也需要做这么多动作吗\",\"url\":\"www.baidu.com\"},{\"parent\":\"http://mp.weixin.qq.com/s?__biz=MzAxNDYzNzI0OQ==&mid=2655137165&idx=2&sn=308f6bb3bc37be19f966ba669f34b7ea&scene=0&key=d54b4a39ce7d6c1b55c3230b94820699acdd863caa9214f6450e986686993bbf27a954e56da4a92d36a581d6282b8c905935744d224daf9db77d28e96d7ffec83fd5740cac6decec7bc50bae70ec8d33&ascene=1&uin=MjQxNzUyODQwNQ%3D%3D&devicetype=android-23&version=2605033c&nettype=WIFI&abtest_cookie=AwABAAgACgAMAAgAnoYeAGqKHgCbih4ApYoeALmKHgC%2Fih4AxYoeANiKHgAAAA%3D%3D&lang=zh_CN&pass_ticket=XFUEw8GIcA86xbYl95jhKHlgT%2BqbC%2BT7OLJRBboYGPr3w%2BOIWwPHnVyvtGf%2FDPth\",\"datetime\":{\"date\":29,\"hours\":0,\"seconds\":0,\"month\":11,\"timezoneOffset\":-480,\"year\":117,\"minutes\":0,\"time\":1514476800000,\"day\":5},\"ltimes\":2,\"author\":\"~咩蜗~\",\"crawltime\":null,\"icon\":\"http://wx.qlogo.cn/mmhead/Q3auHgzwzM6jkFIydKKOAtTtvbkiaD6tQRLX665PtsOTTDeYkZag00Q/96\",\"rltimes\":0,\"reply\":\"\",\"content\":\"肱二外旋那个真是深有体会，加点旋转肱二会更……绷绷的那种感觉……\",\"url\":\"www.baidu.com\"},{\"parent\":\"http://mp.weixin.qq.com/s?__biz=MzAxNDYzNzI0OQ==&mid=2655137165&idx=2&sn=308f6bb3bc37be19f966ba669f34b7ea&scene=0&key=d54b4a39ce7d6c1b55c3230b94820699acdd863caa9214f6450e986686993bbf27a954e56da4a92d36a581d6282b8c905935744d224daf9db77d28e96d7ffec83fd5740cac6decec7bc50bae70ec8d33&ascene=1&uin=MjQxNzUyODQwNQ%3D%3D&devicetype=android-23&version=2605033c&nettype=WIFI&abtest_cookie=AwABAAgACgAMAAgAnoYeAGqKHgCbih4ApYoeALmKHgC%2Fih4AxYoeANiKHgAAAA%3D%3D&lang=zh_CN&pass_ticket=XFUEw8GIcA86xbYl95jhKHlgT%2BqbC%2BT7OLJRBboYGPr3w%2BOIWwPHnVyvtGf%2FDPth\",\"datetime\":{\"date\":29,\"hours\":0,\"seconds\":0,\"month\":11,\"timezoneOffset\":-480,\"year\":117,\"minutes\":0,\"time\":1514476800000,\"day\":5},\"ltimes\":1,\"author\":\"\",\"crawltime\":null,\"icon\":\"http://wx.qlogo.cn/mmhead/Q3auHgzwzM4ocypz2ICjrOlTm8IsEAKicp7ksITqIOvcQT2AHd3UrqA/96\",\"rltimes\":0,\"reply\":\"\",\"content\":\"干货，硬气，我还以为只有我发现了这些秘密\",\"url\":\"www.baidu.com\"},{\"parent\":\"http://mp.weixin.qq.com/s?__biz=MzAxNDYzNzI0OQ==&mid=2655137165&idx=2&sn=308f6bb3bc37be19f966ba669f34b7ea&scene=0&key=d54b4a39ce7d6c1b55c3230b94820699acdd863caa9214f6450e986686993bbf27a954e56da4a92d36a581d6282b8c905935744d224daf9db77d28e96d7ffec83fd5740cac6decec7bc50bae70ec8d33&ascene=1&uin=MjQxNzUyODQwNQ%3D%3D&devicetype=android-23&version=2605033c&nettype=WIFI&abtest_cookie=AwABAAgACgAMAAgAnoYeAGqKHgCbih4ApYoeALmKHgC%2Fih4AxYoeANiKHgAAAA%3D%3D&lang=zh_CN&pass_ticket=XFUEw8GIcA86xbYl95jhKHlgT%2BqbC%2BT7OLJRBboYGPr3w%2BOIWwPHnVyvtGf%2FDPth\",\"datetime\":{\"date\":29,\"hours\":0,\"seconds\":0,\"month\":11,\"timezoneOffset\":-480,\"year\":117,\"minutes\":0,\"time\":1514476800000,\"day\":5},\"ltimes\":1,\"author\":\"骨子里de骄傲\",\"crawltime\":null,\"icon\":\"http://wx.qlogo.cn/mmhead/Q3auHgzwzM4FmejA3OA5KojIZmTxWl91piche8pRLGtMmKMTqK5RLNA/96\",\"rltimes\":0,\"reply\":\"\",\"content\":\"必须赞\",\"url\":\"www.baidu.com\"},{\"parent\":\"http://mp.weixin.qq.com/s?__biz=MzAxNDYzNzI0OQ==&mid=2655137165&idx=2&sn=308f6bb3bc37be19f966ba669f34b7ea&scene=0&key=d54b4a39ce7d6c1b55c3230b94820699acdd863caa9214f6450e986686993bbf27a954e56da4a92d36a581d6282b8c905935744d224daf9db77d28e96d7ffec83fd5740cac6decec7bc50bae70ec8d33&ascene=1&uin=MjQxNzUyODQwNQ%3D%3D&devicetype=android-23&version=2605033c&nettype=WIFI&abtest_cookie=AwABAAgACgAMAAgAnoYeAGqKHgCbih4ApYoeALmKHgC%2Fih4AxYoeANiKHgAAAA%3D%3D&lang=zh_CN&pass_ticket=XFUEw8GIcA86xbYl95jhKHlgT%2BqbC%2BT7OLJRBboYGPr3w%2BOIWwPHnVyvtGf%2FDPth\",\"datetime\":{\"date\":31,\"hours\":0,\"seconds\":0,\"month\":11,\"timezoneOffset\":-480,\"year\":117,\"minutes\":0,\"time\":1514649600000,\"day\":0},\"ltimes\":0,\"author\":\"李浩\",\"crawltime\":null,\"icon\":\"http://wx.qlogo.cn/mmhead/gwhELYibibFdQ402pHt03Y281SP6dIxQA4msEV2ejSeDGhzia65lMJ1lg/96\",\"rltimes\":0,\"reply\":\"\",\"content\":\"满满的干货\",\"url\":\"www.baidu.com\"}]").param("datasourceTypeId", "70")).andDo(print()).andExpect(status().isOk()).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertTrue(com.jeeframework.util.json.JSONUtils.isJSONValid(response));
        System.out.println();
        assertTrue(JSONObject.fromObject(response).getInt("code") == 0);
    }

    @Test
    @Rollback(value = false)
    public void weixinTestTest() throws Exception {
        String requestURI = "/weixin/test.json";
        MvcResult mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders.get(requestURI).param("testInLogin", "no")).andDo(print()).andExpect(status().isOk()).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertTrue(com.jeeframework.util.json.JSONUtils.isJSONValid(response));
        System.out.println();
        assertTrue(JSONObject.fromObject(response).getInt("code") == 0);
    }
}

