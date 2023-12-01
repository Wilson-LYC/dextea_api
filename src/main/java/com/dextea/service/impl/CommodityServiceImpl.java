package com.dextea.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dextea.mapper.CategoryMapper;
import com.dextea.mapper.CommCateMapper;
import com.dextea.mapper.CommodityMapper;
import com.dextea.pojo.Category;
import com.dextea.pojo.CommCate;
import com.dextea.pojo.Commodity;
import com.dextea.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    CommCateMapper commCateMapper;
    @Autowired
    CommodityMapper commodityMapper;

    /**
     * commodity转换为json
     * @param commodity 商品
     * @return JSONObject
     */
    @Override
    public JSONObject toJson(Commodity commodity) {
        JSONObject res= JSONObject.from(commodity);
        String custom=commodity.getCustom();
        if(custom!=null && !custom.isEmpty())
            res.put("custom", JSONArray.parseArray(custom));
        String category=commodity.getCategory();
        if(category!=null && !category.isEmpty()){
            String[] categorys = category.split(",");
            res.put("category", categorys);
        }
        return res;
    }

    /**
     * commodityList转换为json
     * @param commodityList 商品列表
     * @return JSONArray
     */
    @Override
    public JSONArray toJson(List<Commodity> commodityList) {
        JSONArray res=new JSONArray();
        for(Commodity commodity:commodityList){
            JSONObject json=toJson(commodity);
            res.add(json);
        }
        return res;
    }

    /**
     * 获取商品列表(略)
     * @return JSONObject
     */
    @Override
    public JSONObject getAllCommBrief() {
        JSONObject res=new JSONObject();
        List<Commodity> commodityList=commodityMapper.getAllCommBrief();
        res.put("code",200);
        res.put("msg","成功");
        JSONObject data=new JSONObject();
        data.put("commodity",toJson(commodityList));
        res.put("data",data);
        return res;
    }


    /**
     * 获取所有商品（详细）
     * @return JSONObject
     */
    @Override
    public JSONObject getAllCommFull() {
        JSONObject res=new JSONObject();
        List<Commodity> commodityList=commodityMapper.getAllCommFull();
        res.put("code",200);
        res.put("msg","成功");
        JSONObject data=new JSONObject();
        data.put("commodity",toJson(commodityList));
        res.put("data",data);
        return res;
    }

    /**
     * 新增商品
     * @param commodity
     * @return
     */
    @Override
    public JSONObject addCommodity(Commodity commodity) {
        JSONObject res=new JSONObject();
        int result=commodityMapper.addCommodity(commodity);
        if(result==1){
            res.put("code",200);
            res.put("msg","新增商品成功");
        }else{
            res.put("code",500);
            res.put("msg","新增商品失败");
        }
        return res;
    }

    /**
     * 获取单个商品
     * @param id
     * @return
     */
    @Override
    public JSONObject getCommInfo(int id) {
        JSONObject res=new JSONObject();
        Commodity commodity=commodityMapper.getCommById(id);
        res.put("code",200);
        res.put("msg","成功");
        JSONObject data=new JSONObject();
        data.put("commodity",toJson(commodity));
        res.put("data",data);
        return res;
    }

    /**
     * 更新商品
     * @param commodity 商品
     * @param categoryArray 品类列表
     * @return JSONObject
     */
    @Override
    public JSONObject updateCommodity(Commodity commodity, JSONArray categoryArray) {
        JSONObject res=new JSONObject();
        int flag=commodityMapper.updateCommodity(commodity);
        if(flag==0){
            res.put("code",500);
            res.put("msg","失败");
            return res;
        }
        //删除原有品类
        commCateMapper.deleteCateByCommId(commodity.getId());
        //添加新品类
        for(int i=0;i<categoryArray.size();i++){
            String cateName=categoryArray.getString(i);
            Category category=categoryMapper.getCateByName(cateName);
            CommCate commCate=new CommCate();
            commCate.setCommId(commodity.getId());
            commCate.setCateId(category.getId());
            commCateMapper.addCateToComm(commCate);
        }
        res.put("code",200);
        res.put("msg","成功");
        return res;
    }

    /**
     * 搜索商品
     * @param commodity 商品
     * @param cateId    品类id
     * @return JSONObject
     */
    @Override
    public JSONObject searchComm(Commodity commodity, int cateId) {
        JSONObject res=new JSONObject();
        List<Commodity> commodityList=commodityMapper.searchComm(commodity.getId(),commodity.getName(),commodity.getState(),cateId);
        res.put("code",200);
        res.put("msg","成功");
        JSONObject data=new JSONObject();
        data.put("commodity",toJson(commodityList));
        res.put("data",data);
        return res;
    }

    /**
     * 删除商品
     * @param id 商品id
     * @return JSONObject
     */
    @Override
    public JSONObject deleteComm(int id) {
        JSONObject res=new JSONObject();
        //先删除商品的品类
        int result=commCateMapper.deleteCateByCommId(id);
        //再删除商品
        int flag=commodityMapper.deleteComm(id);
        if(flag==0){
            res.put("code",500);
            res.put("msg","失败");
            return res;
        }
        commCateMapper.deleteCateByCommId(id);
        res.put("code",200);
        res.put("msg","成功");
        return res;
    }

    /**
     * 通过品类id获取商品
     * @param cateId 品类id
     * @return  JSONObject
     */
    @Override
    public JSONObject getCommByCateId(int cateId) {
        JSONObject res=new JSONObject();
        List<Commodity> commodityList=commodityMapper.getCommByCateId(cateId);
        res.put("code",200);
        res.put("msg","成功");
        JSONObject data=new JSONObject();
        data.put("commodity",toJson(commodityList));
        res.put("data",data);
        return res;
    }

    /**
     * 修改商品状态
     * @param idList 商品id列表
     * @param state 状态
     * @return JSONObject
     */
    @Override
    public JSONObject updateCommState(JSONArray idList, String state) {
        JSONObject res=new JSONObject();
        for(int i=0;i<idList.size();i++){
            int id=idList.getInteger(i);
            Commodity commodity=commodityMapper.getCommById(id);
            commodity.setState(state);
            commodityMapper.updateCommodity(commodity);
        }
        res.put("code",200);
        res.put("msg","成功");
        return res;
    }

    /**
     * 获取店铺菜单
     * @param id 店铺id
     * @return JSONObject
     */
    @Override
    public JSONObject getStoreMenu(int id) {
        JSONObject res=new JSONObject();
        List<Commodity> commodityList=commodityMapper.getStoreMenu(id);
        res.put("code",200);
        res.put("msg","成功");
        JSONObject data=new JSONObject();
        data.put("commodity",toJson(commodityList));
        res.put("data",data);
        return res;
    }

    /**
     * 商品上架
     * @param cid 商品id
     * @param sid 店铺id
     * @return JSONObject
     */
    @Override
    public JSONObject commOnsale(int cid, int sid) {
        JSONObject res=new JSONObject();
        int flag=commodityMapper.onsale(cid,sid);
        if(flag==0){
            res.put("code",500);
            res.put("msg","失败");
            return res;
        }
        res.put("code",200);
        res.put("msg","成功");
        return res;
    }

    /**
     * 商品下架
     * @param cid 商品id
     * @param sid 店铺id
     * @return JSONObject
     */
    @Override
    public JSONObject commOffsale(int cid, int sid) {
        JSONObject res=new JSONObject();
        int flag=commodityMapper.offsale(cid,sid);
        if(flag==0){
            res.put("code",500);
            res.put("msg","失败");
            return res;
        }
        res.put("code",200);
        res.put("msg","成功");
        return res;
    }

    @Override
    public JSONObject commOnsaleList(int sid, JSONArray cidList) {
        JSONObject res=new JSONObject();
        for(int i=0;i<cidList.size();i++){
            int cid=cidList.getInteger(i);
            commodityMapper.onsale(cid,sid);
        }
        res.put("code",200);
        res.put("msg","成功");
        return res;
    }

    @Override
    public JSONObject commOffsaleList(int sid, JSONArray cidList) {
        JSONObject res=new JSONObject();
        for(int i=0;i<cidList.size();i++){
            int cid=cidList.getInteger(i);
            commodityMapper.offsale(cid,sid);
        }
        res.put("code",200);
        res.put("msg","成功");
        return res;
    }

    @Override
    public JSONObject getMenuByStoreId(int id) {
        JSONObject res=new JSONObject();
        //获取所有品类
        List<Category> categoryList=categoryMapper.getAllCategory();
        //循环品类
        JSONArray categoryArray=new JSONArray();
        for (Category category : categoryList) {
            JSONObject categoryJson = new JSONObject();
            categoryJson.put("class", category.getName());
            //获取品类下的商品
            List<Commodity> commodityList = commodityMapper.getMenuByCateId(id,category.getId());
            //将商品列表转换为json
            JSONArray commodityArray = toJson(commodityList);
            categoryJson.put("commodity", commodityArray);
            categoryArray.add(categoryJson);
        }
        res.put("code",200);
        res.put("msg","成功");
        JSONObject data=new JSONObject();
        data.put("menu",categoryArray);
        res.put("data",data);
        return res;
    }
}