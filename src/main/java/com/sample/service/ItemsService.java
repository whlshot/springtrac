package com.sample.service;

import java.util.List;

import com.sample.entity.ItemsCustom;
import com.sample.entity.ItemsQueryVo;

public interface ItemsService {

	List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;

	ItemsCustom findItemsById(Integer id) throws Exception;

	void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception;

}
