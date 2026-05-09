# Mini Grocery Delivery App (Blinkit Style)

A simple and efficient grocery delivery application built using Kotlin and modern Android development practices. This project was completed as part of the OceanX Agency Android Assignment.

## 🚀 Features
- **Login / OTP**: Secure-looking login flow with fake OTP verification (Use `1234`).
- **Home Screen**: 
    - Category-based product browsing.
    - Live Search/Filter functionality.
    - Grid layout for products.
- **Cart Management**: 
    - Real-time cart updates using shared ViewModel.
    - Increase/Decrease quantity or remove items.
    - Live bill summary calculation.
- **Checkout**: 
    - Address input validation.
    - Payment mode selection (COD/Online).
- **Order Success**: Order ID generation and delivery estimation.

## 🛠 Tech Stack
- **Language**: Kotlin
- **Architecture**: MVVM (Model-View-ViewModel)
- **UI**: XML with ViewBinding and Material Design components.
- **Navigation**: Jetpack Navigation Component.
- **Data Handling**: LiveData & ViewModel (State Management).
- **Image Loading**: Glide.

## 🏃 How to Run
1. Clone this repository:
   ```bash
   git clone <your-repo-link>
   ```
2. Open the project in **Android Studio (Ladybug or newer)**.
3. Let Gradle sync complete.
4. Clean and Build the project.
5. Run on an Emulator or Physical Device (API 24+).
6. **Note**: Use OTP `1234` to bypass the login screen.

## 📦 Submission Requirements
- [x] Kotlin Only
- [x] MVVM Architecture
- [x] XML Layouts
- [x] Search/Filter Functionality
- [x] Proper Code Structure
