from fastapi import FastAPI
from datetime import datetime, timedelta

app = FastAPI()

@app.get("/")
def read_root():
    return {"test": "test"}

@app.get("/ekbtime")
def read_ekb_time():
    ekb_time = get_ekb_time()
    return {"hours": ekb_time.hour, "minutes": ekb_time.minute, "seconds": ekb_time.second}

def get_ekb_time():
    return datetime.now() + timedelta(hours = 5)