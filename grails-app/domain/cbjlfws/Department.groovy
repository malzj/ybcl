package cbjlfws

class Department {
    String name
    String linkMan
    String number
    static  hasOne = [fwsShop:FwsShop]
    static  hasMany = [fwsUser:FwsUser]
    static constraints = {
    }
}
