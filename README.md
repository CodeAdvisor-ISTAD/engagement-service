# Content Engagement Service

Welcome to the **Content Engagement Service**! This platform allows users to interact with content in various ways, including commenting, reacting (like, love, fire), replying to comments, reporting content or comments, and sharing content to external platforms. Additionally, content views are counted per user, ensuring accurate engagement metrics.

This service uses **Kafka** to communicate efficiently across microservices, ensuring smooth operation and scalability.

## 🚀 Features

### 🌟 Content Reactions
- **What it does**: Allows users to react to content with **like**, **love**, or **fire** reactions.
- **Why it matters**: Increases user interaction and engagement with content, providing instant feedback on the content.

### 💬 Commenting & Replies
- **What it does**: Users can comment on content and reply to existing comments.
- **Why it matters**: Facilitates discussions around content, fostering community engagement and feedback.

### 🚨 Reporting Content & Comments
- **What it does**: Enables users to report content or comments that violate community guidelines.
- **Why it matters**: Helps maintain the quality of content and user interactions, ensuring a safe environment for users.

### 🔗 Sharing to External Platforms
- **What it does**: Users can share content to social platforms such as **Facebook**, **LinkedIn**, and **X** (formerly Twitter).
- **Why it matters**: Expands content reach by allowing users to share interesting content across their networks.

### 👀 User-based Content Views
- **What it does**: Tracks content views, counting each view per user to provide accurate engagement metrics.
- **Why it matters**: Ensures that content creators have reliable viewership data based on unique user interactions.

## 🛠️ Tech Stack

- **Spring Boot**: For building a robust and scalable backend.
- **Kafka**: For communication between microservices, ensuring event-driven architecture.
- **Java**: The primary programming language for backend development.
- **Elasticsearch**: For storing and querying engagement-related data.
- **Social Media APIs**: For sharing content to platforms like Facebook, LinkedIn, and X.

## 📂 Project Structure

```
content-engagement/
├── src/main/java/
│    ├── com.example.contentengagement/  # Core logic
│    ├── controller/                     # REST controllers
│    ├── service/                        # Business services
│    ├── repository/                     # Database interactions
│    └── model/                          # Data models
├── src/main/resources/
│    ├── application.yml                 # Configurations for Spring Boot, Kafka, and social media APIs
│    └── static/                         # Static assets (if any)
└── README.md                            # Project documentation

```


## ⚙️ Setup and Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/YourRepo/content-engagement.git
   cd content-engagement

2. **Set up kafka**
   - **install and start kafka**
   ```
   docker-compose -f docker-compose.yml up -d
   ```

3. **Configure the applicatoin**
   - Update application.yml with your Kafka broker details, social media API credentials, and any other required configurations.
  
4. **API Endpoint**
   - Access the API
   - Base URL: http://localhost:8080

### 1. **Get Comments**
- **GET** `/api/v1/engagement/comments`
- **Description**: Retrieves all comments.
  
### 2. **Create a Comment**
- **POST** `/api/v1/engagement/comments`
- **Description**: Allows users to create a new comment on content.
- **Request Body**:
  ```json
  {
    "userId": "user",
    "contentId": "content1",
    "body": "Hello, This is the second comment with kafka."
  }
### 3. **Update a Comment**
- **PUT** `/api/v1/engagement/comments/{id}`
- **Description**: Allows users to update an existing comment by specifying the comment ID.
- **Request Body**:
  ```json
  {
    "userId": "user",
    "contentId": "content1",
    "body": "This is an updated comment."
  }
### 4. **Delete a Comment**
- **DELETE** `/api/v1/engagement/comments/{id}`
- **Description**: Allows users to delete a comment by specifying the comment ID.

---

### 5. **Get Comments by Content ID**
- **GET** `/api/v1/engagement/comments/content/{contentId}`
- **Description**: Retrieves all comments related to a specific content ID.

---

### 6. **Create a Reply**
- **POST** `/api/v1/engagement/replies/{commentId}`
- **Description**: Allows users to create a reply to a specific comment by providing the comment ID.
- **Request Body**:
  ```json
  {
    "userId": "user123",
    "body": "Maow Maow Maow Maow"
  }

---

### 7. **Update a Reply**
- **PUT** `/api/v1/engagement/replies/{id}`
- **Description**: Allows users to update an existing reply by specifying the reply ID.
- **Request Body**:
  ```json
  {
    "userId": "user",
    "body": "Maow Maow Maow update!"
  }

---

### 8. **Delete a Reply**
- **DELETE** `/api/v1/engagement/replies/{id}`
- **Description**: Allows users to delete a reply by specifying the reply ID.

---

