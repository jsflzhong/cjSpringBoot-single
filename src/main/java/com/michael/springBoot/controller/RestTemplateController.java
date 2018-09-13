package com.michael.springBoot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.michael.springBoot.dao.UserMapper;
import com.michael.springBoot.domain.User;
import com.michael.springBoot.service.AsyncRestService;
import com.michael.springBoot.service.RestService;
import com.michael.springBoot.vo.ServiceResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller for RestTemplate in Spring.
 *
 * @author cj
 */
@Controller
@RestController
public class RestTemplateController {

    private static final Logger logger = LoggerFactory.getLogger(RestTemplateController.class);

    @Autowired
    private RestTemplate restTemplate;

    /***********HTTP GET method  (Tested)*************/

    /**
     * 测试直接用RestTemplate的get方法调用.
     * Tested
     * Tested: set time out 5秒
     *
     * @return
     */
    @RequestMapping("/get")
    public String hello() {
        String url = "http://localhost:8081/getApi";
        //在发出请求后,得到回应前,如果超过了在配置restTemplate这个bean时的setReadTimeout时间,则调用这里会抛异常:SocketTimeoutException
        JSONObject json = restTemplate.getForEntity(url, JSONObject.class).getBody();
        return json.toJSONString();
    }

    @RequestMapping("/getApi")
    public Object genJson() {
        JSONObject json = new JSONObject();
        json.put("result", "Hello get!");
        return json;
    }


    /**********HTTP POST method  (Tested)**************/

    /**
     * 测试直接用RestTemplate的post方法调用.
     * Tested
     * Tested: set time out 5秒
     *
     * @return
     */
    @RequestMapping("/post")
    public Object testPost() {
        String url = "http://localhost:8081/postApi";
        JSONObject postData = new JSONObject();
        postData.put("descp", "request for post");
        //在发出请求后,得到回应前,如果超过了在配置restTemplate这个bean时的setReadTimeout时间,则调用这里会抛异常:SocketTimeoutException
        //注意postForEntity方法的第三参,是JsonObject,这就决定了被调用的对方的接口的参数类型应该是JSONObject类型的,而且参数名不限.看下面的方法.
        JSONObject json = restTemplate.postForEntity(url, postData, JSONObject.class).getBody();
        return json.toJSONString();
    }

    @RequestMapping("/postApi")
    public Object iAmPostApi(@RequestBody JSONObject param) {
        System.out.println("@@@Server accepting the json:" + param.toJSONString());
        param.put("result", "Hello post!");
        return param;
    }


    /**********########################## 测试封装好的service--同步调用 (Tested) ##########################**************/

    @Autowired
    private RestService restService;

    /****************************** get ********************************/

    /**
     * 1.调用以 Hashmap为参数的RestTemplate方法,则url不用做特殊处理.
     * Tested
     * Tested: set time out 5秒
     *
     * @return
     */
    @RequestMapping("/get2")
    public Object get() {
        String url = "http://localhost:8081/getApi2";
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("param1", "v1");
        paramMap.put("param2", "v2");

        //调用以 Hashmap为参数的方法,则url不用做特殊处理.
        ServiceResult serviceResult = restService.parseGetResult(paramMap, url);
        return serviceResult;
    }

    @RequestMapping("/getApi2")
    public Object getApi2(String param1, String param2) {
        JSONObject json = new JSONObject();
        json.put("param1", param1);
        json.put("param2", param2);
        json.put("result", "Hello get2!");
        return json;
    }

    /**
     * 2.注意:如果调用以 Hashmap为参数的方法,则url需要做特殊处理,要指明对方接口的形参名.具体进去看方法注释.
     * Tested
     * Tested: set time out 5秒
     *
     * @return
     */
    @RequestMapping("/get3")
    public Object get3() {
        //注意:如果调用以 Hashmap为参数的方法,则url需要做特殊处理,要指明对方接口的形参名.具体进去看方法注释.
        String url = "http://localhost:8081/getApi3?param1={k1}&param2={k2}";
        ImmutableMap<String, Object> paramMap = ImmutableMap.of("k1", "v1", "k2", "v2");

        //注意:如果调用以 Hashmap为参数的方法,则url需要做特殊处理,要指明对方接口的形参名.具体进去看方法注释.
        ServiceResult serviceResult = restService.parseGetResult(paramMap, url);

        return serviceResult;
    }

    @RequestMapping("/getApi3")
    public Object getApi3(String param1, String param2) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("param1", param1);
        paramMap.put("param2", param2);
        return paramMap;
    }


    /****************************** post ********************************/

    /**
     * 调用封装好的post方法.
     * Tested.
     *
     * @return
     */
    @RequestMapping("/post2")
    public Object testPost2() {
        String url = "http://localhost:8081/postApi2";

        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("channel", 1);
        map.add("goods_id", 2);

        //该方法中封装的RestTemplate的postForObject方法的第三参是String.class,这就决定了对方的接口参数类型必须是String,且参数名于map中的key要匹配.
        //看下面的方法参数签名.
        ServiceResult serviceResult = restService.parsePostResult(map, url);
        return serviceResult;
    }

    /**
     * 本次本方法的参数类型不能是 JSONObject 类型,这取决于上面的方法中的postForObject方法的第三参的类型. 看上述说明.
     *
     * @param channel
     * @param goods_id
     * @return
     */
    @RequestMapping("/postApi2")
    public Object postApi2(String channel, String goods_id) {
        return "1";
    }




    /**********########################## 测试封装好的service--异步调用 (Untested) ##########################**************/

    @Autowired
    private AsyncRestService asyncRestService;

    /****************************** get ********************************/

    /**
     * 1.调用以 Hashmap为参数的RestTemplate方法,则url不用做特殊处理.
     * Tested
     * Tested: set time out 5秒
     *
     * @return
     */
    @RequestMapping("/get4")
    public Object get4() {
        String url = "http://localhost:8081/getApi4";
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("param1", "v1");
        paramMap.put("param2", "v2");

        //调用以 Hashmap为参数的方法,则url不用做特殊处理.
        ServiceResult serviceResult = asyncRestService.parseGetResult(paramMap, url);

        System.out.println("@@@调用方已经[异步]的执行完,即将返回预设响应,而不是对方接口的响应值...");

        return serviceResult;
    }

    @RequestMapping("/getApi4")
    public Object getApi4(String param1, String param2) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("@@@被调用的接口已苏醒...");

        JSONObject json = new JSONObject();
        json.put("param1", param1);
        json.put("param2", param2);
        json.put("result", "Hello get4!");
        return json;
    }

    /**
     * 2.注意:如果调用以 Hashmap为参数的方法,则url需要做特殊处理,要指明对方接口的形参名.具体进去看方法注释.
     * Tested
     * Tested: set time out 5秒
     *
     * @return
     */
    @RequestMapping("/get5")
    public Object get5() {
        //注意:如果调用以 Hashmap为参数的方法,则url需要做特殊处理,要指明对方接口的形参名.具体进去看方法注释.
        String url = "http://localhost:8081/getApi5?param1={k1}&param2={k2}";
        ImmutableMap<String, Object> paramMap = ImmutableMap.of("k1", "v1", "k2", "v2");

        //注意:如果调用以 Hashmap为参数的方法,则url需要做特殊处理,要指明对方接口的形参名.具体进去看方法注释.
        ServiceResult serviceResult = restService.parseGetResult(paramMap, url);

        return serviceResult;
    }

    @RequestMapping("/getApi5")
    public Object getApi5(String param1, String param2) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("param1", param1);
        paramMap.put("param2", param2);
        return paramMap;
    }

}
