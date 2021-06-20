package org.jeecg.modules.demo3.config.controller;

import java.util.Arrays;
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
import org.jeecg.modules.demo3.config.entity.ZnConfigV;
import org.jeecg.modules.demo3.config.service.IZnConfigVService;

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
 * @Description: 特殊供应商补货参数
 * @Author: jeecg-boot
 * @Date:   2021-04-14
 * @Version: V1.0
 */
@Api(tags="特殊供应商补货参数")
@RestController
@RequestMapping("/config/znConfigV")
@Slf4j
public class ZnConfigVController extends JeecgController<ZnConfigV, IZnConfigVService> {
	@Autowired
	private IZnConfigVService znConfigVService;
	
	/**
	 * 分页列表查询
	 *
	 * @param znConfigV
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "特殊供应商补货参数-分页列表查询")
	@ApiOperation(value="特殊供应商补货参数-分页列表查询", notes="特殊供应商补货参数-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ZnConfigV znConfigV,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ZnConfigV> queryWrapper = QueryGenerator.initQueryWrapper(znConfigV, req.getParameterMap());
		Page<ZnConfigV> page = new Page<ZnConfigV>(pageNo, pageSize);
		IPage<ZnConfigV> pageList = znConfigVService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param znConfigV
	 * @return
	 */
	@AutoLog(value = "特殊供应商补货参数-添加")
	@ApiOperation(value="特殊供应商补货参数-添加", notes="特殊供应商补货参数-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ZnConfigV znConfigV) {
		System.out.println(znConfigV.getVendername());
		znConfigVService.save(znConfigV);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param znConfigV
	 * @return
	 */
	@AutoLog(value = "特殊供应商补货参数-编辑")
	@ApiOperation(value="特殊供应商补货参数-编辑", notes="特殊供应商补货参数-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ZnConfigV znConfigV) {
		znConfigVService.updateById(znConfigV);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "特殊供应商补货参数-通过id删除")
	@ApiOperation(value="特殊供应商补货参数-通过id删除", notes="特殊供应商补货参数-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		znConfigVService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "特殊供应商补货参数-批量删除")
	@ApiOperation(value="特殊供应商补货参数-批量删除", notes="特殊供应商补货参数-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.znConfigVService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "特殊供应商补货参数-通过id查询")
	@ApiOperation(value="特殊供应商补货参数-通过id查询", notes="特殊供应商补货参数-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ZnConfigV znConfigV = znConfigVService.getById(id);
		if(znConfigV==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(znConfigV);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param znConfigV
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ZnConfigV znConfigV) {
        return super.exportXls(request, znConfigV, ZnConfigV.class, "特殊供应商补货参数");
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
        return super.importExcel(request, response, ZnConfigV.class);
    }

}
