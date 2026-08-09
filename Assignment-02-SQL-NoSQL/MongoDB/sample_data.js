// Switch to database
use("student_management");

// Insert sample students
db.student.insertMany([
{
    name: "Jasleen Kaur",
    email: "jasleenkaur@gmail.com",
    phone: "9876543210",
    address: {
        street: "Sector 62",
        city: "Greater Noida",
        state: "Uttar Pradesh"
    },
    courses: [
        "Database Management System",
        "Java Programming",
        "Python Programming",
        "Operating Systems"
    ],
    marks: [
        { course: "Database Management System", score: 95 },
        { course: "Java Programming", score: 88 },
        { course: "Python Programming", score: 91 },
        { course: "Operating Systems", score: 85 }
    ]
},
{
    name: "Rahul Sharma",
    email: "rahul.sharma@gmail.com",
    phone: "9876543211",
    address: {
        street: "MG Road",
        city: "Delhi",
        state: "Delhi"
    },
    courses: [
        "Database Management System",
        "Java Programming"
    ],
    marks: [
        { course: "Database Management System", score: 78 },
        { course: "Java Programming", score: 82 }
    ]
},
{
    name: "Priya Singh",
    email: "priya.singh@gmail.com",
    phone: "9876543212",
    address: {
        street: "Civil Lines",
        city: "Lucknow",
        state: "Uttar Pradesh"
    },
    courses: [
        "Java Programming",
        "Python Programming"
    ],
    marks: [
        { course: "Java Programming", score: 67 },
        { course: "Python Programming", score: 74 }
    ]
},
{
    name: "Aman Verma",
    email: "aman.verma@gmail.com",
    phone: "9876543213",
    address: {
        street: "Banjara Hills",
        city: "Hyderabad",
        state: "Telangana"
    },
    courses: [
        "Operating Systems",
        "Data Structures"
    ],
    marks: [
        { course: "Operating Systems", score: 39 },
        { course: "Data Structures", score: 93 }
    ]
},
{
    name: "Neha Gupta",
    email: "neha.gupta@gmail.com",
    phone: "9876543214",
    address: {
        street: "Park Street",
        city: "Kolkata",
        state: "West Bengal"
    },
    courses: [
        "Database Management System",
        "Data Structures"
    ],
    marks: [
        { course: "Database Management System", score: 55 },
        { course: "Data Structures", score: 98 }
    ]
}
]);