import { useState } from "react";

const tableHeaders = ["Producent", "Seria", "Liczba rdzeni", "Taktowanie"];
const headerMapping = {
    "Producent": "manufacturer",
    "Seria": 'series',
    "Liczba rdzeni": 'threadCount',
    "Taktowanie": 'frequency'
};

function emptyProcessor() {
    const processor = {};
    for (const header in headerMapping) {
        processor[headerMapping[header]] = '';
    }
    return processor;
}

export function ProcessorForm() {
    const [processor, setProcessor] = useState(emptyProcessor());
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
                                    <input id={header} value={processor[headerMapping[header]]} onChange={(value) => {
                                        const newProcessor = JSON.parse(JSON.stringify(processor));
                                        newProcessor[headerMapping[header]] = value.target.value;
                                        setProcessor(newProcessor);
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
                        if (!processor[value]) {
                            setResponseMessage("Uzupełnij: " + key);
                            return;
                        }
                    }
                    fetch("http://localhost:8080/api/processor", { method: "POST", body: JSON.stringify(processor), headers: { "Content-Type": "application/json" } })
                        .then(x => {
                            if (x.status === 201) {
                                setProcessor(emptyProcessor());
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