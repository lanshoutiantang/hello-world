package org.jeecg.modules.demo3.log.controller;

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
import org.jeecg.modules.demo3.log.entity.ZnProclog;
import org.jeecg.modules.demo3.log.service.IZnProclogService;

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
 * @Description: 执行补货流程
 * @Author: jeecg-boot
 * @Date:   2021-05-12
 * @Version: V1.0
 */
@Api(tags="执行补货流程")
@RestController
@RequestMapping("/log/znProclog")
@Slf4j
public class ZnProclogController extends JeecgController<ZnProclog, IZnProclogService> {
	@Autowired
	private IZnProclogService znProclogService;
	
	/**
	 * 分页列表查询
	 *
	 * @param znProclog
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "执行补货流程-分页列表查询")
	@ApiOperation(value="执行补货流程-分页列表查询", notes="执行补货流程-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ZnProclog znProclog,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ZnProclog> queryWrapper = QueryGenerator.initQueryWrapper(znProclog, req.getParameterMap());
		Page<ZnProclog> page = new Page<ZnProclog>(pageNo, pageSize);
		IPage<ZnProclog> pageList = znProclogService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param znProclog
	 * @return
	 */
	@AutoLog(value = "执行补货流程-添加")
	@ApiOperation(value="执行补货流程-添加", notes="执行补货流程-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ZnProclog znProclog) throws Exception {
		System.out.println(znProclog.getDate());
		System.out.println(znProclog.getShopid());
		System.out.println(znProclog.getShop());
//		znProclogService.save(znProclog);
		znProclogService.produce(znProclog.getDate(),znProclog.getShop());
//		Thread.sleep(20 * 1000);
		return Result.OK("生成补货数据成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param znProclog
	 * @return
	 */
	@AutoLog(value = "执行补货流程-编辑")
	@ApiOperation(value="执行补货流程-编辑", notes="执行补货流程-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ZnProclog znProclog) {
		znProclogService.updateById(znProclog);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "执行补货流程-通过id删除")
	@ApiOperation(value="执行补货流程-通过id删除", notes="执行补货流程-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		znProclogService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "执行补货流程-批量删除")
	@ApiOperation(value="执行补货流程-批量删除", notes="执行补货流程-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.znProclogService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "执行补货流程-通过id查询")
	@ApiOperation(value="执行补货流程-通过id查询", notes="执行补货流程-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ZnProclog znProclog = znProclogService.getById(id);
		if(znProclog==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(znProclog);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param znProclog
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ZnProclog znProclog) {
        return super.exportXls(request, znProclog, ZnProclog.class, "执行补货流程");
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
        return super.importExcel(request, response, ZnProclog.class);
    }

}
