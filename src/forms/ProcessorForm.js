import { useState } from "react";

const tableHeaders = ["Producent", "Seria", "Liczba rdzeni", "Taktowanie"];
const headerMapping = {
    "Producent": "manufacturer",
    "Seria": 'series',
    "Liczba rdzeni": 'threadCount',
    "Taktowanie": 'frequency'
};

function emptyProcessor() {
    return {
        'manufacturer': '',
        'series': '',
        'threadCount': '',
        'frequency': ''
    };
}

export function ProcessorForm() {
    const [processor, setProcessor] = useState(emptyProcessor());
    const [responseMessage, setResponseMessage] = useState();

    console.log(processor);
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
                        .then(x => { return x.status === 400 ? {} : x.json() })
                        .then(item => {
                            console.log(item);
                            if ("message" in item) {
                                setResponseMessage(item.message);
                            } else {
                                setProcessor(emptyProcessor());
                                setResponseMessage("Dodano obiekt.");
                            }
                        })
                }}>Wyślij</button>
            </div>
        </main >
    );
}