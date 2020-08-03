package com.colorful.service;

import com.colorful.dao.GoodsDAO;
import com.colorful.model.Goods;

import java.util.List;
import java.util.stream.Collectors;

public class GoodsService {

    private final GoodsDAO goodsDAO = new GoodsDAO();


    /**
     * 新增商品
     * @param goods 商品实体类
     */
    public void addGoods(Goods goods) {
        goodsDAO.insertGoods(goods);
    }

    /**
     * 删除商品
     * @param id 商品id
     */
    public void removeGoodsById(Integer id) {
        goodsDAO.deleteGoodsById(id);
    }

    /**
     * 编辑商品
     * @param goods 商品实体类
     */
    public void editGoods(Goods goods) {
        goodsDAO.updateGoodsById(goods);
    }

    /**
     * 获取商品详情
     * @param id 商品id
     * @return 商品实体类
     */
    public Goods getById(Integer id) {
        return goodsDAO.selectGoodsById(id);
    }

    /**
     * 获取商品列表
     * @return 商品列表
     */
    public List<Goods> getGoodsList() {
        return goodsDAO.selectGoodsList();
    }

    /**
     * 根据商品名称搜索商品
     * @param name 商品名称
     * @return 商品列表
     */
    public List<Goods> searchGoodsByName(String name) {
        List<Goods> goodsList = this.getGoodsList();
        return goodsList.stream().filter(
                goods -> goods.getName().contains(name)
        ).collect(Collectors.toList());
    }

    /**
     * 根据商品分类搜索商品
     * @param categoryId 分类id
     * @return 商品列表
     */
    public List<Goods> searchGoodsByCategoryId(Integer categoryId) {
        return goodsDAO.selectGoodsListByCategoryId(categoryId);
    }
}
