package org.jeecg.modules.demo3.intel8.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo3.intel8.entity.ZnPromDetail8;
import org.jeecg.modules.demo3.intel8.entity.ZnPromGoods8;
import org.jeecg.modules.demo3.intel8.vo.ZnPromGoods8Page;
import org.jeecg.modules.demo3.intel8.service.IZnPromGoods8Service;
import org.jeecg.modules.demo3.intel8.service.IZnPromDetail8Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: zn_prom_goods8
 * @Author: jeecg-boot
 * @Date:   2020-12-29
 * @Version: V1.0
 */
@Api(tags="zn_prom_goods8")
@RestController
@RequestMapping("/intel8/znPromGoods8")
@Slf4j
public class ZnPromGoods8Controller {
	@Autowired
	private IZnPromGoods8Service znPromGoods8Service;
	@Autowired
	private IZnPromDetail8Service znPromDetail8Service;
	
	/**
	 * 分页列表查询
	 *
	 * @param znPromGoods8
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "zn_prom_goods8-分页列表查询")
	@ApiOperation(value="zn_prom_goods8-分页列表查询", notes="zn_prom_goods8-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ZnPromGoods8 znPromGoods8,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ZnPromGoods8> queryWrapper = QueryGenerator.initQueryWrapper(znPromGoods8, req.getParameterMap());
		Page<ZnPromGoods8> page = new Page<ZnPromGoods8>(pageNo, pageSize);
		IPage<ZnPromGoods8> pageList = znPromGoods8Service.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param znPromGoods8Page
	 * @return
	 */
	@AutoLog(value = "zn_prom_goods8-添加")
	@ApiOperation(value="zn_prom_goods8-添加", notes="zn_prom_goods8-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ZnPromGoods8Page znPromGoods8Page) {
		ZnPromGoods8 znPromGoods8 = new ZnPromGoods8();
		BeanUtils.copyProperties(znPromGoods8Page, znPromGoods8);
		znPromGoods8Service.saveMain(znPromGoods8, znPromGoods8Page.getZnPromDetail8List());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param znPromGoods8Page
	 * @return
	 */
	@AutoLog(value = "zn_prom_goods8-编辑")
	@ApiOperation(value="zn_prom_goods8-编辑", notes="zn_prom_goods8-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ZnPromGoods8Page znPromGoods8Page) {
		ZnPromGoods8 znPromGoods8 = new ZnPromGoods8();
		BeanUtils.copyProperties(znPromGoods8Page, znPromGoods8);
		ZnPromGoods8 znPromGoods8Entity = znPromGoods8Service.getById(znPromGoods8.getId());
		if(znPromGoods8Entity==null) {
			return Result.error("未找到对应数据");
		}
		znPromGoods8Service.updateMain(znPromGoods8, znPromGoods8Page.getZnPromDetail8List());
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "zn_prom_goods8-通过id删除")
	@ApiOperation(value="zn_prom_goods8-通过id删除", notes="zn_prom_goods8-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		znPromGoods8Service.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "zn_prom_goods8-批量删除")
	@ApiOperation(value="zn_prom_goods8-批量删除", notes="zn_prom_goods8-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.znPromGoods8Service.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "zn_prom_goods8-通过id查询")
	@ApiOperation(value="zn_prom_goods8-通过id查询", notes="zn_prom_goods8-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ZnPromGoods8 znPromGoods8 = znPromGoods8Service.getById(id);
		if(znPromGoods8==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(znPromGoods8);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "zn_prom_detail8通过主表ID查询")
	@ApiOperation(value="zn_prom_detail8主表ID查询", notes="zn_prom_detail8-通主表ID查询")
	@GetMapping(value = "/queryZnPromDetail8ByMainId")
	public Result<?> queryZnPromDetail8ListByMainId(@RequestParam(name="id",required=true) String id) {
		List<ZnPromDetail8> znPromDetail8List = znPromDetail8Service.selectByMainId(id);
		return Result.OK(znPromDetail8List);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param znPromGoods8
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ZnPromGoods8 znPromGoods8) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<ZnPromGoods8> queryWrapper = QueryGenerator.initQueryWrapper(znPromGoods8, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<ZnPromGoods8> queryList = znPromGoods8Service.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<ZnPromGoods8> znPromGoods8List = new ArrayList<ZnPromGoods8>();
      if(oConvertUtils.isEmpty(selections)) {
          znPromGoods8List = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          znPromGoods8List = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<ZnPromGoods8Page> pageList = new ArrayList<ZnPromGoods8Page>();
      for (ZnPromGoods8 main : znPromGoods8List) {
          ZnPromGoods8Page vo = new ZnPromGoods8Page();
          BeanUtils.copyProperties(main, vo);
          List<ZnPromDetail8> znPromDetail8List = znPromDetail8Service.selectByMainId(main.getId());
          vo.setZnPromDetail8List(znPromDetail8List);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "zn_prom_goods8列表");
      mv.addObject(NormalExcelConstants.CLASS, ZnPromGoods8Page.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("zn_prom_goods8数据", "导出人:"+sysUser.getRealname(), "zn_prom_goods8"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
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
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// 获取上传文件对象
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<ZnPromGoods8Page> list = ExcelImportUtil.importExcel(file.getInputStream(), ZnPromGoods8Page.class, params);
              for (ZnPromGoods8Page page : list) {
                  ZnPromGoods8 po = new ZnPromGoods8();
                  BeanUtils.copyProperties(page, po);
                  znPromGoods8Service.saveMain(po, page.getZnPromDetail8List());
              }
              return Result.OK("文件导入成功！数据行数:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.OK("文件导入失败！");
    }

}
