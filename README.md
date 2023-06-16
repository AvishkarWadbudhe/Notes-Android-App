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
<img src="https://github.com/AvishkarWadbudhe/Notes-Android-App/assets/96787413/f0347de0-1ecc-42ea-b472-7d9d510af2f3" alt="Screenshot" width="400px">

![LogIn Page](https://github.com/AvishkarWadbudhe/Notes-Android-App/assets/96787413/f0347de0-1ecc-42ea-b472-7d9d510af2f3)
![SignUp Page](https://github.com/AvishkarWadbudhe/Notes-Android-App/assets/96787413/e35bb88e-be9c-4df4-b241-677e21078f7c)
![Home Page](https://github.com/AvishkarWadbudhe/Notes-Android-App/assets/96787413/3707c091-e68d-49b0-a4c1-147000a749c1)
![View Note](https://github.com/AvishkarWadbudhe/Notes-Android-App/assets/96787413/11a3042f-e229-43a4-a8a9-7d076b801de5)
![Edit Note](https://github.com/AvishkarWadbudhe/Notes-Android-App/assets/96787413/bd0dd13e-4082-4cc4-9840-ac9558c9f15d)
![Search Note](https://github.com/AvishkarWadbudhe/Notes-Android-App/assets/96787413/00c6869b-8d34-468d-985a-5b4eee0a78f0)

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
