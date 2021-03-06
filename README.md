# java-sm-spider

使用Java编写的简单爬虫，临时编写，用于爬取视频。只依赖于java虚拟机运行，无需使用数据库。

## 说明 
>只可以爬取资源路径对应的完整资源，如整个htm文档，整个视频，整个图片。无法对数据进行智能分类，无法对超链接进行连续爬取。
>
>唯一爬取策略是指定路径名，其中设置一串可变字段，此字段必须为数字（前缀可补零至指定长度），设置step自动增长进行爬取。\
>唯一保存策略是保存至服务所在主机，可设置保存文件夹（如不存在自动创建），文件名，及日志所在路径。\
>推荐将日志保存在与爬取文件所在的同一文件夹下，因为日志记录的文件名都为相对路径。
>
>日志内容如下
>```
>file 'file1'
>file 'file2'
>file 'file3'
>```
>此日志可直接用于ffmpeg合成视频
## 使用
>本工具由JavaSpringBoot开发，内置了web服务，您可将源代码build，生成jar包，放置在有java环境的机器上运行。
>
>将jdk/bin目录路径添加至全局变量后，您可以在jar包所在目录下运行以下命令
>```
>java -jar 包名.jar
>```
>服务将启动，并监听系统的48861端口，您可以通过浏览器访问localhost:48861，使用时请勿将命令窗口关闭。
>
>服务除下载文件及产生指定日志外，不会占用任何磁盘空间，不会产生任何垃圾文件。
## Help
>此工具编写时只为爬取分段视频，配合ffmpeg使用效果更佳。
>如还不会使用，联系我的QQ2598055586；\
>或送信至<i@4261.ink>
