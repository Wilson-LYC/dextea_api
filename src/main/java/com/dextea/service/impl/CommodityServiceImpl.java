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
        }else {
            res.put("category",new JSONArray());
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
     * 获取所有商品v1
     * @return JSONObject
     */
    @Override
    public JSONObject getAllCommodityV1() {
        JSONObject res=new JSONObject();
        try {
            List<Commodity> commodityList=commodityMapper.getAllCommodity();
            res.put("code",200);
            res.put("msg","成功");
            JSONObject data=new JSONObject();
            data.put("commodity",toJson(commodityList));
            res.put("data",data);
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","数据库异常");
        }
        return res;
    }

    /**
     * 搜索商品v1
     * @return
     */
    @Override
    public JSONObject searchCommV1(JSONObject json) {
        JSONObject res=new JSONObject();
        int id=json.getInteger("id")==null?0:json.getInteger("id");
        String name=json.getString("name")==null?"":json.getString("name");
        String state=json.getString("state")==null?"":json.getString("state");
        int cateId=json.getInteger("cateId")==null?0:json.getInteger("cateId");
        try {
            List<Commodity> commodityList=commodityMapper.searchComm(id,name,state,cateId);
            res.put("code",200);
            res.put("msg","成功");
            JSONObject data=new JSONObject();
            data.put("commodity",toJson(commodityList));
            res.put("data",data);
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","数据库异常");
            System.out.println(e.toString());
        }
        return res;
    }

    /**
     * 删除商品v1
     * @param id 商品id
     * @return JSONObject
     */
    @Override
    public JSONObject deleteCommV1(int id) {
        JSONObject res=new JSONObject();
        try {
            //先删除商品的品类
            commCateMapper.deleteCateByCommId(id);
            //再删除商品
            int flag=commodityMapper.deleteComm(id);
            if(flag==0){
                res.put("code",500);
                res.put("msg","删除失败");
                return res;
            }
            res.put("code",200);
            res.put("msg","删除成功");
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","数据库异常");
        }
        return res;
    }

    /**
     * 修改商品信息v1
     * @param json 商品信息
     * @return JSONObject
     */
    @Override
    public JSONObject updateCommodityV1(JSONObject json) {
        JSONObject res=new JSONObject();
        Commodity commodity=new Commodity();
        commodity.setId(json.getInteger("id"));
        commodity.setName(json.getString("name"));
        commodity.setPrice(json.getDouble("price"));
        commodity.setState(json.getString("state"));
        if(json.getString("introduce")!=null && !json.getString("introduce").isEmpty())
            commodity.setIntroduce(json.getString("introduce"));
        if(json.getString("briefIntro")!=null && !json.getString("briefIntro").isEmpty())
            commodity.setBriefIntro(json.getString("briefIntro"));
        if(json.getString("custom")!=null && !json.getString("custom").isEmpty())
            commodity.setCustom(json.getString("custom"));
        if(json.getString("img")!=null && !json.getString("img").isEmpty())
            commodity.setImg(json.getString("img"));
        JSONArray categoryArray=json.getJSONArray("category");
        try {
            int flag=commodityMapper.updateCommodity(commodity);
            if(flag==0){
                res.put("code",500);
                res.put("msg","更新失败");
                return res;
            }
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","数据库异常");
        }
        //删除原有品类
        try {
            commCateMapper.deleteCateByCommId(commodity.getId());
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","数据库异常");
        }
        try{
            //添加新品类
            for(int i=0;i<categoryArray.size();i++){
                String cateName=categoryArray.getString(i);
                Category category=categoryMapper.getCateByName(cateName);
                CommCate commCate=new CommCate();
                commCate.setCommId(commodity.getId());
                commCate.setCateId(category.getId());
                commCateMapper.addCateToComm(commCate);
            }
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","数据库异常");
        }
        res.put("code",200);
        res.put("msg","更新成功");
        return res;
    }

    /**
     * 修改销售状态v1
     * @param json 商品id列表
     * @return JSONObject
     */
    @Override
    public JSONObject updateCommStateV1(JSONObject json) {
        JSONObject res=new JSONObject();
        try {
            JSONArray idList=json.getJSONArray("id");
            String state=json.getString("state");
            for(int i=0;i<idList.size();i++){
                int id=idList.getInteger(i);
                Commodity commodity=commodityMapper.getCommById(id);
                commodity.setState(state);
                commodityMapper.updateCommodity(commodity);
            }
            res.put("code",200);
            res.put("msg","修改成功");
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","数据库异常");
        }
        return res;
    }

    /**
     * 新增商品v1
     * @param json 商品信息
     * @return JSONObject
     */
    @Override
    public JSONObject addCommodityV1(JSONObject json) {
        JSONObject res=new JSONObject();
        Commodity commodity=new Commodity();
        commodity.setName(json.getString("name"));
        commodity.setPrice(json.getDouble("price"));
        commodity.setState(json.getString("state"));
        JSONArray customArray=json.getJSONArray("custom");
        commodity.setCustom(customArray.toJSONString());
        try {
            int flag=commodityMapper.addCommodity(commodity);
            if(flag==0){
                res.put("code",500);
                res.put("msg","新增失败");
                return res;
            }
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","数据库异常");
        }
        res.put("code",200);
        res.put("msg","新增成功");
        return res;
    }

    /**
     * 获取同一品类的商品v1
     * @param id 品类id
     * @return JSONObject
     */
    @Override
    public JSONObject getCommByCateIdV1(int id) {
        JSONObject res=new JSONObject();
        try {
            List<Commodity> commodityList=commodityMapper.getCommByCateId(id);
            res.put("code",200);
            res.put("msg","成功");
            JSONObject data=new JSONObject();
            data.put("commodity",toJson(commodityList));
            res.put("data",data);
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","数据库异常");
        }
        return res;
    }

    /**
     * 获取门店菜单v1
     * @param id 店铺id
     * @return JSONObject
     */
    @Override
    public JSONObject getMenuByStoreIdV1(int id) {
        JSONObject res=new JSONObject();
        List<Commodity> commodityList=null;
        try {
            commodityList=commodityMapper.getStoreMenu(id);
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","数据库异常");
        }
        res.put("code",200);
        res.put("msg","成功");
        JSONObject data=new JSONObject();
        data.put("commodity",toJson(commodityList));
        res.put("data",data);
        return res;
    }

    /**
     * 上架商品v1
     * @param data json
     * @return JSONObject
     */
    @Override
    public JSONObject commOnsaleV1(JSONObject data) {
        JSONObject res=new JSONObject();
        JSONArray cidList=data.getJSONArray("cid");
        int sid=data.getInteger("sid");
        try {
            for(int i=0;i<cidList.size();i++){
                int cid=cidList.getInteger(i);
                commodityMapper.onsale(cid,sid);
            }
            res.put("code",200);
            res.put("msg","成功");
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","数据库异常");
        }
        return res;
    }

    /**
     * 下架商品v1
     * @param data json
     * @return JSONObject
     */
    @Override
    public JSONObject commOffsaleV1(JSONObject data) {
        JSONObject res=new JSONObject();
        JSONArray cidList=data.getJSONArray("cid");
        int sid=data.getInteger("sid");
        try {
            for(int i=0;i<cidList.size();i++){
                int cid=cidList.getInteger(i);
                commodityMapper.offsale(cid,sid);
            }
            res.put("code",200);
            res.put("msg","成功");
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","数据库异常");
        }
        return res;
    }

    /**
     * 获取单个商品信息v1
     * @param id 商品id
     * @return JSONObject
     */
    @Override
    public JSONObject getCommByIdV1(int id) {
        JSONObject res=new JSONObject();
        Commodity commodity=null;
        try {
            commodity=commodityMapper.getCommById(id);
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","数据库异常");
        }
        res.put("code",200);
        res.put("msg","成功");
        JSONObject data=new JSONObject();
        data.put("commodity",toJson(commodity));
        res.put("data",data);
        return res;
    }

    /**
     * 小程序获取菜单
     * @param id
     * @return
     */
    @Override
    public JSONObject getMenuByStoreIdForCustomer(int id) {
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