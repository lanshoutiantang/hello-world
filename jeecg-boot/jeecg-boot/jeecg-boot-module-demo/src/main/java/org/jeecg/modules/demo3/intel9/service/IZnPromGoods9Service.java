package org.jeecg.modules.demo3.intel9.service;

import org.jeecg.modules.demo3.intel9.entity.ZnPromDetail9;
import org.jeecg.modules.demo3.intel9.entity.ZnPromGoods9;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: zn_prom_goods9
 * @Author: jeecg-boot
 * @Date:   2020-12-29
 * @Version: V1.0
 */
public interface IZnPromGoods9Service extends IService<ZnPromGoods9> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(ZnPromGoods9 znPromGoods9, List<ZnPromDetail9> znPromDetail9List) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(ZnPromGoods9 znPromGoods9, List<ZnPromDetail9> znPromDetail9List);
	
	/**
	 * 删除一对多
	 */
	public void delMain(String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain(Collection<? extends Serializable> idList);
	
}
