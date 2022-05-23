import React, { useState, useEffect } from "react"

const Students = () => {

    const [students, setStudents] = useState([]);
    /*
    useEffect(() => {
        fetch("/api/students")
            .then(response => response.json())
            .then(data => {
                console.log(data);

                setStudents({data});
            })
    }, [])
    */
   
    return (
        <div>
            <h3>Work In Progress</h3>
        </div>
    )
}

export default Students;