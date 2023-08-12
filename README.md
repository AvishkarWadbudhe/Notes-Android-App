# Notes-Android-App with Firebase


This is a simple notes app built using Firebase for authentication and Firestore for online database storage. The app allows users to sign up, log in, create, update, delete, and search notes using a cloud-based Firebase Firestore database. Additionally, it includes email verification and password reset functionalities.

## Features

1. User Authentication
   - Sign up: Users can create a new account by providing their email address and password. An email verification link is sent to the user's registered email.
   - Log in: Existing users can log in using their email and password.
   - Email Verification: Users need to verify their email address before gaining full access to the app.
   - Forgot Password: Users can request a password reset email if they forget their password.

2. Note Management
   - Create Note: Authenticated users can create new notes by entering a title and content.
   - Update Note: Users can edit and update existing notes.
   - Delete Note: Users can delete unwanted notes.
   - Search Note: Users can search for specific notes based on title or content.

3. Firebase Integration
   - Firebase Authentication: User registration, login, email verification, and password reset functionalities are handled using Firebase Authentication.
   - Firestore Database: The notes are stored and retrieved using Firebase Firestore, a NoSQL cloud-based database.

## Technologies Used

- Java: The programming language used for the Android application development.
- Android Studio: The IDE for Android development.
- Firebase Authentication: To handle user registration, login, email verification, and password reset.
- Firebase Firestore: To store and retrieve notes from the cloud-based database.

## How to Use

1. Clone the repository to your local machine.
2. Open the project in Android Studio.
3. Set up your Firebase project on the Firebase console and download the `google-services.json` file.
4. Add the `google-services.json` file to the app module of the Android project.
5. Build and run the app on an Android emulator or physical device.

## Screenshots
<img src="https://github.com/AvishkarWadbudhe/Notes-Android-App/assets/96787413/88742eaa-7b3e-46bc-b32a-a7ad1a8bc53d" alt="LogIn Page" width="200px">
<img src="https://github.com/AvishkarWadbudhe/Notes-Android-App/assets/96787413/efc19009-f1c5-4285-a6c8-625940b19716" alt="sign up Page" width="200px">
<img src="https://github.com/AvishkarWadbudhe/Notes-Android-App/assets/96787413/9edcf960-a224-4cd0-b222-a7883702774d" alt="forget password" width="200px">
<img src="https://github.com/AvishkarWadbudhe/Notes-Android-App/assets/96787413/d15b16ab-a5aa-4f5d-86c8-2d3ef416338d" alt="homepage" width="200px">
<img src="https://github.com/AvishkarWadbudhe/Notes-Android-App/assets/96787413/6eb69879-8dc9-4fd9-a55c-9246eaf8ec60" alt="search" width="200px">
<img src="https://github.com/AvishkarWadbudhe/Notes-Android-App/assets/96787413/d26add12-80e4-494c-bc70-b537a14137ea" alt="sign up Page" width="200px">

## Future Enhancements

This is a basic implementation of a notes app using Firebase. Here are some possible enhancements for future iterations:

1. Add user profile management functionality, such as updating the user's name and profile picture.
2. Implement real-time sync between devices by leveraging Firestore's real-time update capabilities.
3. Enhance the search functionality with filters and sorting options.
4. Add support for adding images or attachments to notes.
5. Implement note sharing functionality with other users.
6. Add a remove button in search box

I am always looking for ways to improve this app. If you have any suggestions, please feel free to open an issue or pull a request.

## License

This project is licensed under the [MIT License](LICENSE).
