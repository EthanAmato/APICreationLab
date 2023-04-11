import React, { useEffect, useState } from 'react';

function StudentTable() {
  const [students, setStudents] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/api/v2/students')
      .then((response) => response.json())
      .then((data) => {
        setStudents(data);
      })
      .catch((error) => {
        console.error('Error:', error);
      });
  }, []);

  return (
    <table style={{margin:'auto', marginTop: '1.5em', borderSpacing: '50px 15px'}}>
      <thead>
        <tr>
          <th>First Name</th>
          <th>Last Name</th>
          <th>State of Residence</th>
          <th>Occupation</th>
          <th>Loves Class</th>
        </tr>
      </thead>
      <tbody >
        {students.map((student) => (
          <tr key={student.id}>
            <td>{student.firstName}</td>
            <td>{student.lastName}</td>
            <td>{student.stateOfResidence}</td>
            <td>{student.occupation}</td>
            <td>{student.lovesClass ? 'Yes' : 'No'}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}

export default StudentTable;
