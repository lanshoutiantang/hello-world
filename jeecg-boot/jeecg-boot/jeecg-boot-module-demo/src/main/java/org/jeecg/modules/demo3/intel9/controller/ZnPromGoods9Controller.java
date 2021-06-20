package org.jeecg.modules.demo3.intel9.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecg.common.aspect.annotation.PermissionData;
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
import org.jeecg.modules.demo3.intel9.entity.ZnPromDetail9;
import org.jeecg.modules.demo3.intel9.entity.ZnPromGoods9;
import org.jeecg.modules.demo3.intel9.vo.ZnPromGoods9Page;
import org.jeecg.modules.demo3.intel9.service.IZnPromGoods9Service;
import org.jeecg.modules.demo3.intel9.service.IZnPromDetail9Service;
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
 * @Description: zn_prom_goods9
 * @Author: jeecg-boot
 * @Date:   2020-12-29
 * @Version: V1.0
 */
@Api(tags="zn_prom_goods9")
@RestController
@RequestMapping("/intel9/znPromGoods9")
@Slf4j
public class ZnPromGoods9Controller {
	@Autowired
	private IZnPromGoods9Service znPromGoods9Service;
	@Autowired
	private IZnPromDetail9Service znPromDetail9Service;
	
	/**
	 * 分页列表查询
	 *
	 * @param znPromGoods9
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "zn_prom_goods9-分页列表查询")
	@ApiOperation(value="zn_prom_goods9-分页列表查询", notes="zn_prom_goods9-分页列表查询")
	@GetMapping(value = "/list")
	@PermissionData(pageComponent = "oademo3/intel9/ZnPromGoods9List")
	public Result<?> queryPageList(ZnPromGoods9 znPromGoods9,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ZnPromGoods9> queryWrapper = QueryGenerator.initQueryWrapper(znPromGoods9, req.getParameterMap());
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(simpleDateFormat.format(date));
		queryWrapper.eq("manual_flag","1").ge("enddate",simpleDateFormat.format(date));
		Page<ZnPromGoods9> page = new Page<ZnPromGoods9>(pageNo, pageSize);
		IPage<ZnPromGoods9> pageList = znPromGoods9Service.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param znPromGoods9Page
	 * @return
	 */
	@AutoLog(value = "zn_prom_goods9-添加")
	@ApiOperation(value="zn_prom_goods9-添加", notes="zn_prom_goods9-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ZnPromGoods9Page znPromGoods9Page) {
		ZnPromGoods9 znPromGoods9 = new ZnPromGoods9();
		BeanUtils.copyProperties(znPromGoods9Page, znPromGoods9);
		znPromGoods9Service.saveMain(znPromGoods9, znPromGoods9Page.getZnPromDetail9List());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param znPromGoods9Page
	 * @return
	 */
	@AutoLog(value = "zn_prom_goods9-编辑")
	@ApiOperation(value="zn_prom_goods9-编辑", notes="zn_prom_goods9-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ZnPromGoods9Page znPromGoods9Page) {
		ZnPromGoods9 znPromGoods9 = new ZnPromGoods9();
		BeanUtils.copyProperties(znPromGoods9Page, znPromGoods9);
		ZnPromGoods9 znPromGoods9Entity = znPromGoods9Service.getById(znPromGoods9.getId());
		if(znPromGoods9Entity==null) {
			return Result.error("未找到对应数据");
		}
		znPromGoods9Service.updateMain(znPromGoods9, znPromGoods9Page.getZnPromDetail9List());
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "zn_prom_goods9-通过id删除")
	@ApiOperation(value="zn_prom_goods9-通过id删除", notes="zn_prom_goods9-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		znPromGoods9Service.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "zn_prom_goods9-批量删除")
	@ApiOperation(value="zn_prom_goods9-批量删除", notes="zn_prom_goods9-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.znPromGoods9Service.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "zn_prom_goods9-通过id查询")
	@ApiOperation(value="zn_prom_goods9-通过id查询", notes="zn_prom_goods9-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ZnPromGoods9 znPromGoods9 = znPromGoods9Service.getById(id);
		if(znPromGoods9==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(znPromGoods9);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "zn_prom_detail9通过主表ID查询")
	@ApiOperation(value="zn_prom_detail9主表ID查询", notes="zn_prom_detail9-通主表ID查询")
	@GetMapping(value = "/queryZnPromDetail9ByMainId")
	public Result<?> queryZnPromDetail9ListByMainId(@RequestParam(name="id",required=true) String id) {
		List<ZnPromDetail9> znPromDetail9List = znPromDetail9Service.selectByMainId(id);
		return Result.OK(znPromDetail9List);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param znPromGoods9
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ZnPromGoods9 znPromGoods9) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<ZnPromGoods9> queryWrapper = QueryGenerator.initQueryWrapper(znPromGoods9, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<ZnPromGoods9> queryList = znPromGoods9Service.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<ZnPromGoods9> znPromGoods9List = new ArrayList<ZnPromGoods9>();
      if(oConvertUtils.isEmpty(selections)) {
          znPromGoods9List = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          znPromGoods9List = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<ZnPromGoods9Page> pageList = new ArrayList<ZnPromGoods9Page>();
      for (ZnPromGoods9 main : znPromGoods9List) {
          ZnPromGoods9Page vo = new ZnPromGoods9Page();
          BeanUtils.copyProperties(main, vo);
          List<ZnPromDetail9> znPromDetail9List = znPromDetail9Service.selectByMainId(main.getId());
          vo.setZnPromDetail9List(znPromDetail9List);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "zn_prom_goods9列表");
      mv.addObject(NormalExcelConstants.CLASS, ZnPromGoods9Page.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("zn_prom_goods9数据", "导出人:"+sysUser.getRealname(), "zn_prom_goods9"));
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
              List<ZnPromGoods9Page> list = ExcelImportUtil.importExcel(file.getInputStream(), ZnPromGoods9Page.class, params);
              for (ZnPromGoods9Page page : list) {
                  ZnPromGoods9 po = new ZnPromGoods9();
                  BeanUtils.copyProperties(page, po);
                  znPromGoods9Service.saveMain(po, page.getZnPromDetail9List());
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
