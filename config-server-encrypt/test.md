> keytool -genkeypair -alias mytestkey -keyalg RSA \
  -dname "CN=Web Server,OU=Unit,O=Organization,L=City,S=State,C=US" \
  -keypass changeme -keystore server.jks -storepass letmein
  
> ./keytool.exe -genkeypair -alias config-server -keyalg RSA -dname "CN=zhenhua, OU=kingnet, O=kdc, L=shanghai, ST=shanghai, C=china" -keypass 222222 -keystore config-server.keystore -storepass 111111

### 一. 配置服务端ClientServer对配置文件内容进行对称加解密（设置配置服务端文件对称加解密）：
 1. 注解：EnableConfigServer
 2. 编辑 application.yml 文件，注意填写 encrypt.key 属性字段值，该值的作用在于给配置文件的内容进行加密用的；
 3. 启动 config-server-encrypt 模块服务，启动1个端口；
 4. 下载 Java 8 JCE 文件，下载下来后文件名为 jce_policy-8.zip，然后将解压后的文件直接覆盖到 jdk 中，将 Java\jdk1.8.0_92\jre\lib\security 路径下的文件覆盖即可；
 5. 打开windows命令窗口，执行命令：
    >curl.exe localhost:8255/encrypt -d foobar-prd
    91e9d2e319f18d32de4821a5e932c759f93e0007dc66e69e0b9587e595e0f241
    
    >curl.exe localhost:8255/encrypt -d foobar-test
    3bf7b3e4adf9228d9fc70ecc168a33ff5269bc6efc66eaac8dde5c8e655303a0
    将这两个值进行保存到配置文件，也就是我们的Git仓库中的配置文件；
 6. 在浏览器输入地址 http://localhost:8255/foobar-default.yml 正常情况下会输出配置文件的内容（内容为：profile: profile-default）；
 7. 在浏览器输入地址 http://localhost:8255/foobar-dev.yml 正常情况下会输出配置文件的内容（内容为：profile: profile-dev）；
 8. 在浏览器输入地址 http://localhost:8255/foobar-prd.yml 正常情况下会输出配置文件的内容（内容为：profile: foobar-prd）；
 9. 在浏览器输入地址 http://localhost:8255/foobar-test.yml 正常情况下会输出配置文件的内容（内容为：profile: foobar-test）；
 10. 在浏览器输入地址 http://localhost:8255/foobar-test.properties 正常情况下会输出配置文件的内容（内容为：profile: foobar-test）；
     * 总结一：一切都正常打印，说明 SpringCloud 的解密已经能正确的完成了；
 11. 修改 application.yml 文件，将 encrypt.key 属性值随便改下，改成比如 encrypt.key: aaaaaaaaaaa
 12. 停止并重启 config-server-encrypt 模块服务，启动1个端口；
 13. 在浏览器输入地址 http://localhost:8255/foobar-default.yml 正常情况下会输出配置文件的内容（内容为：profile: profile-default）；
 14. 在浏览器输入地址 http://localhost:8255/foobar-dev.yml 正常情况下会输出配置文件的内容（内容为：profile: profile-dev）；
 15. 在浏览器输入地址 http://localhost:8255/foobar-prd.yml 不能正常获取配置文件内容（内容为：invalid: profile: <n/a> profile: profile-default）；
 16. 在浏览器输入地址 http://localhost:8255/foobar-test.yml 不能正常获取配置文件内容（内容为：invalid: profile: <n/a> profile: profile-default）；
 17. 在浏览器输入地址 http://localhost:8255/foobar-test.properties 不能正常获取配置文件内容（内容为：invalid: profile: <n/a> profile: profile-default）；
     * 总结二：由此可见 encrypt.key 经过赋值生成配置文件内容后，就不能轻易改变，一旦改变的话，那么原本正常的内容值将获取不到了；  