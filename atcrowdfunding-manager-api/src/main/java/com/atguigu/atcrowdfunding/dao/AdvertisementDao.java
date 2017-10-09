package com.atguigu.atcrowdfunding.dao;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.bean.Advertisement;
import com.atguigu.atcrowdfunding.utils.Datas;

public interface AdvertisementDao {

	Advertisement queryAdvertisement(Map<String, Object> advertisementMap);

	List<Advertisement> pageQuery(Map<String, Object> advertisementMap);

	int queryCount(Map<String, Object> advertisementMap);

	int insertAdvertisement(Advertisement advertisement);

	Advertisement queryById(Integer id);

	int updateAdvertisement(Advertisement advertisement);

	int deleteAdvertisement(Integer id);

	int deleteAdvertisements(Datas ds);
}
