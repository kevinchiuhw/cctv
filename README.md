## Docker

# 1. 登入 Docker Hub（如果還沒登入）

docker login

# 2. 建立 Docker 映像檔（記得先切換到前端專案目錄）

docker build -t kevinchiuhw/cctv:latest .

# 3. 推送映像檔到 Docker Hub

docker push kevinchiuhw/cctv:latest

## Scoop Windows (java 多版本切換 24 -> 8)
scoop reset temurin8-jdk

scoop reset temurin24-jdk


## Scoop 安裝
irm get.scoop.sh | iex
scoop help

scoop install git
scoop bucket add extras
scoop bucket add java
scoop bucket add versions