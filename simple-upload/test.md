### 一. 简单文件上传微服务，并加入 zuul 微服务后用 zuul 微服务地址采取curl或者页面点击实现文件上传（页面上传文件）：
 1. 编写 FileUploadController 文件，添加应用程序的注解 EnableEurekaClient 配置；
 2. 启动 discovery-eureka 模块服务，启动1个端口；
 3. 启动 simple-upload 模块服务；
 4. 新起网页页签，输入 http://localhost:8190/index.html 正常情况下是能看到选择文件上传的界面；
 5. 选择文件，然后点击 upload 上传文件，然后可以在该项目所在的根目录可以看到刚刚上传的那个文件，而且网页也会将刚刚上传完后的磁盘路径呈现在页面上；
### 二. 简单文件上传微服务，并加入 zuul 微服务后用 zuul 微服务地址采取curl或者页面点击实现文件上传（命令上传文件）：
 1. 编写 FileUploadController 文件，添加应用程序的注解 EnableEurekaClient 配置；
 2. 启动 discovery-eureka 模块服务，启动1个端口；
 3. 启动 simple-upload 模块服务；
 4. 进入 curl.exe 所在的目录，尝试 curl.exe www.baidu.com 看看是否正常，正常情况下会打印百度首页的一堆信息；
 5. 执行命令：curl.exe -F GBK "file=@文件名称" localhost:8190/upload
 6. 正常情况下，第5步骤执行后，直接返回上传成功文件所在的磁盘全路径；