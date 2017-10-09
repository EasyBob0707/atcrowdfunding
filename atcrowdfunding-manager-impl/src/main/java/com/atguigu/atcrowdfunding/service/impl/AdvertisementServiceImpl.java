package com.atguigu.atcrowdfunding.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.dao.AdvertisementDao;
import com.atguigu.atcrowdfunding.bean.Advertisement;
import com.atguigu.atcrowdfunding.service.AdvertisementService;
import com.atguigu.atcrowdfunding.utils.Datas;
import com.atguigu.atcrowdfunding.utils.Page;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

	@Autowired
	private AdvertisementDao advertisementDao;
	
	public Advertisement queryAdvertisement(Map<String, Object> AdvertisementMap) {
		return advertisementDao.queryAdvertisement(AdvertisementMap);
	}

	public Page<Advertisement> pageQuery(Map<String, Object> paramMap) {
		Page<Advertisement> AdvertisementPage = new Page<Advertisement>((Integer)paramMap.get("pageno"),(Integer)paramMap.get("pagesize"));
		paramMap.put("startIndex", AdvertisementPage.getStartIndex());
		List<Advertisement> AdvertisementList= advertisementDao.pageQuery(paramMap);
		// 获取数据的总条数
		int count = advertisementDao.queryCount(paramMap);
		
		AdvertisementPage.setData(AdvertisementList);
		AdvertisementPage.setTotalsize(count);
		return AdvertisementPage;
	}

	public int queryCount(Map<String, Object> AdvertisementMap) {
		return advertisementDao.queryCount(AdvertisementMap);
	}

	public int insertAdvertisement(Advertisement Advertisement) {
		return advertisementDao.insertAdvertisement(Advertisement);
	}

	public Advertisement queryById(Integer id) {
		return advertisementDao.queryById(id);
	}

	public int updateAdvertisement(Advertisement Advertisement) {
		return advertisementDao.updateAdvertisement(Advertisement);
	}

	public int deleteAdvertisement(Integer id) {
		return advertisementDao.deleteAdvertisement(id);
	}

	public int deleteAdvertisements(Datas ds) {
		return advertisementDao.deleteAdvertisements(ds);
	}

}
