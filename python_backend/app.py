from flask import Flask, request, jsonify
import os, uuid

app=Flask(__name__)
sessions={}

@app.get("/health")
def health():
    return jsonify({"status":"ok","service":"proj3-python-backend"})

@app.post("/chat")
def chat():
    data=request.get_json(silent=True) or request.form.to_dict()
    message=str(data.get("message","")).strip()
    sid=str(data.get("session_id","")) or str(uuid.uuid4())
    if not message:
        return jsonify({"response":"","session_id":sid,"error":"message is required"}),400
    history=sessions.setdefault(sid,[])
    previous=history[-1] if history else None
    if message.lower() in {"hi","hello","hey"}:
        reply="Hello! The Python backend is connected successfully."
    elif previous:
        reply=f"You said: “{message}”. I remember your previous message: “{previous}”."
    else:
        reply=f"You said: “{message}”. The Python Flask backend received it successfully."
    history.extend([message,reply])
    sessions[sid]=history[-12:]
    return jsonify({"response":reply,"session_id":sid,"error":None})

if __name__=="__main__":
    app.run(host=os.getenv("HOST","0.0.0.0"),port=int(os.getenv("PORT","5000")))
