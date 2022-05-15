import { useState } from "react";
import "./List.css"

const tableHeaders = ["Id", "Procesor", "Karta graficzna", "Płyta główna", "Użytkownicy", "Model", "System operacyjny", "Pamięć RAM", "Długość", "Szerokość", "Wysokość", "Pamięć", "Moc", "Cena"];
const headerMapping = {
    "Id": "id",
    "Procesor": "processorId",
    "Karta graficzna": "graphicsCardId",
    "Płyta główna": "motherboardId",
    "Użytkownicy": "userIds",
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

async function getIncome(id) {
    return await fetch("http://localhost:8080/api/computer/" + id);
}

function filterComputers(computers, filters) {
    let newComputers = computers.map(x => x);
    for (const [key, value] of Object.entries(filters)) {
        newComputers = newComputers.filter(x => JSON.stringify(x[headerMapping[key]]).includes(value));
    }
    return newComputers;
}

export function ComputerList() {
    const [computers, setComputers] = useState();
    const [filteredComputers, setFilteredComputers] = useState();
    const [filters, setFilters] = useState({});
    const [prices, setPrices] = useState({})

    if (!computers) {
        fetch("http://localhost:8080/api/computer").then(response => response.json()).then(body => {
            const tmp = body.map(x => { return { ...x, userIds: x.userIds.join(", ") } });
            setComputers(tmp);
            setFilteredComputers(tmp);
        });
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
                                setFilteredComputers(filterComputers(computers, newFilters));
                            }}></input>
                        </div>
                    )
                }
            </div>
            {
                filteredComputers?.length > 0 ?
                    <table>
                        <thead>
                            <tr>
                                {
                                    tableHeaders.map(header => <th key={header}>{header}</th>)
                                }
                                <th>Zysk</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                filteredComputers.map(computer =>
                                    <tr key={computer.id}>
                                        {Object.keys(computer).map(header =>
                                            <td key={header}>{computer[header]}</td>
                                        )}
                                        <td>
                                            {
                                                prices[computer.id] ??
                                                <button onClick={() => getIncome(computer.id).then(response => response.json()).then(body => {
                                                    const newPrices = { ...prices };
                                                    newPrices[computer.id] = body;
                                                    setPrices(newPrices);
                                                })}>Oblicz</button>
                                            }
                                        </td>
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