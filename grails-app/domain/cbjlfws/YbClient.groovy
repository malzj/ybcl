package cbjlfws

class YbClient {
    //张晶
    //用户名
    String username
    //密码
    String password
    //真实姓名
    String name
    //手机号
    String phone
    //店数
    Integer number
    //开始时间
    Date beginTime
    //结束时间
    Date overTime
    static  hasMany = [fwsShop:FwsShop]
    static constraints = {
    }
}
