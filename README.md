# Docker-Compose-Demo
Docker Compose demo for a containerized web application (Java + Nginx).# Docker Compose Demo

A simple full-stack containerized web application built using Docker Compose, Spring Boot, MongoDB, and a static frontend.

## Overview

This project demonstrates how to build and run a multi-container application using Docker Compose.

The application allows users to:
- Submit records (name, email, age)
- Validate input on the backend
- Store data in MongoDB
- View recent submissions in a simple UI

## Tech Stack

- Docker & Docker Compose
- Java 17
- Spring Boot
- Maven
- MongoDB
- Mongo Express
- HTML, CSS, JavaScript

## Architecture

The application consists of three services:

- **app** → Spring Boot backend
- **mongodb** → Database
- **mongo-express** → Database UI

