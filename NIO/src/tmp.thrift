// 定义thrift服务, 实现跨语言服务调用

namespace go com.huangfugui.rpc
namespace java com.huangfugui.rpc

struct Response {
    1: i32 code
    2: string msg
}

service UserService {
    /*
    登录服务
    请求参数：1：客户端请求时间，2：客户端信息，3：用户名、密码等参数
    响应返回：Response对象，是否登陆成功等信息
     */
    Response login(1: i64 callTime, 2: string cliInfo, 3: map<string, string> paramMap)
}

service UtilService {
    /*
    素数服务
    请求参数：1：客户端请求时间，2：客户端信息，3：阈值参数
    响应返回：在阈值范围内的所有素数列表
     */
    list<i32> primeNumber(1: i64 callTime, 2: string cliInfo, 3: i32 threshold)
}



