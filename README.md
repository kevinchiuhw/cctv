## Docker

# 1. 登入 Docker Hub（如果還沒登入）

docker login

# 2. 建立 Docker 映像檔（記得先切換到前端專案目錄）

docker build -t kevinchiuhw/cctv:latest .

# 3. 推送映像檔到 Docker Hub

docker push kevinchiuhw/cctv:latest
