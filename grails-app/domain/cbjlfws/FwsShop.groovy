package cbjlfws

class FwsShop {
    //张晶
    //服务商店名
    String name
    //服务商地址
    String addrass
    //服务商坐标
    String coordinate
    //服务商电话
    String phone
    //服务商店内联系人
    String linkMan
    //服务商店内联系电话
    String linkManPhone
   static  hasOne = [ybClient:YbClient]
    static  hasMany = [fwsStation:FwsStation,fwsUser:FwsUser,department:Department]
    static constraints = {
    }
}
