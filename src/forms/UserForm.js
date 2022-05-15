import { useState } from "react";

const tableHeaders = ["Imię", "Nazwisko", "Data urodzenia", "PESEL", "Adres email", "Numer telefonu", "Adres", "Numer karty kredytowej"];
const headerMapping = {
    "Imię": 'name',
    "Nazwisko": 'lastName',
    "Data urodzenia": 'birthDate',
    "PESEL": 'PESEL',
    "Adres email": 'email',
    "Numer telefonu": 'phoneNumber',
    "Adres": 'address',
    "Numer karty kredytowej": 'creditCardNumber'
};

function emptyUser() {
    const user = {};
    for (const header in headerMapping) {
        user[headerMapping[header]] = '';
    }
    return user;
}

export function UserForm() {
    const [user, setUser] = useState(emptyUser());
    const [responseMessage, setResponseMessage] = useState();

    return (
        <main>
            <div style={{ display: "flex", flexDirection: "column", alignItems: "center", textAlign: "center", gap: "10px" }}>
                {
                    tableHeaders.map(
                        header => {
                            return (
                                <div key={header} style={{ display: "flex" }}>
                                    <label htmlFor={header} style={{ display: "inline-block", width: "100px" }}>{header}</label>
                                    <input id={header} value={user[headerMapping[header]]} onChange={(value) => {
                                        const newUser = JSON.parse(JSON.stringify(user));
                                        newUser[headerMapping[header]] = value.target.value;
                                        setUser(newUser);
                                        setResponseMessage();
                                    }}></input>
                                </div>
                            )
                        }
                    )
                }
                {
                    responseMessage && <p>{responseMessage}</p>
                }
                <button onClick={() => {
                    for (const [key, value] of Object.entries(headerMapping)) {
                        if (!user[value]) {
                            setResponseMessage("Uzupełnij: " + key);
                            return;
                        }
                    }
                    fetch("http://localhost:8080/api/user", { method: "POST", body: JSON.stringify(user), headers: { "Content-Type": "application/json" } })
                        .then(x => {
                            if (x.status === 201) {
                                setUser(emptyUser());
                                setResponseMessage("Dodano obiekt.");
                                return;
                            } else {
                                return x.json();
                            }
                        })
                        .then(item => item && "message" in item && setResponseMessage(item.message))
                }}>Wyślij</button>
            </div>
        </main >
    );
}