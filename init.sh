sudo apt update
sudo apt install openjdk-21-jdk
sudo apt install maven
sudo apt install python3.12-venv
python3.12 -m venv .venv
source .venv/bin/activate
pip install --upgrade mysql-connector-python