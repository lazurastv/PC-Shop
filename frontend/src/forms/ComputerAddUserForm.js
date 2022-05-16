import { useState } from "react";

const tableHeaders = ["Komputer", "Użytkownik"];
const headerMapping = {
    "Komputer": "computerId",
    "Użytkownik": "userId"
};

function emptyComputerAddUser() {
    const computerAddUser = {};
    for (const header in headerMapping) {
        computerAddUser[headerMapping[header]] = '';
    }
    return computerAddUser;
}

export function ComputerAddUserForm() {
    const [computerAddUser, setComputerAddUser] = useState(emptyComputerAddUser());
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
                                    <input id={header} value={computerAddUser[headerMapping[header]]} onChange={(value) => {
                                        const newComputerAddUser = JSON.parse(JSON.stringify(computerAddUser));
                                        newComputerAddUser[headerMapping[header]] = value.target.value;
                                        setComputerAddUser(newComputerAddUser);
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
                        if (!computerAddUser[value]) {
                            setResponseMessage("Uzupełnij: " + key);
                            return;
                        }
                    }
                    fetch("http://localhost:8080/api/computer", { method: "PUT", body: JSON.stringify(computerAddUser), headers: { "Content-Type": "application/json" } })
                        .then(x => {
                            if (x.status === 200) {
                                setComputerAddUser({ ...computerAddUser, userId: '' });
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