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

/**
 * ${description}
 *
 * @author weiqiliu
 * @version 1.0 2018/1/22
 */
public class DevicesControllerTest extends AbstractSpringBaseControllerTest {


    @Test
    @Rollback(value = false)
    public void getDevicesInfo() throws Exception {
        String requestURI = "/devicesInfo/getDevicesInfo.json";
        MvcResult mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders.post(requestURI).param("testInLogin", "no")).andDo(print()).andExpect(status().isOk()).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertTrue(com.jeeframework.util.json.JSONUtils.isJSONValid(response));
        System.out.println();
        assertTrue(JSONObject.fromObject(response).getInt("code") == 0);
    }

    @Test
    @Rollback(value = false)
    public void testGetEnableDevices() throws Exception {
        String requestURI = "/devicesInfo/addDevicesInfo.json";
        MvcResult mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders.post(requestURI).param("testInLogin", "no").param("id","1").param("devicesName", "192.168.1.114:5555").param("registServer", "mcss_2").param("funcation","controlPanel")).andDo(print()).andExpect(status().isOk()).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertTrue(com.jeeframework.util.json.JSONUtils.isJSONValid(response));
        System.out.println();
        assertTrue(JSONObject.fromObject(response).getInt("code") == 0);
    }





}
