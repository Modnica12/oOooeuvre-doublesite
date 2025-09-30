### Полезные команды
- uvicorn main:app --reload (https://gist.github.com/ShilGen/abaeafe8b130ccd8d43edde4af8d6dce) – *Запуск бэкенда*
- sudo systemctl restart nginx – *рестарт nginx*
- git reset --hard origin/docker-for-backend – *сбросить ветку, когда сделали pull после форс пуша*

### Файлы
- /etc/nginx/sites-enabled/ – *файл default с конфигом для nginx*
- /home/ooooeuvre-boss/oOooeuvre-doublesite/ – *проект на машинке*


sudo rm -rf /var/www/dist
git pull
git reset --hard origin/upgrades-for-frontend
sudo mv /home/ooooeuvre-boss/oOooeuvre-doublesite/frontend/artifacts/dist/ /var/www/
sudo systemctl restart nginx

sudo python3 -m venv fastapi-env
source fastapi-env/bin/activate
pip install fastapi
pip install 'uvicorn[standard]'
pip install gunicorn
gunicorn -k uvicorn.workers.UvicornWorker -b 127.0.0.1:8000 -w 4 main:app


sudo nano /etc/systemd/system/fastapi.service

sudo systemctl daemon-reload
sudo systemctl start fastapi
sudo systemctl enable fastapi
