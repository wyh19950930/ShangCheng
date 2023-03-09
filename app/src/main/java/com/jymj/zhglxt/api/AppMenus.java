package com.jymj.zhglxt.api;

import android.app.Activity;

import com.google.gson.Gson;
import com.jymj.zhglxt.login.bean.MenuBean;
import com.jymj.zhglxt.login.bean.SysRoleEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * App内存缓存
 */
public class AppMenus {
    private volatile static AppMenus instance;
    private String textCenter;//测试时使用的中共点
    private List<MenuBean> menuBeans;//MenuBean   菜单  尽量不要让菜单重复
    private List<MenuBean> menus;//MenuBean   菜单  尽量不要让菜单重复
    private List<SysRoleEntity> sysRoleEntities;//角色
    private String cardPath = "";

    private AppMenus() {
    }
    public static AppMenus getInstance() {
        if (null == instance) {
            synchronized (AppMenus.class) {
                if (instance == null) {
                    instance = new AppMenus();
                }
            }
        }
        return instance;
    }

    public String getMenuBeans() {//List<MenuBean>
        if (menuBeans==null){
            menuBeans = new ArrayList<MenuBean>();
        }
        return new Gson().toJson(menuBeans);
    }
    public List<MenuBean> getMenuBeans1() {//
        if (menuBeans==null){
            menuBeans = new ArrayList<MenuBean>();
        }
        return menuBeans;
    }

    public void setMenuBeans(List<MenuBean> menuBeans) {
        this.menuBeans = menuBeans;
    }

    public List<MenuBean> getMenus() {
        if (menus==null){
            menus = new ArrayList<MenuBean>();
        }
        return menus;
    }

    public void setMenus(List<MenuBean> menus) {
        this.menus = menus;
    }

    public List<SysRoleEntity> getSysRoleEntities() {
        if (sysRoleEntities==null){
            sysRoleEntities = new ArrayList<SysRoleEntity>();
        }
        return sysRoleEntities;
    }

    public void setSysRoleEntities(List<SysRoleEntity> sysRoleEntities) {
        this.sysRoleEntities = sysRoleEntities;
    }

    public String getTextCenter() {
        return textCenter==null?"":textCenter;
    }

    public void setTextCenter(String textCenter) {
        this.textCenter = textCenter;
    }

    public String getCardPath() {
        return cardPath==null?"":cardPath;
    }

    public void setCardPath(String cardPath) {
        this.cardPath = cardPath;
    }
}
