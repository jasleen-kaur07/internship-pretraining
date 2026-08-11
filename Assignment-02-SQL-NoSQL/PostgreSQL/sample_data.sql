
-- Insert Students
INSERT INTO student (name, email, phone) VALUES
('Jasleen Kaur', 'jasleenkaur@gmail.com', '9876543210'),
('Rahul Sharma', 'rahul.sharma@gmail.com', '9876543211'),
('Priya Singh', 'priya.singh@gmail.com', '9876543212'),
('Aman Verma', 'aman.verma@gmail.com', '9876543213'),
('Neha Gupta', 'neha.gupta@gmail.com', '9876543214');

-- Insert Addresses
INSERT INTO address (student_id, street, city, state) VALUES
(1, 'Sector 62', 'Greater Noida', 'Uttar Pradesh'),
(2, 'MG Road', 'Delhi', 'Delhi'),
(3, 'Civil Lines', 'Lucknow', 'Uttar Pradesh'),
(4, 'Banjara Hills', 'Hyderabad', 'Telangana'),
(5, 'Park Street', 'Kolkata', 'West Bengal');

-- Insert Courses
INSERT INTO course (name, description) VALUES
('Database Management System', 'Introduction to SQL and Relational Databases'),
('Java Programming', 'Core Java Programming'),
('Python Programming', 'Python Basics'),
('Operating Systems', 'Operating System Concepts'),
('Data Structures', 'Introduction to Data Structures');