import { useState } from "react";

export function MotherboardList() {
    const [motherboards, setMotherboards] = useState();
    const tableHeaders = ["Id", "Producent", "Seria", "Rozmiar", "Gniazdo procesora", "Maksymalna pamięć RAM"];
    if (!motherboards) {
        fetch("http://localhost:8080/api/motherboard").then(response => response.json()).then(body => setMotherboards(body));
    }
    return (
        <div className="App">
            {
                motherboards ?
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
                                motherboards.map(motherboard => <tr key={motherboard.id}>{Object.keys(motherboard).map(header => <td key={header}>{motherboard[header]}</td>)}</tr>)
                            }
                        </tbody>
                    </table >
                    :
                    <p>Brak płyt głównych w bazie.</p>
            }
        </div >
    );
}