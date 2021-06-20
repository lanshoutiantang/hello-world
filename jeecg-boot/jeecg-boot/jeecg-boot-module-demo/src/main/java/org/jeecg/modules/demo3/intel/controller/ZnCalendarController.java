package org.jeecg.modules.demo3.intel.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo3.intel.entity.ZnCalendar;
import org.jeecg.modules.demo3.intel.service.IZnCalendarService;

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
 * @Description: 阳历与阴历对应关系表
 * @Author: jeecg-boot
 * @Date:   2020-11-14
 * @Version: V1.0
 */
@Api(tags="阳历与阴历对应关系表")
@RestController
@RequestMapping("/intel/znCalendar")
@Slf4j
public class ZnCalendarController extends JeecgController<ZnCalendar, IZnCalendarService> {
	@Autowired
	private IZnCalendarService znCalendarService;
	
	/**
	 * 分页列表查询
	 *
	 * @param znCalendar
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "阳历与阴历对应关系表-分页列表查询")
	@ApiOperation(value="阳历与阴历对应关系表-分页列表查询", notes="阳历与阴历对应关系表-分页列表查询")
	@GetMapping(value = "/list")
	@PermissionData(pageComponent = "oademo3/intel/ZnCalendarList")
	public Result<?> queryPageList(ZnCalendar znCalendar,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ZnCalendar> queryWrapper = QueryGenerator.initQueryWrapper(znCalendar, req.getParameterMap());
		Date date = new Date();
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-01-01");
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-12-31");
		String data1 = simpleDateFormat1.format(date);
		String data2 = simpleDateFormat2.format(date);
		System.out.println(data1);
		System.out.println(data2);
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		Calendar calendar = Calendar.getInstance();
//		Calendar calendar1 = Calendar.getInstance();
//		calendar.add(Calendar.MONTH, -6);
//		calendar1.add(Calendar.MONTH, 6);
//		System.out.println(simpleDateFormat.format(calendar.getTime()));
//		System.out.println(simpleDateFormat.format(calendar1.getTime()));
//		String data1 = simpleDateFormat.format(calendar.getTime());
//		String data2 = simpleDateFormat.format(calendar1.getTime());
		queryWrapper.ne("holidayid","");
		queryWrapper.between("sundate",data1,data2);
		queryWrapper.orderByAsc("sundate");
		Page<ZnCalendar> page = new Page<ZnCalendar>(pageNo, pageSize);
		IPage<ZnCalendar> pageList = znCalendarService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param znCalendar
	 * @return
	 */
	@AutoLog(value = "阳历与阴历对应关系表-添加")
	@ApiOperation(value="阳历与阴历对应关系表-添加", notes="阳历与阴历对应关系表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ZnCalendar znCalendar) {
		znCalendarService.save(znCalendar);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param znCalendar
	 * @return
	 */
	@AutoLog(value = "阳历与阴历对应关系表-编辑")
	@ApiOperation(value="阳历与阴历对应关系表-编辑", notes="阳历与阴历对应关系表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ZnCalendar znCalendar) {
		znCalendarService.updateById(znCalendar);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "阳历与阴历对应关系表-通过id删除")
	@ApiOperation(value="阳历与阴历对应关系表-通过id删除", notes="阳历与阴历对应关系表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		znCalendarService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "阳历与阴历对应关系表-批量删除")
	@ApiOperation(value="阳历与阴历对应关系表-批量删除", notes="阳历与阴历对应关系表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.znCalendarService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "阳历与阴历对应关系表-通过id查询")
	@ApiOperation(value="阳历与阴历对应关系表-通过id查询", notes="阳历与阴历对应关系表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ZnCalendar znCalendar = znCalendarService.getById(id);
		if(znCalendar==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(znCalendar);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param znCalendar
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ZnCalendar znCalendar) {
        return super.exportXls(request, znCalendar, ZnCalendar.class, "阳历与阴历对应关系表");
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
        return super.importExcel(request, response, ZnCalendar.class);
    }

}
