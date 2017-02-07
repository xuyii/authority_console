package com.cdel.plat.grant.facade;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cdel.plat.grant.domain.PrivilegeRel;
import com.cdel.util.BaseFacadeImpl;

@Service
@SuppressWarnings("serial")
public class PrivilegeRelFacade extends BaseFacadeImpl<PrivilegeRel, Integer>
		implements Serializable {

	/**
	 * 获取关联节点
	 * 
	 * @param plist
	 * @return
	 */
	public List<Integer> getPrivilegeRelByPri(List<Integer> plist) {
		if (plist == null || plist.size() == 0) {
			return null;
		}
		List<PrivilegeRel> tmp = this.baseDao.findSomeList(plist,
				"getPrivilegeRelByPri");
		if (tmp != null && tmp.size() > 0) {
			for (PrivilegeRel pri : tmp) {
				if (!plist.contains(pri.getRelPrivilegeID())) {
					plist.add(pri.getRelPrivilegeID());
				}
				if (!plist.contains(pri.getRelNeighborID())) {
					plist.add(pri.getRelNeighborID());
				}
			}
		}
		return plist;
	}

}
