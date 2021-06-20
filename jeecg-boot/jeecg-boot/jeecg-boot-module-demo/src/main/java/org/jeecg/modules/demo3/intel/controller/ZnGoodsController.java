package org.jeecg.modules.demo3.intel.controller;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo3.intel.entity.ZnGoods;
import org.jeecg.modules.demo3.intel.service.IZnGoodsService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 门店补货商品表
 * @Author: jeecg-boot
 * @Date:   2020-11-25
 * @Version: V1.0
 */
@Api(tags="门店补货商品表")
@RestController
@RequestMapping("/intel/znGoods")
@Slf4j
public class ZnGoodsController extends JeecgController<ZnGoods, IZnGoodsService> {
	@Autowired
	private IZnGoodsService znGoodsService;

	/**
	 * 分页列表查询
	 *
	 * @param znGoods
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "门店补货商品表-分页列表查询")
	@ApiOperation(value="门店补货商品表-分页列表查询", notes="门店补货商品表-分页列表查询")
	@GetMapping(value = "/list")
	@PermissionData(pageComponent = "oademo3/intel/ZnGoodsList")
	public Result<?> queryPageList(ZnGoods znGoods,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ZnGoods> queryWrapper = QueryGenerator.initQueryWrapper(znGoods, req.getParameterMap());
		Page<ZnGoods> page = new Page<ZnGoods>(pageNo, pageSize);
		IPage<ZnGoods> pageList = znGoodsService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param znGoods
	 * @return
	 */
	@AutoLog(value = "门店补货商品表-添加")
	@ApiOperation(value="门店补货商品表-添加", notes="门店补货商品表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ZnGoods znGoods) {
		znGoodsService.save(znGoods);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param znGoods
	 * @return
	 */
	@AutoLog(value = "门店补货商品表-编辑")
	@ApiOperation(value="门店补货商品表-编辑", notes="门店补货商品表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ZnGoods znGoods) {
		znGoodsService.updateById(znGoods);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "门店补货商品表-通过id删除")
	@ApiOperation(value="门店补货商品表-通过id删除", notes="门店补货商品表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		znGoodsService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "门店补货商品表-批量删除")
	@ApiOperation(value="门店补货商品表-批量删除", notes="门店补货商品表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.znGoodsService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "门店补货商品表-通过id查询")
	@ApiOperation(value="门店补货商品表-通过id查询", notes="门店补货商品表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ZnGoods znGoods = znGoodsService.getById(id);
		if(znGoods==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(znGoods);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param znGoods
    */
    @RequestMapping(value = "/exportXls")
	@PermissionData(pageComponent = "oademo3/intel/ZnGoodsList")
    public ModelAndView exportXls(HttpServletRequest request, ZnGoods znGoods) {
		//获取导出表格字段
//		String exportFields = getExportFields();
		QueryWrapper<ZnGoods> query = new QueryWrapper();
		String shopid = znGoods.getShopid();
		String blid =znGoods.getBlid();
		String cgid =znGoods.getCgid();
		String xlname =znGoods.getXlname();
		String goodsname =znGoods.getGoodsname();
		String vendername =znGoods.getVendername();
		String classtype =znGoods.getClasstype();
		String flag = znGoods.getFlag();
		System.out.println(shopid);
		System.out.println(blid);
		System.out.println(cgid);
		System.out.println(xlname);
		System.out.println(goodsname);
		System.out.println(vendername);
		System.out.println(classtype);
		System.out.println(flag);
		if(shopid != null && blid != "" ){
			query.eq("shopid",shopid);
		}
		if(blid != null && blid != "" ){
			query.eq("blid",blid);
		}
		if(cgid != null && cgid != "" ){
			query.eq("cgid",cgid);
		}
		if(xlname != null && xlname != "" ){
			query.eq("xlname",xlname);
		}
		if(goodsname != null && goodsname != "" ){
			query.eq("goodsname",goodsname);
		}
		if(vendername != null && vendername != "" ){
			query.eq("vendername",vendername);
		}
		if(classtype != null && classtype != "" ){
			ArrayList<String> arrayList1 =new ArrayList<>();
			if(classtype.equals("T") ) {
				arrayList1.add("T");
			}
			else{
				arrayList1.add("A");
				arrayList1.add("B");
				arrayList1.add("C");
			}
			System.out.println(arrayList1);
			query.in("classtype",arrayList1);
		}
		if(flag != null && flag != "" ){
			ArrayList<String> arrayList =new ArrayList<>();
			if(flag.equals("1") ) {
				arrayList.add("1");
				arrayList.add("3");
				arrayList.add("6");
				arrayList.add("8");
				arrayList.add("9");
				arrayList.add("10");
				arrayList.add("13");
			}else if (flag.equals("2")){
				arrayList.add("2");
				arrayList.add("4");
				arrayList.add("5");
				arrayList.add("7");
			}else if (flag.equals("3")){
				arrayList.add("13");
			}
			System.out.println(arrayList);
			query.in("flag",arrayList);
		}
		query.gt("qty",0);
		query.orderByDesc("manual_qty");

		List pageList4 = znGoodsService.list(query);

		return super.exportXls3(request, pageList4, ZnGoods.class, "门店补货商品表");
    }


	 /**
	  * 导出一excel，补货标识为1，3，6，9且补货数量qty大于0
	  *
	  * @param request
	  * @param znGoods
	  */

	 @RequestMapping(value = "/buHuo1ExportXls")
	 @PermissionData(pageComponent = "oademo3/intel/ZnGoodsList")
	 public ModelAndView buHuo1ExportXls(HttpServletRequest request, ZnGoods znGoods) {
		 //获取导出表格字段
//		String exportFields = getExportFields();
		 return super.exportXls1(request, znGoods, ZnGoods.class, "门店补货商品表");
	 }



	 /**
	  * 导出二excel，补货标识为2，4，5，7，11，12
	  *
	  * @param request
	  * @param znGoods
	  */
	 @RequestMapping(value = "/buHuo2ExportXls")
	 @PermissionData(pageComponent = "oademo3/intel/ZnGoodsList")
	 public ModelAndView buHuo2ExportXls(HttpServletRequest request, ZnGoods znGoods) {
		 //获取导出表格字段
//		String exportFields = getExportFields();
		 return super.exportXls2(request, znGoods, ZnGoods.class, "门店补货商品表");
	 }

	 /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, ZnGoods.class);
    }

	 @GetMapping(value = "/getData")
	 @PermissionData(pageComponent = "oademo3/intel/ZnGoodsList")
	 public Result<?> getData(ZnGoods znGoods,
							  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
							  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
							  @RequestParam(name = "parentId", required = false) String parentId,
							  HttpServletRequest req) {
		 QueryWrapper<ZnGoods> queryWrapper = QueryGenerator.initQueryWrapper(znGoods, req.getParameterMap());

		 QueryWrapper<ZnGoods> query = new QueryWrapper();

		 String shopid = znGoods.getShopid();
		 String blid =znGoods.getBlid();
		 String cgid =znGoods.getCgid();
		 String xlname =znGoods.getXlname();
		 String goodsname =znGoods.getGoodsname();
		 String vendername =znGoods.getVendername();
		 String classtype =znGoods.getClasstype();
		 String flag = znGoods.getFlag();
		 System.out.println(shopid);
		 System.out.println(blid);
		 System.out.println(cgid);
		 System.out.println(xlname);
		 System.out.println(goodsname);
		 System.out.println(vendername);
		 System.out.println(classtype);
		 System.out.println(flag);
		 if(shopid != null && blid != "" ){
			 query.eq("shopid",shopid);
		 }
		 if(blid != null && blid != "" ){
			 query.eq("blid",blid);
		 }
		 if(cgid != null && cgid != "" ){
			 query.eq("cgid",cgid);
		 }
		 if(xlname != null && xlname != "" ){
			 query.eq("xlname",xlname);
		 }
		 if(goodsname != null && goodsname != "" ){
			 query.eq("goodsname",goodsname);
		 }
		 if(vendername != null && vendername != "" ){
			 query.eq("vendername",vendername);
		 }
		 if(classtype != null && classtype != "" ){
			 ArrayList<String> arrayList1 =new ArrayList<>();
			 if(classtype.equals("T") ) {
				 arrayList1.add("T");
			 }
			 else{
				 arrayList1.add("A");
				 arrayList1.add("B");
				 arrayList1.add("C");
			 }
			 System.out.println(arrayList1);
			 query.in("classtype",arrayList1);
		 }
		 if(flag != null && flag != "" ){
			 ArrayList<String> arrayList =new ArrayList<>();
			 if(flag.equals("1") ) {
				 arrayList.add("1");
				 arrayList.add("3");
				 arrayList.add("6");
				 arrayList.add("8");
				 arrayList.add("9");
				 arrayList.add("10");
				 arrayList.add("13");
			 }else if (flag.equals("2")){
				 arrayList.add("2");
				 arrayList.add("4");
				 arrayList.add("5");
				 arrayList.add("7");
			 }else if (flag.equals("3")){
				 arrayList.add("13");
			 }
			 System.out.println(arrayList);
			 query.in("flag",arrayList);
		 }
		 query.orderByDesc("manual_qty");

		 Page<ZnGoods> page = new Page<ZnGoods>(pageNo, pageSize);
		 IPage<ZnGoods> pageList = znGoodsService.page(page, queryWrapper);
		 System.out.println(pageList.getTotal());
//		 pageList.getClass();
		 System.out.println(pageList.getRecords());
//		 znGoodsService.page(page, queryWrapper).getRecords();
		 return Result.OK(pageList);
	 }

	 @PutMapping("/immediateSaveRow")
	 public Result mockImmediateSaveRow(@RequestBody JSONObject rowData) throws Exception {
		 BigDecimal manualQty = rowData.getBigDecimal("manualQty");
		 String goodsname = rowData.getString("goodsname");
		 znGoodsService.updateByManualQty(manualQty,goodsname);
		 // 延时1.5秒，模拟网慢堵塞真实感
		 Thread.sleep(500);
		 return Result.ok();
	 }

	 @PutMapping("/immediateSavaComment")
	 public Result mockImmediateSavaComment(@RequestBody JSONObject rowData) throws Exception{
		 String comment = rowData.getString("comment");
		 String id = rowData.getString("id");
		 System.out.println(rowData);
		 System.out.println(comment);
		 System.out.println(id);


		 znGoodsService.updateByComment(comment,id);
		 // 延时1.5秒，模拟网慢堵塞真实感
		 Thread.sleep(500);
		 return Result.ok();

	 }


	 @PutMapping("/submitBh")
	 @PermissionData(pageComponent = "oademo3/intel/ZnGoodsList")
	 public Result<?> submitBh(@RequestBody JSONObject formData, HttpServletRequest req) throws Exception {
		 String shopId = formData.getString("shopid");
		 String blId = formData.getString("blid");
		 String cgId = formData.getString("cgid");
		 String xlname = formData.getString("xlname");
		 String goodsname = formData.getString("goodsname");
		 String flag = formData.getString("flag");
		 String xlId = formData.getString("xlid");   //
		 String vendername = formData.getString("vendername");    //
		 String classtype = formData.getString("classtype");    //
		 ZnGoods znGoods = new ZnGoods();
		 znGoods.setShopid(shopId);
		 znGoods.setBlid(blId);
		 znGoods.setCgid(cgId);
		 znGoods.setXlname(xlname);
		 znGoods.setGoodsname(goodsname);
		 znGoods.setFlag(flag);
		 znGoods.setXlid(xlId);//
		 znGoods.setVendername(vendername);//
		 znGoods.setClasstype(classtype);//
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 String orgCode = sysUser.getOrgCode();
		 System.out.println(sysUser);
		 System.out.println("liuming");
		 String dbUrl = znGoodsService.getDbUrl(orgCode);
		 System.out.println(orgCode);
		 String dbUserName = znGoodsService.getDbUserName(orgCode);
		 String dbPassword = znGoodsService.getDbPassword(orgCode);
		 //拿到前台所有数据
		 System.out.println(znGoods.toString());

		 QueryWrapper<ZnGoods> queryWrapper = QueryGenerator.initQueryWrapper(znGoods, req.getParameterMap());
		 Page<ZnGoods> page = new Page<ZnGoods>();
		 page.setSize(Long.MAX_VALUE);
		 IPage<ZnGoods> pageList = znGoodsService.page(page, queryWrapper);
		 int countSend = pageList.getRecords().size();//传送4251的总条数
		 if(countSend > 2000){
			 Thread.sleep(20 * 1000);
		 }else {
			 Thread.sleep(8 * 1000);
		 }

		 //传送成功的条数
		 AtomicInteger countSuccess = new AtomicInteger();
		 Date date = new Date();//获得系统当前时间.
		 SimpleDateFormat sdf = new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss ");
		 String busdate = sdf.format(date);
		 Result result = new Result();
		 Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		 if(conn == null){
			 return result.error500("传送数据失败！");
		 }
		 conn.setAutoCommit(false);


		 //多线程
		 ExecutorService pool = Executors.newFixedThreadPool(4);
		 pool.execute(new Runnable()  {
			 @Override
			 public void run() {
				 try {
					 //传送数据
					 PreparedStatement stmt = null;
					 PreparedStatement stmt2 = null;


					 String sql = "insert into stockapply_fath (busdate,organ,class) values (?,?,?)";
					 String sql2 = "insert into stockapply_chi (code,aount,amou_opl,amou_stock," +
							 "ave_sale,conversion,pack_amou,costprice,price) values (?,?,?,?,?,?,?,?,?)";
					 stmt = conn.prepareStatement(sql);
					 stmt2 = conn.prepareStatement(sql2);
					 int sendNum = 0;
					 String spec = null;
					 for (ZnGoods record : pageList.getRecords()) {
						 String blid = record.getBlid();
						 String cgid = record.getCgid();
						 String goodsid = record.getGoodsid();//商品编码
						 sendNum = znGoodsService.getSendNum(goodsid);
						 //判断sendNum是否为0
						 if (sendNum == 0) {
							 countSuccess.getAndIncrement();
							 znGoodsService.updateSendNum(goodsid);
							 //向主表插入数据
							 stmt.setString(1, busdate);
							 stmt.setString(2, blid);
							 stmt.setString(3, cgid);
							 //stmt.executeUpdate();
							 stmt.addBatch();

							 //向子表插入数据
							 BigDecimal manualQty = record.getManualQty();//要货数量
							 BigDecimal qty = record.getQty();//建议补货数量
							 BigDecimal minstock = record.getMinstock();//库存数量
							 BigDecimal aveCount = manualQty;//日均销量
							 BigDecimal minorder = record.getMinorder();//件数
							 BigDecimal normalprice = record.getNormalprice();//进价
							 BigDecimal trueprice = record.getTrueprice();

							 spec = znGoodsService.getSpec(goodsid);
							 stmt2.setString(1, goodsid);
							 stmt2.setBigDecimal(2, manualQty);
							 stmt2.setBigDecimal(3, qty);
							 stmt2.setBigDecimal(4, minstock);
							 stmt2.setBigDecimal(5, aveCount);
							 stmt2.setString(6, spec);
							 stmt2.setBigDecimal(7, minorder);
							 stmt2.setBigDecimal(8, normalprice);
							 stmt2.setBigDecimal(9, trueprice);
							 stmt2.addBatch();
							 if (countSuccess.get() % 500 == 0 || countSend == countSuccess.get()) {// 当增加了500个批处理的时候再提交
								 stmt.executeBatch();// 执行批处理
								 stmt2.executeBatch();
								 stmt.clearBatch();
								 stmt2.clearBatch();
								 stmt = conn.prepareStatement(sql);
								 stmt2 = conn.prepareStatement(sql2);
								 System.out.println("countSuccess: " + countSuccess);
							 }
						 }
					 }

					 conn.commit();
					 conn.setAutoCommit(true);
					 stmt2.close();
					 stmt.close();
					 conn.close();
				 } catch (Exception e) {
					 e.printStackTrace();
				 } finally {


				 }
			 }
		 });
		 pool.shutdown();

		 //传送记录表的相关数据
		 String shopid = znGoodsService.getShopid(orgCode);
		 String operator = sysUser.getUsername();//操作者
		 String sendtime = busdate;
		 System.out.println(countSuccess.get());
		 System.out.println(countSend);
		 znGoodsService.insertSendNum(shopid, countSend, countSend, operator, sendtime);
		 //返回响应信息
		 return result.success("数据传送成功！");

	 }








}
