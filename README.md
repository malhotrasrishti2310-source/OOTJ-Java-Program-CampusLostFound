# Campus Lost and Found Hub

## Overview

Campus Lost and Found Hub is a Java-based desktop application developed to help students report, search, and manage lost and found belongings within a college campus.

The application provides a graphical dashboard where users can report lost or found items, view item records, search for belongings, and update the status of items when they are claimed.

The system uses Java Swing for its graphical user interface and Object-Oriented Programming concepts to organize and manage item records.

## Features

- **Report Lost Items:** Add details of belongings that have been lost on campus.
- **Report Found Items:** Register items found within the campus.
- **Search Items:** Search for items using their name, category, or location.
- **View All Items:** Display all reported lost and found items in a table.
- **Mark Items as Claimed:** Update an item's status when it has been returned or claimed.
- **Dashboard Statistics:** View the total number of active lost items, found items, and claimed items.
- **Input Validation:** Prevent submission of incomplete item details.
- **Interactive GUI:** User-friendly desktop interface developed using Java Swing.

## Technologies Used

- Java
- Java Swing
- Java AWT
- Java Collections Framework (ArrayList)
- Eclipse IDE

## Project Structure

```text
CampusLostFound/
│
└── src/
    └── campuslostfound/
        ├── Item.java
        ├── Manager.java
        └── LostFoundHub.java
```

## Class Description

### 1. Item.java
- Represents an individual lost or found item.
- Stores item ID, name, category, location, type, status, and contact information.
- Uses a constructor to initialize item details.
- Provides getter methods to access item information.
- Includes a method to mark an item as claimed.

### 2. Manager.java
- Manages the collection of lost and found items.
- Uses an ArrayList to store item objects.
- Generates unique
