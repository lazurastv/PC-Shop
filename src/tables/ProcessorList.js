import { useState } from "react";

export function ProcessorList() {
    const [processors, setProcessors] = useState();
    const tableHeaders = ["Id", "Producent", "Seria", "Liczba rdzeni", "Taktowanie"];
    if (!processors) {
        fetch("http://localhost:8080/api/processor").then(response => response.json()).then(body => setProcessors(body));
    }
    return (
        <main>
            {
                processors ?
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
                                processors.map(processor => <tr key={processor.id}>{Object.keys(processor).map(header => <td key={header}>{processor[header]}</td>)}</tr>)
                            }
                        </tbody>
                    </table >
                    :
                    <p>Brak procesorów w bazie.</p>
            }
        </main >
    );
}