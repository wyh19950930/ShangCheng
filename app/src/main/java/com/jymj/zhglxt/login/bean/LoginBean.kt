package com.jymj.zhglxt.login.bean

import java.io.Serializable

/**
 * Created by setsuna on 18-3-27.
 */
class LoginBean :Serializable{
    var msg: String ?= null
    var code: Int ?= null
    var user : User? =null
    var token :String ? =null
    override fun toString(): String {
        return "LoginBean(msg=$msg, code=$code, user=$user)"
    }


}