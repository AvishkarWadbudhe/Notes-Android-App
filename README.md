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

![Screenshot 1](screenshots/screenshot1.png)
![Screenshot 2](screenshots/screenshot2.png)
![Screenshot 3](screenshots/screenshot3.png)

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
