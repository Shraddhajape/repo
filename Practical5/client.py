import requests

def consume_service():
    url = "http://localhost:5000/api/greet"
    params = {"name": "Distributed Application"}
    response = requests.get(url, params=params)

    if response.status_code == 200:
        print(f"Response from the web service: {response.json()}")
    else:
        print(f"Failed to consume service: {response.status_code}")

if __name__ == "__main__":
    consume_service()