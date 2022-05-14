import { useState } from "react";
import "./List.css"

export function ComputerList() {
    const [computers, setcomputers] = useState();
    const tableHeaders = ["Id", "Procesor", "Karta graficzna", "Płyta główna", "Użytkownicy", "Model", "System operacyjny", "Pamięć RAM", "Długość", "Szerokość", "Wysokość", "Pamięć", "Moc", "Cena"];
    if (!computers) {
        fetch("http://localhost:8080/api/computer").then(response => response.json()).then(body => setcomputers(body));
    }
    return (
        <main>
            {
                computers ?
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
                                computers.map(computer => <tr key={computer.id}>{Object.keys(computer).map(header => <td key={header}>{computer[header]}</td>)}</tr>)
                            }
                        </tbody>
                    </table >
                    :
                    <p>Brak komputerów w bazie.</p>
            }
        </main>
    );
}