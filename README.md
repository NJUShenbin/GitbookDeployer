# GitbookDeployer

gitbook部署服务器,提供一个webhook用来部署gitbook

##依赖
* linux
* java8
* git
* gitbook
* npm

## 使用
1. 下载release文件夹,运行start.sh
2. 访问http://yourhost:8080,若出现"hello,gitbook deployer!",则部署成功.
2. 向一个github项目添加webhook,url为http://yourhost:8080/deploy,数据格式为json.
2. 触发一次提交,十几秒后可以在http://yourhost:8080/your_repo_name访问到gitbook.
