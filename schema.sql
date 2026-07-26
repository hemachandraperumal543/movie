-- WatchWise Database Schema
-- You can run this inside MySQL Workbench, or let Spring Boot auto-create them.

CREATE DATABASE IF NOT EXISTS watchwise_db;
USE watchwise_db;

-- 1. Users Table
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    avatar VARCHAR(255) DEFAULT 'https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?auto=format&fit=crop&w=100&q=80',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Feedback/Suggestions Table
CREATE TABLE IF NOT EXISTS feedback (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    category VARCHAR(50) NOT NULL,
    message TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 3. Movie Comments Table
CREATE TABLE IF NOT EXISTS movie_comments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    movie_id VARCHAR(50) NOT NULL,
    username VARCHAR(50) NOT NULL,
    avatar VARCHAR(255) DEFAULT 'https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?auto=format&fit=crop&w=100&q=80',
    text TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
