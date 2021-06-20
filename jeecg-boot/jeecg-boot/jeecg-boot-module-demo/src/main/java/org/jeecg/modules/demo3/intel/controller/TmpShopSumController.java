package org.jeecg.modules.demo3.intel.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo3.intel.entity.TmpShopSum;
import org.jeecg.modules.demo3.intel.service.ITmpShopSumService;

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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 按日期门店总销售
 * @Author: jeecg-boot
 * @Date:   2020-12-18
 * @Version: V1.0
 */
@Api(tags="按日期门店总销售")
@RestController
@RequestMapping("/intel/tmpShopSum")
@Slf4j
public class TmpShopSumController extends JeecgController<TmpShopSum, ITmpShopSumService> {
	@Autowired
	private ITmpShopSumService tmpShopSumService;
	 @Autowired
	 private JdbcTemplate jdbcTemplate;
	/**
	 * 分页列表查询
	 *
	 * @param tmpShopSum
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "按日期门店总销售-分页列表查询")
	@ApiOperation(value="按日期门店总销售-分页列表查询", notes="按日期门店总销售-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(TmpShopSum tmpShopSum,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TmpShopSum> queryWrapper = QueryGenerator.initQueryWrapper(tmpShopSum, req.getParameterMap());
		Page<TmpShopSum> page = new Page<TmpShopSum>(pageNo, pageSize);
		IPage<TmpShopSum> pageList = tmpShopSumService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	 /**
	  * 汇总
	  *
	  */

	 @AutoLog(value = "按日期门店总销售汇总")
	 @ApiOperation(value="按日期门店总销售汇总", notes="按日期门店总销售汇总")
	 @GetMapping(value = "/shopSumlist")
	 public Result<?> queryPageList1(TmpShopSum tmpShopSum,
									@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									HttpServletRequest req) throws ClassNotFoundException, SQLException {
		 String shopid = tmpShopSum.getShopid();
		 Date begindate = tmpShopSum.getBegindate();
		 Date enddate = tmpShopSum.getEnddate();
		 Date date = new Date();
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		 String begindate1 = null;
		 String enddate1 = null;
		 // begindate或enddate为空设置为字符类型null
		 if(begindate == null || enddate == null) {
			 if (begindate == null && enddate == null) {
				 begindate1 = null;
				 enddate1 = null;
			 } else if (begindate != null && enddate == null) {
				 begindate1 = simpleDateFormat.format(begindate);
				 enddate1 = null;
			 } else if (begindate == null && enddate != null) {
				 begindate1 = null;
				 enddate1 = simpleDateFormat.format(enddate);
			 }
		 }else if( begindate != null && enddate != null) {
			 begindate1 = simpleDateFormat.format(begindate);
			 enddate1 = simpleDateFormat.format(enddate);
		 }
		 String sql = "call proc_new_sum1('" + shopid + "','" + begindate1 + "','" + enddate1 +"')";
		 jdbcTemplate.execute("call proc_new_sum1('" + shopid + "','" + begindate1 + "','" + enddate1 +"')");


		 QueryWrapper<TmpShopSum> queryWrapper = QueryGenerator.initQueryWrapper(tmpShopSum, req.getParameterMap());
		 Page<TmpShopSum> page = new Page<TmpShopSum>(pageNo, pageSize);
		 IPage<TmpShopSum> pageList = tmpShopSumService.page(page, queryWrapper);
		 return Result.OK(pageList);
	 }
	
	/**
	 *   添加
	 *
	 * @param tmpShopSum
	 * @return
	 */
	@AutoLog(value = "按日期门店总销售-添加")
	@ApiOperation(value="按日期门店总销售-添加", notes="按日期门店总销售-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TmpShopSum tmpShopSum) {
		tmpShopSumService.save(tmpShopSum);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param tmpShopSum
	 * @return
	 */
	@AutoLog(value = "按日期门店总销售-编辑")
	@ApiOperation(value="按日期门店总销售-编辑", notes="按日期门店总销售-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody TmpShopSum tmpShopSum) {
		tmpShopSumService.updateById(tmpShopSum);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "按日期门店总销售-通过id删除")
	@ApiOperation(value="按日期门店总销售-通过id删除", notes="按日期门店总销售-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		tmpShopSumService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "按日期门店总销售-批量删除")
	@ApiOperation(value="按日期门店总销售-批量删除", notes="按日期门店总销售-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tmpShopSumService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "按日期门店总销售-通过id查询")
	@ApiOperation(value="按日期门店总销售-通过id查询", notes="按日期门店总销售-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		TmpShopSum tmpShopSum = tmpShopSumService.getById(id);
		if(tmpShopSum==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(tmpShopSum);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param tmpShopSum
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TmpShopSum tmpShopSum) {
        return super.exportXls(request, tmpShopSum, TmpShopSum.class, "按日期门店总销售");
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
        return super.importExcel(request, response, TmpShopSum.class);
    }

}
