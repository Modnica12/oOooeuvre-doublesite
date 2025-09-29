from fastapi import FastAPI
from datetime import datetime, timedelta
import json

app = FastAPI()

@app.get("/")
def read_root():
    return {"test": "test"}

@app.get("/mainpage")
def read_main_page():
    ekb_time = get_ekb_time()
    with open('photos.json') as user_file:
      file_contents = user_file.read()

    parsed_json = json.loads(file_contents)

    photos = parsed_json["photos"]
    photos = list(map(map_photo, photos))

    return {
        "photos": photos,
        "clockTime": {
            "hours": ekb_time.hour,
            "minutes": ekb_time.minute,
            "seconds": ekb_time.second
        }
    }

@app.get("/ekbtime")
def read_ekb_time():
    ekb_time = get_ekb_time()
    return {"hours": ekb_time.hour, "minutes": ekb_time.minute, "seconds": ekb_time.second}

def get_ekb_time():
    return datetime.now() + timedelta(hours = 5)

def map_photo(photo):
    imageName = photo["imageName"]
    text = photo["text"]
    url = "https://storage.yandexcloud.net/ooooeuvre-polutions-images/" + imageName
    newText = stringToSymbols(text)
    return {"url": url, "text": newText}


def stringToSymbols(inputStr):
    symbols = [str(c) for c in inputStr]
    symbols = list(map(toBitsString, symbols))
    return "​".join(symbols)

def toBitsString(char):
    bits = "".join([f'{i:08b}' for i in char.encode()])
    symbols = list(map(bitToSymbol, [int(b) for b in bits]))
    return "".join([str(s) for s in symbols])

def bitToSymbol(bit):
    if bit == 0:
        return "○"
    elif bit == 1:
        return "●"
    else:
        return ""