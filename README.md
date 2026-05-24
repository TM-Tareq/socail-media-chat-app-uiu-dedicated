# UIU Connect 🎓
> A campus social media platform built for United International University students

## 📌 About
UIU Connect is a full-stack REST API backend for a campus social media application. Students can connect with each other, share posts, join clubs, attend events, and more — all within the UIU community.

## 🛠️ Tech Stack
- **Java 17+**
- **Spring Boot**
- **Spring Security + JWT Authentication**
- **Spring Data JPA / Hibernate**
- **MySQL**
- **Lombok**

## ✨ Features
| Feature | Description |
|---------|-------------|
| 🔐 Authentication | Register & Login with JWT token |
| 📝 Posts | Create, read posts with pagination |
| ❤️ Like System | Toggle like/unlike on posts |
| 💬 Comments | Add & delete comments on posts |
| 👥 Follow System | Follow/unfollow other users |
| 👤 Profile | View own & other users' profiles with follower/following count |
| 🔍 Search | Search users and posts by keyword |
| 🏛️ Clubs | Create clubs, join/leave, view all clubs |
| 📅 Events | Create events, attend/unattend, filter by category |
| 🔎 Lost & Found | Post lost/found items, filter by status |

## 🚀 Getting Started

### Prerequisites
- Java 17+
- MySQL 8+
- Maven

### Setup

**1. Clone the repository**
```bash
git clone https://github.com/TM-Tareq/socail-media-chat-app-uiu-dedicated.git
cd socail-media-chat-app-uiu-dedicated/socialapp
```

**2. Configure database**

Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/social_app
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

**3. Run the application**
```bash
mvn spring-boot:run
```

Server will start at `http://localhost:8080`

## 📡 API Endpoints

### Auth
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/users/register` | Register new user |
| POST | `/api/users/login` | Login & get JWT token |

### Posts
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/posts` | Create a post |
| GET | `/api/posts` | Get all posts (paginated) |
| GET | `/api/posts/me` | Get my posts |

### Likes
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/likes/{postId}` | Toggle like/unlike |
| GET | `/api/likes/{postId}/count` | Get like count |

### Comments
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/comments/{postId}` | Add comment |
| GET | `/api/comments/{postId}` | Get comments |
| DELETE | `/api/comments/{commentId}` | Delete comment |

### Follow
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/follows/{targetUserId}` | Follow/unfollow user |

### Profile
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/profile/me` | Get my profile |
| GET | `/api/profile/{userId}` | Get user profile |

### Search
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/search/users?query=` | Search users |
| GET | `/api/search/posts?query=` | Search posts |

### Clubs
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/clubs` | Create club |
| GET | `/api/clubs` | Get all clubs |
| GET | `/api/clubs/me` | Get my clubs |
| POST | `/api/clubs/{clubId}/join` | Join/leave club |

### Events
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/events` | Create event |
| GET | `/api/events` | Get all events |
| GET | `/api/events/category?category=` | Filter by category |
| POST | `/api/events/{eventId}/attend` | Attend/unattend |

### Lost & Found
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/lostFound` | Post lost/found item |
| GET | `/api/lostFound` | Get all items |
| GET | `/api/lostFound/status?status=LOST` | Filter by status |
| GET | `/api/lostFound/me` | Get my posts |

## 🔒 Authentication
All endpoints (except `/register` and `/login`) require JWT token in header:
```
Authorization: Bearer <your_token>
```

## 📮 Postman Collection
Postman collection is included in the `/postman` directory for easy API testing.

## 👨‍💻 Author
**Tareq** — [GitHub](https://github.com/TM-Tareq)
