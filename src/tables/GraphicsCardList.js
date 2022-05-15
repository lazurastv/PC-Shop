import { useState } from "react";

const tableHeaders = ["Id", "Producent", "Seria", "Pamięć wideo", "Rodzaj pamięci", "Długość", "Szerokość", "Wysokość"];
const headerMapping = {
    "Id": 'id',
    "Producent": 'manufacturer',
    "Seria": 'series',
    "Pamięć wideo": 'vRAM',
    "Rodzaj pamięci": 'memoryType',
    "Długość": 'length',
    "Szerokość": 'width',
    "Wysokość": 'height'
};

function filterGraphicsCards(graphicsCards, filters) {
    let newGraphicsCards = graphicsCards.map(x => x);
    for (const [key, value] of Object.entries(filters)) {
        newGraphicsCards = newGraphicsCards.filter(x => JSON.stringify(x[headerMapping[key]]).includes(value));
    }
    return newGraphicsCards;
}

export function GraphicsCardList() {
    const [graphicsCards, setGraphicsCards] = useState();
    const [filteredGraphicsCards, setFilteredGraphicsCards] = useState();
    const [filters, setFilters] = useState({});

    if (!graphicsCards) {
        fetch("http://localhost:8080/api/graphicsCard").then(response => response.json()).then(body => {
            setGraphicsCards(body);
            setFilteredGraphicsCards(body);
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
                                setFilteredGraphicsCards(filterGraphicsCards(graphicsCards, newFilters));
                            }}></input>
                        </div>
                    )
                }
            </div>
            {
                filteredGraphicsCards?.length > 0 ?
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
                                filteredGraphicsCards.map(graphicsCard =>
                                    <tr key={graphicsCard.id}>
                                        {Object.keys(graphicsCard).map(header =>
                                            <td key={header}>{graphicsCard[header]}</td>
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