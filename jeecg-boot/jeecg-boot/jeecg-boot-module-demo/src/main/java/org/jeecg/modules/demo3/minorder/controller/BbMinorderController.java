package org.jeecg.modules.demo3.minorder.controller;

import java.math.BigDecimal;
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

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo3.intel.entity.ZnSeasonXl;
import org.jeecg.modules.demo3.minorder.entity.BbMinorder;
import org.jeecg.modules.demo3.minorder.service.IBbMinorderService;

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
 * @Description: 商品最小订货量
 * @Author: jeecg-boot
 * @Date:   2021-04-12
 * @Version: V1.0
 */
@Api(tags="商品最小订货量")
@RestController
@RequestMapping("/minorder/bbMinorder")
@Slf4j
public class BbMinorderController extends JeecgController<BbMinorder, IBbMinorderService> {
	@Autowired
	private IBbMinorderService bbMinorderService;
	
	/**
	 * 分页列表查询
	 *
	 * @param bbMinorder
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "商品最小订货量-分页列表查询")
	@ApiOperation(value="商品最小订货量-分页列表查询", notes="商品最小订货量-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(BbMinorder bbMinorder,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<BbMinorder> queryWrapper = QueryGenerator.initQueryWrapper(bbMinorder, req.getParameterMap());
		Page<BbMinorder> page = new Page<BbMinorder>(pageNo, pageSize);
		IPage<BbMinorder> pageList = bbMinorderService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param bbMinorder
	 * @return
	 */
/*	@AutoLog(value = "商品最小订货量-添加")
	@ApiOperation(value="商品最小订货量-添加", notes="商品最小订货量-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody BbMinorder bbMinorder) {
		bbMinorderService.save(bbMinorder);
		return Result.OK("添加成功！");
	}*/
	
	/**
	 *  编辑
	 *
	 * @param bbMinorder
	 * @return
	 */
	@AutoLog(value = "商品最小订货量-编辑")
	@ApiOperation(value="商品最小订货量-编辑", notes="商品最小订货量-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody BbMinorder bbMinorder) {
		bbMinorderService.updateById(bbMinorder);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "商品最小订货量-通过id删除")
	@ApiOperation(value="商品最小订货量-通过id删除", notes="商品最小订货量-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		bbMinorderService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "商品最小订货量-批量删除")
	@ApiOperation(value="商品最小订货量-批量删除", notes="商品最小订货量-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.bbMinorderService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "商品最小订货量-通过id查询")
	@ApiOperation(value="商品最小订货量-通过id查询", notes="商品最小订货量-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		BbMinorder bbMinorder = bbMinorderService.getById(id);
		if(bbMinorder==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(bbMinorder);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param bbMinorder
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, BbMinorder bbMinorder) {
        return super.exportXls(request, bbMinorder, BbMinorder.class, "商品最小订货量");
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
        return super.importExcel(request, response, BbMinorder.class);
    }


	 @GetMapping(value = "/getData")
	 @PermissionData(pageComponent = "oademo3/minorder/BbMinorderList")
	 public Result<?> getData(BbMinorder bbMinorder,
							  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
							  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
							  @RequestParam(name = "parentId", required = false) String parentId,
							  HttpServletRequest req) {
		 QueryWrapper<BbMinorder> queryWrapper = QueryGenerator.initQueryWrapper(bbMinorder, req.getParameterMap());
		 Page<BbMinorder> page = new Page<BbMinorder>(pageNo, pageSize);
		 IPage<BbMinorder> pageList = bbMinorderService.page(page,queryWrapper);
		 return Result.OK(pageList);
	 }

	 @PutMapping("/immediateSaveRow")
	 public Result mockImmediateSaveRow(@RequestBody JSONObject rowData) throws Exception {

		 String shopid = rowData.getString("shopid");
		 String goodsid = rowData.getString("goodsid");
		 BigDecimal minorderqty = rowData.getBigDecimal("minorderqty");
		 BigDecimal minstockqty = rowData.getBigDecimal("minstockqty");

		 bbMinorderService.updateByMinorderqty(minorderqty,shopid,goodsid);
		 bbMinorderService.updateByMinstockqty(minstockqty,shopid,goodsid);
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
	 @AutoLog(value = "商品最小订货量-通过id删除")
	 @ApiOperation(value="商品最小订货量-通过id删除", notes="商品最小订货量-通过id删除")
	 @DeleteMapping(value = "/dele")
	 public Result<?> dele(@RequestParam(name="id",required=true) String id) {
		 bbMinorderService.removeById(id);
		 return Result.OK("删除成功!");
	 }


/*	 *
	  *   添加
	  *
	  * @param bbMinorder
	  * @return*/

	 @AutoLog(value = "商品最小订货量-添加")
	 @ApiOperation(value="商品最小订货量-添加", notes="商品最小订货量-添加")
	 @PostMapping(value = "/add")
	 public Result<?> add(@RequestBody BbMinorder bbMinorder) {
		 String shopid = bbMinorder.getShopid();
		 String goodsid = bbMinorder.getGoodsid();
		 BigDecimal minorderqty = bbMinorder.getMinorderqty();
		 BigDecimal minstockqty = bbMinorder.getMinstockqty();
		 System.out.println(shopid);
		 System.out.println(goodsid);
		 System.out.println(minorderqty);
		 System.out.println(minorderqty);
		 bbMinorderService.saveLie(shopid,goodsid,minorderqty,minstockqty);
//		 bbMinorderService.save(bbMinorder);
		 return Result.OK("添加成功！");
	 }
}
