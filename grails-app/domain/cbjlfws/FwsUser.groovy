package cbjlfws

class FwsUser {
    //张晶
    //用户名
    String username
    //密码
    String password
    //真实姓名
    String name
    //电话
    String phone
    //职位
    String position
    //录入时间
    Date time
    static hasOne = [fwsShop:FwsShop]
    static constraints = {
    }
}
