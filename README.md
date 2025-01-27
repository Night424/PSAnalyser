# Password Strength Analyzer

A user-friendly Java application for analyzing password strength, providing recommendations to improve weak passwords, and visually displaying password strength.

## Features

- **Password Strength Analysis**:
  - Analyzes password based on length, use of uppercase and lowercase letters, digits, and special characters.
  - Strength levels: Weak, Moderate, Strong.
  
- **Recommendations**:
  - Provides real-time suggestions to enhance password security if the entered password doesn't meet best practices.

- **Interactive UI**:
  - Built with Java Swing.
  - Features include a dynamic sidebar and draggable window for easy navigation.

## Technologies Used

- **Java Swing**: For the graphical user interface (GUI).
- **Regex**: For password pattern matching.
- **Java AWT**: For custom window handling.

## Screenshots

![Screenshot 2025-01-23 075711](https://github.com/user-attachments/assets/1e16458e-06ea-490e-a830-51eda8adc32c)

## How It Works

1. **Enter a Password**: Type the password you want to analyze in the input field.
2. **Analyze**: Click the "Analyze" button to evaluate the password.
3. **Recommendations**: The app will:
   - Display the password's strength (Weak, Moderate, or Strong).
   - Show specific recommendations to strengthen the password.
4. **Progress Bar**: Visualize the password's strength on a progress bar.

## Installation

1. Clone this repository:
git clone https://github.com/Night424/PSAnalyser.git

2. Navigate to the project directory:
cd password-strength-analyzer

3. Compile the program
javac -d out src/org/example/*.java

4. Run the application
java -cp out org.example.mainGui

## Development

### Prerequisites

- Java Development Kit (JDK) 8 or higher.
- Any Java IDE (e.g., IntelliJ IDEA, Eclipse) for editing the code.
- A tool like Maven for dependency management if you extend the project.

### Project Structure
`src/
└── org.example/
    └── mainGui.java
images/
└── lgogo.jpg`

### Key Components

-   **mainGui.java**:
    -   Main class that handles the GUI, password analysis, and recommendations.
-   **Images Folder**:
    -   Contains icons or graphics used in the application.

## Future Enhancements

-   Integration with a password manager for generating and saving secure passwords.
-   Advanced password checks (e.g., dictionary attacks, previously leaked passwords).
-   Support for theming and custom UI improvements.

## License

This project is licensed under the MIT License. See the LICENSE file for details.

### Author

-   **Night** - [GitHub Profile](https://github.com/Night424)
