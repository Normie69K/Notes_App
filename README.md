````markdown
# 📡 Notes Bluetooth Scanner

An Android app that scans nearby Bluetooth devices and sends the discovered list to a backend server.

---

## 🚀 Features
- Enable/disable Bluetooth  
- Start and stop device scanning  
- Display current scan status  
- Send discovered devices to backend using Retrofit  

---

## 🛠 Tech Stack
- **Language:** Kotlin  
- **UI:** ConstraintLayout  
- **Networking:** Retrofit + Gson  
- **Bluetooth:** Android Bluetooth API  

---

## ⚙️ Setup & Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/Normie69K/Notes_App.git
   cd Notes_App
````

2. Open project in **Android Studio**
3. Update backend API URL in `Constants.kt`:

   ```kotlin
   object Constants {
       const val BASE_URL = "http://your-server-ip:port/"
   }
   ```
4. Connect a real Android device (Bluetooth required)
5. Build & run the project

---

## 🔑 Required Permissions

Add these to your `AndroidManifest.xml`:

```xml
<uses-permission android:name="android.permission.BLUETOOTH"/>
<uses-permission android:name="android.permission.BLUETOOTH_ADMIN"/>
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>

<!-- For Android 12+ -->
<uses-permission android:name="android.permission.BLUETOOTH_SCAN" android:usesPermissionFlags="neverForLocation"/>
<uses-permission android:name="android.permission.BLUETOOTH_CONNECT"/>
```

