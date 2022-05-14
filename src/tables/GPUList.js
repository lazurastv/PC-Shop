import { useState } from "react";

export function GPUList() {
    const [graphicsCards, setGraphicsCards] = useState();
    const tableHeaders = ["Id", "Producent", "Seria", "Pamięć wideo", "Rodzaj pamięci", "Długość", "Szerokość", "Wysokość"];
    if (!graphicsCards) {
        fetch("http://localhost:8080/api/graphicsCard").then(response => response.json()).then(body => setGraphicsCards(body));
    }
    return (
        <div className="App">
            {
                graphicsCards ?
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
                                graphicsCards.map(graphicsCard => <tr key={graphicsCard.id}>{Object.keys(graphicsCard).map(header => <td key={header}>{graphicsCard[header]}</td>)}</tr>)
                            }
                        </tbody>
                    </table >
                    :
                    <p>Brak kart graficznych w bazie.</p>
            }
        </div >
    );
}