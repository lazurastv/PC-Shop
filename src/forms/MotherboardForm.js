import { useState } from "react";

const tableHeaders = ["Producent", "Seria", "Rozmiar", "Gniazdo procesora", "Maksymalna pamięć RAM"];
const headerMapping = {
    "Producent": 'manufacturer',
    "Seria": 'series',
    "Rozmiar": 'size',
    "Gniazdo procesora": 'socket',
    "Maksymalna pamięć RAM": 'maxRAM'
};

function emptyMotherboard() {
    const motherboard = {};
    for (const header in headerMapping) {
        motherboard[headerMapping[header]] = '';
    }
    return motherboard;
}

export function MotherboardForm() {
    const [motherboard, setMotherboard] = useState(emptyMotherboard());
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
                                    <input id={header} value={motherboard[headerMapping[header]]} onChange={(value) => {
                                        const newMotherboard = JSON.parse(JSON.stringify(motherboard));
                                        newMotherboard[headerMapping[header]] = value.target.value;
                                        setMotherboard(newMotherboard);
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
                        if (!motherboard[value]) {
                            setResponseMessage("Uzupełnij: " + key);
                            return;
                        }
                    }
                    fetch("http://localhost:8080/api/motherboard", { method: "POST", body: JSON.stringify(motherboard), headers: { "Content-Type": "application/json" } })
                        .then(x => {
                            if (x.status === 201) {
                                setMotherboard(emptyMotherboard());
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