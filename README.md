# Recipe App

A Recipe App that offers exciting and delicious recipe to foodies!

## Overview
The project is designed by following the MVVM architecture. This project is structured to ensure scalability, maintainability, and separation of concerns.

📂 project-root<br>
├── 📂 build-logic        # Build script module to be shared across project<br>
├── 📂 app                # Main application module<br>
├── 📂 core               # Core module<br>
├── ├── 📂 common         # Common utilities and helpers<br>
├── ├── 📂 design-system  # UI components and theme<br>
├── ├── 📂 data           # Repository and data sources<br>
├── ├── 📂 model          # Models to be consumed  in Ui<br>
├── ├── 📂 network        # API and network layer<br>
├── 📂 features           # Feature module<br>
├── ├── 📂 recipe         # Recipe Feature<br>
├── 📂 domain             # Domain of the project<br>
├── 📄 settings.gradle.kts<br>
└── 📄 build.gradle.kts<br>

## Modules Description
- Build-Logic Module: Centralized Gradle Build script module that contains build scripts to be shared among modules.
- App Module: The main application that brings all modules together.
- Core Module: Contains shared components and logic used across feature modules.
- common: Shared utilities, extensions, and constants.
- design-system: Shared UI components and theming.
- data: Repository layer handling business logic and data sources.
- network: API services and networking configurations.
- Feature Modules: Independent features that interact with core modules.
- Domain Module: Domain of the project.

## 🛠️ Tech Stack
- Programming Language: Kotlin
- UI Framework: Jetpack Compose
- Image Library: Coil
- Architecture: MVVM
- Navigation: Jetpack Navigation
- Dependency Injection: Hilt
- Asynchronous Programming: Coroutines
- Networking: Retrofit, OkHttp
- State Management: StateFlow, SharedFlow
- Testing: JUnit, Mockito, Roboelectric
- CI: GitHub Actions, CI will be triggered on main branch is pushed.

## Setup & Installation

1. Clone the repository:
```
git clone git@github.com:winphyoethu/coles_test.git
cd your-repo
```
2. Open the project in Android Studio.
3. Sync Gradle files and build the project.
4. Run the application on an emulator or device.

## Screenshots
Portrait<br/>
<img src="https://github.com/winphyoethu/coles_test/blob/main/screenshots/portrait.jpg?raw=true" width="200" alt="portrait"/><img src="https://github.com/winphyoethu/coles_test/blob/main/screenshots/portrait_detail.jpg?raw=true" width="200" alt="portrait_detail"/><br/>
Landscape<br/>
<img src="https://github.com/winphyoethu/coles_test/blob/main/screenshots/landscape.jpg?raw=true" width="500" alt="landscape"/><img src="https://github.com/winphyoethu/coles_test/blob/main/screenshots/landscape_detail.jpg?raw=true" width="500" alt="landscape_detail"/>