import cbjlfws.YbUser
import org.h2.util.New

class BootStrap {

    def init = { servletContext ->
        //张晶
        //创建初始YB用户
        def ybUser = YbUser.findByUsername("ybadmin")
        if (!ybUser){
            ybUser = new YbUser()
            ybUser.username ="ybadmin"
            ybUser.password = "ybadmin"
            ybUser.name ="yb管理员"
            ybUser.phone = "18910337291"
            ybUser.position ="管理员"
            ybUser.time = new Date()
            if(ybUser.save(flush: true)){
                print(1)
            }else (
                 print(2)
            )

        }
    }




    def destroy = {

    }

}


