package org.jeecg.modules.demo3.difference.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecg.modules.demo3.intel.entity.TmpShopSum;
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
import org.jeecg.modules.demo3.difference.entity.DifferenceBuhuo2;
import org.jeecg.modules.demo3.difference.entity.DifferenceBuhuo;
import org.jeecg.modules.demo3.difference.vo.DifferenceBuhuoPage;
import org.jeecg.modules.demo3.difference.service.IDifferenceBuhuoService;
import org.jeecg.modules.demo3.difference.service.IDifferenceBuhuo2Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
 * @Description: 补货差异主表
 * @Author: jeecg-boot
 * @Date:   2021-04-15
 * @Version: V1.0
 */
@Api(tags="补货差异主表")
@RestController
@RequestMapping("/difference/differenceBuhuo")
@Slf4j
public class DifferenceBuhuoController {
	@Autowired
	private IDifferenceBuhuoService differenceBuhuoService;
	@Autowired
	private IDifferenceBuhuo2Service differenceBuhuo2Service;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 分页列表查询
	 *
	 * @param differenceBuhuo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "补货差异主表-分页列表查询")
	@ApiOperation(value="补货差异主表-分页列表查询", notes="补货差异主表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(DifferenceBuhuo differenceBuhuo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<DifferenceBuhuo> queryWrapper = QueryGenerator.initQueryWrapper(differenceBuhuo, req.getParameterMap());
		Page<DifferenceBuhuo> page = new Page<DifferenceBuhuo>(pageNo, pageSize);
		IPage<DifferenceBuhuo> pageList = differenceBuhuoService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	 /**
	  * 汇总
	  *
	  */

	 @AutoLog(value = "补货差异汇总")
	 @ApiOperation(value="补货差异汇总", notes="补货差异汇总")
	 @GetMapping(value = "/differenceBuhuolist")
	 public Result<?> queryPageList1(DifferenceBuhuo differenceBuhuo,
									 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									 HttpServletRequest req) throws ClassNotFoundException, SQLException {
	 	 String shopid =differenceBuhuo.getShopid();
		 System.out.println(shopid);
//		 String sql = "call proc_new_sum1('" + shopid + "','" + begindate1 + "','" + enddate1 +"')";
		 jdbcTemplate.execute("call proc_buhuo_differences()");
		 QueryWrapper<DifferenceBuhuo> queryWrapper = QueryGenerator.initQueryWrapper(differenceBuhuo, req.getParameterMap());
		 Page<DifferenceBuhuo> page = new Page<DifferenceBuhuo>(pageNo, pageSize);
		 IPage<DifferenceBuhuo> pageList = differenceBuhuoService.page(page, queryWrapper);
		 return Result.OK(pageList);
	 }


	 /**
	 *   添加
	 *
	 * @param differenceBuhuoPage
	 * @return
	 */
	@AutoLog(value = "补货差异主表-添加")
	@ApiOperation(value="补货差异主表-添加", notes="补货差异主表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody DifferenceBuhuoPage differenceBuhuoPage) {
		DifferenceBuhuo differenceBuhuo = new DifferenceBuhuo();
		BeanUtils.copyProperties(differenceBuhuoPage, differenceBuhuo);
		differenceBuhuoService.saveMain(differenceBuhuo, differenceBuhuoPage.getDifferenceBuhuo2List());
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param differenceBuhuoPage
	 * @return
	 */
	@AutoLog(value = "补货差异主表-编辑")
	@ApiOperation(value="补货差异主表-编辑", notes="补货差异主表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody DifferenceBuhuoPage differenceBuhuoPage) {
		DifferenceBuhuo differenceBuhuo = new DifferenceBuhuo();
		BeanUtils.copyProperties(differenceBuhuoPage, differenceBuhuo);
		DifferenceBuhuo differenceBuhuoEntity = differenceBuhuoService.getById(differenceBuhuo.getId());
		if(differenceBuhuoEntity==null) {
			return Result.error("未找到对应数据");
		}
		differenceBuhuoService.updateMain(differenceBuhuo, differenceBuhuoPage.getDifferenceBuhuo2List());
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "补货差异主表-通过id删除")
	@ApiOperation(value="补货差异主表-通过id删除", notes="补货差异主表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		differenceBuhuoService.delMain(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "补货差异主表-批量删除")
	@ApiOperation(value="补货差异主表-批量删除", notes="补货差异主表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.differenceBuhuoService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "补货差异主表-通过id查询")
	@ApiOperation(value="补货差异主表-通过id查询", notes="补货差异主表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		DifferenceBuhuo differenceBuhuo = differenceBuhuoService.getById(id);
		if(differenceBuhuo==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(differenceBuhuo);

	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存在补货差异的商品如下通过主表ID查询")
	@ApiOperation(value="存在补货差异的商品如下主表ID查询", notes="存在补货差异的商品如下-通主表ID查询")
	@GetMapping(value = "/queryDifferenceBuhuo2ByMainId")
	public Result<?> queryDifferenceBuhuo2ListByMainId(@RequestParam(name="id",required=true) String id) {
		String shopid = differenceBuhuo2Service.getShopid(id);
		List<DifferenceBuhuo2> differenceBuhuo2List = differenceBuhuo2Service.selectByMainId(shopid);
		return Result.OK(differenceBuhuo2List);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param differenceBuhuo
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, DifferenceBuhuo differenceBuhuo) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<DifferenceBuhuo> queryWrapper = QueryGenerator.initQueryWrapper(differenceBuhuo, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<DifferenceBuhuo> queryList = differenceBuhuoService.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<DifferenceBuhuo> differenceBuhuoList = new ArrayList<DifferenceBuhuo>();
      if(oConvertUtils.isEmpty(selections)) {
          differenceBuhuoList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          differenceBuhuoList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<DifferenceBuhuoPage> pageList = new ArrayList<DifferenceBuhuoPage>();
      for (DifferenceBuhuo main : differenceBuhuoList) {
          DifferenceBuhuoPage vo = new DifferenceBuhuoPage();
          BeanUtils.copyProperties(main, vo);
          String shopid = differenceBuhuo2Service.getShopid(main.getId());
          List<DifferenceBuhuo2> differenceBuhuo2List = differenceBuhuo2Service.selectByMainId(shopid);
          vo.setDifferenceBuhuo2List(differenceBuhuo2List);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "补货差异主表列表");
      mv.addObject(NormalExcelConstants.CLASS, DifferenceBuhuoPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("补货差异主表数据", "导出人:"+sysUser.getRealname(), "补货差异主表"));
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
              List<DifferenceBuhuoPage> list = ExcelImportUtil.importExcel(file.getInputStream(), DifferenceBuhuoPage.class, params);
              for (DifferenceBuhuoPage page : list) {
                  DifferenceBuhuo po = new DifferenceBuhuo();
                  BeanUtils.copyProperties(page, po);
                  differenceBuhuoService.saveMain(po, page.getDifferenceBuhuo2List());
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
