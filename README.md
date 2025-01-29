Election System

Project Overview

The Election System is a web-based application built using Java and JSP/Servlets, designed to facilitate the election process. This project includes database scripts for setup and a structured Java backend to handle election-related functionalities.

Features

User authentication and role-based access.

Candidate registration and management.

Secure voting mechanism.

Real-time vote counting and results display.

Database-driven backend with SQL scripts for setup.

Installation and Setup

Prerequisites

Ensure you have the following installed:

Java JDK 8+

Apache Tomcat 9+

MySQL or MariaDB

Eclipse IDE (optional, for development)

Steps to Run

Clone the Repository

git clone <repository-url>
cd ElectionSystem-main

Set Up the Database

Open MySQL and create a new database:

CREATE DATABASE election_system;

Execute the SQL script to create the necessary tables:

mysql -u root -p election_system < create.sql

Configure the Project in Eclipse

Open Eclipse and import the project as an Existing Java Web Project.

Ensure Java EE and Web Tools plugins are installed.

Configure the server (Apache Tomcat) in Eclipse -> Servers.

Deploy the Application

Copy the project folder to your Tomcat webapps/ directory, or use Eclipse to deploy it.
