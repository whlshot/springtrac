package com.sample.mapper;

import java.util.List;

import com.sample.entity.ItemsCustom;
import com.sample.entity.ItemsQueryVo;

public interface ItemsMapperCustom {
	// 商品查询列表
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
}