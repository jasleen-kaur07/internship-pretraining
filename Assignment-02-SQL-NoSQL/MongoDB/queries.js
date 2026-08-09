
// Query 1: Insert a Student Document
db.student.insertOne({
    name: "Rohan Mehta",
    email: "rohan.mehta@gmail.com",
    phone: "9876543220"
});


// Query 2: Insert Address as Embedded Document
db.student.updateOne(
    {
        name: "Rohan Mehta"
    },
    {
        $set: {
            address: {
                street: "Raj Nagar",
                city: "Ghaziabad",
                state: "Uttar Pradesh"
            }
        }
    }
);

// Query 3: Find Student by Name
db.student.find({
    name: "Jasleen Kaur"
});


// Query 4: Update Student Phone Number
db.student.updateOne(
    {
        name: "Jasleen Kaur"
    },
    {
        $set: {
            phone: "9999999999"
        }
    }
);

// Query 5: Add a New Course
db.student.updateOne(
    {
        name: "Rohan Mehta"
    },
    {
        $push: {
            courses: "Machine Learning"
        }
    }
);

// Query 6: Enroll a Student in a Course
db.student.updateOne(
    { name: "Rahul Sharma" },
    {
        $push: {
            courses: "Data Structures"
        }
    }
);


// Query 7: Get All Students Enrolled in a Specific Course
db.student.find(
    {
        courses: "Java Programming"
    }
);



// Query 8: Add Marks for a Student in a Course
db.student.updateOne(
    {
        name: "Rahul Sharma"
    },
    {
        $push: {
            marks: {
                course: "Data Structures",
                score: 89
            }
        }
    }
);


// Query 9: Get Student Address
db.student.find(
    {
        name: "Jasleen Kaur"
    },
    {
        address: 1,
        _id: 0
    }
);



// Query 10: Get All Courses of a Student
db.student.find(
    {
        name: "Jasleen Kaur"
    },
    {
        courses: 1,
        _id: 0
    }
);


// Query 11: Get Marks for a Student in a Specific Course
db.student.find(
    {
        name: "Jasleen Kaur",
        "marks.course": "Python Programming"
    },
    {
        marks: {
            $elemMatch: {
                course: "Python Programming"
            }
        },
        _id: 0
    }
);



// Query 12: List Students with More Than 3 Enrollments
db.student.find(
    {
        $expr: {
            $gt: [
                {
                    $size: "$courses"
                },
                3
            ]
        }
    }
);



// Query 13: Delete a Student by Name
db.student.deleteOne(
    {
        name: "Rohan Mehta"
    }
);


// Query 14: Remove a Course from Student Enrollment
db.student.updateOne(
    {
        name: "Jasleen Kaur"
    },
    {
        $pull: {
            courses: "Operating Systems"
        }
    }
);


// Query 15: Update Student Address City
db.student.updateOne(
    {
        name: "Jasleen Kaur"
    },
    {
        $set: {
            "address.city": "Noida"
        }
    }
);


// Query 16: Count Number of Students Per Course
db.student.aggregate([
    {
        $unwind: "$courses"
    },
    {
        $group: {
            _id: "$courses",
            totalStudents: {
                $sum: 1
            }
        }
    }
    ]);
    
    
    
    // Query 17: List All Students from a Specific City
    db.student.find({
        "address.city": "Noida"
    });
    
    
    
    // Query 18: Sort Students by Name
    db.student.find().sort({
        name: 1
    });
    
    
    
    // Query 19: Add Graduated Field
    db.student.updateMany(
        {},
        {
            $set: {
                graduated: false
            }
        }
    );
    
    
    // Query 20: Students Who Scored More Than 90
    db.student.find({
        "marks.score": {
            $gt: 90
        }
    });
    
    
    // Query 21: Calculate Average Marks for a Student
    db.student.aggregate([
    {
        $match: {
            name: "Jasleen Kaur"
        }
    },
    {
        $unwind: "$marks"
    },
    {
        $group: {
            _id: "$name",
            averageMarks: {
                $avg: "$marks.score"
            }
        }
    }
    ]);
    

    // Query 22: Group Students by City
    db.student.aggregate([
    {
        $group: {
            _id: "$address.city",
            totalStudents: {
                $sum: 1
            }
        }
    }
    ]);
    
    
    
    // Query 23: Check if Student is Enrolled in a Course
    db.student.find({
        name: "Jasleen Kaur",
        courses: "Java Programming"
    });
    
    

    // Query 24: Add Multiple Courses to a Student
    db.student.updateOne(
    {
        name: "Neha Gupta"
    },
    {
        $push: {
            courses: {
                $each: [
                    "Machine Learning",
                    "Cloud Computing"
                ]
            }
        }
    }
    );
    
    
    // Query 25: Find Students Not Enrolled in Any Course
    db.student.find({
        courses: {
            $size: 0
        }
    });
    
    
    
    // Query 26: Top 3 Scorers
    db.student.aggregate([
    {
        $unwind: "$marks"
    },
    {
        $match: {
            "marks.course": "Database Management System"
        }
    },
    {
        $sort: {
            "marks.score": -1
        }
    },
    {
        $limit: 3
    }
    ]);
    
    

    // Query 27: Get All Students and Their Total Marks
    db.student.aggregate([
    {
        $unwind: "$marks"
    },
    {
        $group: {
            _id: "$name",
            totalMarks: {
                $sum: "$marks.score"
            }
        }
    }
    ]);
    
    
    // Query 28: Number of Courses Per Student
    db.student.aggregate([
    {
        $project: {
            name: 1,
            totalCourses: {
                $size: "$courses"
            }
        }
    }
    ]);
    
    
    // Query 29: Find Students Who Failed
    db.student.find({
        "marks.score": {
            $lt: 40
        }
    });
    
    
    // Query 30: Delete Marks for a Specific Course
    db.student.updateMany(
    {},
    {
        $pull: {
            marks: {
                course: "Data Structures"
            }
        }
    });