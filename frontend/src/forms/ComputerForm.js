import { useState } from "react";

const tableHeaders = ["Procesor", "Karta graficzna", "Płyta główna", "Model", "System operacyjny", "Pamięć RAM", "Długość", "Szerokość", "Wysokość", "Pamięć", "Moc", "Cena"];
const headerMapping = {
    "Procesor": "processorId",
    "Karta graficzna": "graphicsCardId",
    "Płyta główna": "motherboardId",
    "Model": "model",
    "System operacyjny": "operatingSystem",
    "Pamięć RAM": "RAM",
    "Długość": "length",
    "Szerokość": "width",
    "Wysokość": "height",
    "Pamięć": "memory",
    "Moc": "wattage",
    "Cena": "price"
};

function emptyComputer() {
    const computer = {};
    for (const header in headerMapping) {
        computer[headerMapping[header]] = '';
    }
    return computer;
}

export function ComputerForm() {
    const [computer, setComputer] = useState(emptyComputer());
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
                                    <input id={header} value={computer[headerMapping[header]]} onChange={(value) => {
                                        const newComputer = JSON.parse(JSON.stringify(computer));
                                        newComputer[headerMapping[header]] = value.target.value;
                                        setComputer(newComputer);
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
                        if (!computer[value]) {
                            setResponseMessage("Uzupełnij: " + key);
                            return;
                        }
                    }
                    fetch("http://localhost:8080/api/computer", { method: "POST", body: JSON.stringify(computer), headers: { "Content-Type": "application/json" } })
                        .then(x => {
                            if (x.status === 201) {
                                setComputer(emptyComputer());
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