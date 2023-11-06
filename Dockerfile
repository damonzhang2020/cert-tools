# 使用基础镜像
FROM alpine:latest

# 安装 zlint 依赖
RUN apk add --update --no-cache git go
RUN go get -u github.com/zmap/zlint

# 设置工作目录
WORKDIR /app

# 将 zlint 配置文件复制到容器中
#COPY zlint-config.toml /app/zlint-config.toml

# 执行 zlint 命令
ENTRYPOINT ["zlint"]
