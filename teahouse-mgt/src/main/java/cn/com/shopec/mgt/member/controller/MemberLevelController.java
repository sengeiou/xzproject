package cn.com.shopec.mgt.member.controller;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.shopec.common.ResultInfo;
import cn.com.shopec.common.constants.Constant;
import cn.com.shopec.core.common.PageFinder;
import cn.com.shopec.core.common.Query;
import cn.com.shopec.core.member.model.MemberLevel;
import cn.com.shopec.core.member.service.MemberLevelService;
import cn.com.shopec.mgt.common.BaseController;

/**
 * 会员等级管理
 * 
 * @author zln
 *
 */
@Controller
@RequestMapping("/memberLevel")
public class MemberLevelController extends BaseController {

	@Resource
	private MemberLevelService memberLevelService;
	@Value("${base_path}")
	private String basePath;

	/**
	 * 进入会员等级列表页面
	 * 
	 * @return
	 */
	@RequestMapping("toMemberLevelList")
	public String toMemberLevelList() {
		return "/member/member_level_list";
	}

	/**
	 * 查询会员等级列表
	 * 
	 * @param query
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("pageListMemberLevel")
	@ResponseBody
	public PageFinder<MemberLevel> pageListMemberLevel(@ModelAttribute("memberLevel") MemberLevel memberLevel,
			@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) throws ParseException {
		Query q = new Query(pageNo, pageSize, memberLevel);
		return memberLevelService.getMemberLevelPagedList(q);
	}

	/**
	 * 会员等级详情
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping("toMemberLevelView")
	public String toMemberLevelView(String memberLevelId, ModelMap model) {
		MemberLevel memberLevel = memberLevelService.getMemberLevel(memberLevelId);
		model.addAttribute("memberLevel", memberLevel);
		return "/member/member_level_view";
	}

	/**
	 * 会员等级添加页面
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping("toAddMemberLevel")
	public String toAddMemberLevel() {
		return "/member/member_level_add";
	}

	/**
	 * 会员等级添加
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping("addMemberLevel")
	@ResponseBody
	public ResultInfo<MemberLevel> addMemberLevel(@ModelAttribute("memberLevel") MemberLevel memberLevel) {
		ResultInfo<MemberLevel> resultInfo = new ResultInfo<MemberLevel>();
		MemberLevel mlSearch = new MemberLevel();
		mlSearch.setUpgradePoint(memberLevel.getUpgradePoint());
		List<MemberLevel> list = memberLevelService.getMemberLevelList(new Query(mlSearch));
		if (list != null && list.size() > 0) {
			resultInfo.setCode(Constant.FAIL);
			resultInfo.setMsg("该积分值的等级已经存在！");
			return resultInfo;
		} else {
			return memberLevelService.addMemberLevel(memberLevel, getOperator());
		}
	}

	/**
	 * 会员等级编辑页面
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping("toUpdateMemberLevel")
	public String toUpdateMemberLevel(String memberLevelId, ModelMap model) {
		MemberLevel memberLevel = memberLevelService.getMemberLevel(memberLevelId);
		model.addAttribute("memberLevel", memberLevel);
		return "/member/member_level_edit";
	}

	/**
	 * 会员等级修改
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping("updateMemberLevel")
	@ResponseBody
	public ResultInfo<MemberLevel> updateMemberLevel(@ModelAttribute("memberLevel") MemberLevel memberLevel) {
		ResultInfo<MemberLevel> resultInfo = new ResultInfo<MemberLevel>();
		MemberLevel mlSearch = new MemberLevel();
		mlSearch.setUpgradePoint(memberLevel.getUpgradePoint());
		List<MemberLevel> list = memberLevelService.getMemberLevelList(new Query(mlSearch));
		Integer tag = 0;
		if (list != null && list.size() > 0) {
			for (MemberLevel m : list) {
				if (!m.getMemberLevelId().equals(memberLevel.getMemberLevelId())) {
					tag = 1;
				}
			}
		}
		if (tag.equals(1)) {
			resultInfo.setCode(Constant.FAIL);
			resultInfo.setMsg("该积分值的等级已经存在！");
			return resultInfo;
		} else {
			return memberLevelService.updateMemberLevel(memberLevel, getOperator());
		}
	}

	/**
	 * 会员等级删除
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping("delMemberLevel")
	@ResponseBody
	public ResultInfo<MemberLevel> delMemberLevel(String memberLevelId) {
		MemberLevel ml = new MemberLevel();
		ml.setMemberLevelId(memberLevelId);
		ml.setIsDeleted(1);
		return memberLevelService.updateMemberLevel(ml, getOperator());
	}

	/**
	 * 会员等级新增校验
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping("checkMemberLevel")
	@ResponseBody
	public ResultInfo<String> checkMemberLevel(MemberLevel memberLevel) {

		ResultInfo<String> resultInfo = new ResultInfo<String>();

		List<MemberLevel> ls = memberLevelService.getMemberLevelList(new Query(memberLevel));
		if (ls.size() > 0) {
			resultInfo.setCode("1");
		} else {
			resultInfo.setCode("0");
		}

		return resultInfo;
	}

	/**
	 * 会员等级新增校验
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping("checkMemberLevelUp")
	@ResponseBody
	public ResultInfo<String> checkMemberLevelUp(String memberLevelId, String levelName) {

		ResultInfo<String> resultInfo = new ResultInfo<String>();
		MemberLevel ml = memberLevelService.getMemberLevel(memberLevelId);
		if (ml.getLevelName().equals(levelName)) {
			resultInfo.setCode("0");
		} else {
			MemberLevel memberLevel = new MemberLevel();
			memberLevel.setLevelName(levelName);

			List<MemberLevel> ls = memberLevelService.getMemberLevelList(new Query(memberLevel));
			if (ls.size() > 0) {
				resultInfo.setCode("1");
			} else {
				resultInfo.setCode("0");
			}
		}

		return resultInfo;
	}

}