### 9. **Create a Reaction**
- **POST** `/api/v1/reactions/content/{contentId}`
- **Description**: Allows users to create a reaction (like, love, etc.) on a specific content by specifying the content ID.
- **Request Body**:
  ```json
  {
    "contentId": "content1",
    "userId": "user456",
    "reactionType": "like"
  }

---

### 10. **Delete a Reaction**
- **DELETE** `/api/v1/engagement/comments/{id}/reaction`
- **Description**: Allows users to delete a reaction on a comment by specifying the comment ID.

---

 ### 11. **Get Reactions by Content ID**
- **GET** `/api/v1/reactions/content/{contentId}`
- **Description**: Retrieves the count of reactions (like, love, fire, etc.) for a specific content ID.
- **Response**:
  ```json
  {
    "love": 2,
    "like": 1,
    "fire": 0
  }

---

### 12. **Get Report**
- **GET** `/api/v1/reports`
- **Description**: Retrieves a report with detailed information.
- **Response**:
  ```json
  {
    "id": "67623be5222f67549ea68ac9",
    "dataType": "comment",
    "userId": "user123",
    "status": "pending",
    "reason": "Inappropriate content",
    "description": "This comment contains offensive language.",
    "createdAt": "2024-12-18T10:05:09.404",
    "url": "http://example.com/comments/12345",
    "isDeleted": false
  }

---

### 13. **Create Report**
- **POST** `/api/v1/reports`
- **Description**: Allows users to create a report for a content or comment based on the given reason.
- **Request Body**:
  ```json
  {
    "dataId": "12345",
    "dataType": "content",
    "userId": "user23",
    "status": "pending",
    "reason": "Inappropriate content",
    "description": "This comment contains offensive language.",
    "createdAt": "2024-12-18T14:30:00", 
    "url": "http://example.com/comments/12345",
    "isDeleted": false
  }

---

### 14. **Delete Report**
- **DELETE** `/api/v1/reports/{id}`
- **Description**: Allows users to delete a report by specifying the report ID.

---

### 15. **Get Report by Type (Comment or Content)**
- **GET** `/api/v1/reports/data/history`
- **Description**: Retrieves reports filtered by data type (either "comment" or "content").
- **Response**:
  ```json
  [
    {
      "id": "67623be5222f67549ea68ac9",
      "dataType": "comment",
      "userId": "user123",
      "status": "pending",
      "reason": "Inappropriate content",
      "description": "This comment contains offensive language.",
      "createdAt": "2024-12-18T10:05:09.404",
      "url": "http://example.com/comments/12345",
      "isDeleted": false
    },
    {
      "id": "67623be5222f67549ea68b01",
      "dataType": "content",
      "userId": "user456",
      "status": "resolved",
      "reason": "Spam",
      "description": "This content is flagged as spam.",
      "createdAt": "2024-12-18T15:05:09.404",
      "url": "http://example.com/content/67890",
      "isDeleted": false
    }
  ]

---

### 16. **Get Report by User ID**
- **GET** `/api/v1/reports/user/{userId}`
- **Description**: Retrieves all reports created by a specific user based on the provided user ID.
- **Response**:
  ```json
  [
    {
      "id": "67623be5222f67549ea68ac9",
      "dataType": "comment",
      "userId": "user23",
      "status": "pending",
      "reason": "Inappropriate content",
      "description": "This comment contains offensive language.",
      "createdAt": "2024-12-18T10:05:09.404",
      "url": "http://example.com/comments/12345",
      "isDeleted": false
    },
    {
      "id": "67623be5222f67549ea68b02",
      "dataType": "content",
      "userId": "user23",
      "status": "resolved",
      "reason": "Spam",
      "description": "This content was flagged as spam.",
      "createdAt": "2024-12-18T12:30:00.404",
      "url": "http://example.com/content/67891",
      "isDeleted": false
    }
  ]


## 🛡️ Security
Implement Spring Security to protect sensitive endpoints if needed.
OAuth 2.0 for authentication with third-party platforms (Facebook, LinkedIn, X) when sharing content.
Implement API rate limiting to prevent abuse of reporting and sharing features.
## 🧑‍💻 Kafka Integration
This service communicates via Kafka for all internal messages, such as tracking user reactions, comments, and shares across microservices.
Kafka ensures that different microservices (e.g., user service, content service, reporting service) can work independently while sharing real-time data.
## 🤝 Contribution
We welcome contributions! Please follow these steps:

Fork the repository.
Create a feature branch: git checkout -b feature-name.
Commit your changes: git commit -m 'Add some feature'.
Push to the branch: git push origin feature-name.
Open a pull request.
## 📝 License
This project is licensed under the MIT License.

## 💬 Contact
For any questions, reach out at:

- **Email**: mrrkhann9@gmail.com
- **GitHub**: sokkhann

### 🔥 Start engaging with content today with the Content Engagement Service! 🔥

