package com.jymj.zhglxt.login.bean

import java.io.Serializable

/**
 * Created by setsuna on 18-3-23.
 */
class User :Serializable{
    var username: String ?= null
    var upwd: String ?= null
    var deviceCode :String ? =null
    var userId:Long?=null
    var duties:Int=0//判断是否是领导  0:非  1:是
    var rz:Int=0//判断是否是领导  0:非  1:是
    var type:Int?=null
    var typeText:String?=null
    var code:String?=null
    var xzqmc:String?=null
    var name:String?=null
    var mobile:String?=null
    var roleName:String?=null
    var position:String?=null
    var center:String?=null
    var menus:List<MenuBean> = ArrayList<MenuBean>()
    var roles:List<SysRoleEntity> = ArrayList<SysRoleEntity>()

}