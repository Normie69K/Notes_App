# 📝 Notes App (Kotlin)

A simple **Notes App** built with **Kotlin** for Android.
This app allows users to **create, view, edit, and delete notes**, with all notes stored locally on the device. It follows **MVVM architecture** with Room Database for persistence.

---

## 🚀 Features

* ➕ Add new notes with title & description
* ✏️ Edit existing notes
* 🗑️ Delete notes
* 📋 View all notes in a **RecyclerView**
* 💾 Local storage using **Room Database**
* 🎨 Clean UI with **Material Design**
* 🔄 Lifecycle-aware with **LiveData** and **ViewModel**

---

## 🛠 Tech Stack

* **Language:** Kotlin
* **Architecture:** MVVM (Model–View–ViewModel)
* **Database:** Room (SQLite)
* **UI:** RecyclerView, CardView, Material Components
* **Tools:** Android Studio, Git

---

## 📸 Screenshots *(optional later)*

*(Add app screenshots here once UI is ready)*

---

## 📂 Project Structure

```
com.example.notesapp
│
├── data
│   ├── Note.kt
│   ├── NoteDao.kt
│   ├── NoteDatabase.kt
│
├── repository
│   └── NoteRepository.kt
│
├── ui
│   ├── MainActivity.kt
│   ├── AddEditNoteActivity.kt
│   ├── NoteAdapter.kt
│   └── viewmodel
│       └── NoteViewModel.kt
│
└── utils
```

---

## ⚙️ Setup & Installation

1. Clone this repository:

   ```bash
   git clone https://github.com/Normie69K/Notes_App.git
   ```
2. Open in **Android Studio**.
3. Sync Gradle & run on an emulator/device.

---

## 📖 Learning Outcomes

* Android **Activity lifecycle**
* **Room Database** & CRUD operations
* **RecyclerView** with custom adapters
* MVVM architecture with **LiveData & ViewModel**
* Building Android apps with **Kotlin**

---

## 📜 License

This project is open-source. Feel free to use and improve it!

