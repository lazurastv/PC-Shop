import { useState } from "react";

const tableHeaders = ["Id", "Producent", "Seria", "Rozmiar", "Gniazdo procesora", "Maksymalna pamięć RAM"];
const headerMapping = {
    "Id": 'id',
    "Producent": 'manufacturer',
    "Seria": 'series',
    "Rozmiar": 'size',
    "Gniazdo procesora": 'socket',
    "Maksymalna pamięć RAM": 'maxRAM'
};

function filterMotherboards(motherboards, filters) {
    let newMotherboards = motherboards.map(x => x);
    for (const [key, value] of Object.entries(filters)) {
        newMotherboards = newMotherboards.filter(x => JSON.stringify(x[headerMapping[key]]).includes(value));
    }
    return newMotherboards;
}

export function MotherboardList() {
    const [motherboards, setMotherboards] = useState();
    const [filteredMotherboards, setFilteredMotherboards] = useState();
    const [filters, setFilters] = useState({});

    if (!motherboards) {
        fetch("http://localhost:8080/api/motherboard").then(response => response.json()).then(body => {
            setMotherboards(body);
            setFilteredMotherboards(body);
        })
    }
    return (
        <main style={{ display: "flex", flexDirection: "column", gap: "20px" }}>
            <div style={{ display: "flex", flexWrap: "wrap", gap: "10px" }}>
                {
                    tableHeaders.map(header =>
                        <div key={header}>
                            <label htmlFor={header} style={{ marginRight: "10px" }}>{header}</label>
                            <input id={header} onChange={value => {
                                const newFilters = { ...filters };
                                newFilters[header] = value.target.value;
                                setFilters(newFilters);
                                setFilteredMotherboards(filterMotherboards(motherboards, newFilters));
                            }}></input>
                        </div>
                    )
                }
            </div>
            {
                filteredMotherboards?.length > 0 ?
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
                                filteredMotherboards.map(motherboard =>
                                    <tr key={motherboard.id}>
                                        {Object.keys(motherboard).map(header =>
                                            <td key={header}>{motherboard[header]}</td>
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