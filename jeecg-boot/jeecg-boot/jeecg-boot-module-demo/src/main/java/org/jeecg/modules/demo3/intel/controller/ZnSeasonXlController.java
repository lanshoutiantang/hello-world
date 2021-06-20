package org.jeecg.modules.demo3.intel.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.apache.catalina.filters.ExpiresFilter;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo3.intel.entity.ZnGoods;
import org.jeecg.modules.demo3.intel.entity.ZnSeasonXl;
import org.jeecg.modules.demo3.intel.service.IZnSeasonXlService;

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
 * @Description: 季节性商品表
 * @Author: jeecg-boot
 * @Date:   2020-11-22
 * @Version: V1.0
 */
@Api(tags="季节性商品表")
@RestController
@RequestMapping("/intel/znSeasonXl")
@Slf4j
public class ZnSeasonXlController extends JeecgController<ZnSeasonXl, IZnSeasonXlService> {
	@Autowired
	private IZnSeasonXlService znSeasonXlService;
	
	/**
	 * 分页列表查询
	 *
	 * @param znSeasonXl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "季节性商品表-分页列表查询")
	@ApiOperation(value="季节性商品表-分页列表查询", notes="季节性商品表-分页列表查询")
	@GetMapping(value = "/list")
	 @PermissionData(pageComponent = "oademo3/intel/ZnSeasonXlList")
	public Result<?> queryPageList(ZnSeasonXl znSeasonXl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ZnSeasonXl> queryWrapper = QueryGenerator.initQueryWrapper(znSeasonXl, req.getParameterMap());
		Page<ZnSeasonXl> page = new Page<ZnSeasonXl>(pageNo, pageSize);
		IPage<ZnSeasonXl> pageList = znSeasonXlService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param znSeasonXl
	 * @return
	 */
	@AutoLog(value = "季节性商品表-添加")
	@ApiOperation(value="季节性商品表-添加", notes="季节性商品表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ZnSeasonXl znSeasonXl) {
		znSeasonXlService.save(znSeasonXl);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param znSeasonXl
	 * @return
	 */
	@AutoLog(value = "季节性商品表-编辑")
	@ApiOperation(value="季节性商品表-编辑", notes="季节性商品表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ZnSeasonXl znSeasonXl) {
		znSeasonXlService.updateById(znSeasonXl);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "季节性商品表-通过id删除")
	@ApiOperation(value="季节性商品表-通过id删除", notes="季节性商品表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		znSeasonXlService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "季节性商品表-批量删除")
	@ApiOperation(value="季节性商品表-批量删除", notes="季节性商品表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.znSeasonXlService.removeByIds(Arrays.asList(ids.split(",")));
		System.out.println("22222");
//		response.setHeader("Access-Control-Allow-Origin", "*");
//		response.setHeader("Cache-Control","no-cache");
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "季节性商品表-通过id查询")
	@ApiOperation(value="季节性商品表-通过id查询", notes="季节性商品表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ZnSeasonXl znSeasonXl = znSeasonXlService.getById(id);
		if(znSeasonXl==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(znSeasonXl);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param znSeasonXl
    */
    @RequestMapping(value = "/exportXls")
	@PermissionData(pageComponent = "oademo3/intel/ZnSeasonXlList")
    public ModelAndView exportXls(HttpServletRequest request, ZnSeasonXl znSeasonXl) {
        return super.exportXls(request, znSeasonXl, ZnSeasonXl.class, "季节性商品表");
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
        return super.importExcel(request, response, ZnSeasonXl.class);
    }

	 @GetMapping(value = "/getData")
	 @PermissionData(pageComponent = "oademo3/intel/ZnSeasonList")
	 public Result<?> getData(ZnSeasonXl znSeasonXl,
							  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
							  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
							  @RequestParam(name = "parentId", required = false) String parentId,
							  HttpServletRequest req) {
		 QueryWrapper<ZnSeasonXl> queryWrapper = QueryGenerator.initQueryWrapper(znSeasonXl, req.getParameterMap());
		 Page<ZnSeasonXl> page = new Page<ZnSeasonXl>(pageNo, pageSize);
		 IPage<ZnSeasonXl> pageList = znSeasonXlService.page(page,queryWrapper);
		 return Result.OK(pageList);
	 }

	 @PutMapping("/immediateSaveRow")
	 public Result mockImmediateSaveRow(@RequestBody JSONObject rowData) throws Exception {

		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
//		 String orgCode = sysUser.getOrgCode();
//		 String c = znSeasonXlService.getSysDataSource();
//		 System.out.println(c);
//		 System.out.println(znSeasonXlService.getSysDataSource());
//		 String username = sysUser.getUsername();
		 Date date = new Date();
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 simpleDateFormat.format(date);
		 System.out.println(sysUser);
		 System.out.println(date);
		 System.out.println(simpleDateFormat.format(date));
		 String username = sysUser.getUsername();
		 String begindate = rowData.getString("begindate");
		 String enddate = rowData.getString("enddate");
		 String xlid = rowData.getString("xlid");

		 znSeasonXlService.updateByBegindate(begindate,xlid);
		 znSeasonXlService.updateByEnddate(enddate,xlid);
		 znSeasonXlService.updateByUpdateBy(username,xlid);
//		 znSeasonXlService.updateByUpdateTime();
		 // 延时1.5秒，模拟网慢堵塞真实感
		 Thread.sleep(500);
		 return Result.ok();
	 }

	 /**
	  *   通过id删除
	  *
	  * @param id
	  * @return
	  */
	 @AutoLog(value = "季节性商品表-通过id删除")
	 @ApiOperation(value="季节性商品表-通过id删除", notes="季节性商品表-通过id删除")
	 @DeleteMapping(value = "/dele")
	 public Result<?> dele(@RequestParam(name="id",required=true) String id) {
		 znSeasonXlService.removeById(id);
		 return Result.OK("删除成功!");
	 }

}
