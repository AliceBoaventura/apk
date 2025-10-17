Lucky Money - demo project

Estrutura:

- backend/: Node.js + Express API (GET /api/envelopes, POST /api/open/:id, POST /api/reveal/:id)
- android/: Android app (Java) pronto para abrir no Android Studio

Backend instructions (local testing):
1) cd backend
2) npm install
3) npm start
API will run on http://localhost:3000

Android instructions:
1) Open the android/ folder in Android Studio
2) In LuckyMoneyReceiveUI.java change BASE_URL to your deployed API or to http://10.0.2.2:3000 for emulator
3) Build -> Run

Deploying backend online:
- Use Render, Railway, Heroku or similar
- After deploy, set BASE_URL in the Android app to https://your-domain.com

