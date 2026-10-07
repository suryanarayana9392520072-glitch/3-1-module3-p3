# 3-1 Agentic SDC Mod 3 Proj 3

Complete Android Studio Kotlin/XML project with Python Flask backend.

## Run Python
Open `python_backend` and run `run_windows.bat`, or:
```text
python -m venv .venv
.venv\Scripts\activate
pip install -r requirements.txt
python app.py
```

Backend: `http://127.0.0.1:5000`
Health: `http://127.0.0.1:5000/health`

## Android
Open this project in Android Studio and Sync Gradle.

The emulator connects to `http://10.0.2.2:5000/`.

For a real phone, change `BASE_URL` in
`app/src/main/java/project/handson3/network/RetrofitClient.kt`
to your PC's local IP, e.g. `http://192.168.1.10:5000/`.

Included:
- src/main/java
- XML layouts
- values
- values-night
- xml
- drawable/resource folders
- AndroidManifest.xml
- Gradle configuration
- Python backend
- requirements.txt
