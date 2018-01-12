### 一. 简单文件上传微服务，并加入 zuul 微服务后用 zuul 微服务地址采取curl或者页面点击实现文件上传（加入 zuul 微服务，页面上传文件）：
 1. 启动 discovery-eureka 模块服务，启动1个端口；
 2. 启动 simple-upload 模块服务；
 3. 启动 gateway-zuul-upload 模块服务；
 4. 新起网页页签，输入 http://localhost:8190/index.html 正常情况下是能看到选择文件上传的界面；
 5. 新起网页页签，输入 http://localhost:8195/simple-upload/index.html 正常情况下是能看到选择文件上传的界面；
 6. 选择文件，然后点击 upload 上传文件，上传成功后，直接返回上传成功文件所在的磁盘全路径；
### 二. 简单文件上传微服务，并加入 zuul 微服务后用 zuul 微服务地址采取curl或者页面点击实现文件上传（加入 zuul 微服务，然后采用命令上传文件）：
 1. 启动 discovery-eureka 模块服务，启动1个端口；
 2. 启动 simple-upload 模块服务；
 3. 启动 gateway-zuul-upload 模块服务；
 4. 进入 curl.exe 所在的目录，尝试 curl.exe www.baidu.com 看看是否正常，正常情况下会打印百度首页的一堆信息；
 5. 执行命令：curl.exe -F "file=@文件名称" localhost:8195/simple-upload/upload
 6. 正常情况下，第6步骤执行后，直接返回上传成功文件所在的磁盘全路径；
### 三. 简单文件上传微服务，并加入 zuul 微服务后用 zuul 微服务地址采取curl或者页面点击实现文件上传文件上传微服务（加入 zuul 微服务，然后采用命令上传文件，传送大文件，文件大小大于 max-file-size 这个配置的属性值）：
 1. 启动 discovery-eureka 模块服务，启动1个端口；
 2. 启动 simple-upload 模块服务；
 3. 启动 gateway-zuul-upload 模块服务；
 4. 进入 curl.exe 所在的目录，尝试 curl.exe www.baidu.com 看看是否正常，正常情况下会打印百度首页的一堆信息；
 5. 执行命令：curl.exe -F "file=@文件名称大于50M" localhost:8195/simple-upload/upload
 6. 结果第6步骤执行后，报错提示文件太大，报错信息为：the request was rejected because its size (36611267) exceeds the configured maximum (10485760)
 7. 然而在zuul的集群中，可以饶过SpringMvc的检测，执行命令:curl.exe -F "file=@文件名称大于50M" localhost:8195/zuul/simple-upload/upload
 8. 结果第7步骤执行后，报错提示timeout超时，报错信息为：{"timestamp":1503932607944,"status":500,"error":"Internal Server Error","exception":"com.netflix.zuul.exception.ZuulException","message":"GENERAL"}；
 9. 发现采用了各种办法好像还是没有解决，难道是最近版本 SpringCloud 解决了这样的一个饶过SpringMvc请求访问的bug了么？？？