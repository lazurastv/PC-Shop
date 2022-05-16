import { useState } from "react";

const tableHeaders = ["Id", "Producent", "Seria", "Liczba rdzeni", "Taktowanie"];
const headerMapping = {
    "Id": "id",
    "Producent": "manufacturer",
    "Seria": 'series',
    "Liczba rdzeni": 'threadCount',
    "Taktowanie": 'frequency'
};

function filterProcessors(processors, filters) {
    let newProcessors = processors.map(x => x);
    for (const [key, value] of Object.entries(filters)) {
        newProcessors = newProcessors.filter(x => JSON.stringify(x[headerMapping[key]]).includes(value));
    }
    return newProcessors;
}

export function ProcessorList() {
    const [processors, setProcessors] = useState();
    const [filteredProcessors, setFilteredProcessors] = useState();
    const [filters, setFilters] = useState({});

    if (!processors) {
        fetch("http://localhost:8080/api/processor").then(response => response.json()).then(body => {
            setProcessors(body);
            setFilteredProcessors(body);
        });
    }

    return (
        <main style={{ display: "flex", flexDirection: "column", alignItems: "center", gap: "20px" }}>
            <div style={{ display: "flex", flexWrap: "wrap", gap: "10px" }}>
                {
                    tableHeaders.map(header =>
                        <div key={header}>
                            <label htmlFor={header} style={{ marginRight: "10px" }}>{header}</label>
                            <input id={header} onChange={value => {
                                const newFilters = { ...filters };
                                newFilters[header] = value.target.value;
                                setFilters(newFilters);
                                setFilteredProcessors(filterProcessors(processors, newFilters));
                            }}></input>
                        </div>
                    )
                }
            </div>
            {
                filteredProcessors?.length > 0 ?
                    <table>
                        <thead>
                            <tr>
                                {
                                    tableHeaders.map(header => <th key={header}>{header}</th>)
                                }
                            </tr>
                        </thead>
                        <tbody>
                            {
                                filteredProcessors.map(processor =>
                                    <tr key={processor.id}>
                                        {Object.keys(processor).map(header =>
                                            <td key={header}>{processor[header]}</td>
                                        )}
                                    </tr>)
                            }
                        </tbody>
                    </table >
                    :
                    <p>Brak wyników.</p>
            }
        </main >
    );
}