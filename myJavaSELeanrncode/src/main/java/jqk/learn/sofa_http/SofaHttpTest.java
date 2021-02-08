package jqk.learn.sofa_http;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.type.TypeReference;
import jqk.learn.sofa_http.utils.HttpClientTool;
import jqk.learn.sofa_http.vo.Person;
import jqk.learn.sofa_http.vo.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:JQK
 * @Date:2021/2/5 14:01
 * @Package:jqk.learn.sofa_http
 * @ClassName:SofaTransferHttp
 **/

public class SofaHttpTest {

    public static void main(String[] args) {
        //rpc:需要打包，引入接口
        // User user = chinaPayForServiceFacade.getOptionalRepairCompanies(person);

        //http调用
        Person person = new Person();
        Map<String, String> params = new HashMap<>();
        params.put("data", JSON.toJSONString(person));
        User user = HttpClientTool.tool.doGet("/api/get", params, new TypeReference<User>() {
        });

    }


}
