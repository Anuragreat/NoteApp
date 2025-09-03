# 📝 NoteApp Backend

Backend service for **NoteApp**, a secure and minimal note-taking application.
Built with **Spring Boot**, it provides authentication, OTP-based login, and note management APIs.

🔗 **Frontend Live:** [note-front-tau.vercel.app](https://note-front-tau.vercel.app)
🔗 **Backend Live:** [noteapp-0eu4.onrender.com](https://noteapp-0eu4.onrender.com)

---

## 🚀 Features

* 🔐 **User Authentication** with OTP + JWT
* 📨 **Send OTP** to email for login
* 🗂 **CRUD operations** on notes (Add, View, Delete)
* 👤 **User-specific notes** linked via JWT tokens
* 📡 RESTful APIs ready to integrate with any frontend

---

## 📦 Tech Stack

* **Java 17+**
* **Spring Boot** (Web, Security, Data JPA)
* **JWT Authentication**
* **MongoDB** (for storing users & notes)
* **Lombok** (to reduce boilerplate code)

---

## 📑 API Endpoints

### 🔐 Authentication

| Method | Endpoint                                 | Description                  | Body / Params                                     |
| ------ | ---------------------------------------- | ---------------------------- | ------------------------------------------------- |
| `POST` | `/api/auth/signup`                       | Register new user            | `{ "email": "user@mail.com", "password": "..." }` |
| `POST` | `/api/auth/send-otp?email=user@mail.com` | Send OTP to user email       | Query param `email`                               |
| `POST` | `/api/auth/login`                        | Login with OTP → returns JWT | `{ "email": "user@mail.com", "otp": "1234" }`     |

### 📝 Notes

| Method   | Endpoint                  | Description                    | Headers                       | Body                                             |
| -------- | ------------------------- | ------------------------------ | ----------------------------- | ------------------------------------------------ |
| `POST`   | `/api/notes/add`          | Add a new note (auth required) | `Authorization: Bearer <JWT>` | `{ "title": "My Note", "content": "Note text" }` |
| `GET`    | `/api/notes/all/{userId}` | Get all notes for a user       | —                             | —                                                |
| `DELETE` | `/api/notes/delete/{id}`  | Delete a note by ID            | —                             | —                                                |

---

## ⚙️ Setup & Run Locally

1. **Clone repo**

   ```bash
   git clone https://github.com/Anuragreat/noteapp-backend.git
   cd noteapp-backend
   ```

2. **Configure Database** (MongoDB) in `application.properties`:

   ```properties
   spring.data.mongodb.uri=mongodb+srv://<username>:<password>@cluster.mongodb.net/notesapp
   ```

3. **Run the backend**

   ```bash
   ./mvnw spring-boot:run
   ```

4. API will be live at:

   ```
   http://localhost:8080
   ```

---

## 🌟 Future Enhancements

* 📌 Note categories & tags
* ☁️ Cloud file uploads with notes
* 📧 Resend OTP & expiry handling

---

## 👨‍💻 Author

**Anurag Yadav**
* 📧 [ay3898097@gmail.com](mailto:ay3898097@gmail.com)
---
