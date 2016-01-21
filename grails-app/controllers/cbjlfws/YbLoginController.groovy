package cbjlfws

class YbLoginController {

    def index() {

            }
    def login(){
        def username = params.username
        def password = params.password
        def ybUser = YbUser.findByUsernameAndPassword(username,password)
        if (ybUser){
            redirect(action:"ybUserList")
        }else (
            redirect(action: "index",msg:"您输入账号有误")
        )
    }
    def ybUserList(Integer max){
        params.max = Math.min(max ?: 10, 100)
        [ybUserInstanceList: YbUser.list(params), ybUserInstanceTotal: YbUser.count()]
    }
    def ybGongNengList(Integer max){
        params.max = Math.min(max ?: 10, 100)
        [ybGongNengInstanceList: YbGongNeng.list(params), ybGongNengInstanceTotal: YbGongNeng.count()]
    }
}
