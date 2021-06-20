package org.jeecg;

import cn.hutool.json.JSONObject;
import org.jeecg.common.util.security.SecurityTools;
import org.jeecg.common.util.security.entity.*;
import org.jeecg.modules.Fibonacci;
import org.jeecg.modules.demo.mock.MockController;
import org.jeecg.modules.demo.test.entity.JeecgDemo;
import org.jeecg.modules.demo.test.mapper.JeecgDemoMapper;
import org.jeecg.modules.demo.test.service.IJeecgDemoService;
import org.jeecg.modules.demo3.intel.entity.ZnLog;
import org.jeecg.modules.demo3.intel.entity.ZnSeasonXl;
import org.jeecg.modules.demo3.intel.mapper.ZnLogMapper;
import org.jeecg.modules.demo3.intel.mapper.ZnSeasonXlMapper;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecurityToolsTest {

//    @Resource
    @Autowired
    private JeecgDemoMapper jeecgDemoMapper;
//    @Resource
    @Autowired
    private IJeecgDemoService jeecgDemoService;
    //	@Resource
//	private ISysDataLogService sysDataLogService;
//    @Resource
//    private MockController mock;

    @Autowired
    private ZnSeasonXlMapper znSeasonXlMapper;

    @BeforeClass
    public static void beforeRead(){
        System.out.println("准备测试环境成功...");
    }

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<JeecgDemo> userList = jeecgDemoMapper.selectList(null);
        Assert.assertEquals(18, userList.size());
        userList.forEach(System.out::println);
    }


    @Test
    public void testXmlSql() {
        System.out.println(("----- selectAll method test ------"));
        List<JeecgDemo> userList = jeecgDemoMapper.getDemoByName("Sandy");
        userList.forEach(System.out::println);
    }

    @Test
    public void testZnLog(){
        ZnSeasonXl znSeasonXl = new ZnSeasonXl();
//        znSeasonXl.setId("1335851466715336852");
        znSeasonXl.setYwqid("8080");
        znSeasonXl.setDbName("liuming");
        System.out.println("----- selectALL method test -------");
//        List<ZnSeasonXl> znSeasonXlList = znSeasonXlMapper.selectList(null);
        int insert = znSeasonXlMapper.insert(znSeasonXl);
        Assert.assertEquals("不对",1,insert);
        System.out.println(insert);
//        Assert.assertEquals(134,znSeasonXlList.size());
//        znSeasonXlList.forEach(System.out::println);
    }

    @Test //注解
    public	 void getMax(){
		int a = 3;
		int b = 5 ;
        int max = a>b?a:b;
        Assert.assertEquals(5,max);
        System.out.println("最大值："+max);
    }



    @Test
    public void testFibonacci() {
        Assert.assertEquals(5, Fibonacci.compute(5));
        System.out.println(Fibonacci.compute(5));
    }
    /**
     * 测试事务
     */
    @Test
    public void testTran() {
        jeecgDemoService.testTran();
    }
    @Test
    public void Test(){
        MyKeyPair mkeyPair = SecurityTools.generateKeyPair();

        JSONObject msg = new JSONObject();
        msg.put("name", "党政辉");
        msg.put("age", 50);
        JSONObject identity = new JSONObject();
        identity.put("type", "01");
        identity.put("no", "210882165896524512");
        msg.put("identity", identity);

        // 签名加密部分
        SecuritySignReq signReq = new SecuritySignReq();
        // data为要加密的报文字符串
        signReq.setData(msg.toString());
        // 为rsa私钥
        signReq.setPrikey(mkeyPair.getPriKey());
        // 调用签名方法
        SecuritySignResp sign = SecurityTools.sign(signReq);
        // 打印出来加密数据
        // signData为签名数据
        // data为aes加密数据
        // asekey为ras加密过的aeskey
        System.out.println(new JSONObject(sign).toStringPretty());

        // 验签解密部分
        SecurityReq req = new SecurityReq();
        //对方传过来的数据一一对应
        req.setAesKey(sign.getAesKey());
        req.setData(sign.getData());
        req.setSignData(sign.getSignData());
        //我们的公钥
        req.setPubKey(mkeyPair.getPubKey());
        //验签方法调用
        SecurityResp securityResp = SecurityTools.valid(req);
        //解密报文data为解密报文
        //sucess 为验签成功失败标志 true代码验签成功，false代表失败
        System.out.println(new JSONObject(securityResp).toStringPretty());
    }


    @AfterClass
    public static void afterRead(){
        System.out.println("清理测试环境..");
    }

}
