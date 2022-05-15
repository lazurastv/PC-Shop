import { useState } from "react";

const tableHeaders = ["Producent", "Seria", "Pamięć wideo", "Rodzaj pamięci", "Długość", "Szerokość", "Wysokość"];
const headerMapping = {
    "Producent": 'manufacturer',
    "Seria": 'series',
    "Pamięć wideo": 'vRAM',
    "Rodzaj pamięci": 'memoryType',
    "Długość": 'length',
    "Szerokość": 'width',
    "Wysokość": 'height'
};

function emptyGraphicsCard() {
    const graphicsCard = {};
    for (const header in headerMapping) {
        graphicsCard[headerMapping[header]] = '';
    }
    return graphicsCard;
}

export function GraphicsCardForm() {
    const [graphicsCard, setGraphicsCard] = useState(emptyGraphicsCard());
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
                                    <input id={header} value={graphicsCard[headerMapping[header]]} onChange={(value) => {
                                        const newGraphicsCard = JSON.parse(JSON.stringify(graphicsCard));
                                        newGraphicsCard[headerMapping[header]] = value.target.value;
                                        setGraphicsCard(newGraphicsCard);
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
                        if (!graphicsCard[value]) {
                            setResponseMessage("Uzupełnij: " + key);
                            return;
                        }
                    }
                    fetch("http://localhost:8080/api/graphicsCard", { method: "POST", body: JSON.stringify(graphicsCard), headers: { "Content-Type": "application/json" } })
                        .then(x => {
                            if (x.status === 201) {
                                setGraphicsCard(emptyGraphicsCard());
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