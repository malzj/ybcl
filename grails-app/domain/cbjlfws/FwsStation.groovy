package cbjlfws

class FwsStation {
    //张晶
    //服务商工位
    String stationName
    //服务商工位个数
    Integer stationNumber
    //服务商可用工位个数
    Integer Number
    //录入时间
    Date     time
    static    hasOne = [fwsShop:FwsShop]
    static constraints = {
    }
}
