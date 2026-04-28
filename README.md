# The Hidden Root: Java 2D Game

## 1-About the Project
This project is a 2D graphics game developed entirely in Java. It was built to explore core game design mechanics and object-oriented programming principles. Designed as a treasure hunter game, the application operates in a two-dimensional space where objects are represented as flat images (sprites) on a coordinate grid. 

To make the gameplay more competitive, an SQL database was integrated to track players' score times and manage a dynamic leaderboard.

## 2-Application Previews
* **Main Menu:** ![Main Menu](image_ee2ffd.png)

* **Gameplay:** ![Gameplay](image_ee2fe2.png)

## 3-How We Built It
The game relies on a continuous game loop that cycles through input handling, logic updates, and rendering to ensure a responsive, real-time experience. 

Here are the core technical components implemented in the project:
* **Rendering Engine:** Built utilizing standard Java libraries like AWT for rendering graphics, sprites, and UI elements.
* **World Generation:** The game world uses tile-based rendering, where the map is represented as a 2D array and populated with individual tile images.
* **Camera System:** The viewport tracks the player's position, rendering only the visible portion of the world using coordinate offsets.
* **Collision Detection:** Object interaction is managed through bounding box collision, which checks for intersections between the rectangles surrounding game objects.
* **Game State Management:** The flow of the game is controlled via enumerated types, allowing smooth transitions between the Title Screen, Playing state, and Game Over screen.
* **Leaderboard Database:** An SQL database records player completion times and scores, adding a competitive edge to the game.
* **Audio:** Background atmosphere and sound effects are powered by the `javax.sound.sampled` library.
* **Assets & Soundtracks:** Visual assets were sourced and created using Pixilart and Gamedeveloperstudio , while 8-bit audio tracks were sourced from Uppbeat.

## 4-Contributors
This project was a collaborative effort by the following team members:
* Abdullah Mohammad Alhasawi
* Anas Al-Jumaiah
* Ibrahim Alborsias
* Khalid Sami Almuhaysh

## 5-Legal and Academic Disclaimer
This project was developed for academic purposes as part of the Object-Oriented Programming 2 (OOP2) curriculum at King Faisal University's College of Computer Sciences and Information Technology. The project was supervised by M Shujah Islam Sameem. All third-party assets (audio and tile sheets) belong to their respective creators and are used here strictly for educational, non-commercial purposes.
