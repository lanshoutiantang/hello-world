package org.jeecg.modules.demo3.intel8.service;

import org.jeecg.modules.demo3.intel8.entity.ZnPromDetail8;
import org.jeecg.modules.demo3.intel8.entity.ZnPromGoods8;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: zn_prom_goods8
 * @Author: jeecg-boot
 * @Date:   2020-12-29
 * @Version: V1.0
 */
public interface IZnPromGoods8Service extends IService<ZnPromGoods8> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(ZnPromGoods8 znPromGoods8, List<ZnPromDetail8> znPromDetail8List) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(ZnPromGoods8 znPromGoods8, List<ZnPromDetail8> znPromDetail8List);
	
	/**
	 * 删除一对多
	 */
	public void delMain(String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain(Collection<? extends Serializable> idList);
	
}
