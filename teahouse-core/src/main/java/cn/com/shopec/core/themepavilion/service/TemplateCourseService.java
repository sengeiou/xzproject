package cn.com.shopec.core.themepavilion.service;

import java.util.List;

import cn.com.shopec.common.Operator;
import cn.com.shopec.common.ResultInfo;
import cn.com.shopec.core.common.BaseService;
import cn.com.shopec.core.common.PageFinder;
import cn.com.shopec.core.common.Query;
import cn.com.shopec.core.themepavilion.model.TemplateCourse;

/**
 * 排课模版表 服务接口类
 */
public interface TemplateCourseService extends BaseService {

	/**
	 * 根据查询条件，查询并返回TemplateCourse的列表
	 * @param q 查询条件
	 * @return
	 */
	public List<TemplateCourse> getTemplateCourseList(Query q);
	
	/**
	 * 查询一共多少行
	 * @author LiHuan
	 */
	public List<TemplateCourse> templateCourseList(Query q);
	
	/**
	 * 根据删除行号，查询所有大于该行号的排课数据
	 */
	public List<TemplateCourse> getTemplateCourseRowList(String storeNo, int rowNo);
	
	/**
	 * 根据查询条件，分页查询并返回TemplateCourse的分页结果
	 * @param q 查询条件
	 * @return
	 */
	public PageFinder<TemplateCourse> getTemplateCoursePagedList(Query q);
	
	/**
	 * 根据主键，查询并返回一个TemplateCourse对象
	 * @param id 主键id
	 * @return
	 */
	public TemplateCourse getTemplateCourse(String id);

	/**
	 * 根据主键数组，查询并返回一组TemplateCourse对象
	 * @param ids 主键数组，数组中的主键值应当无重复值，如有重复值时，则返回的列表长度可能小于传入的数组长度
	 * @return
	 */
	public List<TemplateCourse> getTemplateCourseByIds(String[] ids);
	
	/**
	 * 根据主键，删除特定的TemplateCourse记录
	 * @param id 主键id
	 * @param operator 操作人对象
	 * @return
	 */
	public ResultInfo<TemplateCourse> delTemplateCourse(String id, Operator operator);
	
	/**
	 * 新增一条TemplateCourse记录，执行成功后传入对象及返回对象的主键属性值不为null
	 * @param templateCourse 新增的TemplateCourse数据（如果无特殊需求，新增对象的主键值请保留为null）
	 * @param operator 操作人对象
	 * @return
	 */
	public ResultInfo<TemplateCourse> addTemplateCourse(TemplateCourse templateCourse, Operator operator);
	
	/**
	 * 根据主键，更新一条TemplateCourse记录
	 * @param templateCourse 更新的TemplateCourse数据，且其主键不能为空
	 * @param operator 操作人对象
	 * @return
	 */
	public ResultInfo<TemplateCourse> updateTemplateCourse(TemplateCourse templateCourse, Operator operator);

	/**
	 * 生成主键
	 * @return
	 */
	public String generatePK();
	
		/**
	 * 为TemplateCourse对象设置默认值
	 * @param obj
	 */
	public void fillDefaultValues(TemplateCourse obj);
		
}
