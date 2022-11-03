## 简介
> 
## Host 配置说明 
这边推荐使用 [SwitchHosts 进行管理](https://oldj.github.io/SwitchHosts/) ，具体使用方法请自行百度

如果是centos 环境，请编辑以下文件
```
vi /etc/hosts
```

添加如下内容 
```
127.0.0.1 mysql.pmall.com
127.0.0.1 zk.pmall.com
127.0.0.1 kafka.pmall.com
127.0.0.1 redis.pmall.com
127.0.0.1 mysql.pmall.com
```

其中 ,127.0.0.1 修改为对于应用的Ip地址即可

如果是Windows环境，请编辑以下文件
```
C:\Windows\System32\drivers\etc\hosts
```
将上述示例内容添加到hosts文件中即可
