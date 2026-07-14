
-- Query 1: Insert a Student
INSERT INTO student (name, email, phone)
VALUES (
    'Jasleen Kaur',
    'jasleenkaur@gmail.com',
    '9876543210'
);

-- Query 2: Insert Student Address
INSERT INTO address (student_id, street, city, state)
VALUES (
    1,
    'Sector 62',
    'Greater Noida',
    'Uttar Pradesh'
);


-- Query 3: Fetch Student by Name
SELECT *
FROM student
WHERE name = 'Jasleen Kaur';


-- Query 4: Update Student Phone Number
UPDATE student
SET phone = '9876501234'
WHERE id = 1;


-- Query 5: Insert a Course
INSERT INTO course (name, description)
VALUES (
    'Database Management System',
    'Introduction to SQL and relational databases'
);


-- Query 6: Enroll Students in Courses
INSERT INTO enrollment (student_id, course_id) VALUES
(1,1),
(1,2),
(1,3),
(1,4),

(2,1),
(2,2),

(3,2),
(3,3),

(4,4),
(4,5),

(5,1),
(5,5);


-- Query 7: Get All Students Enrolled in a Specific Course
SELECT s.id,
       s.name,
       s.email,
       c.name AS course_name
FROM student s
JOIN enrollment e
ON s.id = e.student_id
JOIN course c
ON e.course_id = c.id
WHERE c.id = 1;


-- Query 8: Insert Marks
INSERT INTO marks (student_id, course_id, score)
VALUES
(1,1,95),
(1,2,88),
(1,3,91),
(1,4,85),

(2,1,78),
(2,2,82),

(3,2,67),
(3,3,74),

(4,4,39),
(4,5,93),

(5,1,55),
(5,5,98);


-- Query 9: Get Student's Address by Student ID
SELECT *
FROM address
WHERE student_id = 1;


-- Query 10: List Courses of a Student
SELECT c.id,
       c.name,
       c.description
FROM course c
JOIN enrollment e
ON c.id = e.course_id
WHERE e.student_id = 1;


-- Query 11: Get Marks of a Student in a Course
SELECT score
FROM marks
WHERE student_id = 1
AND course_id = 1;


-- Query 12: Students with More Than 3 Enrollments
SELECT student_id,
       COUNT(course_id) AS total_courses
FROM enrollment
GROUP BY student_id
HAVING COUNT(course_id) > 3;


-- Query 13: Delete a Student
DELETE FROM student
WHERE id = 5;

-- Query 14: Remove a Course Enrollment
DELETE FROM enrollment
WHERE student_id = 1
AND course_id = 4;


-- Query 15: Update Student City
UPDATE address
SET city = 'Noida'
WHERE student_id = 1;


-- Query 16: Count Number of Students Per Course
SELECT
    c.id,
    c.name,
    COUNT(e.student_id) AS total_students
FROM course c
LEFT JOIN enrollment e
ON c.id = e.course_id
GROUP BY c.id, c.name
ORDER BY c.id;


-- Query 17: Get Students from a Particular City
SELECT
    s.id,
    s.name,
    a.city
FROM student s
JOIN address a
ON s.id = a.student_id
WHERE a.city = 'Noida';


-- Query 18: Order Students Alphabetically
SELECT *
FROM student
ORDER BY name ASC;


-- Query 19: Add Graduated Column
ALTER TABLE student
ADD COLUMN IF NOT EXISTS graduated BOOLEAN DEFAULT FALSE;

UPDATE student
SET graduated = TRUE
WHERE id = 1;


-- Query 20: Students Who Scored More Than 90
SELECT
    s.name,
    c.name AS course,
    m.score
FROM student s
JOIN marks m
ON s.id = m.student_id
JOIN course c
ON c.id = m.course_id
WHERE m.score > 90;


-- Query 21: Average Marks Per Student
SELECT
    s.id,
    s.name,
    AVG(m.score) AS average_marks
FROM student s
JOIN marks m
ON s.id = m.student_id
GROUP BY s.id, s.name
ORDER BY s.id;



-- Query 22: Group Students By City
SELECT
    city,
    COUNT(student_id) AS total_students
FROM address
GROUP BY city
ORDER BY city;


-- Query 23: Check if a Student is Enrolled in a Course
SELECT *
FROM enrollment
WHERE student_id = 1
AND course_id = 2;


-- Query 24: Enroll a Student into Multiple Courses
INSERT INTO enrollment (student_id, course_id)
VALUES
(5,2),
(5,3);



-- Query 25: List Students With No Course Enrollments
SELECT
    s.id,
    s.name
FROM student s
LEFT JOIN enrollment e
ON s.id = e.student_id
WHERE e.student_id IS NULL;


-- Query 26: Top 3 Scorers in a Course
SELECT
    s.name,
    m.score
FROM student s
JOIN marks m
ON s.id = m.student_id
WHERE m.course_id = 1
ORDER BY m.score DESC
LIMIT 3;



-- Query 27: Students With Their Total Marks
SELECT
    s.id,
    s.name,
    SUM(m.score) AS total_marks
FROM student s
JOIN marks m
ON s.id = m.student_id
GROUP BY s.id, s.name
ORDER BY total_marks DESC;


-- Query 28: Students With Number of Enrollments
SELECT
    s.id,
    s.name,
    COUNT(e.course_id) AS total_enrollments
FROM student s
LEFT JOIN enrollment e
ON s.id = e.student_id
GROUP BY s.id, s.name
ORDER BY total_enrollments DESC;



-- Query 29: Students Who Scored Less Than 40
SELECT
    s.name,
    c.name AS course_name,
    m.score
FROM student s
JOIN marks m
ON s.id = m.student_id
JOIN course c
ON c.id = m.course_id
WHERE m.score < 40;


-- Query 30: Delete All Marks for a Course
DELETE FROM marks
WHERE course_id = 5;