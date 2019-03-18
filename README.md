# monitor-example
dubbo monitor by sentinel and zikpin

### zipkin
```bash
#ttps://search.maven.org/remote_content?g=io.zipkin.java&a=zipkin-server&v=LATEST&c=exec
title = zipkin
java -Dserver.port=5566 -jar zipkin-server-2.12.5-exec.jar --logging.level.zipkin2=DEBUG
```

### sentinel
```bash
#https://github.com/alibaba/Sentinel/releases
title = sentinel
set path=D:\developer\OpenJDK\openjdk\jdk-11.0.1\bin;%path%
java -Dserver.port=7788 -Dcsp.sentinel.dashboard.server=localhost:7788 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.5.0.jar &
```
