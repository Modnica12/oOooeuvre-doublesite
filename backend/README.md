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
uvicorn main:app --reload