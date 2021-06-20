package org.jeecg.modules.demo3.difference.service;

import org.jeecg.modules.demo3.difference.entity.DifferenceBuhuo2;
import org.jeecg.modules.demo3.difference.entity.DifferenceBuhuo;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 补货差异主表
 * @Author: jeecg-boot
 * @Date:   2021-04-15
 * @Version: V1.0
 */
public interface IDifferenceBuhuoService extends IService<DifferenceBuhuo> {

	/**
	 * 添加一对多
	 *
	 */
	public void saveMain(DifferenceBuhuo differenceBuhuo, List<DifferenceBuhuo2> differenceBuhuo2List) ;

	/**
	 * 修改一对多
	 *
	 */
	public void updateMain(DifferenceBuhuo differenceBuhuo, List<DifferenceBuhuo2> differenceBuhuo2List);

	/**
	 * 删除一对多
	 */
	public void delMain(String id);

	/**
	 * 批量删除一对多
	 */
	public void delBatchMain(Collection<? extends Serializable> idList);

}
