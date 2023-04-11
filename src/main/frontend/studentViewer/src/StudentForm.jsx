import React, { useState } from 'react';
import './form.css'
function StudentForm() {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [stateOfResidence, setStateOfResidence] = useState('');
    const [occupation, setOccupation] = useState('');
    const [lovesClass, setLovesClass] = useState(false);

    const handleSubmit = (event) => {
        event.preventDefault();

        const data = {
            firstName,
            lastName,
            stateOfResidence,
            occupation,
            lovesClass,
        };

        fetch('http://localhost:8080/api/v2/students', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        })
            .then((response) => response.json())
            .then((data) => {
                console.log('Success:', data);
            })
            .catch((error) => {
                console.error('Error:', error);
            });
    };

    return (
        <form className="form-container" onSubmit={handleSubmit}>
            <label className="form-label">
                First Name:
                <input
                    className="form-input"
                    type="text"
                    value={firstName}
                    onChange={(event) => setFirstName(event.target.value)}
                />
            </label>
            <label className="form-label">
                Last Name:
                <input
                    className="form-input"
                    type="text"
                    value={lastName}
                    onChange={(event) => setLastName(event.target.value)}
                />
            </label>
            <label className="form-label">
                State of Residence:
                <input
                    className="form-input"
                    type="text"
                    value={stateOfResidence}
                    onChange={(event) => setStateOfResidence(event.target.value)}
                />
            </label>
            <label className="form-label">
                Occupation:
                <input
                    className="form-input"
                    type="text"
                    value={occupation}
                    onChange={(event) => setOccupation(event.target.value)}
                />
            </label>
            <label className="form-label">
                Loves Class:
                <input
                    className="form-checkbox"
                    type="checkbox"
                    checked={lovesClass}
                    onChange={(event) => setLovesClass(event.target.checked)}
                />
            </label>
            <br />
            <button className="form-submit-button" type="submit">
                Submit
            </button>
        </form>
    );
}

export default StudentForm;
