Commit failed with error
			0 file committed, 10 files failed to commit: 接口参数
			> running pre-commit hook: npm run precommit
			

解决方法：git commit --no-verify -m "commit"     忽略检测，强制提交 


git init   
git remote add origin git地址   报错的话先git init  
git add .
git commit -m 'first commit'
git push -u origin  分支名称，例如master

 git remote -v   查看远程仓库列表
git remote rm   删除远程仓库
git branch -a   查看所有分支
git push -u origin 分支名称  向分支推送代码
git branch 分支名称  创建分支
git  checkout  分支名 切换分支
git checkout -b 分支名称   创建并且切换分支

git pull --rebase origin master   命令行拉取代码

类型检查： eslint  src/pages/Type/**

             2:查看状态                 git status     
             3:将文件提交到暂存区       git add a1.txt  执行后也可以查看状态 git status
             4:将文件由暂存区提交到仓库 git commit -m 'add a1.txt'  引号中的为描述
             5:查看是否提交成功         git status  
             
        4;修改仓库文件
             1:查看文件                  ls
             2;查看状态                  git status
             3:向a1.txt文件中添加        vi a1.txt    进入页面,可以添加
             4:查看文件内容              cat a1.txt   
             5:查看状态                  git status   可以看到被修改了
             6:可以进行后面的先add,再commit操作
  
        5:删除仓库文件
             1:删除本地文件             rm -rf a1.txt
             2:从git中删除文件          git rm a1.txt
             3:提交操作                 git commit -m '删除文件'

     
二:远端操作(对云端的操作)
        作用:实现备份和共享

    一般步骤:
      1:将云端代码复制到本地
          git config --list      验证配置信息
          git clone 项目网址     复制代码到本地
      2:对复制的代码进行编辑,然后add到暂存区,commit到本地仓库
      3:由本地仓库提交到云端    
          git push
             
三:搭建个人网站
  
     搭建步骤
       1:创建个人站点  --->新建仓库(注意:仓库名必须是[用户名.github.io])
       2;在仓库下新建index.html文件
   注意:GitHub pages仅支持静态网页,仓库里面只能是html文件
    

四:邀请成员参与代码开发
 需要发送邀请,对面也要接受请求才行
